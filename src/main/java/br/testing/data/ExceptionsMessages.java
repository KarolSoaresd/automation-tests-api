package br.testing.data;

import lombok.Getter;

@Getter
public enum ExceptionsMessages {

    USER_INVALID_ACCESS("Invalid credentials"),
    USER_NOT_FOUND("User with id '1000' not found"),
    TOKEN_INVALID("Invalid/Expired Token!"),
    FORBIDDEN_AUTH("Authentication Problem"),
    PRODUCT_NOT_FOUND("Product with id '1200' not found");

    private final String message;

    ExceptionsMessages(String message) {
        this.message = message;
    }

}
