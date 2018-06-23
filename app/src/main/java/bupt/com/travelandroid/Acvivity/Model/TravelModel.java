package bupt.com.travelandroid.Acvivity.Model;

import android.util.Log;

import java.util.List;
import java.util.Map;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.CallBack.IResultCallBack;
import bupt.com.travelandroid.Bean.TravelTotalBean;
import bupt.com.travelandroid.Bean.response.MessageInterface;
import bupt.com.travelandroid.Bean.response.TravelIdInterface;
import bupt.com.travelandroid.Bean.response.TravelInterface;
import bupt.com.travelandroid.Bean.response.UpdateTravelDetailInterface;
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

    public void selectTravelById(Integer travelId, final IICallBack<TravelTotalBean> callBack){
        ApiService apiService = RetrofitUtil.getApiServiceGson();
        Call<TravelIdInterface> call = apiService.selectTrvalByid(travelId);
        call.enqueue(new Callback<TravelIdInterface>() {
            @Override
            public void onResponse(Call<TravelIdInterface> call, Response<TravelIdInterface> response) {
                TravelIdInterface travelTotalBean = response.body();
                if(travelTotalBean == null || travelTotalBean.getData() == null){
                    callBack.error("预览失败");
                }else{
                    callBack.getData(travelTotalBean.getData());
                }
            }

            @Override
            public void onFailure(Call<TravelIdInterface> call, Throwable t) {
                    callBack.error("网络异常");
            }
        });
    }

    public void updateTravelDetaiComplete(Integer id, Integer type, final IICallBack<String> callBack){
        ApiService apiService = RetrofitUtil.getApiServiceGson();
        Call<UpdateTravelDetailInterface> call = apiService.updateTravelDetail(id ,type);
        call.enqueue(new Callback<UpdateTravelDetailInterface>() {
            @Override
            public void onResponse(Call<UpdateTravelDetailInterface> call, Response<UpdateTravelDetailInterface> response) {
                UpdateTravelDetailInterface detailInterface = response.body();
                if(detailInterface == null){
                    Log.e("updateTravelDetai","接口返回数据为null1");
                    callBack.error("网络错误");
                    return;
                }
                if(detailInterface.getCode() == ContantsUtil.error_code){
                    callBack.error(detailInterface.getMsg());
                }else{
                    callBack.getData(detailInterface.getMsg());
                }
            }

            @Override
            public void onFailure(Call<UpdateTravelDetailInterface> call, Throwable t) {
                Log.e("updateTravelDetai","异常回调");
                t.printStackTrace();
                callBack.error("网络错误");
            }
        });
    }
}
