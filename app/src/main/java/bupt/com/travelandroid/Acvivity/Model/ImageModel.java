package bupt.com.travelandroid.Acvivity.Model;

import android.util.Log;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Bean.response.ImageInterface;
import bupt.com.travelandroid.util.ApiService;
import bupt.com.travelandroid.util.ContantsUtil;
import bupt.com.travelandroid.util.RetrofitUtil;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageModel {

    /**
     * 上传照片，上传成功返回照片url
     * @param file
     * @param callBack
     */
    public void uploadImage(MultipartBody.Part file, final IICallBack<String> callBack){
        ApiService apiService = RetrofitUtil.getApiServiceGson();
        Call<ImageInterface> call = apiService.uploadImage(file);

        call.enqueue(new Callback<ImageInterface>() {
            @Override
            public void onResponse(Call<ImageInterface> call, Response<ImageInterface> response) {
                ImageInterface imageInterface = response.body();
                if(imageInterface == null){
                    Log.e("uploadImage","接口返回null");
                    callBack.error("网络错误");
                    return;
                }
                if(imageInterface .getCode() == ContantsUtil.error_code){
                    callBack.error(imageInterface.getMsg());
                }else if(imageInterface.getCode() == ContantsUtil.success_code){
                    callBack.getData(imageInterface.getData());
                }
            }

            @Override
            public void onFailure(Call<ImageInterface> call, Throwable t) {
                t.printStackTrace();
                Log.e("uploadImage","回调异常");
                callBack.error("网络错误");
            }
        });
    }
}
