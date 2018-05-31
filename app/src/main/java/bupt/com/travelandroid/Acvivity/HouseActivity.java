package bupt.com.travelandroid.Acvivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

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
    HouseBean houseBean;
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
        }
    }

    @Override
    public void initData() {
        houseBean = (HouseBean) getIntent().getSerializableExtra("data");
    }

    @Override
    public Serializable getResultData() {
        HouseBean houseBean = new HouseBean();
        if(TextUtils.isEmpty(dioHouseName.getContent())){
            return  null;
        }
        houseBean.setHouseName(TextUtils.isEmpty(dioHouseName.getContent())==true?"无":dioHouseName.getContent());
        houseBean.setHouseAddress(TextUtils.isEmpty(dioHouseAddress.getContent())==true?"无":dioHouseAddress.getContent());
        return houseBean;
    }
}
