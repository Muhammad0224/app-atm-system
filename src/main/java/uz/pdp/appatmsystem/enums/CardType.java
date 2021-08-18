package uz.pdp.appatmsystem.enums;

public enum CardType {
    UZCARD("UZS"),
    HUMO("UZS"),
    VISA("USD");

    private final String currency;

    CardType(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }
}
