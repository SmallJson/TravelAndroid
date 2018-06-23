package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.security.auth.login.LoginException;

import bupt.com.travelandroid.Acvivity.CallBack.IResultCallBack;
import bupt.com.travelandroid.Acvivity.IView.ILoginView;
import bupt.com.travelandroid.Acvivity.Presenter.LoginPresenter;
import bupt.com.travelandroid.Bean.User;
import bupt.com.travelandroid.DesiginView.BackTitle;
import bupt.com.travelandroid.DesiginView.EditTextPlus;
import bupt.com.travelandroid.R;
import bupt.com.travelandroid.util.ContanApplication;
import bupt.com.travelandroid.util.SpUtil;

/**
 * Created by tuyin on 2018/5/18 0018.
 */

public class LoginActivity extends  BaseActivity {
    //标题栏
    BackTitle backTitle;
    //密码
    EditTextPlus etpPassword;
    //手机号码
    EditTextPlus etpPhone;
    //登录按钮
    Button btLogin;

    RelativeLayout rlRoot;

    LoginPresenter loginPresenter;

    //登录对话框内容
    AlertDialog dialog;
    View dialogView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    /*private int scrollToPosition=0;
    public void autoScrllView(){
        rlRoot = findViewById(R.id.rl_root);
        rlRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();

                //获取root在窗体的可视区域
                rlRoot.getWindowVisibleDisplayFrame(rect);

                //获取root在窗体的不可视区域高度(被遮挡的高度)
                int rootInvisibleHeight = rlRoot.getRootView().getHeight() - rect.bottom;

                //若不可视区域高度大于150，则键盘显示
                if (rootInvisibleHeight > 150) {

                    //获取scrollToView在窗体的坐标,location[0]为x坐标，location[1]为y坐标
                    int[] location = new int[2];
                    btLogin.getLocationInWindow(location);

                    //计算root滚动高度，使scrollToView在可见区域的底部
                    int scrollHeight = (location[1] + btLogin.getHeight()) - rect.bottom;

                    //注意，scrollHeight是一个相对移动距离，而scrollToPosition是一个绝对移动距离
                    scrollToPosition += scrollHeight;

                } else {
                    //键盘隐藏
                    scrollToPosition = 0;
                }
                rlRoot.scrollTo(0, scrollToPosition);

            }
        });
    }*/

    @Override
    public void initData() {
        //配置View Presenter内容
        loginPresenter = new LoginPresenter(mContext);
        loginPresenter.setLoginView(new ILoginView() {
            @Override
            public Map<String, String> getLogin() {
                Map<String, String> map = new HashMap<String, String>();
                String password = etpPassword.getContent();
                String account = etpPhone.getContent();
                map.put("password",password);
                map.put("account",account);
                return map;
            }
        });
        //读取上次成功登录的账号密码，回显示到输入框中
        if(!TextUtils.isEmpty(SpUtil.getString(mContext,"account"))
            && !TextUtils.isEmpty(SpUtil.getString(mContext, "password"))){
            etpPassword.setContent(SpUtil.getString(mContext,"password"));
            etpPhone.setContent(SpUtil.getString(mContext,"account"));
        }
    }

    @Override
    public void initView() {
        super.initView();
    /*    //软件盘遮挡逻辑修改
        autoScrllView();*/
        backTitle = (BackTitle) findViewById(R.id.bt_title);
        backTitle.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });

        etpPassword = (EditTextPlus) findViewById(R.id.etp_password);
        //设置忘记密码点击事件，跳转逻辑
        etpPassword.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext,FindPasswordActivity.class));
            }
        });
        //设置密码输入框类型
        etpPassword.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
        etpPassword.setPasswordType();

        etpPhone = (EditTextPlus) findViewById(R.id.etp_phone);
        //设置只能输入电话号码
        etpPassword.setInputType(InputType.TYPE_CLASS_PHONE);

        btLogin = (Button) findViewById(R.id.bt_login);
        //设置登录事件的跳转逻辑
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap<String, String>();
                String password = etpPassword.getContent();
                String account = etpPhone.getContent();

                //非空判断
                if(TextUtils.isEmpty(password) || TextUtils.isEmpty(account)){
                    Snackbar.make(findViewById(R.id.rl_root),"账号密码不能为空",Snackbar.LENGTH_SHORT).show();
                    return ;
                }

                //登录
                showDialog();
                loginPresenter.login(new IResultCallBack(){
                    @Override
                    public void getData(Map<String, Object> response) {
                            User user = (User)response.get("user");
                            if( user != null){
                                Log.e("login",user.toString());
                                //1.将成功登录的账号密码，保存到SharePrefrence中
                                String phone =  etpPhone.getContent();
                                String password = etpPassword.getContent();

                                SpUtil.putString(mContext, "account", phone);
                                SpUtil.putString(mContext, "password",password);

                                //1.1保存全局id信息
                                ContanApplication app = (ContanApplication)getApplication();
                                app.setUid(user.getUid());
                                app.setUser(user);
                                //1.2登录环信聊天
                                loadIm(phone, password, user.getUnReadMsg());
                            }
                            else {
                                //登录失败提示
                                Snackbar.make(findViewById(R.id.rl_root),(String)response.get("msg"),Snackbar.LENGTH_SHORT).show();
                            }
                        hideDialog();
                    }
                    @Override
                    public void error(String msg) {
                        //登录失败提示
                        Snackbar.make(findViewById(R.id.rl_root),msg,Snackbar.LENGTH_SHORT).show();
                        hideDialog();
                    }
                });
            }
        });
    }
    //展示对话框
    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        dialogView = LayoutInflater.from(mContext).inflate(R.layout.loading, null);
        builder.setView(dialogView);
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        //改变对话框的大小
     WindowManager.LayoutParams  lp= dialog.getWindow().getAttributes();
        lp.width=550;//定义宽度
        lp.height=450;//定义高度
        dialog.getWindow().setAttributes(lp);
    }
    public  void hideDialog(){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    //登录到环信IM
    public void loadIm(String userName, String password, final Integer unReadMsg){
        Log.e("im",EMClient.getInstance().isLoggedInBefore()+"");
        EMClient.getInstance().logout(true);
        EMClient.getInstance().login(userName,password,new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.e("im", "登录聊天服务器成功！");
                //本地服务器和环信服务器同时登录成功，登录成功跳转
                Intent intent = new Intent(mContext,HomeActivity.class);
                Log.e("loginActivity","登录页面获取的未读消息数"+unReadMsg);
                intent.putExtra("unReadMsg", unReadMsg);
                mContext.startActivity(intent);
                finish();
            }

            @Override
            public void onProgress(int progress, String status) {
                Log.e("im", "waiting");
            }

            @Override
            public void onError(int code, String message) {
                Log.d("im", message+code);
            }
        });
    }
}
