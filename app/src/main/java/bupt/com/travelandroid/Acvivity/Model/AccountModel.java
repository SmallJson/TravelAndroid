package bupt.com.travelandroid.Acvivity.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import bupt.com.travelandroid.Acvivity.CallBack.IResultCallBack;
import bupt.com.travelandroid.Bean.User;
import bupt.com.travelandroid.Bean.response.UserInterface;
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
        Call<UserInterface> call= apiService.login(account,password);
        final Map<String, Object> result = null;
        //异步查询
        call.enqueue(new Callback<UserInterface>() {
            @Override
            public void onResponse(Call<UserInterface> call, Response<UserInterface> response) {
                UserInterface userInterface = response.body();
                Map<String ,Object> map = new HashMap<String, Object>();
                map.put("user", userInterface.getData());
                callBack.getData(map);
            }

            @Override
            public void onFailure(Call<UserInterface> call, Throwable t) {
                callBack.error("登录失败");
            }
        });
    }
}
