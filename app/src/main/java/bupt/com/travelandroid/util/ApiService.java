package bupt.com.travelandroid.util;

import java.util.Map;

import bupt.com.travelandroid.Bean.User;
import bupt.com.travelandroid.Bean.response.UserInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/5/31 0031.
 * Retrofit用到的接口类
 */
public interface ApiService {

    //登录
    @FormUrlEncoded
    @POST("account/login")
    public Call<UserInterface> login(@Field("account") String account
            , @Field("password") String password);

    //添加关系
    @FormUrlEncoded
    @POST("addRelation")
    public Call<Map<String, Object>> addRelation(@Field("uid") Integer uid, @Field("destPhone") String destPhone
                                                ,@Field("relationType") Integer relationType);
}
