package bupt.com.travelandroid.DesiginView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MNestScrollView extends NestedScrollView{


    public MNestScrollView(@NonNull Context context) {
        super(context);
    }

    public MNestScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MNestScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //永远不拦截，子控件事件。
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
