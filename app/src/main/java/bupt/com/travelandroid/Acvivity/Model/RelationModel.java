package bupt.com.travelandroid.Acvivity.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.CallBack.IResultCallBack;
import bupt.com.travelandroid.Bean.response.RelationInfo;
import bupt.com.travelandroid.Bean.response.RelationInterface;
import bupt.com.travelandroid.util.ApiService;
import bupt.com.travelandroid.util.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/6/11 0011.
 */
public class RelationModel {

    public void addRelation(Integer uid, String destPhone, Integer relationType, final IResultCallBack callBack){
        ApiService apiService = RetrofitUtil.getApiServiceGson();
        Call<Map<String, Object>> call = apiService.addRelation(uid, destPhone, relationType);

        call.enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                callBack.getData(response.body());
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                callBack.error("添加失败");
            }
        });
    }

    //查询亲属信息
    public void selectRelation(Integer uid, final IICallBack<ArrayList<RelationInfo>> callBack){
        ApiService apiService = RetrofitUtil.getApiServiceGson();

        Call<RelationInterface> call = apiService.selectRelation(uid);

        call.enqueue(new Callback<RelationInterface>() {
            @Override
            public void onResponse(Call<RelationInterface> call, Response<RelationInterface> response) {
                ArrayList<RelationInfo> list = response.body().getData();
                if(list != null){
                    callBack.getData(list);
                }else{
                    callBack.error(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<RelationInterface> call, Throwable t) {
                callBack.error("加载亲属信息失败");
            }
        });
    }
}
