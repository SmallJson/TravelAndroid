package bupt.com.travelandroid.Acvivity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.TimedText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bupt.com.travelandroid.Acvivity.CallBack.OnItemClickListenerRecy;
import bupt.com.travelandroid.Acvivity.PreviewAcitivity;
import bupt.com.travelandroid.Bean.TravelBean;
import bupt.com.travelandroid.Bean.TravelTotalBean;
import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/6/12 0012.
 */

public class RecyTravelAdapter  extends RecyclerView.Adapter<RecyTravelAdapter.ViewHolder>{

    public Context context;
    public List<TravelTotalBean> list;

    public OnItemClickListenerRecy listener ;

    public void setOnItenLickListener(OnItemClickListenerRecy listener){

        this.listener = listener;
    }

    public RecyTravelAdapter(Context context, List<TravelTotalBean> list){
            this.context = context;
            this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recy_travel_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        TravelTotalBean travelTotalBean = list.get(position);
        TravelBean travelBean = travelTotalBean.getTravelBean();
        if(travelBean !=null){
            holder.tvName.setText(travelBean.travelName);
            holder.tvTime.setText(travelBean.dataCount+"天/"+travelBean.time);
            //加载网络图片,默认从本地加载图片
            holder.ivBackground.setBackgroundResource(R.drawable.gugong);
        }

       /* //点击进入预览页面
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
*/

        //点击进入预览页面
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null )
                    listener.onClick(position);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(listener != null)
                    listener.onLongClick(position);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class ViewHolder extends  RecyclerView.ViewHolder{
        public ImageView ivBackground;
        public TextView tvName;
        public TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            this.ivBackground = itemView.findViewById(R.id.iv_background);
            this.tvName = itemView.findViewById(R.id.tv_travel_name);
            this.tvTime = itemView.findViewById(R.id.tv_time);
        }
    }
}
