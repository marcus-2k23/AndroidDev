package com.example.task6;

import java.io.Serializable;

public class User implements Serializable {
    String UserId;
    String EmailId;

    public User(String userId, String emailId) {
        UserId = userId;
        EmailId = emailId;
    }
}
