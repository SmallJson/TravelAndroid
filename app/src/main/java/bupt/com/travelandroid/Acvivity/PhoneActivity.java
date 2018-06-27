package bupt.com.travelandroid.Acvivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.CollapsibleActionView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import bupt.com.travelandroid.Acvivity.Adapter.RecyPhoneAdapter;
import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.Presenter.PhotoPresenter;
import bupt.com.travelandroid.Bean.UserInfo;
import bupt.com.travelandroid.Bean.response.PhotoInfo;
import bupt.com.travelandroid.R;
import bupt.com.travelandroid.util.ContanApplication;
import okhttp3.MultipartBody;

public class PhoneActivity  extends BaseActivity{

    CollapsingToolbarLayout collapsingToolbarLayout;
    public ImageView ivBackground;
    public ImageView ivHeader;
    public RecyclerView recyPhone;
    public ProgressBar progressBar;
    public ImageView ivBack;

    public PhotoPresenter photoPresenter = new PhotoPresenter(mContext);
    public List<PhotoInfo> photoInfoList = new ArrayList<>();
    public RecyPhoneAdapter adapter  = null;

    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone1);
        initView();
        initData();
    }

     @Override
    public void initView() {
        super.initView();
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        ivBackground = findViewById(R.id.iv_background);
        ivHeader = findViewById(R.id.iv_header);
        recyPhone = findViewById(R.id.recv_phone);
        progressBar = findViewById(R.id.progressBar);

        swipeRefreshLayout = findViewById(R.id.swipReference);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.mgreen));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadPhoto();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void initData() {
        //0.加载图片和头像等背景
        ContanApplication app = (ContanApplication) getApplication();
        UserInfo info = app.getUser().getInfo();
        if(info != null){
            //加载图片
            Glide.with(mContext).load(info.getAvator()).into(ivHeader);
            //加载背景墙
        }
        //1.加载朋友圈信息
        loadPhoto();
    }

    public  void loadPhoto(){
        //0.查询登录信息
        ContanApplication app = (ContanApplication) getApplication();
        Integer uid = app.getUid();
        if(uid == null){
            Toast.makeText(mContext, "尚未登录",Toast.LENGTH_SHORT).show();
            return;
        }
        //1.加载相册信息
        photoPresenter.selectPhone(uid, new IICallBack<List<PhotoInfo>>() {
            @Override
            public void getData(List<PhotoInfo> response) {
                progressBar.setVisibility(View.GONE);
                if(response !=null){
                    notififyChange(response);
                }
            }

            @Override
            public void error(String msg) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(mContext, msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void notififyChange(List<PhotoInfo> list){
        photoInfoList.clear();
        photoInfoList.addAll(list);
        if(adapter == null){
            adapter = new RecyPhoneAdapter(mContext, list);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            recyPhone.setLayoutManager(linearLayoutManager);
            recyPhone.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }
    }
}
