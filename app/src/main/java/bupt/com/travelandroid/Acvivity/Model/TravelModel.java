package bupt.com.travelandroid.Acvivity.Model;

import android.util.Log;

import java.util.List;
import java.util.Map;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.CallBack.IResultCallBack;
import bupt.com.travelandroid.Bean.TravelTotalBean;
import bupt.com.travelandroid.Bean.response.MessageInterface;
import bupt.com.travelandroid.Bean.response.TravelInterface;
import bupt.com.travelandroid.util.ApiService;
import bupt.com.travelandroid.util.ContantsUtil;
import bupt.com.travelandroid.util.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/6/12 0012.
 */

public class TravelModel {


    public  void addTravel(TravelTotalBean travelTotalBean, final IICallBack<MessageInterface> callBack){
        ApiService apiService = RetrofitUtil.getApiServiceGson();
        Call<MessageInterface> call = apiService.addTravel(travelTotalBean);
        call.enqueue(new Callback<MessageInterface>() {
            @Override
            public void onResponse(Call<MessageInterface> call, Response<MessageInterface> response) {
                if(response.body() == null ){
                    callBack.error("分享失败");
                }else if (response.body().getCode() .equals(ContantsUtil.error_code)){
                    callBack.error((String) response.body().getMsg());
                }else{
                    callBack.getData(response.body());
                }
            }

            @Override
            public void onFailure(Call<MessageInterface> call, Throwable t) {
                callBack.error("分享失败");
            }
        });

    }

    public void selectTravel(Map<String, Object> param, final IICallBack<List<TravelTotalBean>> callBack){
        //构建ApiService
        ApiService apiService = RetrofitUtil.getApiServiceGson();
        Integer fromUid = (Integer) param.get("fromUid");
        Integer toUid = (Integer) param.get("toUid");
        Integer type = (Integer) param.get("type");
        final Integer readType = (Integer) param.get("readType");
        Call<TravelInterface> call = apiService.selectTravel(fromUid, toUid, type, readType);

        call.enqueue(new Callback<TravelInterface>() {
            @Override
            public void onResponse(Call<TravelInterface> call, Response<TravelInterface> response) {
                Log.e("travel",response.body().toString());
                if(response.body().getCode() == ContantsUtil.error_code){
                    callBack.error("读取是失败");
                    Log.e("travelSelect",response.body().getMsg());
                }else{
                    callBack.getData(response.body().getData());
                }
            }
            @Override
            public void onFailure(Call<TravelInterface> call, Throwable t) {
                    callBack.error("读取失败");
                    t.printStackTrace();
            }
        });
    }
}
