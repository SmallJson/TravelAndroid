package bupt.com.travelandroid.Acvivity.Presenter;

import android.content.Context;

import java.util.List;
import java.util.Map;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.CallBack.IResultCallBack;
import bupt.com.travelandroid.Acvivity.IView.ITravelView;
import bupt.com.travelandroid.Acvivity.Model.TravelModel;
import bupt.com.travelandroid.Bean.TravelTotalBean;
import bupt.com.travelandroid.Bean.response.MessageInterface;

/**
 * Created by Administrator on 2018/6/12 0012.
 */

public class TravelPresenter {

    public  Context context;
    public TravelModel travelModel = new TravelModel();
    public ITravelView travelView;

    public TravelPresenter(Context context){
        this.context = context;
    }

    public void setTravelView(ITravelView travelView){
        this.travelView = travelView;
    }

    //保存旅游信息
    public void saveTravel( IICallBack<MessageInterface> callBack){
        if(travelView!= null && travelView.getTravelTotalBean() !=null){
            travelModel.addTravel(travelView.getTravelTotalBean(), callBack);
        }
    }

    //查询旅游信息
    public void selectTravel(Map<String, Object> param, IICallBack<List<TravelTotalBean>> callBack){
        travelModel.selectTravel(param, callBack);
    }

    /**
     * 通过id查询旅游信息
     * @param travelId
     * @param callBack
     */
    public  void  selectTravelById(Integer travelId, IICallBack<TravelTotalBean> callBack){
        travelModel.selectTravelById(travelId, callBack);
    }

    /**
     * 修改旅游信息的完成度
     */
     public void updateTravelDetail(Integer id, Integer type, IICallBack<String> callBack){
         travelModel.updateTravelDetaiComplete(id ,type, callBack);
     }

}
