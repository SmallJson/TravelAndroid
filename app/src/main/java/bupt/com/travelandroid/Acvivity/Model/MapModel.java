package bupt.com.travelandroid.Acvivity.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Bean.response.AddressInterface;
import bupt.com.travelandroid.Bean.response.LocationBean;
import bupt.com.travelandroid.util.ApiService;
import bupt.com.travelandroid.util.BaiduApiService;
import bupt.com.travelandroid.util.ContantsUtil;
import bupt.com.travelandroid.util.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapModel {


    public void selectAddress(final String address , final IICallBack<List<LocationBean>> callBack){
        ApiService apiService = RetrofitUtil.getApiServiceGson();
        Call<AddressInterface> call = apiService.selectAddress(address, ContantsUtil.AK,"json");
        call.enqueue(new Callback<AddressInterface>() {
            @Override
            public void onResponse(Call<AddressInterface> call, Response<AddressInterface> response) {
                AddressInterface addressInterface = response.body();

                if(addressInterface ==null){
                    Log.e("Map","接口返回null");
                    callBack.error("网络错误");
                    return;
                }
                if(addressInterface.getData()== null){
                    Log.e("Map",addressInterface.getMessage());
                    callBack.error("网络错误");
                    return;
                }
                callBack.getData(addressInterface.getData());
            }

            @Override
            public void onFailure(Call<AddressInterface> call, Throwable t) {
                t.printStackTrace();
                Log.e("MapModel","调用异常");
            }
        });
    }
}
