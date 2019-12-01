package com.group0536.puzzlemazing.utils;

import com.group0536.puzzlemazing.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

/**
 * This class contains helper methods which parse objects to make them easier
 * to work with.
 *
 * @author Jimmy Lan
 */
public final class Parser {
    private Parser() {
        throw new UnsupportedOperationException();
    }

    /**
     * Parse response body to JSON object
     * @param response Response object from network request
     * @return JSONObject corresponding to the body of Response
     * @throws IOException if response cannot be processed
     * @throws JSONException if the body of response cannot be turned to JSON object
     */
    public static JSONObject parseResponseBody(Response response)
            throws IOException, JSONException {
        String bodyString = response.body().string();
        return new JSONObject(bodyString);
    }


    /**
     * get JSON attribute and return its value
     * @param obj JSON object to be parsed
     * @param key the attribute to find
     * @return the value of attribute if it exists, empty string if otherwise.
     */
    public static String getJSONAttribute(JSONObject obj, String key) {
        String result;
        try {
            result = obj.getString(key);
        } catch(JSONException e) {
            return "";
        }
        return result;
    }

    /**
     * Parse response object resulted from login or register action to a user object.
     * An empty user object will be returned together with printed error stack trace if
     * the response object is invalid.
     *
     * @param res response object resulted from login or register action
     * @return user object resulted from response
     */
    public static User parseResponseToUser(Response res) {
        User user = new User();
        try {
            JSONObject body = Parser.parseResponseBody(res);
            user.setUsername(body.getString("username"));
            user.setToken(body.getString("token"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
