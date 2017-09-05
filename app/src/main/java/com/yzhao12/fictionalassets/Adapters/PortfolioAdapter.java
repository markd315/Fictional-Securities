package com.yzhao12.fictionalassets.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.yzhao12.fictionalassets.DataObjects.PortfolioItem;
import com.yzhao12.fictionalassets.R;

import java.util.List;

/**
 * Created by Yang on 9/5/2017.
 */

public class PortfolioAdapter extends ArrayAdapter<PortfolioItem> {
    public PortfolioAdapter(Context context, int resource, List<PortfolioItem> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PortfolioItem item = getItem(position);

        if (convertView == null)
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.profile_portfolio_item, parent, false);

        TextView ticker = (TextView)convertView.findViewById(R.id.profile_ticker);
        TextView shares = (TextView)convertView.findViewById(R.id.profile_shares);

        ticker.setText(item.getTicker());
        shares.setText(item.getShares());

        return convertView;
    }
}


