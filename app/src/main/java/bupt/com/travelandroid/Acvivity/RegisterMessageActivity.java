package bupt.com.travelandroid.Acvivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import bupt.com.travelandroid.DesiginView.BackTitle;
import bupt.com.travelandroid.DesiginView.EditTextPlus;
import bupt.com.travelandroid.R;

/**
 * Created by tuyin on 2018/5/18 0018.
 * 注册短信验证码页面
 */
public class RegisterMessageActivity extends  BaseActivity {
    BackTitle backTitle;
    CountDownTimer timer;
    EditTextPlus etpCode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_msg);
        Log.e("regiserActivity","test");
        initView();
        initData();
    }

    @Override
    public void initView() {
        backTitle = (BackTitle) findViewById(R.id.bt_title);
        backTitle.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
        etpCode = (EditTextPlus) findViewById(R.id.etp_msg);

        //定时器
        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                etpCode.setRightTextColor(R.color.mgray);
                etpCode.setRightClickable(false);
                etpCode.setRightText( millisUntilFinished / 1000 + "s后重新发送");
            }

            @Override
            public void onFinish() {
                etpCode.setRightText("获取验证码");
                etpCode.setRightClickable(true);
                etpCode.setRightTextColor(R.color.mgreen);
            }
        };

        //设置点击事件
        etpCode.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
            }
        });


    }

    @Override
    public void initData() {
    }

}
