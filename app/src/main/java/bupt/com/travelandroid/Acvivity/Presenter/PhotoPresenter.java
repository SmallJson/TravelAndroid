package bupt.com.travelandroid.Acvivity.Presenter;

import android.content.Context;

import java.util.List;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.Model.PhotoModel;
import bupt.com.travelandroid.Bean.response.PhotoInfo;

public class PhotoPresenter {
    public Context context;
    PhotoModel photoModel = new PhotoModel();

    public PhotoPresenter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void selectPhone(Integer uid, IICallBack<List<PhotoInfo>> callBack){
        photoModel.selectPhone(uid, callBack);
    }
}
