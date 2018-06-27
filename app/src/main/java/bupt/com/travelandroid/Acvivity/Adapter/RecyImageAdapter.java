package bupt.com.travelandroid.Acvivity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import bupt.com.travelandroid.Acvivity.CallBack.IRecyPicListener;
import bupt.com.travelandroid.Acvivity.ImageActivity;
import bupt.com.travelandroid.R;

public class RecyImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public Context context;

    List<String> bitmapList;

    public IRecyPicListener listener;

    public RecyImageAdapter(Context context, List<String> list) {
        this.context = context;
        bitmapList = list;
    }

    public void setListener( IRecyPicListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.recy_image_item, parent ,false);

        ViewHold viewHold = new ViewHold(mView);

        return viewHold;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
         ViewHold viewHold  = (ViewHold) holder;
         //从网络加载
         Glide.with(context).load(bitmapList.get(position)).into(viewHold.ivBackground);
         viewHold.ivBackground.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(context, ImageActivity.class);
                 intent.putExtra("imageUrl", bitmapList.get(position));
                 context.startActivity(intent);
             }
         });
    }

    @Override
    public int getItemCount() {
        return bitmapList.size();
    }

    public static class ViewHold extends  RecyclerView.ViewHolder{
        public ImageView ivBackground;
        public ViewHold(View itemView) {
            super(itemView);
            ivBackground = itemView.findViewById(R.id.iv_bg);
        }
    }
}
