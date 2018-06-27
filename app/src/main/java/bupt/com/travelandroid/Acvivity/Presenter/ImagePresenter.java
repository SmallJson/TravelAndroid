package bupt.com.travelandroid.Acvivity.Presenter;

import android.content.Context;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.Model.ImageModel;
import okhttp3.MultipartBody;

public class ImagePresenter {

    public Context context;

    public ImageModel imageModel = new ImageModel();


    public ImagePresenter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public void uploadImage(MultipartBody.Part file, IICallBack<String> callBack){
        imageModel.uploadImage(file, callBack);
    }
}
