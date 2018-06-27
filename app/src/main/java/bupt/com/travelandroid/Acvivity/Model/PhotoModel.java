package bupt.com.travelandroid.Acvivity.Model;

import android.util.Log;

import java.util.List;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Bean.response.PhotoInfo;
import bupt.com.travelandroid.Bean.response.PhotoInfoInterface;
import bupt.com.travelandroid.util.ApiService;
import bupt.com.travelandroid.util.ContantsUtil;
import bupt.com.travelandroid.util.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoModel {

    public void selectPhone(Integer fromUid, final IICallBack<List<PhotoInfo>> callBack){
        ApiService apiService = RetrofitUtil.getApiServiceGson();

        Call<PhotoInfoInterface> call = apiService.selectPhotoInfo(fromUid);

        call.enqueue(new Callback<PhotoInfoInterface>() {
            @Override
            public void onResponse(Call<PhotoInfoInterface> call, Response<PhotoInfoInterface> response) {
                PhotoInfoInterface photoInfoInterface  = response.body();
                if(photoInfoInterface == null){
                    Log.e("selectPhone", "接口返回null值");
                    callBack.error("网络错误");
                    return;
                }
                if(photoInfoInterface.getCode() == ContantsUtil.error_code){
                    Log.e("selectPhone", photoInfoInterface.getMsg());
                    callBack.error(photoInfoInterface.getMsg());
                }else{
                    callBack.getData(photoInfoInterface.getData());
                }
            }

            @Override
            public void onFailure(Call<PhotoInfoInterface> call, Throwable t) {
                t.printStackTrace();
                Log.e("selectPhone", "接口回调错误");
                callBack.error("网络错误");
            }
        });
    }
}
