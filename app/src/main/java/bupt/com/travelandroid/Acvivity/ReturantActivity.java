package bupt.com.travelandroid.Acvivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import java.io.Serializable;

import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/29 0029.
 */

public class ReturantActivity extends  BaseDetailActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returant);
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