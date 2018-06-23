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
    //分享目标手机号码
    public String phone;


    /**
     * 查询行程服务器返回独有的字段
     */
    //分享人的头像
    public  String fromAvator;
    //分享人昵称
    public String fromName;
    //该行程的唯一id
    public  Integer xingchengId;
    //分享人的号码
    public String sharePhone;

    public String getSharePhone() {
        return sharePhone;
    }

    public void setSharePhone(String sharePhone) {
        this.sharePhone = sharePhone;
    }

    public String getFromAvator() {
        return fromAvator;
    }

    public void setFromAvator(String fromAvator) {
        this.fromAvator = fromAvator;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Integer getXingchengId() {
        return xingchengId;
    }

    public void setXingchengId(Integer xingchengId) {
        this.xingchengId = xingchengId;
    }

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
