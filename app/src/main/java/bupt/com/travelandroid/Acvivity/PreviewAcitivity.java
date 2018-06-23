package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import bupt.com.travelandroid.Acvivity.Adapter.RecyPreviewTravelDay;
import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.Presenter.TravelPresenter;
import bupt.com.travelandroid.Bean.TravelTotalBean;
import bupt.com.travelandroid.R;

public class PreviewAcitivity  extends  BaseActivity{

    TravelPresenter travelPresenter = new TravelPresenter(mContext);
    TravelTotalBean mTravelTotalBean;

    ImageView ivLeft;
    ImageView ivShare;

    TextView tvTitle;
    TextView tvTravelDestitionTime;
    TextView tvTravelTime;

    RecyclerView recyclerView;
    RecyPreviewTravelDay recyPreviewTravelDay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int travelId =getIntent().getIntExtra("travelId",-1);
        Log.e("preViewActivity",travelId+"");
        setContentView(R.layout.activity_preinit);
        super.initView();
        if(travelId != -1){
            travelPresenter.selectTravelById(travelId, new IICallBack<TravelTotalBean>() {
                @Override
                public void getData(TravelTotalBean response) {
                    mTravelTotalBean = response;
                    setContentView(R.layout.acitivity_travel_arrange);
                    initView();
                    initData();
                }
                @Override
                public void error(String msg) {
                    setContentView(R.layout.activity_error);
                }
            });
        }else {
            setContentView(R.layout.activity_error);
            super.initView();
            Toast.makeText(mContext,"旅行ID不正确",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void initData() {

        //重点数据渲染之处
        recyclerView = findViewById(R.id.recv_travel_detail);
        recyPreviewTravelDay = new RecyPreviewTravelDay(mContext, mTravelTotalBean.getTravelDayMap(),mTravelTotalBean.getTravelBean());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(recyPreviewTravelDay);
    }

    @Override
    public void initView() {
        super.initView();
        ivLeft  = findViewById(R.id.iv_back);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivShare = findViewById(R.id.iv_share);
        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, PictureActivity.class));
            }
        });

    }
}
