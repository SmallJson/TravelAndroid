package bupt.com.travelandroid.Bean.response;


import java.util.List;

import bupt.com.travelandroid.Bean.PlaceBean;
import bupt.com.travelandroid.Bean.UserInfo;

//封装相册信息的类
public class PhotoInfo {

    public UserInfo userInfo;

    public String time;


    public PlaceBean place;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


   public PlaceBean getPlace() {
        return place;
    }

    public void setPlace(PlaceBean place) {
        this.place = place;
    }
}
