package bupt.com.travelandroid.Acvivity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.List;

import bupt.com.travelandroid.Bean.response.RelationInfo;

public class RecySmallRelationAdater  extends RecyclerView.Adapter<RecySmallRelationAdater.ViewHolder>{

    public Context context;
    public List<RelationInfo> list;

    public RecySmallRelationAdater(Context context, List<RelationInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        public CheckBox cbBox;

        public ViewHolder(View itemView) {
            super(itemView);

        }
    }
}
