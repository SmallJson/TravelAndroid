package bupt.com.travelandroid.Acvivity.Presenter;

import android.content.Context;

import java.util.List;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.Model.MessageModel;
import bupt.com.travelandroid.Bean.IM.ImMessage;
import bupt.com.travelandroid.Bean.response.ImMessageInterface;
import bupt.com.travelandroid.util.ContanApplication;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MessagePresenter {

    public Context context;

    public MessageModel messageModel = new MessageModel();


    public  MessagePresenter(Context context){
        this.context = context;
    }

    public void getAllMessage(Integer toUid, IICallBack<List<ImMessage>> callBack){
        messageModel.selectMessage(toUid,callBack);
    }

    public  void setMessage(RequestBody fromUid, RequestBody dest, RequestBody type
            , RequestBody text, RequestBody placeId, MultipartBody.Part file, final IICallBack<String> callBack){
        messageModel.sendMessage(fromUid, dest, type, text, placeId, file,callBack);
    }

    /**
     * 更新消息的已读未读状态
     */
    public void  updateMessage(Integer messageId, Integer uid ,IICallBack<Integer> callBack){ ;
        messageModel.updateMessage(messageId, uid, callBack);
    }

    /**
     * 查询消息未读数量
     */
}
