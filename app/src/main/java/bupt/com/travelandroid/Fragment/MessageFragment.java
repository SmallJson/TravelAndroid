package bupt.com.travelandroid.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.CallBack.IMessageListener;
import bupt.com.travelandroid.Acvivity.CallBack.OnItemClickListenerRecy;
import bupt.com.travelandroid.Acvivity.ChatActivity;
import bupt.com.travelandroid.Acvivity.HomeActivity;
import bupt.com.travelandroid.Acvivity.Presenter.MessagePresenter;
import bupt.com.travelandroid.Acvivity.Presenter.TravelPresenter;
import bupt.com.travelandroid.Acvivity.PreviewAcitivity;
import bupt.com.travelandroid.Bean.IM.ImMessage;
import bupt.com.travelandroid.Bean.TravelTotalBean;
import bupt.com.travelandroid.Fragment.Adapter.RecyMessageAdapter;
import bupt.com.travelandroid.Fragment.Adapter.RecyTravelAdapter;
import bupt.com.travelandroid.R;
import bupt.com.travelandroid.util.ContanApplication;
import bupt.com.travelandroid.util.ContantsUtil;
import bupt.com.travelandroid.util.SnackUtils;

/**
 * Created by Administrator on 2018/5/27 0027.
 */

public class MessageFragment extends Fragment {

    public View mView;
    public RecyclerView rlvMessage;
    public SwipeRefreshLayout srlReference;
    public RelativeLayout snackRoot;
 /*   public TravelPresenter travelPresenter = new TravelPresenter(getActivity());*/

    MessagePresenter messagePresenter = new MessagePresenter(getActivity());
    RecyMessageAdapter messageAdapter =null;
    HomeActivity homeActivity;
    public List<ImMessage> imList = new ArrayList<>();

    public void setNewMessage(List<ImMessage> list){
/*        this.imList.addAll(list);
        changeMessageAdaper();*/
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*return super.onCreateView(inflater, container, savedInstanceState);*/
        mView  = inflater.inflate(R.layout.fragment_message,container,false);
        initView();
        initData();
        Log.e("messageFragment","onCreatView");
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    public void initView(){
        srlReference = mView.findViewById(R.id.srl_reference);
        srlReference.setColorSchemeColors(getResources().getColor(R.color.mgreen));
        srlReference.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadImMessage();
            }
        });
        rlvMessage = (RecyclerView) mView.findViewById(R.id.rlv_message);
        snackRoot = getActivity().findViewById(R.id.ll_root);

    }

    public void initData(){

        /**
         * 注册监听事件信息
         */
        homeActivity = (HomeActivity) getActivity();
        homeActivity.setMessageArriveListener(new IMessageListener() {
            @Override
            public void messageArrival(List<ImMessage> message) {
                Log.e("messageFragment","回调进入Fragment");
                Log.e("msgFragment","收到了信息");
                for(int i = 0 ;i<message.size(); i++){
                    imList.add(0,message.get((i)));
                }
                changeMessageAdaper();
            }
        });
        loadImMessage();
    }


    public void loadImMessage(){
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
        messagePresenter.getAllMessage(uid, new IICallBack<List<ImMessage>>() {
            @Override
            public void getData(List<ImMessage> response) {
                srlReference.setRefreshing(false);
                if(response == null){
                    return;
                }
                if(response.size() == 0){
                    SnackUtils.showSnackLong(snackRoot, "目前无消息");
                }
                //更新数据
                imList.clear();
                imList.addAll(response);
                changeMessageAdaper();
            }

            @Override
            public void error(String msg) {
                SnackUtils.showSnackShort(snackRoot, msg);
                srlReference.setRefreshing(false);
            }
        });
    }

    public void changeMessageAdaper(){
        if(messageAdapter == null){
            //第一次创建
            messageAdapter = new RecyMessageAdapter(imList, getActivity());
            rlvMessage.setLayoutManager(new LinearLayoutManager(getContext()));
            rlvMessage.setAdapter(messageAdapter);
            messageAdapter.setOnItemClickListener(new OnItemClickListenerRecy() {
                @Override
                public void onClick(int position) {
                    ImMessage message = imList.get(position);
                    processClickMessage(message);
                }

                @Override
                public void onLongClick(int position) {

                }
            });
        }else{
            //更新数据
            messageAdapter.notifyDataSetChanged();
        }
        int unReadMsgCount = 0 ;
        for(ImMessage msg : imList){
            if(msg.getReadType() == ContantsUtil.IM_UNREAD){
                unReadMsgCount ++;
            }
        }
        updateHomeUnReadMsg(unReadMsgCount);
    }
    public void processClickMessage(ImMessage message){
        //1.携带数据跳转到对应页面
        switch (message.getType()){
            case 0:
                //文字信息
            case 1:
                //图片消息
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("message", message);
                getActivity().startActivity(intent);
                break;
            case 2:
                //行程消息
                intent = new Intent(getActivity(), PreviewAcitivity.class);
                intent.putExtra("travelId",message.getTravelId());
                Log.e("messagFragmen",message.getTravelId()+"");
                getActivity().startActivity(intent);
                break;
            default:
                break;
        }
        //2 更新消息的已读状态
        ContanApplication application = (ContanApplication) getActivity().getApplication();
        Integer uid = application.getUid();
        if(uid == null){
            SnackUtils.showSnackShort(snackRoot, "请登录");
        }
        messagePresenter.updateMessage(message.getId(), uid, new IICallBack<Integer>() {
            @Override
            public void getData(Integer response) {
                //3 修改HomeActivity的未读消息总数
                HomeActivity homeActivity = (HomeActivity) getActivity();
                homeActivity.setmTextBadgeItem(response);
            }

            @Override
            public void error(String msg) {
                Toast.makeText(getActivity(),msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateHomeUnReadMsg(Integer count){
        //3 修改HomeActivity的未读消息总数
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setmTextBadgeItem(count);
    }
}
