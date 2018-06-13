package bupt.com.travelandroid.util;

import java.util.Map;

import bupt.com.travelandroid.Bean.TravelTotalBean;
import bupt.com.travelandroid.Bean.User;
import bupt.com.travelandroid.Bean.UserBean;
import bupt.com.travelandroid.Bean.response.MessageInterface;
import bupt.com.travelandroid.Bean.response.RegisterInterface;
import bupt.com.travelandroid.Bean.response.RelationInfo;
import bupt.com.travelandroid.Bean.response.RelationInterface;
import bupt.com.travelandroid.Bean.response.TravelInterface;
import bupt.com.travelandroid.Bean.response.UserInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
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

    @POST("account/register")
    public Call<RegisterInterface> register(@Body UserBean userBean);

    //添加关系
    @FormUrlEncoded
    @POST("addRelation")
    public Call<Map<String, Object>> addRelation(@Field("uid") Integer uid, @Field("destPhone") String destPhone
                                                ,@Field("relationType") Integer relationType);

    //查询关系
    @FormUrlEncoded
    @POST("selectRelation")
    public Call<RelationInterface> selectRelation(@Field("uid") Integer uid);

    //添加行程信息
    @POST("addTravel")
    public Call<MessageInterface> addTravel(@Body TravelTotalBean travelTotalBean);

    //查询行程信息
    @FormUrlEncoded
    @POST("selectTravel")
    public Call<TravelInterface> selectTravel(@Field("fromUid") Integer fromUid, @Field("toUid") Integer toUid
                                                    ,@Field("type") Integer type, @Field("readType") Integer readType);
}
