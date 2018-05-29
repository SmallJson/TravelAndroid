package bupt.com.travelandroid.Acvivity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.io.Serializable;

import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/29 0029.
 */

public class PlaceActivity extends  BaseDetailActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        
    }

    @Override
    public Serializable getResultData() {
        return null;
    }
}
