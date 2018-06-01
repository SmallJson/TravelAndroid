package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import bupt.com.travelandroid.R;

public class MainActivity extends BaseActivity {

    TextView tvPhoneLogin;
    TextView tvRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    @Override
    public void initView() {
        tvRegister = (TextView) findViewById(R.id.tv_register);
        tvPhoneLogin = (TextView) findViewById(R.id.tv_phone_login);


        //点击跳转到注册页面
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext,RegisterActivity.class));
            }
        });

        //点击跳转到登录页面
        tvPhoneLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, LoginActivity.class));
            }
        });
    }

    @Override
    public void initData() {
    }
}
