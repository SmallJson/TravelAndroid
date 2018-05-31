package bupt.com.travelandroid.Acvivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

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
    ResBean resBean;
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
        }
    }


    @Override
    public void initData() {
        resBean = (ResBean) getIntent().getSerializableExtra("data");
    }

    @Override
    public Serializable getResultData() {
        ResBean resBean  = new ResBean();
        if(TextUtils.isEmpty(dioResName.getContent())){
            return  null;
        }
        resBean.setResName(dioResName.getContent());
        resBean.setResAddress(dioResAddress.getContent());
        return resBean;
    }
}
