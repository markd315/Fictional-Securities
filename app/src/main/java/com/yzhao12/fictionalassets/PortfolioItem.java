package com.yzhao12.fictionalassets;

/**
 * Created by Yang on 6/24/2017.
 */

public class PortfolioItem {

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


    private String m_ticker;
    private int m_shares;
}
