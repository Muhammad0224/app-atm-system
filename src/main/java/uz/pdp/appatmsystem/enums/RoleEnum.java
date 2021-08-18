package uz.pdp.appatmsystem.enums;

public enum RoleEnum {
    ROLE_DIRECTOR("DIRECTOR"),
    ROLE_EMPLOYEE("EMPLOYEE");

    private final String code;

    RoleEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
