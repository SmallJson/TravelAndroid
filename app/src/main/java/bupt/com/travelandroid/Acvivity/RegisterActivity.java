package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import bupt.com.travelandroid.DesiginView.BackTitle;
import bupt.com.travelandroid.R;

/**
 * Created by tuyin on 2018/5/18 0018.
 */

public class RegisterActivity extends BaseActivity {

    BackTitle backTitle;
    Button btNextStep;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.e("registerActivity","enter");
        initView();
        initData();
    }

    @Override
    public void initView() {
        super.initView();
        backTitle = (BackTitle) findViewById(R.id.bt_title);
        btNextStep = (Button) findViewById(R.id.bt_next_step);
    }

    @Override
    public void initData() {
        //返回键设置返回动作
        backTitle.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
        //进入验证码页面
        btNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("registerActivity","test");
                mContext.startActivity(new Intent(mContext,RegisterMessageActivity.class));
            }
        });
    }
}
