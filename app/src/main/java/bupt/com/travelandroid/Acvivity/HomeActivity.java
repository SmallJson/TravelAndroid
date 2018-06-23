package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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
import com.bumptech.glide.Glide;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMFileMessageBody;
import com.hyphenate.chat.EMImageMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.util.ImageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import bupt.com.travelandroid.Acvivity.CallBack.IMessageListener;
import bupt.com.travelandroid.Bean.IM.ImMessage;
import bupt.com.travelandroid.Fragment.MeFragment;
import bupt.com.travelandroid.Fragment.MessageFragment;
import bupt.com.travelandroid.Fragment.XingchengFragment;
import bupt.com.travelandroid.R;
import bupt.com.travelandroid.util.DpUtil;
import bupt.com.travelandroid.util.SnackUtils;

import static com.hyphenate.chat.EMMessage.Type.TXT;

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

    /*保存环信的消息
     */
    public List<ImMessage> msgList = new ArrayList<>();
    public MsgListener msgListener;

    public IMessageListener msgFragmentListener;

    public int messageCount = 0;
    public boolean isFirstComming = true;
    /**
     * Handle处理异步消息
     */
    public  Handler mHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int size = (int) msg.obj;
            Log.e("messgeCount","before"+messageCount+"");
            if(msgFragmentListener != null){
                msgFragmentListener.messageArrival(msgList);
                Log.e("handler","收到消息"+msgList.size());
                Log.e("handle","进入listner不为null的条件，messageCount="+messageCount);
            }else{
/*                SnackUtils.showSnackShort(llRoot,"收到"+messageCount+"新消息");*/
             /*   if(!isFirstComming){
                    //在登录时，受到的未读消息数，包括了从Im服务器推送过来的离线消息数
                    //在第一次进入该activity时，不纳入计算
                    messageCount = messageCount + msgList.size();
                }
                isFirstComming =false;*/
                messageCount = messageCount + msgList.size();
                MessageFragment messageFragment = (MessageFragment) fragmentList.get(1);
                messageFragment.setNewMessage(msgList);
                Log.e("handle","进入listner为null的条件，messageCount="+messageCount);
                Log.e("HomeAcitivity","离线消息"+msgList.size());
            }
            Log.e("messgeCount","after"+messageCount+"");
            showTextBadgeItem();
        }
    };

    public  void showTextBadgeItem(){
        if(messageCount > 0){
            mTextBadgeItem.show();
            mTextBadgeItem.setText(messageCount+"");
        }
        else {
            mTextBadgeItem.hide();
        }
    }

    public  void  setmTextBadgeItem(Integer count){
        messageCount = count;
        showTextBadgeItem();
    }
    /**
     * messageFragment注册监听事件
     */
    public void  setMessageArriveListener(IMessageListener listener){
        this.msgFragmentListener = listener;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
        Log.e("homeActivity","onCreate");
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
        //监听环信的消息事件
        initIm();

    }
    public  void  initIm(){
        msgListener = new MsgListener();
        Log.e("homeActivity","设置监听事件");
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
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

        //获取登录时的未读消息总数
        Intent intent = getIntent();
        Integer unReadMsg = intent.getIntExtra("unReadMsg",-1);

        //显示未读消息总数
        if(unReadMsg == -1){
            Log.e("homeActivity","读取未读消息数失败");
        }else {
            Log.e("homeActivity","读取未读消息"+unReadMsg);
            messageCount = unReadMsg;
        }

        mTextBadgeItem = new TextBadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.colorAccent)
                .setAnimationDuration(200)
                .setText(unReadMsg+"")
                .setHideOnSelect(false);
        showTextBadgeItem();
   /*     mShapeBadgeItem = new ShapeBadgeItem()
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
                        .setInactiveIcon(ContextCompat.getDrawable(mContext,R.drawable.message_nomal))
                        .setBadgeItem(mTextBadgeItem))
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("home","推出登录");
        EMClient.getInstance().logout(true);
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }

    class  MsgListener implements EMMessageListener{
        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            //对接收到的消息做一次信息提取操作
            try {
                for (int i = 0 ;i< messages.size();i++){
                    ImMessage imMessage = new ImMessage();
                    imMessage.setCreatTime(messages.get(i).getStringAttribute("creatTime",""));
/*                   imMessage.set(messages.get(i).getFrom());*/
                    imMessage.setType(messages.get(i).getIntAttribute("type",-1));
                    imMessage.setImgUrl(messages.get(i).getStringAttribute("url"));
                    imMessage.setFromAvator(messages.get(i).getStringAttribute("avator"));
                    imMessage.setFromUid(messages.get(i).getIntAttribute("fromUid"));
                    imMessage.setToUid(messages.get(i).getIntAttribute("toUid"));
                    imMessage.setFromName(messages.get(i).getStringAttribute("fromName"));
                    imMessage.setId(messages.get(i).getIntAttribute("id"));
                    imMessage.setReadType(messages.get(i).getIntAttribute("read"));

                    if(messages.get(i).getIntAttribute("type") == 0){
                        EMTextMessageBody text = (EMTextMessageBody) messages.get(i).getBody();
                        imMessage.setText(text.getMessage());
                    }else{
                        //图片和行程规划信息，就直接使用附加字段
                        imMessage.setText(messages.get(i).getStringAttribute("text"));
                    }

                    Log.e("home",imMessage.toString());
                    msgList.clear();
                    msgList.add(imMessage);
                }
            }catch ( Exception e){
                e.printStackTrace();
            }
            Message msg =Message.obtain();
            msg.obj =messages.size();
            mHandler.sendMessage(msg);
        }
        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //收到透传消息
        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
            //收到已读回执
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
            //收到已送达回执
        }
        @Override
        public void onMessageRecalled(List<EMMessage> messages) {
            //消息被撤回
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
            //消息状态变动
        }
    }

}
