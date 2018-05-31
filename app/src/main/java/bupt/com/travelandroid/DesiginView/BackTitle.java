package bupt.com.travelandroid.DesiginView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import bupt.com.travelandroid.R;

/**
 * Created by tuyin on 2018/5/18 0018.
 * //带有返回键的标题栏
 */

public class BackTitle extends LinearLayout{

    //自定义属性
    String strTitle;
    Drawable backIcon;
    //控件
    View mView;
    ImageView ivBack;
    TextView tvTitle;

    public BackTitle(Context context) {
        this(context,null);
        Log.e("backTitle","自定义控件1");
    }

    public BackTitle(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        Log.e("backTitle","自定义控件2");
    }

    public BackTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
        Log.e("backTitle","自定义控件3");
    }

    public BackTitle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.e("backTitle","自定义控件4");
        initAttrs(context,attrs);
        initView(context);
    }

    /**
     * 初始化控件
     */
    public void initView(Context context){
        Log.e("backTitle","initView");
        mView = LayoutInflater.from(context).inflate(R.layout.view_title,null);
        addView(mView);
        ivBack = (ImageView) mView.findViewById(R.id.iv_back);
        tvTitle = (TextView) mView.findViewById(R.id.tv_title);
        ivBack.setBackground(backIcon);
        tvTitle.setText(strTitle);
    }

    /**
     * 初始化，引入相关属性
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context,AttributeSet attrs){
        Log.e("backTitle","initAttrs");
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BackTitle);
        strTitle = typedArray.getString(R.styleable.BackTitle_title);
        backIcon = typedArray.getDrawable(R.styleable.BackTitle_drawable_back);
        Log.e("BackTitle",strTitle);
        typedArray.recycle();
    }

    public void setLeftClick(OnClickListener listener){
       ivBack.setOnClickListener(listener);
    }
}
