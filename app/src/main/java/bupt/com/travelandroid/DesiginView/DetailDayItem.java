package bupt.com.travelandroid.DesiginView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/30 0030.
 * 地点，餐饮，酒店，备注自定义展现框
 */

public class DetailDayItem extends LinearLayout {
    ImageView ivIcon;
    TextView tvTitle;
    Drawable icon;
    String title;
    View mView;
    public DetailDayItem(Context context) {
        this(context,null);
    }


    public DetailDayItem(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public DetailDayItem(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DetailDayItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(context,attrs);
        initView(context);
    }


    /**
     * 初始化控件
     */
    public void initView(Context context){
        Log.e("backTitle","initView");
        mView = LayoutInflater.from(context).inflate(R.layout.detail_day,this,false);
        addView(mView);
        ivIcon = (ImageView) mView.findViewById(R.id.iv_icon);
        tvTitle = (TextView) mView.findViewById(R.id.tv_title);

        if(icon!=null){
            ivIcon.setBackground(icon);
        }
        if(!TextUtils.isEmpty(title)){
            tvTitle.setText(title);
        }
    }

    /**
     * 初始化，引入相关属性
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DetailDayItem);
        icon = typedArray.getDrawable(R.styleable.DetailDayItem_detailDay_icon);
        title = typedArray.getString(R.styleable.DetailDayItem_detailDay_title);
        typedArray.recycle();
    }

    //设置icon
    public  void setIcon(Drawable icon){
        ivIcon.setBackground(icon);
    }
    //设置标题
    public void setTitle(String title){
        tvTitle.setText(title);
    }
}
