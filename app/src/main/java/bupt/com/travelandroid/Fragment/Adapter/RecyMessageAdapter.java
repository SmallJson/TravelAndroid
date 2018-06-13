package bupt.com.travelandroid.Fragment.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import bupt.com.travelandroid.Bean.MessageBean;
import bupt.com.travelandroid.Bean.TravelTotalBean;
import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/28 0028.
 */

public class RecyMessageAdapter extends RecyclerView.Adapter<RecyMessageAdapter.ViewHolder> {

    public List<TravelTotalBean> messageList;
    public Context context;

    public RecyMessageAdapter(List<TravelTotalBean> messageList, Context context) {
        this.messageList = messageList;
        this.context = context;
    }

    //此处可以考虑实现RecyclerView的多Type布局
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recy_message_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //此处展示不做任何处理,直接直接显示默认的布局文件
        TravelTotalBean travelTotalBean = messageList.get(position);
        //渲染视图
        //1.设置行程名称
        holder.tvDes.setText("分享行程:"+travelTotalBean.getTravelBean().travelName);
        //2.设置行程时间
        holder.tvTime.setText(travelTotalBean.getTravelBean().getCreatTime());
        //3.设置分享人昵称
        holder.tvTitle.setText(travelTotalBean.getFromName());
        //4.加载分享人头像
        Glide.with(context).load(travelTotalBean.fromAvator).into(holder.ivLeft);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class  ViewHolder extends  RecyclerView.ViewHolder{
        public ImageView ivLeft;
        public TextView tvTitle;
        public TextView tvDes;
        public TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            this.ivLeft = (ImageView) itemView.findViewById(R.id.iv_icon_left);
            this.tvTitle = (TextView) itemView.findViewById(R.id.tv_message_title);
            this.tvDes = (TextView) itemView.findViewById(R.id.tv_message_dsc);
            this.tvTime = (TextView) itemView.findViewById(R.id.tv_message_time);
        }
    }
}
