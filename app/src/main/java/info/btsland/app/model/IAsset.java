package info.btsland.app.model;

import java.util.ArrayList;
import java.util.List;

import info.btsland.app.BtslandApplication;
import info.btsland.app.api.asset;
import info.btsland.app.api.asset_object;
import info.btsland.app.api.object_id;
import info.btsland.app.exception.NetworkStatusException;

/**
 * author：lw1000
 * function：分类资产
 * 2017/11/15.
 */

public class IAsset {
    public asset mAsset;
    public Double total=0.0;
    public Double usable=0.0;
    public Double orders=0.0;
    public String coinName;
    public Double totalCNY=0.0;

    public IAsset( String coinName){
        this.coinName=coinName;
        mAsset=new asset();
    }
    public IAsset(asset mAsset) {
        this.mAsset=mAsset;
        fillIn();
    }
    public IAsset(asset mAsset,Double totalCNY) {
        this.mAsset=mAsset;
        this.totalCNY=totalCNY;
        fillIn();
    }
    public IAsset() {
    }

//    public IAsset(asset mAsset, Double total, Double usable, Double orders, String coinName) {
//        this.mAsset=mAsset;
//        this.total=total;
//        this.usable=usable;
//        this.orders=orders;
//        this.coinName=coinName;
//    }






    /**
     *
     */
    private void fillIn(){
        List<object_id<asset_object>> object_ids=new ArrayList <>();
        object_ids.add(mAsset.asset_id);
        try {
            List<asset_object> assetObjects = BtslandApplication.getMarketStat().mWebsocketApi.get_assets(object_ids);
            if(assetObjects!=null){
                asset_object objects = assetObjects.get(0);
                coinName=objects.symbol;
                total=mAsset.amount/Math.pow(10,objects.precision);
            }
        } catch (NetworkStatusException e) {
            e.printStackTrace();
        }
    }
}
