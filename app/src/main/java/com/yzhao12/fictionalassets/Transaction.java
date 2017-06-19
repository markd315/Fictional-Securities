package com.yzhao12.fictionalassets;

import java.sql.Time;

/**
 * Created by Yang on 6/18/2017.
 */

public class Transaction {

    public Transaction(int shares, float price, Time timestamp, int userid) {
        m_shares = shares;
        m_price = price;
        m_timestamp = timestamp;
        m_userid = userid;
    }




    private int m_shares;
    private float m_price;
    private Time m_timestamp;
    private int m_userid;
}
