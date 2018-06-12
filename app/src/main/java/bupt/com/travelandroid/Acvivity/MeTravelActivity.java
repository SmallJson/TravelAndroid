package bupt.com.travelandroid.Acvivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bupt.com.travelandroid.Acvivity.Adapter.RecyRelationAdapter;
import bupt.com.travelandroid.Acvivity.Adapter.RecyTravelAdapter;
import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.Presenter.TravelPresenter;
import bupt.com.travelandroid.Bean.TravelTotalBean;
import bupt.com.travelandroid.R;
import bupt.com.travelandroid.util.ContanApplication;

/**
 * Created by Administrator on 2018/6/12 0012.
 * 我的专属行程页面
 */
public class MeTravelActivity extends  BaseActivity{

    public Button btLocal;
    public Button btShare;

    public RecyclerView recyclerView;
    public RecyTravelAdapter adapter;
    public List<TravelTotalBean> travelTotalBeanList = new ArrayList<>();
    public SwipeRefreshLayout swipeRefreshLayout;
    public Integer type = 1;

    TravelPresenter travelPresenter = new TravelPresenter(mContext);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_travel);
        initView();
        initData();

    }

    @Override
    public void initData() {
        loadTravel();
    }

    @Override
    public void initView() {
        super.initView();
        btShare= findViewById(R.id.bt_share);
        btShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRight();
                loadTravel();
            }
        });

        btLocal = findViewById(R.id.bt_local);
        btLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLeft();
                loadTravel();
            }
        });
        swipeRefreshLayout = findViewById(R.id.srl_reference);
        swipeRefreshLayout.setColorSchemeColors((getResources().getColor(R.color.mgreen)));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadTravel();
            }
        });
        recyclerView = findViewById(R.id.rlv_travel);
        //默认显示右侧
        clickRight();
    }




    //点击左侧按钮的颜色变化
    public void clickLeft(){
        type = 2 ;
        btLocal.setBackgroundColor(getResources().getColor(R.color.mgreen));
        btLocal.setTextColor(getResources().getColor(R.color.homeBackground));

        btShare.setBackgroundColor(getResources().getColor(R.color.homeBackground));
        btShare.setTextColor(getResources().getColor(R.color.mgreen));
    }

    //点击右侧按钮的颜色变化
    public void clickRight(){
        type = 1 ;
        btShare.setBackgroundColor(getResources().getColor(R.color.mgreen));
        btShare.setTextColor(getResources().getColor(R.color.homeBackground));

        btLocal.setBackgroundColor(getResources().getColor(R.color.homeBackground));
        btLocal.setTextColor(getResources().getColor(R.color.mgreen));
    }


    public void loadTravel(){
        swipeRefreshLayout.setRefreshing(true);
        //0.判断用户是否处于登录状态
        ContanApplication contanApplication = (ContanApplication) getApplication();
        Integer uid = contanApplication.getUid();
        if(uid == null){
            //1.用户未登录，给出提示信息
            Snackbar.make(findViewById(R.id.ll_root),"未登录",Snackbar.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
        }else{
            //2.用户登录状态，加载旅行信息
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("fromUid", uid);
            param.put("type",type);
            travelPresenter.selectTravel(param, new IICallBack<List<TravelTotalBean>>() {
                @Override
                public void getData(List<TravelTotalBean> response) {
                    Log.e("MeTravel",response.size()+"");
                        if(response != null){
                            travelTotalBeanList.clear();
                            travelTotalBeanList.addAll(response);
                            if(adapter == null){
                                adapter= new RecyTravelAdapter(mContext, travelTotalBeanList);
                                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                                recyclerView.setAdapter(adapter);
                            }else{
                                adapter.notifyDataSetChanged();
                            }
                        }
                    swipeRefreshLayout.setRefreshing(false);
                }
                @Override
                public void error(String msg) {
                    swipeRefreshLayout.setRefreshing(false);
                    Snackbar.make(findViewById(R.id.ll_root),msg,Snackbar.LENGTH_SHORT).show();
                }
            });
        }
    }
}
