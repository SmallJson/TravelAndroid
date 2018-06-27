package bupt.com.travelandroid.Acvivity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Map;

import bupt.com.travelandroid.Bean.TravelBean;
import bupt.com.travelandroid.Bean.TravelDayBean;
import bupt.com.travelandroid.R;

public class RecyPreviewTravelDay extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final int TRAVEL_TOTAL = 1;
    final int TRAVEL_DAY = 2;

    Context mContext ;
    Map<String,TravelDayBean> map;
    TravelBean travelBean;


    public RecyPreviewTravelDay(Context mContext, Map<String, TravelDayBean> map, TravelBean travelBean) {
        this.mContext = mContext;
        this.map = map;
        this.travelBean = travelBean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TRAVEL_DAY){
            View view = LayoutInflater.from(mContext).inflate(R.layout.recy_preview_day, parent,false);
            ViewHolder viewHolde = new ViewHolder(view);
            return viewHolde;
        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.rcv_preview_travel_brief, parent,false);
            RecyPreviewTravelDay.TotalViewHolder viewHolde = new RecyPreviewTravelDay.TotalViewHolder(view);
            return viewHolde;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(position == 0){
            processTravelBrief(holder, position);
        }else {
           processTravelDayViewHolder(holder ,position);
        }
    }

    private   void  processTravelBrief(RecyclerView.ViewHolder holder1, int position){
        RecyPreviewTravelDay.TotalViewHolder holder = (TotalViewHolder) holder1;
        holder.tvTime.setText("出发时间:"+travelBean.getTime());
        holder.tvDestinationTime.setText(travelBean.getDataCount() + "天/" + travelBean.getCity());
        holder.tvName.setText(travelBean.getTravelName());
    }

    private void processTravelDayViewHolder(RecyclerView.ViewHolder holder1 , int position){

        RecyPreviewTravelDay.ViewHolder holder = (RecyPreviewTravelDay.ViewHolder) holder1;
        int day  = position ;
        TravelDayBean travelDayBean = map.get(day+"");
/*        holder.flContent.setVisibility(View.VISIBLE);*/
        if(travelDayBean == null){
            holder.tvDay.setText("第"+day+"天"+"(无行程安排)");
/*            holder.flContent.setVisibility(View.GONE);*/
            holder.llTraffic.setVisibility(View.GONE);
            holder.llHotel.setVisibility(View.GONE);
            holder.llFood.setVisibility(View.GONE);
            holder.llPlacec.setVisibility(View.GONE);
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
                }else{
                    holder.tvFlight.setText(travelDayBean.getTrafficBean().getFlightName());
                    holder.tvStartPlace.setText(travelDayBean.getTrafficBean().getStartPlace());
                    holder.tvEndPlace.setText(travelDayBean.getTrafficBean().getEndPlace());
                }
            }

            //住宿
            if(travelDayBean.getHouseBean() == null){
                holder.llHotel.setVisibility(View.GONE);
            }else{
                holder.llHotel.setVisibility(View.VISIBLE);
                if(TextUtils.isEmpty(travelDayBean.getHouseBean().getHouseName())
                        && TextUtils.isEmpty(travelDayBean.getHouseBean().getHouseAddress())){
                    holder.llHotel.setVisibility(View.GONE);
                }else{
                    holder.tvHotelName.setText(travelDayBean.getHouseBean().getHouseName());
                    holder.tvHotelLocation.setText(travelDayBean.getHouseBean().getHouseAddress());
                    if(!TextUtils.isEmpty(travelDayBean.getHouseBean().getImg())){
                        Glide.with(mContext).load(travelDayBean.getHouseBean().getImg())
                        .into(holder.ivHotelPic);
                    }else{
                        holder.ivHotelPic.setVisibility(View.GONE);
                    }
                }
            }

            //酒店
            if(travelDayBean.getResBean() == null){
                holder.llFood.setVisibility(View.GONE);
            }else{
                holder.llFood.setVisibility(View.VISIBLE);
                if(TextUtils.isEmpty(travelDayBean.getResBean().getResName())
                        && TextUtils.isEmpty(travelDayBean.getResBean().getResAddress())){
                    holder.llFood.setVisibility(View.GONE);
                }else{
                    holder.tvResName.setText(travelDayBean.getResBean().getResName());
                    holder.tvResLocation.setText(travelDayBean.getResBean().getResAddress());
                    if(!TextUtils.isEmpty(travelDayBean.getResBean().getImg())){
                        Glide.with(mContext).load(travelDayBean.getResBean().getImg())
                        .into(holder.ivFoodPic);
                    }else{
                        holder.ivFoodPic.setVisibility(View.GONE);
                    }
                }
            }

            //景点
            if(travelDayBean.getPlaceBean() == null){
                holder.llPlacec.setVisibility(View.GONE);
            }else{
                holder.llPlacec.setVisibility(View.VISIBLE);
                if(TextUtils.isEmpty(travelDayBean.getPlaceBean().getPlaceName())
                        && TextUtils.isEmpty(travelDayBean.getPlaceBean().getPlayTime())){
                    holder.llPlacec.setVisibility(View.GONE);
                }else{
                    holder.tvPlaceName.setText(travelDayBean.getPlaceBean().getPlaceName());
                    if(!TextUtils.isEmpty(travelDayBean.getPlaceBean().getImg())){
                        Glide.with(mContext).load(travelDayBean.getPlaceBean().getImg())
                                .into(holder.ivPlaceBg);
                    }else{
                        holder.ivPlaceBg.setVisibility(View.GONE);
                    }
                }
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
            if(position == 0){
                return TRAVEL_TOTAL;
            }else {
                return  TRAVEL_DAY;
            }
    }

    @Override
    public int getItemCount() {
        return map.size() + 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        //旅行天数
        TextView tvDay;
     /*   //每个行程记录的总布局
        LinearLayout flContent;*/
        //交通内容
        public LinearLayout llTraffic;
        public TextView tvFlight;
        public TextView tvStartPlace;
        public TextView tvEndPlace;
        public ImageView ivTraffic;

        //景点内容
        public LinearLayout llPlacec;
        public TextView tvPlaceName;
        public ImageView ivPlaceBg;

        //餐馆内容
        public LinearLayout llFood;
        public TextView tvResName;
        public TextView tvResLocation;
        public ImageView ivFoodPic;

        //酒店内容
        public LinearLayout llHotel;
        public TextView tvHotelName;
        public ImageView ivHotelPic;
        public TextView tvHotelLocation;

        public ViewHolder(View itemView) {
            super(itemView);
            //天数
            tvDay = itemView.findViewById(R.id.tv_day);
      /*      flContent = itemView.findViewById(R.id.fl_content);*/

            //交通信息
            llTraffic = itemView.findViewById(R.id.ll_traffic_detail);
            tvFlight = itemView.findViewById(R.id.tv_flight);
            tvStartPlace = itemView.findViewById(R.id.tv_start_place);
            tvEndPlace = itemView.findViewById(R.id.tv_end_place);


            //景点内容
            llPlacec = itemView.findViewById(R.id.ll_place_detail);
            tvPlaceName = itemView.findViewById(R.id.tv_place_name);
            ivPlaceBg = itemView.findViewById(R.id.iv_place_bg);

            //餐馆内容
             llFood = itemView.findViewById(R.id.ll_food_detail);
             tvResName = itemView.findViewById(R.id.tv_res_name);
             tvResLocation = itemView.findViewById(R.id.tv_res_location);
             ivFoodPic  = itemView.findViewById(R.id.iv_food_picture);

             //酒店内容
             llHotel = itemView.findViewById(R.id.ll_hotel_details);
             tvHotelName = itemView.findViewById(R.id.tv_hotel_name);
             ivHotelPic =  itemView.findViewById(R.id.iv_hotel_picture);
             tvHotelLocation = itemView.findViewById(R.id.iv_hotel_location);
        }
    }



    public  static class TotalViewHolder extends  RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvDestinationTime;
        TextView tvTime;

        public TotalViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_travel_title);
            tvDestinationTime = itemView.findViewById(R.id.tv_destination_time);
            tvTime = itemView.findViewById(R.id.tv_travel_time);
        }
    }
}
