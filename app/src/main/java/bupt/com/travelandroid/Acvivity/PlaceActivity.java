package bupt.com.travelandroid.Acvivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TimePicker;

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
    PlaceBean placeBean;
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
        }
    }

    @Override
    public void initData() {
        placeBean = (PlaceBean) getIntent().getSerializableExtra("data");
    }

    @Override
    public Serializable getResultData() {
        PlaceBean placeBean = new PlaceBean();
        if(TextUtils.isEmpty(dioPlaceName.getContent())){
            return null;
        }
        placeBean.setPlaceName(dioPlaceName.getContent());
        placeBean.setPlayTime(dioPlaceName.getContent());
        return placeBean;
    }


}
