package bupt.com.travelandroid.Acvivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.Serializable;

import bupt.com.travelandroid.Bean.PlaceBean;
import bupt.com.travelandroid.DesiginView.DetailItemOne;
import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/29 0029.
 */

public class PlaceActivity extends  BaseDetailActivity {
    DetailItemOne dioPlaceName;
    DetailItemOne dioPlayTime;
    PlaceBean placeBean = new PlaceBean();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        initData();
        initView();
    }

    @Override
    public void initView() {
        super.initView();
        dioPlaceName = (DetailItemOne) findViewById(R.id.dio_place_name);
        dioPlayTime = (DetailItemOne) findViewById(R.id.dio_play_time);

        dioPlayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(mContext, AlertDialog.THEME_HOLO_LIGHT,new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (minute < 10){
                            dioPlayTime.setContent(hourOfDay+":"+"0"+minute);
                        }else {
                            dioPlayTime.setContent(hourOfDay+":"+minute);
                        }
                    }
                }, 0, 0, true).show();
            }
        });

        //渲染数据
        if(placeBean != null){
            dioPlayTime.setContent(placeBean.getPlayTime());
            dioPlaceName.setContent(placeBean.getPlaceName());
            if(!TextUtils.isEmpty(placeBean.getImg())){
                Glide.with(mContext).load(placeBean.getImg()).into(ivAdd);
            }
        }
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        if(intent != null){
          PlaceBean bean = (PlaceBean) getIntent().getSerializableExtra("data");
           if(bean != null){
               placeBean = bean;
           }
        }
    }

    @Override
    public Serializable getResultData() {
        if(TextUtils.isEmpty(dioPlaceName.getContent())){
            return null;
        }
        placeBean.setPlaceName(dioPlaceName.getContent());
        placeBean.setPlayTime(dioPlaceName.getContent());
        return placeBean;
    }


    @Override
    protected void uploadError(String msg) {
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void upLoadSuccess(String url) {
        Glide.with(mContext).load(url).into(ivAdd);
        Log.e("placeUri",url);
        placeBean.setImg(url);
    }

    @Override
    protected void clickCancle() {
        placeBean.setImg("");
        Glide.with(mContext).load(R.drawable.add_gray).into(ivAdd);
    }

    @Override
    protected void onResume() {
        Log.e("onResume","into");
        super.onResume();
    }
}
