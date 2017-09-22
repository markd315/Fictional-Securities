package com.yzhao12.fictionalassets.DataObjects;

import java.util.ArrayList;

/**
 * Created by Yang on 6/16/2017.
 */

public class Meme {
    public Meme() {

    }

    public Meme(String name, String ticker, String description, String price) {
        m_name = name;
        m_ticker = ticker;
        m_description = description;
        m_price = price;
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

    private String m_name;
    private String m_ticker;
    private String m_description;
    private String m_price;
}
