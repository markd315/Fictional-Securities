package com.yzhao12.fictionalassets.DataObjects;

/**
 * Created by Yang on 6/24/2017.
 */

public class PortfolioItem {
    public PortfolioItem() {
        m_ticker = "";
        m_shares = -1;
    }

    public PortfolioItem(String ticker, int shares, float price) {
        m_ticker = ticker;
        m_shares = shares;
        m_price = price;
    }

    public String getTicker() {
        return m_ticker;
    }

    public void setTicker(String ticker) {
        m_ticker = ticker;
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

    private String m_ticker;
    private int m_shares;
    private float m_price;
}
