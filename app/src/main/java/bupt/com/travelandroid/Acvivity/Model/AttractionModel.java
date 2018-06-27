package bupt.com.travelandroid.Acvivity.Model;

import android.util.Log;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Bean.response.AddressInterface;
import bupt.com.travelandroid.Bean.response.AttractionBean;
import bupt.com.travelandroid.Bean.response.AttractionInterface;
import bupt.com.travelandroid.util.ApiService;
import bupt.com.travelandroid.util.ContantsUtil;
import bupt.com.travelandroid.util.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttractionModel {

    public void getAttractionInfo(String keyword, final IICallBack<AttractionBean>  callBack){
        ApiService apiService = RetrofitUtil.getApiServiceGson();
        Call<AttractionInterface> call = apiService.selectAttractionInfo(keyword);
        call.enqueue(new Callback<AttractionInterface>() {
            @Override
            public void onResponse(Call<AttractionInterface> call, Response<AttractionInterface> response) {
                AttractionInterface attractionInterface = response.body();
                if(attractionInterface == null){
                    Log.e("attractionInfo","接口返回值null");
                    callBack.error("网络错误");
                    return;
                }
                if(attractionInterface.getCode() == ContantsUtil.error_code){
                    callBack.error(attractionInterface.getMsg());
                }else{
                    callBack.getData(attractionInterface.getData());
                }
            }

            @Override
            public void onFailure(Call<AttractionInterface> call, Throwable t) {
                t.printStackTrace();
                Log.e("attractionInfo","回调异常");
                callBack.error("网络错误");
            }
        });
    }
}
