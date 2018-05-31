package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import bupt.com.travelandroid.Bean.HouseBean;
import bupt.com.travelandroid.Bean.PlaceBean;
import bupt.com.travelandroid.Bean.ResBean;
import bupt.com.travelandroid.Bean.TrafficBean;
import bupt.com.travelandroid.Bean.TravelBean;
import bupt.com.travelandroid.Bean.TravelDayBean;
import bupt.com.travelandroid.DesiginView.DetailDayItem;
import bupt.com.travelandroid.DesiginView.TrafficDayItem;
import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/28 0028.
 * 第1/2/3天等页面的具体页面
 */
public class TravelDetailActivity extends BaseActivity {
    //第几天的行程
    int day ;
    TextView tvDay;
    ImageView ivBack;
    FloatingActionButton fabTraffic;
    FloatingActionButton fabPlace;
    FloatingActionButton fabResturant;
    FloatingActionButton fabSlepping;
    FloatingActionButton fabThings;

    //交通信息
    TrafficDayItem trafficDayItem;
    //地点信息
    DetailDayItem ddiPlace;
    //餐厅信息
    DetailDayItem ddiRes;
    //酒店信息
    DetailDayItem ddiHouse;
    //备注信息
    DetailDayItem ddiNote;

    //一天中行程的总信息
    TravelDayBean travelDayBean = new TravelDayBean();
    /**
     * startActivityForResult需要的状态码
     */
    public final int TRAFFIC_CODE = 1;
    public final int PLACE_CODE =2;
    public final int RESTURANT_CODE =3;
    public final int HOUSE_CODE =4;
    public final int NOTE_CODE =5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_detail);
        initData();
        initView();
    }

    @Override
    public void initData() {
        day = getIntent().getIntExtra("day",-1);
        if(null != getIntent().getSerializableExtra("data")){
            travelDayBean = (TravelDayBean) getIntent().getSerializableExtra("data");
        }
    }

    @Override
    public void initView() {
        super.initView();
        //标题的天数
        tvDay = (TextView) findViewById(R.id.tv_title);
        if(day !=-1){
            tvDay.setText("第"+day+"天");
        }else{
            tvDay.setText("第1天");
        }
        //退回按钮点击事件
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conveyData();
            }
        });

        //悬浮按钮的各个点击事件
        //1.交通信息
        fabTraffic = (FloatingActionButton) findViewById(R.id.fab_traffic);
        fabTraffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,TrafficActivity.class);
                //将当前页面显示的信息，携带进去。
                intent.putExtra("data",travelDayBean.getTrafficBean());
                mContext.startActivityForResult(intent,TRAFFIC_CODE);
            }
        });

        //2.地点信息
        fabPlace = (FloatingActionButton) findViewById(R.id.fab_place);
        fabPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,PlaceActivity.class);
                intent.putExtra("data",travelDayBean.getPlaceBean());
                mContext.startActivityForResult(intent,PLACE_CODE);
            }
        });

        //餐饮信息
        fabResturant = (FloatingActionButton) findViewById(R.id.fab_eat);
        fabResturant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ReturantActivity.class);
                intent.putExtra("data",travelDayBean.getResBean());
                mContext.startActivityForResult(intent,RESTURANT_CODE);
            }
        });

        //酒店信息
        fabSlepping = (FloatingActionButton) findViewById(R.id.fab_slepping);
        fabSlepping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,HouseActivity.class);
                intent.putExtra("data",travelDayBean.getHouseBean());
                mContext.startActivityForResult(intent, HOUSE_CODE);
            }
        });

        //备注信息
        fabThings = (FloatingActionButton) findViewById(R.id.fab_beizhu);
        fabThings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //备注信息展示屏蔽
               // mContext.startActivityForResult(new Intent(mContext,NoteActivity.class),NOTE_CODE);
            }
        });

        trafficDayItem = (TrafficDayItem) findViewById(R.id.traffic_item);
        ddiPlace = (DetailDayItem) findViewById(R.id.ddi_place);
        ddiHouse = (DetailDayItem) findViewById(R.id.ddi_house);
        ddiRes = (DetailDayItem) findViewById(R.id.ddi_returant);
        ddiNote = (DetailDayItem) findViewById(R.id.ddi_note);

        //初始化更新所有视图
        updataAll();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //更新UI的逻辑
        switch (requestCode){
            case TRAFFIC_CODE:
                //获取交通信息
                TrafficBean trafficBean = (TrafficBean) data.getSerializableExtra("result");
                if(trafficBean != null){
                    //更新视图
                    updataTraffic(trafficBean);
                    //存储数据
                    travelDayBean.setTrafficBean(trafficBean);
                }
                break;
            case PLACE_CODE:
                //获取地点信息
                PlaceBean placeBean = (PlaceBean) data.getSerializableExtra("result");
                if(placeBean!=null){
                    //视图
                    updataPlace(placeBean);
                    //存储数据
                    travelDayBean.setPlaceBean(placeBean);
                }
                break;
            case RESTURANT_CODE:
                //获取餐饮信息
                ResBean resBean = (ResBean) data.getSerializableExtra("result");
                if(resBean!=null){
                    //视图
                    updataRes(resBean);
                    //存储数据
                    travelDayBean.setResBean(resBean);
                }
                break;
            case HOUSE_CODE:
                //获取酒店信息
                HouseBean houseBean = (HouseBean) data.getSerializableExtra("result");
                if(houseBean!=null){
                    //视图
                    updataHouse(houseBean);
                    //存储数据
                    travelDayBean.setHouseBean(houseBean);
                }
                break;
            case NOTE_CODE:
                //获取备注信息
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //回传数据
    private void conveyData(){
        Intent intent = new Intent();
        intent.putExtra("result",travelDayBean);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        conveyData();
    }

    //更新交通视图
    public void updataTraffic(TrafficBean trafficBean){
        if(trafficBean != null){
            trafficDayItem.setVisibility(View.VISIBLE);
            trafficDayItem.setFlightName(trafficBean.flightName);
            trafficDayItem.setStartPlace(trafficBean.startPlace);
            trafficDayItem.setEndPlace(trafficBean.endPlace);
            trafficDayItem.setStartTime(trafficBean.startTime);
        }
    }
    //更新地点视图
    public void updataPlace(PlaceBean placeBean){
        if(placeBean != null){
            ddiPlace.setVisibility(View.VISIBLE);
            ddiPlace.setTitle(placeBean.getPlaceName());
        }
    }

    //更新餐饮视图
    public void updataRes(ResBean resBean){
        if(resBean != null){
            ddiRes.setVisibility(View.VISIBLE);
            ddiRes.setTitle(resBean.getResName());
        }
    }

    //更新酒店信息
    public void updataHouse(HouseBean houseBean){
        if(houseBean != null){
            ddiHouse.setVisibility(View.VISIBLE);
            ddiHouse.setTitle(houseBean.getHouseName());
        }
    }
    public void updataAll(){
        updataTraffic(travelDayBean.getTrafficBean());
        updataHouse(travelDayBean.getHouseBean());
        updataRes(travelDayBean.getResBean());
        updataPlace(travelDayBean.getPlaceBean());
        //备注没有做。
    }
}
