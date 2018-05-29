package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        super.initView();
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result",BaseDetailActivity.this.getResultData());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        tvTitle = (TextView) findViewById(R.id.tv_title);
    }

    public abstract Serializable getResultData();

    //覆盖android的后退键
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("result",BaseDetailActivity.this.getResultData());
        setResult(RESULT_OK,intent);
        finish();
    }
}
