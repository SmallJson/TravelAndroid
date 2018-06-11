package bupt.com.travelandroid.Acvivity.Model;

import java.util.Map;

import bupt.com.travelandroid.Acvivity.CallBack.IResultCallBack;
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
}
