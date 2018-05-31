package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bupt.com.travelandroid.Bean.TravelBean;
import bupt.com.travelandroid.Bean.TravelDayBean;
import bupt.com.travelandroid.Bean.TravelTotalBean;
import bupt.com.travelandroid.DesiginView.DetailDayItem;
import bupt.com.travelandroid.DesiginView.TrafficDayItem;
import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/28 0028.
 * 第一天，第二天，第三天，第四天等页面
 */

public class TravelListActivity extends BaseActivity {
    LinearLayout llContent;
    TextView tvTravelName;
    //行程视图
    List<View>  viewList = new ArrayList<View>();
    //每天行程具体信息
    HashMap<String,TravelDayBean> dayMap = new HashMap<>();

    //行程简要信息
    TravelBean travelBean ;
    //
    TravelTotalBean travelTotalBean = new TravelTotalBean();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_list);
        initData();
        initView();
    }

    @Override
    public void initView() {
        super.initView();
        llContent = (LinearLayout) findViewById(R.id.ll_content);
        tvTravelName = (TextView) findViewById(R.id.tv_travel_name);

        if(!TextUtils.isEmpty(travelBean.travelName)){
            tvTravelName.setText(travelBean.travelName);
        }else{
            tvTravelName.setText("默认");
        }

        //为空的情况下，默认显示六天
        travelBean.dataCount = TextUtils.isEmpty(travelBean.dataCount)?"6":travelBean.dataCount;

        if(travelBean.dataCount != null){
            int count = Integer.parseInt(travelBean.dataCount);
            for(int i = 0 ;i< count;i++){
                //此处需要设置llContent父容器
                View view = LayoutInflater.from(mContext).inflate(R.layout.day_item,llContent,false);
                TextView tvdata = (TextView) view.findViewById(R.id.tv_data);
                final int day = i + 1;
                tvdata.setText("第"+day+"天");
/*               LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
                view.setLayoutParams(params);*/
                llContent.addView(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent  intent = new Intent(mContext,TravelDetailActivity.class);
                        //传递天数信息
                        intent.putExtra("day",day);
                        //传递视图信息
                        intent.putExtra("data",dayMap.get(""+day));
                        //将天数作为requestCode
                        mContext.startActivityForResult(intent,day);
                    }
                });
                viewList.add(view);
            }
        }
    }

    @Override
    public void initData() {
        travelBean =  (TravelBean) getIntent().getSerializableExtra("travel");
        travelTotalBean.setTrafficBean(travelBean);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //requesyCode代表是第几天传回来的数据
        TravelDayBean dayBean = (TravelDayBean) data.getSerializableExtra("result");
        if(dayBean != null){
            //存储数据
            dayMap.put(""+requestCode,dayBean);
            //更新视图
            updataDayView(requestCode);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //更新第几天的具体视图信息
    private  void updataDayView(Integer day){
        //获取指定天数对应的View视图
        View view = viewList.get(day-1);
        //获取指定天数对应的数据
        TravelDayBean dayBean = dayMap.get(""+day);
        /**
         * 将数据同步到视图上，用户输入非空条件下展示数据
         */
        //1.找到视图控件
        //交通
        TrafficDayItem trafficDayItem = (TrafficDayItem) view.findViewById(R.id.traffic_item);
        //地点
        DetailDayItem placeDayItem = (DetailDayItem) view.findViewById(R.id.ddi_place);
        //餐饮
        DetailDayItem resDayItem = (DetailDayItem) view.findViewById(R.id.ddi_returant);
        //酒店
        DetailDayItem houseDayItem = (DetailDayItem) view.findViewById(R.id.ddi_house);
        //备注暂无
        //2.渲染交通视图
        if(dayBean.getTrafficBean()!=null){
            trafficDayItem.setFlightName(dayBean.getTrafficBean().getFlightName());
            trafficDayItem.setStartTime(dayBean.getTrafficBean().getStartTime());
            trafficDayItem.setEndPlace(dayBean.getTrafficBean().getEndPlace());
            trafficDayItem.setStartPlace(dayBean.getTrafficBean().getStartPlace());
            trafficDayItem.setVisibility(View.VISIBLE);
        }
        //3.渲染地点视图(目前只显示地点名称)
        if(dayBean.getPlaceBean()!=null){
            placeDayItem.setTitle(dayBean.getPlaceBean().getPlaceName());
            placeDayItem.setVisibility(View.VISIBLE);
        }
        //3.渲染餐饮(只显示餐馆名称)
        if(dayBean.getResBean()!=null){
            resDayItem.setTitle(dayBean.getResBean().getResName());
            resDayItem.setVisibility(View.VISIBLE);
        }
        //4.酒店(值显示酒店名称)
        if(dayBean.getHouseBean()!=null){
            houseDayItem.setTitle(dayBean.getHouseBean().getHouseName());
            houseDayItem.setVisibility(View.VISIBLE);
        }
    }


    //当前activity消失不见的时候，
    //将页面当前输入保存到数据库当中
    @Override
    protected void onStop() {
        travelTotalBean.setTravelDayMap(dayMap);
        //save()将当前数据保存到数据库。
        super.onStop();
    }
}
