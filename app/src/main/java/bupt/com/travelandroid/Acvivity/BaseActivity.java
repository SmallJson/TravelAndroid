package bupt.com.travelandroid.Acvivity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import bupt.com.travelandroid.R;

/**
 * Created by tuyin on 2018/5/18 0018.
 */

public abstract class  BaseActivity extends AppCompatActivity {

    protected Activity mContext = this;
    public Toolbar mToolBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置软键盘不影响布局
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //设置Activity只能是竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //修改状态栏字体和图标颜色，只能适配Android6.0以上系统
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        //activity去掉默认标题栏
       // getSupportActionBar().hide();
        //全屏显示
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public   void initView(){
        mToolBar = (Toolbar) findViewById(R.id.tl_bar);
        if(mToolBar != null){
            setSupportActionBar(mToolBar);
        }
    }
    public abstract  void initData();
}
