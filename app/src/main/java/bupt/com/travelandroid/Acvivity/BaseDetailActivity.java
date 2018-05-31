package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/29 0029.
 *交通/地点/酒店/饭店/备注等页面抽象的activity
 */
public abstract class BaseDetailActivity extends BaseActivity {
    ImageView ivBack;
    TextView tvTitle;
    Button btConfirm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        super.initView();
        //统一设置标题栏功能
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conveyData();
            }
        });

        //统一设置标题颜色
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setTextColor(getResources().getColor(R.color.black));

        //统一设置回退按钮
        btConfirm = (Button) findViewById(R.id.bt_confirm);
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conveyData();
            }
        });
    }

    //子activity收集输入框内容
    public abstract Serializable getResultData();

    //覆盖android的后退键
    @Override
    public void onBackPressed() {
        conveyData();
    }

    private void conveyData(){
        if(BaseDetailActivity.this.getResultData()!=null){
            Intent intent = new Intent();
            intent.putExtra("result",BaseDetailActivity.this.getResultData());
            setResult(RESULT_OK,intent);
            finish();
        }else{
            //弹出消息提示框
            Snackbar.make(findViewById(R.id.ll_root),"信息不能为空",Snackbar.LENGTH_SHORT).show();
        }
    }
}
