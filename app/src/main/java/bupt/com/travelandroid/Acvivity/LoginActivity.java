package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import bupt.com.travelandroid.DesiginView.BackTitle;
import bupt.com.travelandroid.DesiginView.EditTextPlus;
import bupt.com.travelandroid.R;

/**
 * Created by tuyin on 2018/5/18 0018.
 */

public class LoginActivity extends  BaseActivity {
    //标题栏
    BackTitle backTitle;
    //密码标记框
    EditTextPlus etpPassword;
    //手机号码编辑框
    EditTextPlus etpPhone;
    //登录对话框
    Button btLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    @Override
    public void initData() {

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

        etpPhone = (EditTextPlus) findViewById(R.id.etp_phone);
        btLogin = (Button) findViewById(R.id.bt_login);

        //设置登录事件的跳转逻辑
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext,HomeActivity.class));
            }
        });

    }
}
