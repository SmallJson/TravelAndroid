package bupt.com.travelandroid.Acvivity;

import android.animation.AnimatorSet;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.LoginException;

import bupt.com.travelandroid.Acvivity.CallBack.IResultCallBack;
import bupt.com.travelandroid.Acvivity.IView.ILoginView;
import bupt.com.travelandroid.Acvivity.Presenter.LoginPresenter;
import bupt.com.travelandroid.Bean.User;
import bupt.com.travelandroid.R;
import bupt.com.travelandroid.util.ContanApplication;
import bupt.com.travelandroid.util.SpUtil;

public class SplashActivity extends BaseActivity {

    ImageView ivBackGround;
    ProgressBar progressBar;
    RelativeLayout rlRoot;

    LoginPresenter loginPresenter = new LoginPresenter(mContext);

    //登录状态
    public   final int UNLOGIN = 0;
    public   final int LOGIN_WAIT = 1;
    public   final int LOGIN_FAIL =2;
    public   final int LOGIN_SUCCESS =3;

    int login = UNLOGIN;

    //动画状态
    public final int ANIMATION_START = 1;
    public final int ANIMATION_END =2;
    int animationStatus ;

    //保存的密码名字
    String account ="";
    String password ="";

    //动画集合
    AnimationSet  animationSet ;

    //登录成功获得的未读消息总数
    Integer mUnReadMsg =-1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initData();
        initView();
    }

    @Override
    public void initData() {
        //配置View Presenter内容
        loginPresenter = new LoginPresenter(mContext);
        loginPresenter.setLoginView(new ILoginView() {
            @Override
            public Map<String, String> getLogin() {
                Map<String, String> map = new HashMap<String, String>();
                map.put("password",password);
                map.put("account",account);
                Log.e("account",account);
                Log.e("password",password);
                return map;
            }
        });
    }

    @Override
    public void initView() {
        super.initView();
        ivBackGround = findViewById(R.id.iv_bg);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        rlRoot = findViewById(R.id.rl_root);
        //旋转动画
        RotateAnimation rotateAnimation  = new RotateAnimation(0.0f,359.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

        //透明度动画
        AlphaAnimation alphaanimation  = new AlphaAnimation(0,1);

        //缩放动画
        ScaleAnimation scaleAnimation  = new ScaleAnimation(0,1,0,1);

        //动画集合
        animationSet = new AnimationSet(false);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(alphaanimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setInterpolator(new AccelerateInterpolator());

        animationSet.setDuration(1500);
        animationSet.setFillAfter(true);
        animationSet.setFillBefore(false);
        rlRoot.startAnimation(animationSet);
        alphaanimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animationStatus =ANIMATION_START;

                //1.动画开始开启自动登录
                //1.1检查是否有保存过的账号密码
                //读取上次成功登录的账号密码，回显示到输入框中
                if(!TextUtils.isEmpty(SpUtil.getString(mContext,"account"))
                        && !TextUtils.isEmpty(SpUtil.getString(mContext, "password"))){

                    account = SpUtil.getString(mContext,"account");
                    password = SpUtil.getString(mContext,"password");
                    //如果存在账号密码，则执行自动登录逻辑
                    login = 1 ;
                    autoLogin();
                }
                login = 0;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
              Log.e("animationend",login+"");
              animationStatus = ANIMATION_END;
              switch (login){
                  case LOGIN_WAIT:
                      //正在登录不做处理
                      break;
                  case  UNLOGIN:
                        gotoLoginActivity();
                      break;
                  case   LOGIN_FAIL:
                        gotoLoginActivity();
                      break;
                  case  LOGIN_SUCCESS:
                      //登录成功
                        if(mUnReadMsg != -1){
                           gotoHomeActivity(mUnReadMsg);
                        }

                      break;
                  default:
                      break;
              }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void autoLogin(){
        loginPresenter.login(new IResultCallBack(){
            @Override
            public void getData(Map<String, Object> response) {
                User user = (User)response.get("user");
                if( user != null){
                    //1.1保存全局id信息
                    ContanApplication app = (ContanApplication)getApplication();
                    app.setUid(user.getUid());
                    app.setUser(user);
                    //1.2登录环信聊天
                    loadIm(account, password, user.getUnReadMsg());
                }
                else {
                    login = LOGIN_FAIL;
                    gotoLoginActivity();
                }
            }
            @Override
            public void error(String msg) {
                login = LOGIN_FAIL;
                Log.e("splash","服务端登录错误");
                gotoLoginActivity();
            }
        });
    }

    //登录到环信IM
    public void loadIm(final String userName, String password, final Integer unReadMsg){
        Log.e("im", EMClient.getInstance().isLoggedInBefore()+"");
        EMClient.getInstance().logout(true);
        EMClient.getInstance().login(userName,password,new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.e("im", "登录聊天服务器成功！");
                login = LOGIN_SUCCESS;
                if(animationStatus == ANIMATION_END){
                   gotoHomeActivity(unReadMsg);
                }
                mUnReadMsg = unReadMsg;
            }

            @Override
            public void onProgress(int progress, String status) {
                Log.e("im", "waiting");
            }

            @Override
            public void onError(int code, String message) {
                Log.d("im", message+code);
                login = LOGIN_FAIL;
                Log.e("spplash","环信登录错误");
                gotoLoginActivity();
            }
        });
    }


    public  void gotoLoginActivity(){
        if(animationStatus == ANIMATION_END){
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void gotoHomeActivity(Integer unReadMsg){
        //本地服务器和环信服务器同时登录成功，且动画结束则跳转
        Intent intent = new Intent(mContext, HomeActivity.class);
        Log.e("splash","登录页面获取的未读消息数"+unReadMsg);
        intent.putExtra("unReadMsg", unReadMsg);
        mContext.startActivity(intent);
        finish();
    }
}
