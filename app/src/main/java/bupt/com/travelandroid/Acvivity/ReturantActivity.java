package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.Serializable;

import bupt.com.travelandroid.Bean.ResBean;
import bupt.com.travelandroid.DesiginView.DetailItemOne;
import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/29 0029.
 */

public class ReturantActivity extends  BaseDetailActivity {

    DetailItemOne dioResName;
    DetailItemOne dioResAddress;
    ResBean resBean = new ResBean();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returant);
        initData();
        initView();
    }

    @Override
    public void initView() {
        super.initView();
        dioResName = (DetailItemOne) findViewById(R.id.dio_res_name);
        dioResAddress = (DetailItemOne) findViewById(R.id.dio_res_address);

        //渲染数据
        if(resBean != null){
            dioResName.setContent(resBean.getResName());
            dioResAddress.setContent(resBean.getResAddress());
            if(!TextUtils.isEmpty(resBean.getImg())){
                Glide.with(mContext).load(resBean.getImg()).into(ivAdd);
            }
        }
    }


    @Override
    public void initData() {
        Intent intent = getIntent();
        if(intent != null){
            ResBean bean = (ResBean) getIntent().getSerializableExtra("data");
            if(bean!=null){
                resBean = bean;
            }
        }
    }

    @Override
    public Serializable getResultData() {
        if(TextUtils.isEmpty(dioResName.getContent())
                || TextUtils.isEmpty(dioResAddress.getContent())){
            return  null;
        }
        resBean.setResName(dioResName.getContent());
        resBean.setResAddress(dioResAddress.getContent());
        return resBean;
    }

    @Override
    protected void uploadError(String msg) {
        Toast.makeText(mContext, msg,Toast.LENGTH_SHORT).show();
        Log.e("resActivity",msg);
    }

    @Override
    protected void upLoadSuccess(String url) {
        Glide.with(mContext).load(url).into(ivAdd);
        Log.e("ResUri",url);
        resBean.setImg(url);
    }

    @Override
    protected void clickCancle() {
        resBean.setImg("");
        Glide.with(mContext).load(R.drawable.add_gray).into(ivAdd);
    }
}
