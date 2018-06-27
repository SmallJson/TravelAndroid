package bupt.com.travelandroid.Acvivity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import bupt.com.travelandroid.Bean.response.PhotoInfo;
import bupt.com.travelandroid.R;

public class RecyPhoneAdapter extends RecyclerView.Adapter<RecyPhoneAdapter.ViewHold> {

    public Context context;
    public List<PhotoInfo> list;
    RecyImageAdapter imageAdapter;
    public RecyPhoneAdapter(Context context, List<PhotoInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.recy_phone_item, parent, false);
        ViewHold viewHold = new ViewHold(mView);
        return viewHold;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHold holder, int position) {
        PhotoInfo photoInfo = list.get(position);
        if(photoInfo != null){
            //1.渲染头像
            Glide.with(context).load(photoInfo.getUserInfo().getAvator()).into(holder.ivHeader);
            //2.渲染名字
            holder.tvName.setText(photoInfo.getUserInfo().getName());
            //3.渲染地点
            holder.tvPlaceName.setText(photoInfo.getTime()+"游览了"+photoInfo.getPlace().getPlaceName());
            //4.渲染照片
            if(photoInfo.getPlace().getImageUrl()!=null){
                imageAdapter = new RecyImageAdapter(context, photoInfo.getPlace().getImageUrl());
                GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
                holder.recyImage.setLayoutManager(layoutManager);
                holder.recyImage.setAdapter(imageAdapter);
            }else {
                holder.recyImage.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHold extends RecyclerView.ViewHolder {

        public ImageView ivHeader;
        public RecyclerView recyImage;
        public TextView tvName;
        public TextView tvPlaceName;

        public ViewHold(View itemView) {
            super(itemView);
            ivHeader = itemView.findViewById(R.id.iv_header);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPlaceName = itemView.findViewById(R.id.tv_place_name);
            recyImage = itemView.findViewById(R.id.recy_phone);
        }
    }
}
