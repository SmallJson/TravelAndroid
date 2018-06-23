package bupt.com.travelandroid.DesiginView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/18 0018.
 * 登录注册页面的输入框
 */

public class EditTextPlus extends LinearLayout{
    View mView;
    Drawable iconLeft;
    EditText editText;
    ImageView ivLeft;
    String hint;
    String rightHint;
    TextView tvRight;
    ImageView ivLine;
    //是否显示分隔符
    boolean line;
    public EditTextPlus(Context context) {
        this(context,null);
    }

    public EditTextPlus(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EditTextPlus(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public EditTextPlus(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(context,attrs);
        initView(context);
    }

    /**
     * 初始化控件
     */
    public void initView(Context context){
        Log.e("backTitle","initView");
        mView = LayoutInflater.from(context).inflate(R.layout.view_edittext,null);
        addView(mView);
        ivLeft= (ImageView) mView.findViewById(R.id.iv_left);
        editText = (EditText) mView.findViewById(R.id.et_plus);
        tvRight = (TextView) mView.findViewById(R.id.tv_right_hint);
        ivLine = (ImageView) mView.findViewById(R.id.iv_line);

        if(iconLeft !=null ){
            ivLeft.setBackground(iconLeft);
        }else{
            ivLeft.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(hint)){
            editText.setHint(hint);
        }

        //若有属性则设置
        if(!TextUtils.isEmpty(rightHint)){
            tvRight.setText(rightHint);
        }

        if(line == false){
            ivLine.setVisibility(View.GONE);
        }else{
            ivLine.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 初始化，引入相关属性
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditextPlus);
        hint = typedArray.getString(R.styleable.EditextPlus_hint);
        iconLeft = typedArray.getDrawable(R.styleable.EditextPlus_drawable_left);
        rightHint = typedArray.getString(R.styleable.EditextPlus_right_hint);
        //设置boolean默认值是false
        line = typedArray.getBoolean(R.styleable.EditextPlus_line,false);
        typedArray.recycle();
    }

    /**
     * 获取EditText里面的内容
     * @return
     */
    public String getContent(){
        return editText.getText().toString();
    }

    /**
     * 设置右侧提示文本的事件
     */
    public void setRightClick(OnClickListener listener){
        if(tvRight != null){
            tvRight.setOnClickListener(listener);
        }
    }
    /**
     * 设置右侧提示本文的内容
     */
    public void setRightText(String content){
        if(tvRight != null){
            tvRight.setText(content);
        }
    }

    /**
     * 设计右侧文本是否可点击
     * @param clickable
     */
    public void setRightClickable(boolean clickable){
        if(tvRight != null){
            tvRight.setClickable(clickable);
        }
    }

    /**
     * 设置右侧文本的提示内容
     * @param color
     */
    public void setRightTextColor(int color){
        if(tvRight != null){
            tvRight.setTextColor(color);
        }
    }
    /**
     * 设置输入框的输入限制
     */
    public void setInputType(int type){
      /*  if(type == InputType.TYPE_TEXT_VARIATION_PASSWORD){
            //设置隐藏密码
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }*/
        editText.setInputType(type);
    }
    public  void setPasswordType(){
        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    /**
     * 设置输入框内容
     */
    public  void setContent(String content){
        editText.setText(content);
    }
}
