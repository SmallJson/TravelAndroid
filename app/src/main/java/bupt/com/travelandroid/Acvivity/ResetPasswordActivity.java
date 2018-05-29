package bupt.com.travelandroid.Acvivity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import bupt.com.travelandroid.DesiginView.BackTitle;
import bupt.com.travelandroid.DesiginView.EditTextPlus;
import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/19 0019.
 * 重置密码的activity
 */
public class ResetPasswordActivity extends BaseActivity {
    BackTitle backTitle;
    EditTextPlus etpPassword;
    Button btConfirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_resetpassword);
        initView();
        initData();
    }

    @Override
    public void initView() {
        super.initView();

        backTitle = (BackTitle) findViewById(R.id.bt_title);
        etpPassword = (EditTextPlus) findViewById(R.id.etp_password);
        btConfirm = (Button) findViewById(R.id.bt_confirm);

        //设置标题栏返回按钮
        backTitle.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
    }

    @Override
    public void initData() {

    }
}
