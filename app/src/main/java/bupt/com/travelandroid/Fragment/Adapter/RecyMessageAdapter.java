package bupt.com.travelandroid.Fragment.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import bupt.com.travelandroid.Bean.MessageBean;
import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/28 0028.
 */

public class RecyMessageAdapter extends RecyclerView.Adapter<RecyMessageAdapter.ViewHolder> {

    public ArrayList<MessageBean> messageList;
    public Context context;

    public RecyMessageAdapter(ArrayList<MessageBean> messageList, Context context) {
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
    }

    @Override
    public int getItemCount() {
        return 12;
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
