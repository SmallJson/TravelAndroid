package bupt.com.travelandroid.Acvivity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.io.Serializable;

import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/29 0029.
 */

public class NoteActivity extends  BaseDetailActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        initData();
        initView();
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

    @Override
    protected void uploadError(String msg) {

    }

    @Override
    protected void upLoadSuccess(String url) {

    }

    @Override
    protected void clickCancle() {

    }
}
