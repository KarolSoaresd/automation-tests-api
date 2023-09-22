package br.testing.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private String username;
    private String password;
    private String email;
    private int id;
    private boolean success;
    private int status;
    private String message;
    private String token;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
