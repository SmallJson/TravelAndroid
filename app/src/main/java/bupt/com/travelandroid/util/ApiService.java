package bupt.com.travelandroid.util;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/5/31 0031.
 * Retrofit用到的接口类
 */
public interface ApiService {
    @FormUrlEncoded
    @POST("account/login")
    public Call<Map<String, Object>> login(@Field("account") String account
            , @Field("password") String password);
}
