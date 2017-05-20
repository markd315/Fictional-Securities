package com.yzhao12.fictionalassets;

import com.robinhood.spark.SparkAdapter;
import com.robinhood.spark.SparkView;

/**
 * Created by Yang on 3/29/2017.
 */

public class PortfolioSparkAdapter extends SparkAdapter {
    private float[] yData;

    public PortfolioSparkAdapter(float[] data) {
        this.yData = data;
    }

    public int getCount() {
        return yData.length;
    }

    public Object getItem(int index) {
        return yData[index];
    }

    public float getY(int index) {
        return yData[index];
    }
}
