package bupt.com.travelandroid.Acvivity.Presenter;

import android.content.Context;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.Model.AttractionModel;
import bupt.com.travelandroid.Bean.response.AttractionBean;
import bupt.com.travelandroid.util.ContantsUtil;

public class AttractionPresenter {

    Context context;
    AttractionModel attractionModel = new AttractionModel();

    public AttractionPresenter(Context context) {
        this.context = context;
    }

    public void getAttractionInfo(String attractionName, IICallBack<AttractionBean> callBack){
        attractionModel.getAttractionInfo(attractionName, callBack);
    }
}
