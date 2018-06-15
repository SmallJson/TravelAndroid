package bupt.com.travelandroid.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.LoginException;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.Presenter.TravelPresenter;
import bupt.com.travelandroid.Bean.MessageBean;
import bupt.com.travelandroid.Bean.TravelTotalBean;
import bupt.com.travelandroid.Fragment.Adapter.RecyMessageAdapter;
import bupt.com.travelandroid.R;
import bupt.com.travelandroid.util.ContanApplication;
import bupt.com.travelandroid.util.SnackUtils;

/**
 * Created by Administrator on 2018/5/27 0027.
 */

public class MessageFragment extends Fragment {

    public  View mView;
    public RecyclerView rlvMessage;
    public SwipeRefreshLayout srlReference;
    public List<TravelTotalBean> messageList = new ArrayList<>();
    public RecyMessageAdapter mAdapter ;
    public RelativeLayout snackRoot;
    TravelPresenter travelPresenter = new TravelPresenter(getActivity());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*return super.onCreateView(inflater, container, savedInstanceState);*/
        mView  = inflater.inflate(R.layout.fragment_message,container,false);
        initView();
        initData();
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        //Fragment从不可见到可见的过程，需要调用消息加载函数
/*        Log.e("onStart","loadMeesgae()");
        loadMessage();*/
    }

    public void initView(){
        srlReference = mView.findViewById(R.id.srl_reference);
        srlReference.setColorSchemeColors(getResources().getColor(R.color.mgreen));
        srlReference.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadMessage();
            }
        });
        rlvMessage = (RecyclerView) mView.findViewById(R.id.rlv_message);
        snackRoot = getActivity().findViewById(R.id.ll_root);
    }

    public void initData(){
        loadMessage();
    }

    public  void loadMessage(){
        //1.检查用户是否登录
        ContanApplication app = (ContanApplication) getActivity().getApplication();
        Integer uid = app.getUid();
        Log.e("msg", uid+"");
        if(uid == null){
            //提示登录
            SnackUtils.showSnackShort(snackRoot, "请登录");
            return;
        }
        //1.1 打开刷新标识
        srlReference.setRefreshing(true);
        //2.加载网络信息
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("toUid", uid);
        travelPresenter.selectTravel(param, new IICallBack<List<TravelTotalBean>>() {
            @Override
            public void getData(List<TravelTotalBean> response) {
                srlReference.setRefreshing(false);
                if(response == null){
                    return;
                }
                if(response.size() == 0){
                    SnackUtils.showSnackLong(snackRoot, "目前无行程");
                }
                //更新数据
                messageList.clear();
                messageList.addAll(response);
                if(mAdapter == null){
                    //第一次创建
                    mAdapter = new RecyMessageAdapter(messageList, getActivity());
                    rlvMessage.setLayoutManager(new LinearLayoutManager(getContext()));
                    rlvMessage.setAdapter(mAdapter);
                }else{
                    //更新数据
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void error(String msg) {
                SnackUtils.showSnackShort(snackRoot, msg);
                srlReference.setRefreshing(false);
            }
        });
    }
}
