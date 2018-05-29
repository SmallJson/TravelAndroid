package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/28 0028.
 */

public class DetailTravelActivity extends BaseActivity {
    //第几天的行程
    int day ;
    TextView tvDay;
    ImageView ivBack;
    FloatingActionButton fabTraffic;
    FloatingActionButton fabPlace;
    FloatingActionButton fabResturant;
    FloatingActionButton fabSlepping;
    FloatingActionButton fabThings;
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
                finish();
            }
        });
        //悬浮按钮的各个点击事件
        fabTraffic = (FloatingActionButton) findViewById(R.id.fab_traffic);
        fabTraffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivityForResult(new Intent(mContext,TrafficActivity.class),TRAFFIC_CODE);
            }
        });
        fabPlace = (FloatingActionButton) findViewById(R.id.fab_place);
        fabPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivityForResult(new Intent(mContext,PlaceActivity.class),PLACE_CODE);
            }
        });
        fabResturant = (FloatingActionButton) findViewById(R.id.fab_eat);
        fabResturant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivityForResult(new Intent(mContext,ReturantActivity.class),RESTURANT_CODE);
            }
        });
        fabSlepping = (FloatingActionButton) findViewById(R.id.fab_slepping);
        fabSlepping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivityForResult(new Intent(mContext,HouseActivity.class), HOUSE_CODE);
            }
        });
        fabThings = (FloatingActionButton) findViewById(R.id.fab_beizhu);
        fabThings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivityForResult(new Intent(mContext,NoteActivity.class),NOTE_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //更回退更新UI的逻辑
        switch (requestCode){
            case TRAFFIC_CODE:
                break;
            case PLACE_CODE:
                break;
            case RESTURANT_CODE:
                break;
            case HOUSE_CODE:
                break;
            case NOTE_CODE:
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
