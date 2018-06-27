package bupt.com.travelandroid.util;

import java.util.Map;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Bean.IM.SelectUnReadMessageInterface;
import bupt.com.travelandroid.Bean.IM.UpdateMessageInterface;
import bupt.com.travelandroid.Bean.TravelTotalBean;
import bupt.com.travelandroid.Bean.User;
import bupt.com.travelandroid.Bean.UserBean;
import bupt.com.travelandroid.Bean.response.AddressInterface;
import bupt.com.travelandroid.Bean.response.AttractionBean;
import bupt.com.travelandroid.Bean.response.AttractionInterface;
import bupt.com.travelandroid.Bean.response.ImMessageInterface;
import bupt.com.travelandroid.Bean.response.ImageInterface;
import bupt.com.travelandroid.Bean.response.MessageInterface;
import bupt.com.travelandroid.Bean.response.PhotoInfoInterface;
import bupt.com.travelandroid.Bean.response.RegisterInterface;
import bupt.com.travelandroid.Bean.response.RelationInfo;
import bupt.com.travelandroid.Bean.response.RelationInterface;
import bupt.com.travelandroid.Bean.response.TravelIdInterface;
import bupt.com.travelandroid.Bean.response.TravelInterface;
import bupt.com.travelandroid.Bean.response.UpdateTravelDetailInterface;
import bupt.com.travelandroid.Bean.response.UserInterface;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

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

    @GET("selectMessage")
    public Call<ImMessageInterface> selectMessage(@Query("toUid") Integer toUid);

    /**
     * 发送消息的接口
     * @param fronUid 发送人id
     * @param dest 目标对象的手机号码
     * @param type 消息类型， type =0.文本消息，type =1 图片消息
     * @param text 文本消息的具体内容
     * @param file 具体的图片
     * @return
     */
    @Multipart
    @POST("sendMessage")
    Call<MessageInterface> sendImageMessage(@Part("fromUid") RequestBody fronUid,
                             @Part("dest") RequestBody dest,
                             @Part("type") RequestBody type,
                             @Part("text") RequestBody text,
                             @Part("placeId") RequestBody placeId,
                             @Part() MultipartBody.Part file);

    @GET("selectTravelByid")
    Call<TravelIdInterface> selectTrvalByid(@Query("travelId") Integer travelId);



    /**
     * 更新指定消息为已读
     * @param id 消息id
     * @param toUid 用户的userId
     * @return
     */
    @GET("updateMsgStatus")
    Call<UpdateMessageInterface> updataMessage(@Query("id") Integer id
                                                       , @Query("toUid") Integer toUid);

    /**
     * 查询用户的未读消息数量
     * @Param toUid 用户userId
     * @return
     */
    @GET("selectUnReadMsg")
    Call<SelectUnReadMessageInterface> selectUnReadMessage( @Query("toUid") Integer toUid);

    /**
     * 更新旅行具体内容完成状态
     */
    @GET("updateTravelComplete")
    Call<UpdateTravelDetailInterface> updateTravelDetail(@Query("id") Integer id, @Query("type") Integer type);


    //上传图片，返回图片url地址
    @Multipart
    @POST("image")
    Call<ImageInterface >uploadImage(@Part()MultipartBody.Part file);

    /**
     * 查询所有的照片信息
     */
    @GET("photo")
    Call<PhotoInfoInterface> selectPhotoInfo(@Query("fromUid") Integer fromUid);

    /**
     * 解析结构化地址，返回经纬度信息
     */
    @GET("cloudgc/v1")
    Call<AddressInterface> selectAddress(@Query("address") String address, @Query("ak") String ak, @Query("output") String output);

    /**
     * @param keyword 景点信息关键词
     * @return
     */
    @GET("attractionInfomation")
    Call<AttractionInterface> selectAttractionInfo(@Query("keyword") String keyword);
}
