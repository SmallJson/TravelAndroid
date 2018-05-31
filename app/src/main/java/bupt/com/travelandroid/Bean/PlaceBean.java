package bupt.com.travelandroid.Bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/30 0030.
 * 地点页面对应的Bean实体
 */

public class PlaceBean implements Serializable{
    String placeName;
    String playTime;

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }
}
