package bupt.com.travelandroid.Acvivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.List;

import bupt.com.travelandroid.Acvivity.Adapter.RecyRelationAdapter;
import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.Presenter.RelationPresenter;
import bupt.com.travelandroid.Bean.response.RelationInfo;
import bupt.com.travelandroid.Fragment.Adapter.RecyMessageAdapter;
import bupt.com.travelandroid.R;
import bupt.com.travelandroid.util.ContanApplication;

/**
 * Created by Administrator on 2018/6/11 0011.
 * 我的亲属页面
 */

public class RelationActivity extends  BaseActivity {

    public RecyclerView recyclerView;

    public RelationPresenter relationPresenter;

    public List<RelationInfo> relationInfoList ;

    public LinearLayout llRoot;

    public RecyRelationAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relation);
        initView();
        initData();
    }

    @Override
    public void initView() {
        super.initView();
        recyclerView = (RecyclerView)findViewById(R.id.rcl_relation);
        llRoot = (LinearLayout) findViewById(R.id.ll_root);
    }

    @Override
    public void initData() {
        //0.准备
        relationPresenter = new RelationPresenter(mContext);
        //检查是否登录
        ContanApplication contanApplication = (ContanApplication) mContext.getApplication();
        Integer uid = contanApplication.getUid();
        if(uid != null){
            loadRelation(uid);
        }else {
            //失败提示
            Snackbar.make(findViewById(R.id.ll_root),"请先登录",Snackbar.LENGTH_SHORT).show();
        }
    }

    private void loadRelation(Integer uid){
        //1.加载亲属数据
        //1.1显示精度条
        //1.2加载数据
        relationPresenter.selectRelation(uid, new IICallBack() {
            @Override
            public void getData(Object response) {
                //数据加载完成后，更新recyclerView
                relationInfoList = (List<RelationInfo>) response;
                Log.e("relation",relationInfoList.toString());
                adapter = new RecyRelationAdapter(mContext , relationInfoList);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void error(String msg) {
                //失败提示
                Snackbar.make(findViewById(R.id.ll_root),msg,Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
