package bupt.com.travelandroid.Bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/5/30 0030.
 */
public class TravelTotalBean implements Serializable {
    //保存每一天的行程信息
    public HashMap<String,TravelDayBean> travelDayMap;
    //行程的总体信息
    public TravelBean trafficBean;

    public HashMap<String, TravelDayBean> getTravelDayMap() {
        return travelDayMap;
    }

    public void setTravelDayMap(HashMap<String, TravelDayBean> travelDayMap) {
        this.travelDayMap = travelDayMap;
    }

    public TravelBean getTrafficBean() {
        return trafficBean;
    }

    public void setTrafficBean(TravelBean trafficBean) {
        this.trafficBean = trafficBean;
    }
}
