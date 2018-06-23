package bupt.com.travelandroid.Acvivity.Model;

import android.util.Log;

import com.hyphenate.chat.EMTextMessageBody;

import java.util.List;
import java.util.Map;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Bean.IM.ImMessage;
import bupt.com.travelandroid.Bean.IM.SelectUnReadMessageInterface;
import bupt.com.travelandroid.Bean.IM.UpdateMessageInterface;
import bupt.com.travelandroid.Bean.TravelTotalBean;
import bupt.com.travelandroid.Bean.response.ImMessageInterface;
import bupt.com.travelandroid.Bean.response.MessageInterface;
import bupt.com.travelandroid.Bean.response.TravelInterface;
import bupt.com.travelandroid.util.ApiService;
import bupt.com.travelandroid.util.ContantsUtil;
import bupt.com.travelandroid.util.RetrofitUtil;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Part;

public class MessageModel {


    public void selectMessage(Integer toUid, final IICallBack<List<ImMessage>> callBack){
        //构建ApiService
        ApiService apiService = RetrofitUtil.getApiServiceGson();
        Call<ImMessageInterface> call = apiService.selectMessage(toUid);
        call.enqueue(new Callback<ImMessageInterface>() {
            @Override
            public void onResponse(Call<ImMessageInterface> call, Response<ImMessageInterface> response) {
                ImMessageInterface imMessageInterface = response.body();
                if(imMessageInterface == null || imMessageInterface.getData() == null){
                    callBack.error("查询失败");
                }else{
                    callBack.getData(imMessageInterface.getData());
                }
            }
            @Override
            public void onFailure(Call<ImMessageInterface> call, Throwable t) {
                t.printStackTrace();
                callBack.error("查询消息失败");
            }
        });
    }


    public void sendMessage(RequestBody fromUid, RequestBody dest, RequestBody type
                            , RequestBody text, MultipartBody.Part file, final IICallBack<String> callBack){
            ApiService apiService = RetrofitUtil.getApiServiceGson();
            Call<MessageInterface> call = apiService.sendImageMessage(fromUid, dest, type, text, file);
            call.enqueue(new Callback<MessageInterface>() {
                @Override
                public void onResponse(Call<MessageInterface> call, Response<MessageInterface> response) {
                    MessageInterface messageInterface = response.body();
                    if(messageInterface.getCode() == ContantsUtil.error_code){
                        callBack.error(messageInterface.getMsg());
                    }else{
                        callBack.getData(messageInterface.getMsg());
                    }
                }

                @Override
                public void onFailure(Call<MessageInterface> call, Throwable t) {
                        callBack.error("网络错误");
                        t.printStackTrace();
                }
            });
    }

    /**
     * 更新执行消息的状态为已读，并且返回当前用户的总未读消息数
     * @param messageId
     * @param userId
     * @param callBack
     */
    public void updateMessage(Integer messageId, Integer userId, final IICallBack<Integer> callBack){
        ApiService apiService = RetrofitUtil.getApiServiceGson();
        Call<UpdateMessageInterface> call = apiService.updataMessage(messageId, userId);

        call.enqueue(new Callback<UpdateMessageInterface>() {
            @Override
            public void onResponse(Call<UpdateMessageInterface> call, Response<UpdateMessageInterface> response) {
                UpdateMessageInterface messageInterface = response.body();
                if(messageInterface == null){
                    Log.e("messageModel","接口返回空值");
                    callBack.error("网络错误");
                    return;
                }
                if(messageInterface.getCode() == ContantsUtil.error_code){
                    Log.e("messageModel",messageInterface.getMsg());
                    callBack.error("网络错误");
                    return;
                }else if(messageInterface.getCode() == ContantsUtil.success_code){
                    callBack.getData(messageInterface.getData());
                }
            }

            @Override
            public void onFailure(Call<UpdateMessageInterface> call, Throwable t) {
                t.printStackTrace();
                Log.e("updateMessage","网络错误");
            }
        });
    }

    /**
     * 获取当前用户的总未读消息数
     * @param userId
     * @param callBack
     */
    public void selectUnReadMessage(Integer userId, final IICallBack<Integer> callBack){
        ApiService apiService = RetrofitUtil.getApiServiceGson();
        Call<SelectUnReadMessageInterface> call = apiService.selectUnReadMessage(userId);
        call.enqueue(new Callback<SelectUnReadMessageInterface>() {
            @Override
            public void onResponse(Call<SelectUnReadMessageInterface> call, Response<SelectUnReadMessageInterface> response) {
                SelectUnReadMessageInterface messageInterface = response.body();
                if(messageInterface !=null && messageInterface.getData()!=null){
                    callBack.getData(messageInterface.getData());
                }else{
                    Log.e("selectUnRead",messageInterface.getMsg());
                }
            }

            @Override
            public void onFailure(Call<SelectUnReadMessageInterface> call, Throwable t) {
                t.printStackTrace();
                Log.e("selectUnRead","查询失败");
            }
        });
    }
}
