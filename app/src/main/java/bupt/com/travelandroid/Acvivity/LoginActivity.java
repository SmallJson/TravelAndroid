package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
                                //1.将成功登录的账号密码，保存到SharePrefrence中
                                SpUtil.putString(mContext, "account", etpPhone.getContent());
                                SpUtil.putString(mContext, "password",etpPassword.getContent());
                                //1.1保存全局id信息
                                ContanApplication app = (ContanApplication)getApplication();
                                app.setUid(user.getUid());
                                hideDialog();
                                //2.登录成功跳转
                                mContext.startActivity(new Intent(mContext,HomeActivity.class));
                                finish();
                            }
                            else {
                                //登录失败提示
                                Snackbar.make(findViewById(R.id.rl_root),(String)response.get("msg"),Snackbar.LENGTH_SHORT).show();
                            }
                    }
                    @Override
                    public void error(String msg) {
                        //登录失败提示
                        Snackbar.make(findViewById(R.id.rl_root),msg,Snackbar.LENGTH_SHORT).show();
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
}
