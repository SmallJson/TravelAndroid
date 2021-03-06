package bupt.com.travelandroid.util;

import android.app.ActivityManager;
import android.app.Application;
import android.content.pm.PackageManager;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.mob.MobSDK;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import bupt.com.travelandroid.Bean.User;

import static com.hyphenate.chat.EMClient.TAG;

/**
 * Created by Administrator on 2018/6/11 0011.
 */

public class ContanApplication extends Application {

    private static final String TAG = "easeMob";

    //应用程序，登录之后会持有唯一的uid，-1代表没有登录
    Integer uid = null;

    User user  = null;

    @Override
    public void onCreate() {
        super.onCreate();
        initBaiduMap();
        initialEaseMob();
    }

    /**
     * 初始社会分享功能
     */
    public void shareSDK(){
        MobSDK.init(this);
    }

    /**
     * 初始化百度地图信息
     */
    public void initBaiduMap(){
        SDKInitializer.initialize(getApplicationContext());
    }

    //环信IM相关的初始化工作
    private void initialEaseMob() {
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        //取消自动登录
        options.setAutoLogin(false);
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
         // 如果APP启用了远程的service，此application:onCreate会被调用2次
         // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
         // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回
        if (processAppName == null || !processAppName.equalsIgnoreCase(this.getPackageName())) {
            Log.e(TAG, "enter the service process!");
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
        //初始化
        EMClient.getInstance().init(this, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }
    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }
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
