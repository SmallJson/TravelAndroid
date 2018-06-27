package bupt.com.travelandroid.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.HomeActivity;
import bupt.com.travelandroid.Acvivity.MainActivity;
import bupt.com.travelandroid.Acvivity.PhoneActivity;
import bupt.com.travelandroid.Acvivity.Presenter.RelationPresenter;
import bupt.com.travelandroid.Acvivity.RelationActivity;
import bupt.com.travelandroid.Bean.User;
import bupt.com.travelandroid.R;
import bupt.com.travelandroid.util.ContanApplication;

/**
 * Created by Administrator on 2018/5/27 0027.
 */

public class MeFragment extends Fragment {

    public  View mView;
    public RelativeLayout rlParent;
    public TextView tvName;
    public TextView tvPhone;
    public ImageView ivHeader;

    public TextView tvUnlogin;

    public RelativeLayout rlPhone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*return super.onCreateView(inflater, container, savedInstanceState);*/
        mView  = inflater.inflate(R.layout.fragment_me,container,false);
        initView();
        initData();
        return mView;
    }
    public void initData(){
    }

    public void initView(){

        tvUnlogin = mView.findViewById(R.id.tv_unlogin);
        tvUnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空登录信息
                ContanApplication app = (ContanApplication) getActivity().getApplication();
                app.setUid(null);
                app.setUser(null);
                //跳转页面
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            }
        });

        rlParent = (RelativeLayout) mView.findViewById(R.id.rl_parent);
        rlParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getActivity().startActivity(new Intent(getActivity(), RelationActivity.class));
            }
        });

        rlPhone  = mView.findViewById(R.id.rl_phone);
        rlPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), PhoneActivity.class));
            }
        });

        tvName = mView.findViewById(R.id.tv_name);
        tvPhone = mView.findViewById(R.id.tv_phone);
        ivHeader = mView.findViewById(R.id.iv_header);
        ContanApplication application = (ContanApplication) getActivity().getApplication();
        User user  = application.getUser();
        if(user != null){
            //显示用户名称
            tvName.setText(user.getInfo().getName());
            //显示手机号码
            tvPhone.setText("手机号:"+user.getAccount());
            //加载用户头像
            Glide.with(getActivity()).load(user.getInfo().getAvator()).into(ivHeader);
            tvUnlogin.setText("注销登录");
        }else{
            //显示用户名称
            tvName.setText("未登录");
            //显示手机号码
            tvPhone.setText("手机号:"+"无");
            //加载用户头像
            Glide.with(getActivity()).load(R.drawable.header).into(ivHeader);
            tvUnlogin.setText("登录");
        }

    }
}
