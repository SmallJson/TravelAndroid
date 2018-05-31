package bupt.com.travelandroid.Acvivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

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
    TrafficBean trafficBean = null;
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
        trafficBean = (TrafficBean) getIntent().getSerializableExtra("data");
    }

    @Override
    public Serializable getResultData() {
        //获取EditText信息
        TrafficBean trafficBean = new TrafficBean();
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
