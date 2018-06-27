package bupt.com.travelandroid.util;

import java.util.Map;

import bupt.com.travelandroid.Bean.IM.SelectUnReadMessageInterface;
import bupt.com.travelandroid.Bean.IM.UpdateMessageInterface;
import bupt.com.travelandroid.Bean.TravelTotalBean;
import bupt.com.travelandroid.Bean.UserBean;
import bupt.com.travelandroid.Bean.response.AddressInterface;
import bupt.com.travelandroid.Bean.response.ImMessageInterface;
import bupt.com.travelandroid.Bean.response.ImageInterface;
import bupt.com.travelandroid.Bean.response.MessageInterface;
import bupt.com.travelandroid.Bean.response.PhotoInfoInterface;
import bupt.com.travelandroid.Bean.response.RegisterInterface;
import bupt.com.travelandroid.Bean.response.RelationInterface;
import bupt.com.travelandroid.Bean.response.TravelIdInterface;
import bupt.com.travelandroid.Bean.response.TravelInterface;
import bupt.com.travelandroid.Bean.response.UpdateTravelDetailInterface;
import bupt.com.travelandroid.Bean.response.UserInterface;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
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
public interface BaiduApiService {

    /**
     * 解析结构化地址，返回经纬度信息
     */
    @GET("cloudgc/v1")
    Call<AddressInterface> selectAddress(@Query("address") String address, @Query("ak") String ak, @Query("output") String output);
}
