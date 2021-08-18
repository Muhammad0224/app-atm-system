package uz.pdp.appatmsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appatmsystem.domain.*;
import uz.pdp.appatmsystem.enums.CardType;
import uz.pdp.appatmsystem.enums.OperationType;
import uz.pdp.appatmsystem.model.ApiResponse;
import uz.pdp.appatmsystem.model.CardDto;
import uz.pdp.appatmsystem.model.FillDto;
import uz.pdp.appatmsystem.model.WithDrawDto;
import uz.pdp.appatmsystem.repository.ATMHistoryRepo;
import uz.pdp.appatmsystem.repository.ATMRepo;
import uz.pdp.appatmsystem.repository.CardRepo;
import uz.pdp.appatmsystem.service.ATMService;
import uz.pdp.appatmsystem.service.CardService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepo cardRepo;

    private final ATMRepo atmRepo;

    private final ATMHistoryRepo atmHistoryRepo;

    private final ATMService atmService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse withdrawMoney(WithDrawDto dto) {
        Optional<ATM> optionalATM = atmRepo.findById(dto.getAtmId());
        if (!optionalATM.isPresent())
            return new ApiResponse("ATM not found", false);
        ATM atm = optionalATM.get();
        Card card = cardRepo.findByNumber(Card.getCurrentCard().getUsername()).get();
        if (!atm.getCardType().equals(card.getCardType()))
            return new ApiResponse("Card type did not match", false);

        if (dto.getAmount() > Penny.calculatePennies(atm.getPennies()) || dto.getAmount() > atm.getMaxLimit())
            return new ApiResponse("The ATM does not have that much money", false);

        Double withdrawAmount = dto.getAmount() + calculateCommission(card, atm, dto.getAmount(), OperationType.GET);

        if (withdrawAmount > card.getBalance())
            return new ApiResponse("Your card does not have that much money", false);

        List<Penny> givingPennies = Penny.getPennies(dto.getAmount(), atm.getPennies());
        if (givingPennies == null)
            return new ApiResponse("There is no money in such a banknote in the atm", false);

        card.setBalance(card.getBalance() - withdrawAmount);
        cardRepo.save(card);

        ATM savedATM = atmRepo.save(Penny.withdrawPennies(atm, givingPennies));
        if (Penny.calculatePennies(savedATM.getPennies()) < savedATM.getMinLimit()) {
            atmService.sendEmail(savedATM);
        }

        Map<String, String> details = Penny.toDetail(givingPennies, atm);
        details.put("Sum", String.valueOf(Penny.calculatePennies(givingPennies)));

        atmHistoryRepo.save(ATMHistory.createHistory(OperationType.GET, atm, details, false));

        details.clear();
        details.put("Card", card.getNumber());
        details.put("Currency", card.getCardType().getCurrency());
        details.put("Withdraw money", String.valueOf(withdrawAmount));
        atmHistoryRepo.save(ATMHistory.createHistory(OperationType.WITHDRAW, atm, details, false));

        return new ApiResponse("OK", true);
    }

    @Override
    public ApiResponse depositMoney(FillDto dto) {
      try {
          Optional<ATM> optionalATM = atmRepo.findById(dto.getAtmId());
          if (!optionalATM.isPresent())
              return new ApiResponse("ATM not found", false);
          ATM atm = optionalATM.get();
          Card card = cardRepo.findByNumber(Card.getCurrentCard().getUsername()).get();
          if (!atm.getCardType().equals(card.getCardType()))
              return new ApiResponse("Card type did not match", false);

          List<Penny> pennies = Penny.toPenny(dto.getPennies());

          List<Penny> atmPennies = atm.getPennies();
          for (Penny penny : pennies) {
              boolean hasPenny = false;
              for (Penny atmPenny : atmPennies) {
                  if (!penny.getCurrency().equals(atmPenny.getCurrency())) {
                      return new ApiResponse("You put wrong currency", false);
                  }
                  if (penny.getKey().equals(atmPenny.getKey())) {
                      atmPenny.setValue(atmPenny.getValue() + penny.getValue());
                      hasPenny = true;
                  }
              }
              if (!hasPenny) {
                  return new ApiResponse("There is no box for inserted banknotes", false);
              }
          }

          Double deposit = Penny.calculatePennies(pennies) - calculateCommission(card, atm, Penny.calculatePennies(pennies), OperationType.PUT);

          card.setBalance(card.getBalance() + deposit);
          atmRepo.save(atm);
          cardRepo.save(card);

          Map<String, String> details = Penny.toDetail(pennies, atm);
          details.put("Sum", String.valueOf(Penny.calculatePennies(pennies)));

          atmHistoryRepo.save(ATMHistory.createHistory(OperationType.PUT, atm, details, false));

          details.clear();
          details.put("Card", card.getNumber());
          details.put("Currency", card.getCardType().getCurrency());
          details.put("Deposit", String.valueOf(deposit));
          atmHistoryRepo.save(ATMHistory.createHistory(OperationType.DEPOSIT, atm, details, false));

          return new ApiResponse("OK", true);
      }catch (Exception e) {
          return new ApiResponse("Currency type format is not correct use UZS, USD", false);
      }
    }

    @Override
    public ApiResponse register(CardDto dto) {
        try {
            if (cardRepo.existsByNumber(dto.getNumber()))
                return new ApiResponse("Card has already existed", false);
            if (Card.checkValidity(dto.getExpiry()))
                return new ApiResponse("Enter true expiry date", false);
            Card card = new Card();
            card.setBalance(dto.getBalance());
            card.setCardType(CardType.valueOf(dto.getCardType()));
            card.setBankCode(dto.getBankCode());
            card.setCVV(dto.getCVV());
            card.setExpiry(dto.getExpiry());
            card.setOwnerName(dto.getOwnerName());
            card.setPassword(passwordEncoder.encode(dto.getPassword()));
            card.setNumber(dto.getNumber());
            cardRepo.save(card);
            return new ApiResponse("Created", true);
        } catch (Exception e) {
            return new ApiResponse("Card type format is not correct. (Use UZCARD, HUMO, VISA)", false);
        }
    }

    @Override
    public ApiResponse activate(String number) {
        Optional<Card> optionalCard = cardRepo.findByNumber(number);
        if (!optionalCard.isPresent())
            return new ApiResponse("Card not found", false);
        Card card = optionalCard.get();
        if (Card.checkValidity(card.getExpiry())) {
            String[] split = card.getExpiry().split("/");
            split[1] = String.valueOf(Integer.parseInt(split[1] + 3));
            card.setExpiry(split[0] + "/" + split[1]);
        }
        card.setBlocked(true);
        card.setActive(true);
        cardRepo.save(card);
        return new ApiResponse("OK", true);
    }

    public Double calculateCommission(Card card, ATM atm, Long amount, OperationType operationType) {
        if (card.getBankCode().equals(atm.getBankCode())) {
            for (Commission commission : atm.getCommissions()) {
                if (commission.isAdditional() && commission.getKey().equals(operationType)) {
                    return amount * Double.parseDouble(commission.getValue()) / 100;
                }
            }
        }
        for (Commission commission : atm.getCommissions()) {
            if (!commission.isAdditional() && commission.getKey().equals(operationType)) {
                return amount * Double.parseDouble(commission.getValue()) / 100;
            }
        }
        return null;
    }
}
