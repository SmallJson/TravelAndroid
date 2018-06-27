package bupt.com.travelandroid.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/30 0030.
 * 地点页面对应的Bean实体
 */

public class PlaceBean implements Serializable{
    String placeName;
    String playTime;

    Integer complete;
    Integer id;

    //经典对应的推荐图片
    String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    //该景点下关联的一系列图片
    List<String> imageUrl;

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }

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
