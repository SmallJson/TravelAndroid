package bupt.com.travelandroid.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.Presenter.RelationPresenter;
import bupt.com.travelandroid.Acvivity.RelationActivity;
import bupt.com.travelandroid.R;
import bupt.com.travelandroid.util.ContanApplication;

/**
 * Created by Administrator on 2018/5/27 0027.
 */

public class MeFragment extends Fragment {

    public  View mView;
    public RelativeLayout rlParent;

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

        rlParent = (RelativeLayout) mView.findViewById(R.id.rl_parent);
        rlParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getActivity().startActivity(new Intent(getActivity(), RelationActivity.class));
            }
        });
    }
}
