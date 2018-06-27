package bupt.com.travelandroid.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/5/31 0031.
 */

public class RetrofitUtil {

    public static Retrofit retrofitGson;

    public static Retrofit retrofitRx;

    public static  ApiService apiService;

    public static  BaiduApiService baiduApiService;
    public static ApiService getApiServiceGson(){
        if(apiService != null){
            return apiService;
        }else {
            retrofitGson = new Retrofit.Builder()
                                .baseUrl(ContantsUtil.baseUrl)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
            apiService = retrofitGson.create(ApiService.class);
            return apiService;
        }
    }

    public static BaiduApiService getBadiApiServiceGson(){
        if(baiduApiService != null){
            return baiduApiService;
        }else {
            retrofitGson = new Retrofit.Builder()
                    .baseUrl(ContantsUtil.baiduUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            baiduApiService = retrofitGson.create(BaiduApiService.class);
            return baiduApiService;
        }
    }
}
