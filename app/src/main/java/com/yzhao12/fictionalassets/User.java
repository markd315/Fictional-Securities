package com.yzhao12.fictionalassets;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Yang on 6/17/2017.
 */

public class User {

    public User(FirebaseUser userInstance) {
        m_userInstance = userInstance;
    }





    private FirebaseUser m_userInstance;

}
