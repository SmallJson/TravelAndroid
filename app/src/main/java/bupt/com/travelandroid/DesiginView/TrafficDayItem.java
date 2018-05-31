package bupt.com.travelandroid.DesiginView;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/29 0029.
 * 交通自定义展现框
 */

public class TrafficDayItem extends LinearLayout {
    TextView tvStartPlace;
    TextView tvEndPlace;
    TextView tvFlight;
    TextView tvStartTime;
    String   startPlace;
    String   endPlace;
    String   flightName;
    String   startTime;
    View mView;
    public TrafficDayItem(Context context) {
        this(context,null);
    }

    public TrafficDayItem(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TrafficDayItem(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }
    public TrafficDayItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }


    private void initView(Context context){
        mView = LayoutInflater.from(context).inflate(R.layout.traffic_day,this,false);
        addView(mView);
        tvFlight = (TextView) mView.findViewById(R.id.tv_flight);
        tvStartPlace = (TextView) mView.findViewById(R.id.tv_start_place);
        tvEndPlace = (TextView) mView.findViewById(R.id.tv_end_place);
        tvStartTime = (TextView) mView.findViewById(R.id.tv_start_time);
    }

    public void setStartPlace(String startPlace) {
        if(!TextUtils.isEmpty(startPlace)){
            tvStartPlace.setText(startPlace);
            this.startPlace = startPlace;
        }
    }

    public void setEndPlace(String endPlace) {
        if(!TextUtils.isEmpty(endPlace)){
            this.endPlace = endPlace;
            tvEndPlace.setText(endPlace);
        }
    }

    public void setFlightName(String flightName) {
        if(!TextUtils.isEmpty(flightName)){
            this.flightName = flightName;
            tvFlight.setText(flightName);
        }
    }

    public void setStartTime(String startTime) {
        if(!TextUtils.isEmpty(startTime)){
            this.startTime = startTime;
            tvStartTime.setText(startTime);
        }
    }


    public String getStartTime() {
        return startTime;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public String getFlightName() {
        return flightName;
    }
}
