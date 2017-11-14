package info.btsland.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import android.support.multidex.MultiDexApplication;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.Toast;

import org.spongycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.btsland.app.api.MarketStat;
import info.btsland.app.api.Websocket_api;
import info.btsland.app.api.account_object;
import info.btsland.app.api.asset;
import info.btsland.app.api.asset_object;
import info.btsland.app.exception.NetworkStatusException;
import info.btsland.app.model.DataK;
import info.btsland.app.ui.activity.WelcomeActivity;
import info.btsland.app.util.IDateUitl;
import info.btsland.app.util.InternetUtil;
import okhttp3.WebSocket;

/**
 * Created by Administrator on 2017/10/30.
 */

public class BtslandApplication  extends MultiDexApplication implements MarketStat.OnMarketStatUpdateListener{
    private static final String TAG="BtslandApplication";
    private static Context instance;

    private static BtslandApplication application;

    public static boolean isLogin=false;

    public static account_object accountObject;

    public static boolean isWel=false;
    private static SharedPreferences sharedPreferences;

    public static MarketStat marketStat;
    public static WebSocket mWebsocket;
    public static int nRet= Websocket_api.WEBSOCKET_CONNECT_INVALID;
    public static Map<String,List<MarketStat.HistoryPrice>> dataKMap=new HashMap<>();
    public static int _nDatabaseId = -1;
    public static int _nHistoryId = -1;
    public static int _nBroadcastId = -1;

    public static String[] bases={"CNY", "BTS", "USD", "BTC"};
    public static String[] quotes1={"BTC", "ETH", "BTS", "LTC", "OMG", "STEEM", "VEN", "HPB", "OCT", "YOYOW", "DOGE", "HASH"};
    public static String[] quotes2={"CNY","BTS", "USD", "OPEN.BTC", "OPEN.ETH", "YOYOW", "OCT", "OPEN.LTC", "OPEN.STEEM", "OPEN.DASH", "HPB", "OPEN.OMG", "IMIAO"};


    public static MarketStat getMarketStat() {
        if(marketStat==null){
            marketStat=new MarketStat();
        }
        return marketStat;
    }

    public BtslandApplication() {

    }

    /**
     *
     *
     */
    public static void queryAccount(){

        sharedPreferences=getInstance().getSharedPreferences("Login", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","");
        if (nRet!=Websocket_api.WEBSOCKET_CONNECT_SUCCESS){
            return;
        }
        if(username==null||username.equals("")){
            isLogin=false;
            return;
        }
        try {
            accountObject = getMarketStat().mWebsocketApi.get_account_by_name(username);
            //List<asset>  assets =getMarketStat().mWebsocketApi.list_account_balances(accountObject.id);
            if(accountObject!=null){
                isLogin=true;
            }
        } catch (NetworkStatusException e) {
            e.printStackTrace();
        }
    }

    public static Context getInstance() {
        return instance;
    }

    public static BtslandApplication getListener() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Fabric.with(this, new Crashlytics());
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
        instance=getApplicationContext();
        application=this;

        if(InternetUtil.isConnected(this)){
                ConnectThread();
        }else {
            Toast.makeText(this, "无法连接网络", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onMarketStatUpdate(MarketStat.Stat stat) {
        if(stat!=null){
            this.nRet=stat.nRet;
        }else {
            this.nRet=Websocket_api.WEBSOCKET_CONNECT_INVALID;
        }
        WelcomeActivity.sendBroadcast(getInstance(),this.nRet);
        QueryAccountThread queryAccountThread=new QueryAccountThread();
        queryAccountThread.start();
    }
    public static void ConnectThread(){
        MarketStat marketStat = getMarketStat();
        MarketStat.Connect connect = marketStat.connect(MarketStat.STAT_COUNECT,getListener());
        connect.start();
    }
    public static class QueryAccountThread extends Thread{
        @Override
        public void run() {
            queryAccount();
        }
    }
}
