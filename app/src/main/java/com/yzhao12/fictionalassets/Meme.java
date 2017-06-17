package com.yzhao12.fictionalassets;

/**
 * Created by Yang on 6/16/2017.
 */

public class Meme {
    public Meme() {

    }

    public Meme(String name, float[] historicalPrices, int issuedShares, String exampleUrl) {
        m_name = name;
        m_historicalPrices = historicalPrices;
        m_issuedShares = issuedShares;
        m_exampleUrl = exampleUrl;
    }

    public String getName() {
        return m_name;
    }

    public void setName(String name) {
        m_name = name;
    }

    public float[] getHistoricalPrices() {
        return m_historicalPrices;
    }

    public void setHistoricalPrices(float[] historicalPrices) {
        m_historicalPrices = historicalPrices;
    }

    public int getIssuedShares() {
        return m_issuedShares;
    }

    public void setIssuedShares(int issuedShares) {
        m_issuedShares = issuedShares;
    }

    public String getExampleUrl() {
        return m_exampleUrl;
    }

    public void setExampleUrl(String exampleUrl) {
        m_exampleUrl = exampleUrl;
    }



    private String m_name;
    private float[] m_historicalPrices;
    private int m_issuedShares;
    private String m_exampleUrl;
}
