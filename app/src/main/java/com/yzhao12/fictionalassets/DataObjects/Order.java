package com.yzhao12.fictionalassets.DataObjects;

import java.sql.Time;

/**
 * Created by Yang on 6/18/2017.
 */

public class Order {
    public Order() {

    }

    public Order(int shares, float price, /*Time timestamp,*/ String userid) {
        m_shares = shares;
        m_price = price;
        //m_timestamp = timestamp;
        m_userid = userid;
    }

    public int getShares() {
        return m_shares;
    }

    public void setShares(int shares) {
        m_shares = shares;
    }

    public float getPrice() {
        return m_price;
    }

    public void setPrice(float price) {
        m_price = price;
    }

    public Time getTimestamp() {
        return m_timestamp;
    }

    public void setTimestamp(Time timestamp) {
        m_timestamp = timestamp;
    }

    public String getUserid() {
        return m_userid;
    }

    public void setUserid(String userid) {
        m_userid = userid;
    }


    private int m_shares;
    private float m_price;
    private Time m_timestamp;
    private String m_userid;
}
