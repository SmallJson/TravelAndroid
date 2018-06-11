package bupt.com.travelandroid.util;

import android.app.Application;

import java.util.Objects;

/**
 * Created by Administrator on 2018/6/11 0011.
 */

public class ContanApplication extends Application {

    //应用程序，登录之后会持有唯一的uid，-1代表没有登录
    Integer uid = null;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

}
