package br.com.application.enums;

public enum CodeType {

    CHANGE_PASSWORD (0),
    EMAIL_CONFIRMATION(1),
    PHONE_CONFIRMATION(2),
    CANCELED(3);

    private int code;

    CodeType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CodeType valueOf(int code) {
        for (CodeType value : CodeType.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid CodeType");
    }
}
