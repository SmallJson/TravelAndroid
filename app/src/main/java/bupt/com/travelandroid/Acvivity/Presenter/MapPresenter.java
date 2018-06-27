package bupt.com.travelandroid.Acvivity.Presenter;

import android.content.Context;

import java.util.List;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.Model.MapModel;
import bupt.com.travelandroid.Bean.response.AddressInterface;
import bupt.com.travelandroid.Bean.response.LocationBean;

public class MapPresenter {

    Context context;

    MapModel mapModel = new MapModel();

    public MapPresenter(Context context) {
        this.context = context;
    }


    public void selectAddress(String address,
                              IICallBack<List<LocationBean>> callBack){
        mapModel.selectAddress(address, callBack);
    }
}
