package bupt.com.travelandroid.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/27 0027.
 */

public class MeFragment extends Fragment {

    public  View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*return super.onCreateView(inflater, container, savedInstanceState);*/
        View mView  = inflater.inflate(R.layout.fragment_me,container,false);
        return mView;
    }
}
