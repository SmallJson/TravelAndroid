package bupt.com.travelandroid.util;

/**
 * Created by Administrator on 2018/5/31 0031.
 * 保留android中使用的常量信息
 */
public class ContantsUtil {
public static final String baseUrl = "http://123.206.50.85:8080/travel/";
/* public static final String baseUrl = "http://10.108.245.188:8080/";*/
/*  public static final String baseUrl = "http://10.108.245.188:8080/travel/";*/
      public static final int error_code = -1;
      public static final int success_code =200;

    /**
     * Im相关常量
     */
    //已读消息
    public  static final int IM_READ = 1;
    //为读消息
    public static final int IM_UNREAD = 2;

    /**
     * 行程相关的常量信息
     */
    //行程完成
    public static final int COMPLETE = 1;
    //行程未完成
    public static final int UNCOMPLETE = 2;

    /**
     * 细节行程对应的类别
     */
    public static final int HOUSE = 1;
    public static final int TRAFFIC = 2;
    public static final int PLACE = 3;
    public static final int RES = 4;

    /**
     * 消息类型分类
     */
    //发送文本信息
    public static  final int TEXT_MSG = 0;
    //发送图片信息
    public static  final int IMAGE_MSG = 1;
    //发送行程信息
    public static final int TRAVEL_MSG =2;
}

