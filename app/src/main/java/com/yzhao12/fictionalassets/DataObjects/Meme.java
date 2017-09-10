package com.yzhao12.fictionalassets.DataObjects;

import java.util.ArrayList;

/**
 * Created by Yang on 6/16/2017.
 */

public class Meme {
    public Meme() {

    }

    public Meme(String name, String ticker, String description, ArrayList<Transaction> buy, ArrayList<Transaction> sell) {
        m_name = name;
        m_ticker = ticker;
        m_description = description;
        m_buy = buy;
        m_sell = sell;
    }

    public String getName() {
        return m_name;
    }

    public void setName(String name) {
        m_name = name;
    }

    public String getTicker() {
        return m_ticker;
    }

    public void setTicker(String ticker) {
        m_ticker = ticker;
    }

    public String getDescription() {
        return m_description;
    }

    public void setDescription(String description) {
        m_description = description;
    }

    public ArrayList<Transaction> getBuy() {
        return m_buy;
    }

    public void setBuy(ArrayList<Transaction> buy) {
        m_buy = buy;
    }

    public ArrayList<Transaction> getSell() {
        return m_sell;
    }

    public void setSell(ArrayList<Transaction> sell) {
        m_sell = sell;
    }

    private String m_name;
    private String m_ticker;
    private String m_description;

    private ArrayList<Transaction> m_buy;
    private ArrayList<Transaction> m_sell;
}
