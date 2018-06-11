package bupt.com.travelandroid.Bean;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/5/30 0030.
 */
public class TravelTotalBean implements Serializable {
    //保存每一天的行程信息
    public HashMap<String,TravelDayBean> travelDayMap;
    //行程的总体信息
    public TravelBean travelBean;

    public HashMap<String, TravelDayBean> getTravelDayMap() {
        return travelDayMap;
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
}
