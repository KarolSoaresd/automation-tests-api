package br.testing.data;

import lombok.Getter;

@Getter
public enum Messages {

    TEST_CHECK("ok");

    private final String message;

    Messages(String message){
        this.message = message;
    }

}
