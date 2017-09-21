package com.yzhao12.fictionalassets.DataObjects;

import java.util.ArrayList;

/**
 * Created by Yang on 6/16/2017.
 */

public class Meme {
    public Meme() {

    }

    public Meme(String name, String ticker, String description, String price, ArrayList<Order> buy, ArrayList<Order> sell) {
        m_name = name;
        m_ticker = ticker;
        m_description = description;
        m_price = price;
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

    public String getPrice() {
        return m_price;
    }

    public void setPrice(String price) {
        m_price = price;
    }

    public ArrayList<Order> getBuy() {
        return m_buy;
    }

    public void setBuy(ArrayList<Order> buy) {
        m_buy = buy;
    }

    public ArrayList<Order> getSell() {
        return m_sell;
    }

    public void setSell(ArrayList<Order> sell) {
        m_sell = sell;
    }

    private String m_name;
    private String m_ticker;
    private String m_description;
    private String m_price;

    private ArrayList<Order> m_buy;
    private ArrayList<Order> m_sell;
}
