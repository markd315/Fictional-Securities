package com.yzhao12.fictionalassets.DataObjects;

import java.util.ArrayList;

/**
 * Created by Yang on 9/22/2017.
 */

public class OrderMeme extends Meme {
    public OrderMeme() {
        super(null, null, null, null);
    }

    public OrderMeme(String ticker) {
        super(null, ticker, null, null);

        buy = new ArrayList<>();
        sell = new ArrayList<>();
    }

    public ArrayList<Order> getBuy() {
        return buy;
    }

    public void setBuy(ArrayList<Order> buy) {
        this.buy = buy;
    }

    public ArrayList<Order> getSell() {
        return sell;
    }

    public void setSell(ArrayList<Order> sell) {
        this.sell = sell;
    }

    private ArrayList<Order> buy;
    private ArrayList<Order> sell;
}
