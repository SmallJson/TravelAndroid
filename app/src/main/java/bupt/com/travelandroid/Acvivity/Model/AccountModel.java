package bupt.com.travelandroid.Acvivity.Model;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.CallBack.IResultCallBack;
import bupt.com.travelandroid.Bean.User;
import bupt.com.travelandroid.Bean.UserBean;
import bupt.com.travelandroid.Bean.response.RegisterInterface;
import bupt.com.travelandroid.Bean.response.UserInterface;
import bupt.com.travelandroid.util.ApiService;
import bupt.com.travelandroid.util.ContantsUtil;
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
                if(response.body() == null ){
                    callBack.error("登录失败");
                    return;
                }
                if(response.body().getCode().equals(ContantsUtil.error_code)){
                    callBack.error(response.body().getMsg());
                    return;
                }
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

    public void registerAccount(UserBean userBean, final IICallBack<RegisterInterface> callBack){
        ApiService apiService = RetrofitUtil.getApiServiceGson();
        Call<RegisterInterface> call = apiService.register(userBean);
        call.enqueue(new Callback<RegisterInterface>() {
            @Override
            public void onResponse(Call<RegisterInterface> call, Response<RegisterInterface> response) {
                RegisterInterface registerInterface = response.body();
                if(registerInterface == null){
                    Log.e("retrofit","接口返回值null");
                    callBack.error("注册失败");
                    return;
                }
                if(registerInterface.getCode() == ContantsUtil.error_code){
                    callBack.error(registerInterface.getMsg());
                }else{
                    callBack.getData(response.body());
                }
            }

            @Override
            public void onFailure(Call<RegisterInterface> call, Throwable t) {
                t.printStackTrace();
                Log.e("retrofit","异常回调");
                callBack.error("注册失败");
            }
        });
    }
}
