package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import bupt.com.travelandroid.Bean.IM.ImMessage;
import bupt.com.travelandroid.R;

/**
 * 聊天记录的页面信息
 */
public class ChatActivity extends BaseActivity{

    ImMessage message = null;
    TextView tvTitle;
    ImageView ivLeft;

    ImageView ivFromHeader;
    TextView tvImageDes;
    ImageView ivImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_detail);
        initData();
        initView();
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        message = (ImMessage) intent.getSerializableExtra("message");
    }

    @Override
    public void initView() {
        super.initView();
        //1.找到相关控件
        tvTitle  = findViewById(R.id.tv_title);
        ivLeft = findViewById(R.id.iv_back);
        ivFromHeader = findViewById(R.id.iv_from_header);
        tvImageDes = findViewById(R.id.tv_image_desc);
        ivImage  = findViewById(R.id.iv_image);

        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if(message != null){
            //设置标题栏名字
            if(!TextUtils.isEmpty(message.getFromName())){
                tvTitle.setText(message.getFromName());
            }
            //针对消息的不同类型做不同的处理
            switch (message.getType()){
                case 0:
                    processTextMessage();
                    break;
                case 1:
                    processImageMessage();
                    break;
                default:
                    break;

            }
        }
    }

    private void processImageMessage(){
        tvImageDes.setText(message.getFromName()+"分享一张照片");
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImageActivity.class);
                intent.putExtra("imageUrl", message.getImgUrl());
                startActivity(intent);
            }
        });
        Glide.with(mContext).load(message.getImgUrl()).into(ivImage);
        Glide.with(mContext).load(message.getFromAvator()).into(ivFromHeader);
    }
    private void processTextMessage(){
        tvImageDes.setText(message.getText());
        ivImage.setVisibility(View.GONE);
        Glide.with(mContext).load(message.getFromAvator()).into(ivFromHeader);
    }

}
