package bupt.com.travelandroid.Bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/28 0028.
 * 用来在Activity之中传递数据
 */
public class TravelBean implements Serializable{
    //旅行名称
    public String travelName;
    //目标城市
    public String city;
    //持续时间
    public String dataCount;
    //出发日期
    public String time;
}
