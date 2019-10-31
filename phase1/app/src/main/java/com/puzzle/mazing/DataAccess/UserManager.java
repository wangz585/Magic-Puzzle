package com.puzzle.mazing.DataAccess;

import com.puzzle.mazing.Models.User;

public class UserManager {
    private User activeUser;


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
