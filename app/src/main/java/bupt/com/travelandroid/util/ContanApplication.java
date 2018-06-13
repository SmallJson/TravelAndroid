package bupt.com.travelandroid.util;

import android.app.Application;

import java.util.Objects;

import bupt.com.travelandroid.Bean.User;

/**
 * Created by Administrator on 2018/6/11 0011.
 */

public class ContanApplication extends Application {

    //应用程序，登录之后会持有唯一的uid，-1代表没有登录
    Integer uid = null;

    User user  = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

}
