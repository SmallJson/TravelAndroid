package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import bupt.com.travelandroid.R;

public class ImageActivity extends BaseActivity{

    ImageView ivBack;
    ImageView ivBg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initView();
        initData();
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("imageUrl");
        if(imageUrl != null){
            Glide.with(mContext).load(imageUrl).into(ivBg);
        }else{
            Log.e("imageActivity","intent传递数据失败");
        }
    }

    @Override
    public void initView() {
        super.initView();
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivBg = findViewById(R.id.iv_bg);
    }
}
