package bupt.com.travelandroid.Acvivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import bupt.com.travelandroid.Bean.TravelBean;
import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/28 0028.
 */

public class MakingTraveActivity extends BaseActivity{

    Button btConfirm;
    EditText etTravelName;
    EditText etTravelCity;
    EditText etTravelDataCount;

    EditText etEdTime;

    //记录点击时间选择器时候的时间
    int year, month, day;
    //日期选择器
    DatePickerDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maketravel);
        initView();
        initData();
    }

    @Override
    public void initView() {
        super.initView();

        btConfirm = (Button) findViewById(R.id.bt_confirm);
        etTravelName= (EditText) findViewById(R.id.et_name);
        etTravelCity = (EditText) findViewById(R.id.et_city);
        etTravelDataCount = (EditText) findViewById(R.id.et_data);
        //设置输入是数字
        etTravelDataCount.setInputType(InputType.TYPE_CLASS_NUMBER);
        etEdTime = (EditText) findViewById(R.id.et_time);

        //设置输入框是时间
        etEdTime.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
        //输入框静止开启软键盘
        etEdTime.setInputType(InputType.TYPE_NULL);
        //禁止输入框输入
        etEdTime.setFocusable(false);
        //点击弹出时间选择框按钮
        etEdTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 getCurrentTime();
                 showDataDialog();
            }
        });

        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelBean travelBean = new TravelBean();
                if(TextUtils.isEmpty(etTravelName.getText().toString())
                   ||TextUtils.isEmpty(etTravelCity.getText().toString())
                   ||TextUtils.isEmpty(etTravelDataCount.getText().toString())
                   ||TextUtils.isEmpty(etEdTime.getText().toString())){
                    //弹出消息提示框
                    Snackbar.make(findViewById(R.id.ll_root),"信息不能为空",Snackbar.LENGTH_SHORT).show();
                    return ;
                }
                    //赋值
                    travelBean.travelName = etTravelName.getText().toString();
                    travelBean.city = etTravelCity.getText().toString();
                    travelBean.dataCount = etTravelDataCount.getText().toString();
                    travelBean.time= etEdTime.getText().toString();

                    //跳转
                    Intent intent = new Intent(mContext,TravelListActivity.class);
                    intent.putExtra("travel",travelBean);
                    startActivity(intent);
                    finish();
            }
        });
    }

    @Override
    public void initData() {

    }
    public void getCurrentTime(){
        Calendar cal =Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒
        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
    }
    public void showDataDialog(){
        dialog = new DatePickerDialog(mContext, 0, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1 ;
                etEdTime.setText(year + "-" + month + "-" + day);
            }
        },year,month-1,day);
        dialog.show();
    }
}
