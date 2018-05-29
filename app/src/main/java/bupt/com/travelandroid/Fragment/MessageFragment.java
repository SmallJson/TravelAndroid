package bupt.com.travelandroid.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bupt.com.travelandroid.Fragment.Adapter.RecyMessageAdapter;
import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/27 0027.
 */

public class MessageFragment extends Fragment {

    public  View mView;
    RecyclerView rlvMessage;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*return super.onCreateView(inflater, container, savedInstanceState);*/
        mView  = inflater.inflate(R.layout.fragment_message,container,false);
        initView();
        return mView;
    }

    public void initView(){
        rlvMessage = (RecyclerView) mView.findViewById(R.id.rlv_message);
        rlvMessage.setLayoutManager(new LinearLayoutManager(getActivity()));
        rlvMessage.setAdapter(new RecyMessageAdapter(null,getActivity()));
    }
}
