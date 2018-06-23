package bupt.com.travelandroid.Bean.IM;

import com.hyphenate.chat.EMMessageBody;

import java.io.Serializable;

import bupt.com.travelandroid.Bean.UserInfo;

/**
 * 即时通信中交互的消息类型
 */
public class ImMessage implements Serializable{

    public  Integer id;
    public  Integer fromUid;
    public  Integer toUid;
    public String creatTime;
    public Integer type;
    public String text;
    public String imgUrl;
    public Integer travelId;
    public String fromAvator;

    public UserInfo fromUser;

    public String fromName;

    public Integer readType;

    public Integer getReadType() {
        return readType;
    }

    public void setReadType(Integer readType) {
        this.readType = readType;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public UserInfo getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserInfo fromUser) {
        this.fromUser = fromUser;
    }


    public Integer getTravelId() {
        return travelId;
    }

    public void setTravelId(Integer travelId) {
        this.travelId = travelId;
    }

    public String getFromAvator() {
        return fromAvator;
    }

    public void setFromAvator(String fromAvator) {
        this.fromAvator = fromAvator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUuid) {
        this.fromUid = fromUuid;
    }

    public Integer getToUid() {
        return toUid;
    }

    public void setToUid(Integer toUid) {
        this.toUid = toUid;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "ImMessage{" +
                "id=" + id +
                ", fromUid=" + fromUid +
                ", toUid=" + toUid +
                ", creatTime='" + creatTime + '\'' +
                ", type=" + type +
                ", text='" + text + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", travelId=" + travelId +
                ", fromAvator='" + fromAvator + '\'' +
                '}';
    }
}
