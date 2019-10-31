package com.puzzle.mazing.Models;

public class User {
    private String email;
    private String nickname;

    /**
     * JSON web token authenticating user to the current game session
     */
    private String token;


    public User() {
        email = "";
        nickname = "";
        token = "";
    }


    public User(String email, String nickname, String token) {
        this.email = email;
        this.nickname = nickname;
        this.token = token;
    }


    /* ======================
    * Getters and Setters
    * ====================== */

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
