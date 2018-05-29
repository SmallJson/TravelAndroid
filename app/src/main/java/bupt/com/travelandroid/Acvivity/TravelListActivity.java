package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import bupt.com.travelandroid.Bean.TravelBean;
import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/28 0028.
 */

public class TravelListActivity extends BaseActivity {
    TravelBean travelBean;
    LinearLayout llContent;
    TextView tvTravelName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_list);
        initData();
        initView();
    }

    @Override
    public void initView() {
        super.initView();
        llContent = (LinearLayout) findViewById(R.id.ll_content);
        tvTravelName = (TextView) findViewById(R.id.tv_travel_name);

        if(!TextUtils.isEmpty(travelBean.travelName)){
            tvTravelName.setText(travelBean.travelName);
        }else{
            tvTravelName.setText("默认");
        }

        //为空的情况下，默认显示六天
        travelBean.dataCount = TextUtils.isEmpty(travelBean.dataCount)?"6":travelBean.dataCount;

        if(travelBean.dataCount != null){
            int count = Integer.parseInt(travelBean.dataCount);
            for(int i = 0 ;i< count;i++){
                View view = LayoutInflater.from(mContext).inflate(R.layout.day_item,llContent,false);
                TextView tvdata = (TextView) view.findViewById(R.id.tv_data);
                final int day = i + 1;
                tvdata.setText("第"+day+"天");
/*               LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
                view.setLayoutParams(params);*/
                llContent.addView(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent  intent = new Intent(mContext,DetailTravelActivity.class);
                        intent.putExtra("day",day);
                        mContext.startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public void initData() {
        travelBean =  (TravelBean) getIntent().getSerializableExtra("travel");
    }
}
