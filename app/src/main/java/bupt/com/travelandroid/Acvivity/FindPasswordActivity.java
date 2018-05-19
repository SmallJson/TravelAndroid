package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import bupt.com.travelandroid.DesiginView.BackTitle;
import bupt.com.travelandroid.DesiginView.EditTextPlus;
import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/19 0019.
 */

public class FindPasswordActivity extends  BaseActivity {
    BackTitle backTitle;
    EditTextPlus etpPhone;
    EditTextPlus etpCode;
    CountDownTimer timer;
    Button btNextStep;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpassword);
        initView();
        initData();
    }

    @Override
    public void initView() {
        backTitle = (BackTitle) findViewById(R.id.bt_title);
        etpCode = (EditTextPlus) findViewById(R.id.etp_code);
        etpPhone = (EditTextPlus) findViewById(R.id.etp_phone);
        btNextStep = (Button) findViewById(R.id.bt_next_step);
        //设置标题栏点击事件
        backTitle.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });

        //验证码倒数计时器
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

        //设计验证码点击事件
        etpCode.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
            }
        });

        //设置下一步点击事件
        btNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置下一步点击事件
                mContext.startActivity(new Intent(mContext,ResetPasswordActivity.class));
            }
        });
    }

    @Override
    public void initData() {

    }
}
