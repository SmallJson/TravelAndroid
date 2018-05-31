package bupt.com.travelandroid.Bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/30 0030.
 * 酒店信息
 */
public class HouseBean implements Serializable{
    String houseName;
    String houseAddress;

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }
}
