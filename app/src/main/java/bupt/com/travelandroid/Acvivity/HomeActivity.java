package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;

import java.util.ArrayList;

import bupt.com.travelandroid.Fragment.MeFragment;
import bupt.com.travelandroid.Fragment.MessageFragment;
import bupt.com.travelandroid.Fragment.XingchengFragment;
import bupt.com.travelandroid.R;
import bupt.com.travelandroid.util.DpUtil;

/**
 * Created by Administrator on 2018/5/27 0027.
 */

public class HomeActivity extends  BaseActivity {
    private BottomNavigationBar mBottomNavigationBar;
    private TextBadgeItem mTextBadgeItem;
    private ShapeBadgeItem mShapeBadgeItem;
    private RelativeLayout llRoot;
    private ImageView ivParent;

    private final int xingcheng_fragment_index = 0;
    private final int message_fragment_index = 1;
    private final int me_fragment_index = 2;

    //菜单弹出框
    //popWindow的弹出框设置
    PopupWindow popMenu;
    //popWindod对应的视图
    View menuView;
    //菜单弹出框中添加父母的子控件
    LinearLayout llParent;



    //保存所有的Fragment
    ArrayList<Fragment> fragmentList = new ArrayList<>();
    //保留当前Activity显示的Fragment
    Fragment curFragment;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
    }

    @Override
    public void initView() {
        super.initView();
        llRoot = (RelativeLayout) findViewById(R.id.ll_root);
        initNavigationBar();
        initFragment();
        initPopMenu();
        ivParent  = (ImageView) findViewById(R.id.iv_add);
        ivParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrDismissPopMenu();
            }
        });
    }

    @Override
    public void initData(){

    }

    public void initFragment(){
        fragmentList.add(new XingchengFragment());

        fragmentList.add(new MessageFragment());
        fragmentList.add(new MeFragment());

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_contain, fragmentList.get(xingcheng_fragment_index)).commit();
        curFragment = fragmentList.get((0));
    }
    private void initNavigationBar(){
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

     /*   mTextBadgeItem = new TextBadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.colorAccent)
                .setAnimationDuration(200)
                .setText("3")
                .setHideOnSelect(false);

        mShapeBadgeItem = new ShapeBadgeItem()
                .setShapeColorResource(R.color.colorPrimary)
                .setGravity(Gravity.TOP | Gravity.END)
                .setHideOnSelect(false);*/

        //设置模式
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        mBottomNavigationBar //值得一提，模式跟背景的设置都要在添加tab前面，不然不会有效果。
                .setActiveColor(R.color.mgreen);//选中颜色 图标和文字
//                .setInActiveColor("#8e8e8e")//默认未选择颜色
//                .setBarBackgroundColor(R.color.white);//默认背景色

        //设置button的底部内容
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.xingcheng_active,"行程")
                        .setInactiveIcon(ContextCompat.getDrawable(mContext,R.drawable.xingcheng_nomal))
                        .setBadgeItem(mShapeBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.message_active,"消息")
                        .setInactiveIcon(ContextCompat.getDrawable(mContext,R.drawable.message_nomal)))
                .addItem(new BottomNavigationItem(R.drawable.me_active,"我")
                        .setInactiveIcon(ContextCompat.getDrawable(mContext,R.drawable.me_nomal)))
                .setFirstSelectedPosition(0)//设置默认选择的按钮
                .initialise();//所有的设置需在调用该方法前完成

        //底部导航栏的点击事件
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position){
                    case 0:
                        switchFragment(xingcheng_fragment_index);
                        break;
                    case 1:
                        switchFragment(message_fragment_index);
                        break;
                    case 2:
                        switchFragment(me_fragment_index);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
    //切换到指定的Fragment
    public void switchFragment(int postion){
        //如果当前fragment == 切换的fragment不做处理
          if(curFragment != fragmentList.get(postion)){
            //切换fragment未被添加
            if(!fragmentList.get(postion).isAdded()){
                getSupportFragmentManager().beginTransaction().hide(curFragment)
                        .add(R.id.fl_contain,fragmentList.get(postion)).commit();
            }else{
                //切换的fragement已经被添加
                getSupportFragmentManager().beginTransaction().hide(curFragment)
                        .show(fragmentList.get(postion)).commit();
            }
            curFragment = fragmentList.get(postion);
        }
    }

    public void showOrDismissPopMenu(){
        if(!popMenu.isShowing()){
            int height = mToolBar.getHeight();
            popMenu.showAtLocation(llRoot, Gravity.RIGHT|Gravity.TOP,20,height);
        }else{
            popMenu.dismiss();
        }
    }

    public void initPopMenu(){
        menuView = LayoutInflater.from(mContext).inflate(R.layout.menu_addparent_layout, null);
        //在View上捕获到空间
        llParent = (LinearLayout) menuView.findViewById(R.id.ll_parent);
        //跳转到分享亲属
        llParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, AddRelationActivity.class));
                showOrDismissPopMenu();
            }
        });

        popMenu = new PopupWindow(menuView, DpUtil.dp2px(mContext,210),LinearLayout.LayoutParams.WRAP_CONTENT);
        //点击popWindows外让其消失
        popMenu.setOutsideTouchable(false);
        popMenu.setBackgroundDrawable(new ColorDrawable());
    }


}
