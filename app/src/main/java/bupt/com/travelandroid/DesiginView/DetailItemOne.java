package bupt.com.travelandroid.DesiginView;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import bupt.com.travelandroid.R;

/**
 *定制行程中，交通，地点，酒店，就餐以及备注页面用到的填写款框
 */
public class DetailItemOne extends LinearLayout{
        TextView tvTitle;
        EditText etContent;
        String title;
        View mView;
    public DetailItemOne(Context context) {
        this(context,null);
    }

    public DetailItemOne(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DetailItemOne(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public DetailItemOne(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(context,attrs);
        initView(context);
    }
    /**
     * View的属性设置
     */
    private void initView(Context context){
        mView = LayoutInflater.from(context).inflate(R.layout.detail_item_layout,null);
        addView(mView);
        tvTitle = (TextView) mView.findViewById(R.id.tv_title);
        etContent = (EditText) findViewById(R.id.et_content);

        if(!TextUtils.isEmpty(title))
            tvTitle.setText(title);
    }
    /**
     * 获取自定义属性
     */
    private void initAttrs(Context context, AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DetailItemOne);
        title = typedArray.getString(R.styleable.DetailItemOne_detail_one_title);
        typedArray.recycle();
    }

    /**
     * 暴露在外面获取输入框内容的方法
     * @return
     */
    public String getContent(){
        return etContent.getText().toString();
    }

    /**
     * 设置当前编辑框内容
     */
    public void setContent(String content){
        etContent.setText(content);
    }

    public void setOnClickListener(OnClickListener clickListener){
        //设置输入框是时间
        etContent.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
        //输入框静止开启软键盘
        etContent.setInputType(InputType.TYPE_NULL);
        //禁止输入框输入
        etContent.setFocusable(false);
        etContent.setOnClickListener(clickListener);
    }
}
