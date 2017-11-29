package info.btsland.app.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import info.btsland.app.BtslandApplication;
import info.btsland.app.R;
import info.btsland.app.model.MarketTicker;
import info.btsland.app.ui.activity.MarketDetailedActivity;
import info.btsland.app.ui.fragment.MarketSimpleKFragment;

/**
 * Created by Administrator on 2017/10/16.
 */

public class MarketRowAdapter extends BaseAdapter {

    private static final String TAG = "MarketRowAdapter";

    private MarketSimpleKFragment simpleKFragment;
    private List<MarketTicker> markets=new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;

    public void setMarkets(List<MarketTicker> markets) {
        this.markets = markets;
        this.notifyDataSetChanged();
    }

    public MarketRowAdapter(MarketSimpleKFragment simpleKFragment, Context context) {
        this.simpleKFragment = simpleKFragment;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return markets.size();
    }

    @Override
    public Object getItem(int i) {
        return markets.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.market_item, null);
        }
        if(markets==null){
            return null;
        }
        if(markets.get(i)==null){
            return convertView;
        }
        MarketTicker market = markets.get(i);
        //Log.i(TAG, "getView: "+market+"i:"+i);
        TextView tvCoin = convertView.findViewById(R.id.tv_coin);
        TextView tvFluctuation = convertView.findViewById(R.id.tv_fluctuation);
        TextView tvNewPrice = convertView.findViewById(R.id.tv_newPrice);
        TextView tvBestAskNum = convertView.findViewById(R.id.tv_bestAskNum);
        TextView tvBestBidNum = convertView.findViewById(R.id.tv_bestBidNum);
        if(market.quote==null){
            tvCoin.setText("");
        }
        tvCoin.setText(market.quote);
        DecimalFormat dfFluc = new DecimalFormat();
        dfFluc.applyPattern("0.00");
        String fluctuation= String.valueOf(dfFluc.format(market.percent_change))+"%";
        //Log.e("getView", "fluctuation: "+ fluctuation);
        tvFluctuation.setText(fluctuation);
        if(market.latest==null){
            tvNewPrice.setText("");
        }else if(market.latest.length()<9){
            tvNewPrice.setText(market.latest);
        }else {
            tvNewPrice.setText(market.latest.substring(0, 8));
        }
        if(market.lowest_ask==null){
            tvBestAskNum.setText("");
        }else if(market.lowest_ask.length()<9){
            tvBestAskNum.setText(market.lowest_ask);
        }else {
            tvBestAskNum.setText(market.lowest_ask.substring(0, 8));
        }
        if(market.highest_bid==null){
            tvBestBidNum.setText("");
        }else if(market.highest_bid.length()<9){
            tvBestBidNum.setText(market.highest_bid);
        }else {
            tvBestBidNum.setText(market.highest_bid.substring(0, 8));
        }

        if (market.percent_change > 0) {

            tvFluctuation.setTextColor(BtslandApplication.goUp);
            tvNewPrice.setTextColor(BtslandApplication.goUp);
        } else if(market.percent_change < 0) {
            tvFluctuation.setTextColor(BtslandApplication.goDown);
            tvNewPrice.setTextColor(BtslandApplication.goDown);
        }else {
            tvFluctuation.setTextColor(BtslandApplication.suspend);
            tvNewPrice.setTextColor(BtslandApplication.suspend);
        }
        rowOnClickListener clickListener=new rowOnClickListener(market);
        convertView.setOnClickListener(clickListener);
        return convertView;

    }
    long mLastTime=0;
    long mCurTime=0;
    private class rowOnClickListener implements View.OnClickListener{
        private MarketTicker market;

        public rowOnClickListener(MarketTicker market) {
            this.market=market;
        }

        @Override
        public void onClick(View v) {
            Message message=Message.obtain();
            mLastTime=mCurTime;
            mCurTime= System.currentTimeMillis();
            if(mCurTime-mLastTime<300){//双击事件
                mCurTime =0;
                mLastTime = 0;
                Bundle bundle=new Bundle();
                bundle.putInt("result",2);
                bundle.putSerializable("MarketTicker",market);
                //Log.i(TAG, "onClick: market:"+market);
                message.setData(bundle);
                handler.sendMessage(message);
            }else{//单击事件
                Bundle bundle=new Bundle();
                bundle.putInt("result",1);
                bundle.putSerializable("MarketTicker",market);
                //Log.i(TAG, "onClick: market:"+market);
                message.setData(bundle);
                handler.sendMessage(message);
            }

        }
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle=msg.getData();
            MarketTicker market = (MarketTicker) bundle.getSerializable("MarketTicker");
            switch (bundle.getInt("result")) {
                case 1:
                    Toast.makeText(context,BtslandApplication.getInstance().getString(R.string.str_double_click),Toast.LENGTH_SHORT).show();
                    //Log.i(TAG, "handleMessage: market:"+market);
                    simpleKFragment.drawK(market);
                    break;
                case 2:
                    //Log.i(TAG, "handleMessage: market:"+market);
                    MarketDetailedActivity.startAction(context,market,1);
                    break;
            }
        }
    };
}
