package com.yzhao12.fictionalassets.DataObjects;

/**
 * Created by Yang on 9/9/2017.
 */

public class ProposedMeme extends Meme {
    public ProposedMeme() {

    }

    public ProposedMeme(String name, String ticker, String description, int shares) {
        super(name, ticker, description, null, null);
        sharesSold = shares;
    }

    public int getSharesSold() {
        return sharesSold;
    }

    public void setSharesSold(int shares) {
        sharesSold = shares;
    }


    private int sharesSold;

}