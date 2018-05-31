package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import bupt.com.travelandroid.Bean.TravelBean;
import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/28 0028.
 */

public class MakingTraveActivity extends BaseActivity{

    Button btConfirm;
    EditText etTravelName;
    EditText etTravelCity;
    EditText etTravelDataCount;
    EditText etEdTime;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maketravel);
        initView();
        initData();
    }

    @Override
    public void initView() {
        super.initView();

        btConfirm = (Button) findViewById(R.id.bt_confirm);
        etTravelName= (EditText) findViewById(R.id.et_name);
        etTravelCity = (EditText) findViewById(R.id.et_city);
        etTravelDataCount = (EditText) findViewById(R.id.et_data);
        etEdTime = (EditText) findViewById(R.id.et_time);
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelBean travelBean = new TravelBean();
                if(TextUtils.isEmpty(etTravelName.getText().toString())
                   ||TextUtils.isEmpty(etTravelCity.getText().toString())
                   ||TextUtils.isEmpty(etTravelDataCount.getText().toString())
                   ||TextUtils.isEmpty(etEdTime.getText().toString())){
                    //弹出消息提示框
                    Snackbar.make(findViewById(R.id.ll_root),"信息不能为空",Snackbar.LENGTH_SHORT).show();
                    return ;
                }
                    //赋值
                    travelBean.travelName = etTravelName.getText().toString();
                    travelBean.city = etTravelCity.getText().toString();
                    travelBean.dataCount = etTravelDataCount.getText().toString();
                    travelBean.time= etEdTime.getText().toString();

                    //跳转
                    Intent intent = new Intent(mContext,TravelListActivity.class);
                    intent.putExtra("travel",travelBean);
                    startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {

    }
}
