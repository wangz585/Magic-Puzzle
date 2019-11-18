package com.group0536.puzzlemazing.models;

public class User {
    private String email;
    private String nickname;
    private String token;

    public User() {
    }

    public User(String email, String nickname, String token) {
        this.email = email;
        this.nickname = nickname;
        this.token = token;
    }

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
