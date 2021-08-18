package uz.pdp.appatmsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import uz.pdp.appatmsystem.domain.Card;
import uz.pdp.appatmsystem.model.ApiResponse;
import uz.pdp.appatmsystem.model.LoginDto;
import uz.pdp.appatmsystem.repository.CardRepo;
import uz.pdp.appatmsystem.security.JWTProvider;
import uz.pdp.appatmsystem.service.AuthService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;

    private final JWTProvider jwtProvider;

    private final CardRepo cardRepo;

    @Override
    public ApiResponse login(LoginDto dto) {
        Optional<Card> optionalCard = cardRepo.findActiveCard(dto.getLogin());
        try {
            if (optionalCard.isPresent()){
                Card card = optionalCard.get();
                if (Card.checkValidity(card.getExpiry())){
                    card.setActive(false);
                    cardRepo.save(card);
                }
            }
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword()));
            User user = (User) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(user.getUsername(), user.getAuthorities());
            return new ApiResponse("OK", true, token);
        } catch (BadCredentialsException e) {
            if (optionalCard.isPresent()) {
                Card card = optionalCard.get();
                card.setAttempts(card.getAttempts() + 1);
                if (card.getAttempts() == 3)
                    card.setBlocked(true);
                cardRepo.save(card);
            }
            return new ApiResponse("Login or password incorrect", false);
        }
    }

}
