package bupt.com.travelandroid.Acvivity.Model;

import java.util.Map;

import bupt.com.travelandroid.Acvivity.CallBack.IResultCallBack;
import bupt.com.travelandroid.util.ApiService;
import bupt.com.travelandroid.util.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/5/31 0031.
 * 和用户访问相关的model
 */
public class AccountModel {

    //同步查询用户和密码是否存在
    public void selectAccountSyn(String account, String password, final IResultCallBack callBack){
        ApiService apiService = RetrofitUtil.getApiServiceGson();
        Call<Map<String,Object>> call= apiService.login(account,password);
        final Map<String, Object> result = null;
        //异步查询
        call.enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                callBack.getData(response.body());
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {

            }
        });
    }
}
