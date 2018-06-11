package bupt.com.travelandroid.Acvivity.Adapter;

import android.app.Activity;
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

import bupt.com.travelandroid.Bean.response.RelationInfo;
import bupt.com.travelandroid.R;

public class RecyRelationAdapter extends RecyclerView.Adapter<RecyRelationAdapter.ViewHolder> {

    public List<RelationInfo> relationList;
    public Context context;

    public RecyRelationAdapter(Context context, List<RelationInfo> relationInfoList) {
        this.relationList = relationInfoList;
        this.context = context;
    }

    //此处可以考虑实现RecyclerView的多Type布局
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_friend_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //此处展示不做任何处理,直接显示默认的布局文件
        //依次渲染数据
        RelationInfo relationInfo = relationList.get(position);
        holder.tvName.setText(relationInfo.getName());
        holder.tvPhone.setText(relationInfo.getPhone());
        switch (relationInfo.getRelationType()){
            case 1:
                holder.tvRelation.setText("我的父亲");
                break;
            case 2:
                holder.tvRelation.setText("我的母亲");
                break;
            case 3:
                holder.tvRelation.setText("我的子女");
                break;
            case 4:
                holder.tvRelation.setText("我的子女");
                break;
            default:
                break;
        }
         //加载网络图片
        //Glide.with(context).load(relationInfo.avator).into(holder.ivHeader);
    }

    @Override
    public int getItemCount() {
        return relationList.size();
    }

    public static class  ViewHolder extends  RecyclerView.ViewHolder{
        public TextView tvRelation;
        public TextView tvName;
        public TextView tvPhone;
        public ImageView ivHeader;

        public ViewHolder(View itemView) {
            super(itemView);
            this.ivHeader = (ImageView) itemView.findViewById(R.id.tv_header);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
            this.tvPhone = (TextView) itemView.findViewById(R.id.tv_phone);
            this.tvRelation = (TextView) itemView.findViewById(R.id.tv_relation_name);
        }
    }
}