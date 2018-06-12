package bupt.com.travelandroid.Acvivity.Presenter;

import android.content.Context;

import java.util.ArrayList;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.Model.RelationModel;
import bupt.com.travelandroid.Bean.response.RelationInfo;

/**
 * Created by Administrator on 2018/6/11 0011.
 */

public class RelationPresenter {

    Context mContext ;

    RelationModel relationModel = new RelationModel();
    public RelationPresenter(Context context){
        mContext = context;
    }

    public void selectRelation(Integer uid, IICallBack<ArrayList<RelationInfo>> callBack){
        relationModel.selectRelation(uid, callBack);
    }
}
