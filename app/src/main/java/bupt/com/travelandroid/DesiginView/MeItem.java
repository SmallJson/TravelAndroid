package bupt.com.travelandroid.DesiginView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/28 0028.
 * APP主页中我的页面，使用的横向Item
 */
public class MeItem extends RelativeLayout{
    public ImageView imageLeft;
    public TextView  tvDec;
    public ImageView imageRight;

    public Drawable drawableLeft;
    public Drawable drawableRight;
    public String dec;

    View mView;
    public MeItem(Context context) {
        this(context,null);
    }

    public MeItem(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public MeItem(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public MeItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(context,attrs);
        initView(context);
    }

    public void initView(Context context){
        mView = LayoutInflater.from(context).inflate(R.layout.me_item,this,false);
        imageLeft = (ImageView) mView.findViewById(R.id.iv_left);
        imageRight = (ImageView) mView.findViewById(R.id.iv_right);
        tvDec = (TextView) findViewById(R.id.tv_dec);

        if(drawableLeft != null){
            Log.e("MeItem","not null");
            imageLeft.setBackground(drawableLeft);
        }
        if(drawableRight != null){
            Log.e("MeItem","not null");
            imageRight.setBackground(drawableRight);
        }
        if(!TextUtils.isEmpty(dec)){
            Log.e("MeItem","not null");
            tvDec.setText(dec);
        }
        addView(mView);
    }
    /**
     * 初始化，引入相关属性
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MeItem);
        dec = typedArray.getString(R.styleable.MeItem_dec);
        drawableLeft = typedArray.getDrawable(R.styleable.MeItem_MeItem_drawable_left);
        drawableRight = typedArray.getDrawable(R.styleable.MeItem_MeItem_drawable_right);
        typedArray.recycle();
    }
}
