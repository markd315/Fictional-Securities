package com.yzhao12.fictionalassets.DataObjects;

import android.net.Uri;
import com.google.firebase.auth.FirebaseUser;
import com.yzhao12.fictionalassets.DataObjects.PortfolioItem;

/**
 * Created by Yang on 6/17/2017.
 */

public class User {
    public User(FirebaseUser userInstance) {
        m_userName = userInstance.getDisplayName();
        m_email = userInstance.getEmail();
        m_profilePicUrl = userInstance.getPhotoUrl();
        m_uid = userInstance.getUid();
    }

    public String getM_userName() {
        return m_userName;
    }

    public void setM_userName(String userName) {
        m_userName = userName;
    }

    public String getM_email() {
        return m_email;
    }

    public void setM_email(String email) {
        m_email = email;
    }

    public Uri getM_profilePicUrl() {
        return m_profilePicUrl;
    }

    public void setM_profilePicUrl(Uri profilePicUrl) {
        m_profilePicUrl = profilePicUrl;
    }

    public String getM_uid() {
        return m_uid;
    }

    public void setM_uid(String uid) {
        m_uid = uid;
    }

    public int getUserMoney() {
        return m_userMoney;
    }

    public void setUserMoney(int userMoney) {
        m_userMoney = userMoney;
    }

    public PortfolioItem[] getUserPortfolio() {
        return m_userPortfolio;
    }

    public void setUserPortfolio(PortfolioItem[] userPortfolio) {
        m_userPortfolio = userPortfolio;
    }



    private FirebaseUser m_userInstance;

    //Might need to extract string from Firebase.Promise
    private String m_userName;
    private String m_email;
    private Uri m_profilePicUrl;
    private String m_uid;
    private int m_userMoney;
    private PortfolioItem[] m_userPortfolio;

}