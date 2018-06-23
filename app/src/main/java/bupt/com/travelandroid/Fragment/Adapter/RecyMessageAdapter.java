package bupt.com.travelandroid.Fragment.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bupt.com.travelandroid.Acvivity.CallBack.OnItemClickListenerRecy;
import bupt.com.travelandroid.Bean.IM.ImMessage;
import bupt.com.travelandroid.Bean.TravelTotalBean;
import bupt.com.travelandroid.R;
import bupt.com.travelandroid.util.ContanApplication;
import bupt.com.travelandroid.util.ContantsUtil;

/**
 * Created by Administrator on 2018/5/28 0028.
 */

public class RecyMessageAdapter extends RecyclerView.Adapter<RecyMessageAdapter.ViewHolder> {

    public List<ImMessage> messageList;
    public Context context;
    public OnItemClickListenerRecy listener = null;
    public RecyMessageAdapter(List<ImMessage> messageList, Context context) {
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
    public void onBindViewHolder(final ViewHolder holder, final  int position) {
        //此处展示不做任何处理,直接直接显示默认的布局文件
        ImMessage message = messageList.get(position);
        //渲染视图
        //1.设置行程名称
        holder.tvDes.setText(message.getText());
        //2.设置行程时间
        holder.tvTime.setText(message.getCreatTime());
        //3.设置分享人昵称
        holder.tvTitle.setText(message.getFromName());
        //4.加载分享人头像
        Glide.with(context).load(message.getFromAvator()).into(holder.ivLeft);

        //5.设置已读表示位
        if(message.getReadType() == ContantsUtil.IM_UNREAD){
            holder.FlUnReadMsg.setVisibility(View.VISIBLE);
        }else if(message.getReadType() == ContantsUtil.IM_READ){
            holder.FlUnReadMsg.setVisibility(View.GONE);
        }

        //6.设置监听事件
        if( listener!= null){

            holder.itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(position);
                    if(messageList.get(position).getReadType() == ContantsUtil.IM_UNREAD){
                        Log.e("messageAdapter","隐藏并修改已读标识");
                        //隐藏未读标识，并且修改已读标识
                        holder.FlUnReadMsg.setVisibility(View.GONE);
                        messageList.get(position).setReadType(ContantsUtil.IM_READ);
                    }
                }
            });

            holder. itemView.setOnLongClickListener( new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    listener.onLongClick(position);
                    return false;
                }
            });
        }
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
        public FrameLayout FlUnReadMsg;
        public ViewHolder(View itemView) {
            super(itemView);
            this.ivLeft = (ImageView) itemView.findViewById(R.id.iv_icon_left);
            this.tvTitle = (TextView) itemView.findViewById(R.id.tv_message_title);
            this.tvDes = (TextView) itemView.findViewById(R.id.tv_message_dsc);
            this.tvTime = (TextView) itemView.findViewById(R.id.tv_message_time);
            this.FlUnReadMsg = itemView.findViewById(R.id.fl_unread_msg);
        }
    }

    public void setOnItemClickListener(OnItemClickListenerRecy onItemClickListener ){
        this.listener = onItemClickListener;
    }
}
