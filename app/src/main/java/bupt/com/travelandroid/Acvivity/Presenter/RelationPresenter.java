package bupt.com.travelandroid.Acvivity.Presenter;

import android.content.Context;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.Model.RelationModel;

/**
 * Created by Administrator on 2018/6/11 0011.
 */

public class RelationPresenter {

    Context mContext ;

    RelationModel relationModel = new RelationModel();
    public RelationPresenter(Context context){
        mContext = context;
    }

    public void selectRelation(Integer uid, IICallBack callBack){
        relationModel.selectRelation(uid, callBack);
    }
}
