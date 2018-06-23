package bupt.com.travelandroid.Acvivity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Map;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.Presenter.MessagePresenter;
import bupt.com.travelandroid.Acvivity.Presenter.TravelPresenter;
import bupt.com.travelandroid.Bean.TravelBean;
import bupt.com.travelandroid.Bean.TravelDayBean;
import bupt.com.travelandroid.R;
import bupt.com.travelandroid.util.ContantsUtil;
import bupt.com.travelandroid.util.TimeUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RecyCheckTravelDay extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context mContext ;
    Map<String,TravelDayBean> map;
    TravelBean travelBean;

    TravelPresenter travelPresenter;
    MessagePresenter messagePresenter;
    //发送消息的id
    Integer uid;
    //接收消息账号
    String destPhone;

    public RecyCheckTravelDay(Context mContext, Map<String, TravelDayBean> map, Integer fromUid, String destPhone) {
        this.mContext = mContext;
        this.map = map;
    /*    this.travelBean = travelBean;*/
        this.uid = fromUid;
        this.destPhone =destPhone;
        travelPresenter = new TravelPresenter(mContext);
        messagePresenter = new MessagePresenter(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.recy_check_item, parent,false);
            ViewHolder viewHolde = new ViewHolder(view);
            return viewHolde;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

           processTravelDayViewHolder(holder ,position);
    }


    private void processTravelDayViewHolder(RecyclerView.ViewHolder holder1 , int position){

        final RecyCheckTravelDay.ViewHolder holder = (ViewHolder) holder1;
        int day  = position + 1 ;
        final TravelDayBean travelDayBean = map.get(day+"");

        if(travelDayBean == null){
            holder.tvDay.setText("第"+day+"天"+"(无行程安排)");
            holder.llTraffic.setVisibility(View.GONE);
            holder.llHotel.setVisibility(View.GONE);
            holder.llPlacec.setVisibility(View.GONE);
            holder.llFood.setVisibility(View.GONE);
        }else {
            holder.tvDay.setText("第"+day+"天");
            //渲染交通数据
            if(travelDayBean.getTrafficBean() == null){
                holder.llTraffic.setVisibility(View.GONE);
            }else{
                holder.llTraffic.setVisibility(View.VISIBLE);
                if(TextUtils.isEmpty(travelDayBean.getTrafficBean().getFlightName())
                        && TextUtils.isEmpty(travelDayBean.getTrafficBean().getStartPlace())
                        && TextUtils.isEmpty(travelDayBean.getTrafficBean().getEndPlace())
                        && TextUtils.isEmpty(travelDayBean.getTrafficBean().getStartTime())){
                    holder.llTraffic.setVisibility(View.GONE);
                }

                holder.tvFlight.setText(travelDayBean.getTrafficBean().getFlightName());
                holder.tvStartPlace.setText(travelDayBean.getTrafficBean().getStartPlace());
                holder.tvEndPlace.setText(travelDayBean.getTrafficBean().getEndPlace());

                if(travelDayBean.getTrafficBean().getComplete() == ContantsUtil.COMPLETE){
                    holder.ivTrafficStatus.setBackground(mContext.getResources().getDrawable(R.drawable.complete));
                }else if(travelDayBean.getTrafficBean().getComplete() == ContantsUtil.UNCOMPLETE){
                    holder.ivTrafficStatus.setBackground(mContext.getResources().getDrawable(R.drawable.success));
                }
                //设置状态标识的点击事件
                holder.ivTrafficStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int complete = travelDayBean.getTrafficBean().getComplete();
                        if(complete != ContantsUtil.COMPLETE){
                            processImageClick(travelDayBean,holder.ivTrafficStatus, travelDayBean.getTrafficBean().getId(), ContantsUtil.TRAFFIC);
                        }
                    }
                });
            }

            //住宿
            if(travelDayBean.getHouseBean() == null){
                holder.llHotel.setVisibility(View.GONE);
            }else{
                holder.llHotel.setVisibility(View.VISIBLE);

                if(TextUtils.isEmpty(travelDayBean.getHouseBean().getHouseName())
                        && TextUtils.isEmpty(travelDayBean.getHouseBean().getHouseAddress())){
                    holder.llHotel.setVisibility(View.GONE);
                }

                holder.tvHotelName.setText(travelDayBean.getHouseBean().getHouseName());

                if(travelDayBean.getHouseBean().getComplete() == ContantsUtil.COMPLETE){
                    holder.ivHouseStatus.setBackground(mContext.getResources().getDrawable(R.drawable.complete));
                }else if(travelDayBean.getHouseBean().getComplete() == ContantsUtil.UNCOMPLETE){
                    holder.ivHouseStatus.setBackground(mContext.getResources().getDrawable(R.drawable.success));
                }

                //设置状态标识的点击事件
                holder.ivHouseStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int complete = travelDayBean.getHouseBean().getComplete();
                        if(complete != ContantsUtil.COMPLETE){
                            processImageClick(travelDayBean,holder.ivHouseStatus, travelDayBean.getHouseBean().getId(), ContantsUtil.HOUSE);
                        }
                    }
                });
            }

            //餐馆
            if(travelDayBean.getResBean() == null){
                holder.llFood.setVisibility(View.GONE);
            }else{
                holder.llFood.setVisibility(View.VISIBLE);

                if(TextUtils.isEmpty(travelDayBean.getResBean().getResName())
                        && TextUtils.isEmpty(travelDayBean.getResBean().getResAddress())){
                    holder.llFood.setVisibility(View.GONE);
                }

                holder.tvResName.setText(travelDayBean.getResBean().getResName());

                if(travelDayBean.getResBean().getComplete() == ContantsUtil.COMPLETE){
                    holder.ivResStatus.setBackground(mContext.getResources().getDrawable(R.drawable.complete));
                }else if(travelDayBean.getResBean().getComplete() == ContantsUtil.UNCOMPLETE){
                    holder.ivResStatus.setBackground(mContext.getResources().getDrawable(R.drawable.success));
                }
                //设置状态标识的点击事件
                holder.ivResStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int complete = travelDayBean.getResBean().getComplete();
                        if(complete != ContantsUtil.COMPLETE){
                            processImageClick(travelDayBean,holder.ivResStatus, travelDayBean.getResBean().getId(), ContantsUtil.RES);
                        }
                    }
                });
            }

            //景点
            if(travelDayBean.getPlaceBean() == null){
                holder.llPlacec.setVisibility(View.GONE);
            }else{
                holder.llPlacec.setVisibility(View.VISIBLE);

                if(TextUtils.isEmpty(travelDayBean.getPlaceBean().getPlaceName())
                        && TextUtils.isEmpty(travelDayBean.getPlaceBean().getPlayTime())){
                    holder.llPlacec.setVisibility(View.GONE);
                }

                holder.tvPlaceName.setText(travelDayBean.getPlaceBean().getPlaceName());

                if(travelDayBean.getPlaceBean().getComplete() == ContantsUtil.COMPLETE){
                    holder.ivPlaceStatus.setBackground(mContext.getResources().getDrawable(R.drawable.complete));
                }else if(travelDayBean.getPlaceBean().getComplete() == ContantsUtil.UNCOMPLETE){
                    holder.ivPlaceStatus.setBackground(mContext.getResources().getDrawable(R.drawable.success));
                }

                //设置状态标识的点击事件
                holder.ivPlaceStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int complete = travelDayBean.getPlaceBean().getComplete();
                        if(complete != ContantsUtil.COMPLETE){
                            processImageClick(travelDayBean,holder.ivPlaceStatus, travelDayBean.getPlaceBean().getId(), ContantsUtil.PLACE);
                        }
                    }
                });
            }
        }
    }


    @Override
    public int getItemCount() {
        return map.size() ;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        //旅行天数
        TextView tvDay;

        //交通内容
        public LinearLayout llTraffic;
        public TextView tvFlight;
        public TextView tvStartPlace;
        public TextView tvEndPlace;
        public ImageView ivTrafficStatus;

        //景点内容
        public LinearLayout llPlacec;
        public TextView tvPlaceName;
        public ImageView ivPlaceStatus;

        //餐馆内容
        public LinearLayout llFood;
        public TextView tvResName;
        public ImageView ivResStatus;

        //酒店内容
        public LinearLayout llHotel;
        public TextView tvHotelName;
        ImageView ivHouseStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            //天数
            tvDay = itemView.findViewById(R.id.tv_data);

            //交通信息
            llTraffic = itemView.findViewById(R.id.ll_traffic);
            tvFlight = itemView.findViewById(R.id.tv_flight);
            tvStartPlace = itemView.findViewById(R.id.tv_start_place);
            tvEndPlace = itemView.findViewById(R.id.tv_end_place);
            ivTrafficStatus = itemView.findViewById(R.id.traffic_status);

            //景点内容
            llPlacec = itemView.findViewById(R.id.ll_place);
            tvPlaceName = itemView.findViewById(R.id.tv_place_name);
            ivPlaceStatus = itemView.findViewById(R.id.iv_place_status);

            //餐馆内容
             llFood = itemView.findViewById(R.id.ll_food);
             tvResName = itemView.findViewById(R.id.tv_res_name);
             ivResStatus = itemView.findViewById(R.id.iv_foot_status);

             //酒店内容
             llHotel = itemView.findViewById(R.id.ll_hotel);
             tvHotelName = itemView.findViewById(R.id.tv_hotel_name);
             ivHouseStatus = itemView.findViewById(R.id.iv_hotel_status);
        }
    }

    public  void processImageClick(final TravelDayBean dayBean , final ImageView imageView, Integer id, final Integer type){
        if(travelPresenter != null){
            travelPresenter.updateTravelDetail(id, type, new IICallBack<String>() {
                @Override
                public void getData(String response) {
                    //修改本地状态
                    String content = "";
                    String name = "";
                    switch (type){
                        case ContantsUtil.HOUSE:
                            dayBean.getHouseBean().setComplete(ContantsUtil.COMPLETE);
                            name = dayBean.getHouseBean().getHouseName();
                            break;
                        case ContantsUtil.TRAFFIC:
                            dayBean.getTrafficBean().setComplete(ContantsUtil.COMPLETE);
                            name = dayBean.getTrafficBean().getFlightName();
                            break;
                        case ContantsUtil.RES:
                            dayBean.getResBean().setComplete(ContantsUtil.COMPLETE);
                            name = dayBean.getResBean().getResName();
                            break;
                        case ContantsUtil.PLACE:
                            dayBean.getPlaceBean().setComplete(ContantsUtil.COMPLETE);
                            name =dayBean.getPlaceBean().getPlaceName();
                            break;
                        default:
                            break;
                    }
                    //修改背景
                    imageView.setBackground(mContext.getResources().getDrawable(R.drawable.complete));
                    //发送消息逻辑展示为写。
                    if(TextUtils.isEmpty(name)){
                        name = "(缺失行程名)";
                    }
                    content = "完成了"+name+"的游览";
                    sendTextMessage(content);
                }

                @Override
                public void error(String msg) {
                    Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public  void  sendTextMessage(String content){
        //uid
        RequestBody fromUid = RequestBody.create(null, uid+"");
        //目标电话
        RequestBody dest = RequestBody.create(null, destPhone);
        //图片消息的type =1
        RequestBody type = RequestBody.create(null, ContantsUtil.TEXT_MSG+"");
        //关于图片的文字描述
        RequestBody text = RequestBody.create(null, content);

        messagePresenter.setMessage(fromUid, dest, type,text, null,new IICallBack<String>(){
            @Override
            public void getData(String response) {
                Log.e("server_sucess",response);
                Toast.makeText(mContext, "你的行程已经通知对方了哦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void error(String msg) {
                Log.e("server_error",msg);
                Toast.makeText(mContext, "通知失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
