package bupt.com.travelandroid.Bean;

/**
 * Created by Administrator on 2018/5/28 0028.
 * 消息实体
 */
public class MessageBean {
    public String leftUrl;
    public String messageTitle;
    public String messageTime;
    public String messageDec;

    public String getLeftUrl() {
        return leftUrl;
    }

    public void setLeftUrl(String leftUrl) {
        this.leftUrl = leftUrl;
    }

    public String getMessageDec() {
        return messageDec;
    }

    public void setMessageDec(String messageDec) {
        this.messageDec = messageDec;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
}
