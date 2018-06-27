package bupt.com.travelandroid.Acvivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.io.Serializable;

import bupt.com.travelandroid.Bean.TrafficBean;
import bupt.com.travelandroid.DesiginView.DetailItemOne;
import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/29 0029.
 * 交通页面
 */

public class TrafficActivity extends  BaseDetailActivity {
    public DetailItemOne dioFlight;
    public DetailItemOne dioStartTime;
    public DetailItemOne dioStartPlace;
    public DetailItemOne dioEndPlace;
    TrafficBean trafficBean = new TrafficBean();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic);
        initData();
        initView();
    }

    @Override
    public void initView() {
         super.initView();
         dioFlight = (DetailItemOne) findViewById(R.id.dio_flight);
         dioStartTime = (DetailItemOne) findViewById(R.id.dio_start_time);
         dioStartTime.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 new TimePickerDialog(mContext, AlertDialog.THEME_HOLO_LIGHT,new TimePickerDialog.OnTimeSetListener() {

                     @Override
                     public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                         if (minute < 10){
                             dioStartTime.setContent(hourOfDay+":"+"0"+minute);
                         }else {
                             dioStartTime.setContent(hourOfDay+":"+minute);
                         }
                     }
                 }, 0, 0, true).show();
             }
         });

         dioStartPlace = (DetailItemOne) findViewById(R.id.dio_start_place);
         dioEndPlace = (DetailItemOne) findViewById(R.id.dio_end_place);

        //将传递进来的数据，渲染当前视图
        if(trafficBean != null){
            dioFlight.setContent(trafficBean.getFlightName());
            dioStartTime.setContent(trafficBean.getStartTime());
            dioStartPlace.setContent(trafficBean.getStartPlace());
            dioEndPlace.setContent(trafficBean.getEndPlace());
        }
    }

    @Override
    public void initData() {

        Intent intent = getIntent();
        if(intent != null){
            TrafficBean bean = (TrafficBean) getIntent().getSerializableExtra("data");
            if(bean != null){
                trafficBean = bean;
            }
        }
    }

    @Override
    public Serializable getResultData() {
        //获取EditText信息
        //这里需要一个非空的条件判断
        if(TextUtils.isEmpty(dioFlight.getContent()) || TextUtils.isEmpty(dioStartTime.getContent())
                || TextUtils.isEmpty(dioStartPlace.getContent()) || TextUtils.isEmpty(dioEndPlace.getContent())){
            return null;
        }

        trafficBean.setFlightName(dioFlight.getContent());
        trafficBean.setStartTime(dioStartTime.getContent());
        trafficBean.setStartPlace(dioStartPlace.getContent());
        trafficBean.setEndPlace(dioEndPlace.getContent());
        return trafficBean;
    }
}
