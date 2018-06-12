package bupt.com.travelandroid.Bean;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/5/30 0030.
 */
public class TravelTotalBean implements Serializable {
    //保存每一天的行程信息
    public HashMap<String,TravelDayBean> travelDayMap = new HashMap<>();
    //行程的总体信息
    public TravelBean travelBean = new TravelBean();

    public HashMap<String, TravelDayBean> getTravelDayMap() {
        return travelDayMap;
    }
    //分享人id
    public Integer fromUid;
    //分享对象手机号码
    public String phone;

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTravelDayMap(HashMap<String, TravelDayBean> travelDayMap) {
        this.travelDayMap = travelDayMap;
    }

    public TravelBean getTravelBean() {
        return travelBean;
    }

    public void setTravelBean(TravelBean travelBean) {
        this.travelBean = travelBean;
    }

    @Override
    public String toString() {
        return "TravelTotalBean{" +
                "travelDayMap=" + travelDayMap +
                ", travelBean=" + travelBean +
                ", fromUid=" + fromUid +
                ", phone='" + phone + '\'' +
                '}';
    }
}
