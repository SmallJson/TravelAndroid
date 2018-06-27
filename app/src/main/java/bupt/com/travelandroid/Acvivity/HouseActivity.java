package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.Serializable;

import bupt.com.travelandroid.Bean.HouseBean;
import bupt.com.travelandroid.DesiginView.DetailItemOne;
import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/29 0029.
 */
public class HouseActivity extends  BaseDetailActivity {
    //酒店名称
    DetailItemOne dioHouseName;
    //酒店地址
    DetailItemOne dioHouseAddress;
    HouseBean houseBean = new HouseBean();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        initData();
        initView();
    }

    @Override
    public void initView() {
        super.initView();
        dioHouseName = (DetailItemOne) findViewById(R.id.dio_house_name);
        dioHouseAddress = (DetailItemOne) findViewById(R.id.dio_house_address);
        if(houseBean != null){
            dioHouseAddress.setContent(houseBean.getHouseAddress());
            dioHouseName.setContent(houseBean.getHouseName());
            if(!TextUtils.isEmpty(houseBean.getImg())){
                Glide.with(mContext).load(houseBean.getImg()).into(ivAdd);
            }
        }
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        if(intent != null){
            HouseBean bean = (HouseBean) getIntent().getSerializableExtra("data");
            if(bean !=null){
                houseBean = bean;
            }
        }
    }

    @Override
    public Serializable getResultData() {
        if(TextUtils.isEmpty(dioHouseName.getContent())){
            return  null;
        }
        houseBean.setHouseName(TextUtils.isEmpty(dioHouseName.getContent())==true?"无":dioHouseName.getContent());
        houseBean.setHouseAddress(TextUtils.isEmpty(dioHouseAddress.getContent())==true?"无":dioHouseAddress.getContent());
        return houseBean;
    }

    @Override
    protected void uploadError(String msg) {
        Toast.makeText(mContext, msg,Toast.LENGTH_SHORT).show();
        Log.e("resActivity",msg);
    }

    @Override
    protected void upLoadSuccess(String url) {
        Glide.with(mContext).load(url).into(ivAdd);
        Log.e("HouseUri",url);
        houseBean.setImg(url);
    }

    @Override
    protected void clickCancle() {
        houseBean.setImg("");
        Glide.with(mContext).load(R.drawable.add_gray).into(ivAdd);
    }
}
