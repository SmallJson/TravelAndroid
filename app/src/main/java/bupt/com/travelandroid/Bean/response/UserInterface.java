package bupt.com.travelandroid.Bean.response;

import bupt.com.travelandroid.Bean.User;

/**
 * Created by Administrator on 2018/6/11 0011.
 * 网络接口--登录返回的json数据对饮的实体
 */
public class UserInterface {
    public String code;
    public String msg;
    public User data;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
