package bupt.com.travelandroid.Acvivity.Presenter;

import android.content.Context;

import java.util.Map;

import bupt.com.travelandroid.Acvivity.CallBack.IResultCallBack;
import bupt.com.travelandroid.Acvivity.IView.IaddParentView;
import bupt.com.travelandroid.Acvivity.Model.RelationModel;

/**
 * Created by Administrator on 2018/6/11 0011.
 */

public class AddParentPresenter {
    public IaddParentView addParentView;
    public RelationModel relationModel = new RelationModel();
    public Context context;

    public  AddParentPresenter(Context context){
        this.context  = context;
    }

    public void setAddParentView(IaddParentView view){
        addParentView = view;
    }

    public void addParent(IResultCallBack callBack){
       //1.获取relationType 和Phone uid.
        Map<String, Object> map = addParentView.getRelation();
        Integer relationType = (Integer) map.get("relationType");
        String destPhone = (String) map.get("destPhone");
        Integer uid  = (Integer) map.get("uid");
        if(uid == null){
            callBack.error("请先登录");
        }
        relationModel.addRelation(uid, destPhone, relationType, callBack);
    }

}
