package com.puzzle.mazing.DataAccess;

import android.util.Log;

import com.puzzle.mazing.Exceptions.IncorrectCredentialException;
import com.puzzle.mazing.Models.User;
import com.puzzle.mazing.Network.Http;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class UserManager {
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
     * Authenticates user by email and password, updates activeUser if successful
     *
     * @param email    email of user
     * @param password unencrypted user password
     * @throws IOException                  when network request cannot be completed
     * @throws JSONException                when response cannot be parsed
     * @throws IncorrectCredentialException when user credentials are incorrect
     */
    public void authenticate(String email, String password)
            throws IOException, JSONException, IncorrectCredentialException {
        String signInUrl = "https://apis.puzzlemazing.online/sign-in";
        JSONObject body = jsonWithEmailAndPassword(email, password);
        Response response = Http.syncPost(signInUrl, body);

        if (!response.isSuccessful()) {
            throw new IncorrectCredentialException();
        }

        JSONObject responseObject = Http.parseResponseBody(response);
        String userToken = Http.getJSONAttribute(responseObject, "token");
        String nickname = Http.getJSONAttribute(responseObject, "nickname");
        activeUser = new User(email, nickname, userToken);
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
