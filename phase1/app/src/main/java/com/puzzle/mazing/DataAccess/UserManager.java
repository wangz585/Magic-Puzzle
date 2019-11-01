package com.puzzle.mazing.DataAccess;

import com.puzzle.mazing.Models.User;
import com.puzzle.mazing.Network.Http;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import okhttp3.Callback;

public class UserManager implements Serializable {
    private User activeUser;


    public UserManager() {
        activeUser = new User();
    }


    public User findUserByEmail(String email) {
        return new User();
    }


    public User findUserByNickname(String nickname) {
        return new User();
    }


    /**
     * Authenticates user by email and password
     *
     * @param email    email of user
     * @param password unencrypted user password
     * @param done     Callback object after the authentication process completes
     */
    public void authenticate(String email, String password, Callback done) {
        String signInUrl = "https://apis.puzzlemazing.online/sign-in";
        JSONObject body = jsonWithEmailAndPassword(email, password);
        Http.post(signInUrl, body, done);
    }


    /**
     * Register user by email and password
     *
     * @param email    email of user
     * @param password password of user
     * @param done     Callback object after the register process completes
     */
    public void register(String email, String password, Callback done) {
        String signUpUrl = "https://apis.puzzlemazing.online/sign-up";
        JSONObject body = jsonWithEmailAndPassword(email, password);
        Http.post(signUpUrl, body, done);
    }


    /**
     * Construct JSON Object with email and password
     *
     * @param email    required email
     * @param password required password
     * @return JSON object with provided data
     */
    private JSONObject jsonWithEmailAndPassword(String email, String password) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("email", email);
            obj.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }


    /* ======================
     * Getters and Setters
     * ====================== */

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }
}
