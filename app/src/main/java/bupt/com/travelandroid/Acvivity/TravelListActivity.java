package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bupt.com.travelandroid.Bean.TravelBean;
import bupt.com.travelandroid.Bean.TravelDayBean;
import bupt.com.travelandroid.Bean.TravelTotalBean;
import bupt.com.travelandroid.DesiginView.DetailDayItem;
import bupt.com.travelandroid.DesiginView.TrafficDayItem;
import bupt.com.travelandroid.R;
import bupt.com.travelandroid.util.DpUtil;

/**
 * Created by Administrator on 2018/5/28 0028.
 * 第一天，第二天，第三天，第四天等页面
 */

public class TravelListActivity extends BaseActivity {
    LinearLayout llContent;
    LinearLayout llRoot;
    TextView tvTravelName;
    ImageView ivShare;
    //行程视图
    List<View>  viewList = new ArrayList<View>();
    //每天行程具体信息
    HashMap<String,TravelDayBean> dayMap = new HashMap<>();

    //行程简要信息
    TravelBean travelBean ;
    //行程简要信息 + 每天行程具体信息
    TravelTotalBean travelTotalBean = new TravelTotalBean();

    //popWindow的弹出框设置
    PopupWindow popMenu;
    //popWindod对应的视图
    View menuView;
    //菜单弹出框中分享给父母的子控件
    LinearLayout llParent;
    //菜单弹出框的分享到朋友圈的子控件
    LinearLayout llWeixin;
    /**
     * 分享给父母的对话框
     */
    View viewShareParent;
    AlertDialog parentDialog;
    PopupWindow parentPopWindow;
    TextView tvAddParent;
    Button btCancle;
    Button btConfirm;
    //父亲选择框
    CheckBox cbFather;
    //母亲选择框
    CheckBox chMother;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_list);
        initData();
        initView();
    }

    @Override
    public void initView() {
        super.initView();
        llContent = (LinearLayout) findViewById(R.id.ll_content);
        tvTravelName = (TextView) findViewById(R.id.tv_travel_name);
        llRoot = (LinearLayout) findViewById(R.id.ll_root);
        ivShare = (ImageView) findViewById(R.id.iv_share);
        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showOrDismissPopMenu();
            }
        });

       //设置旅行的名字
        if(!TextUtils.isEmpty(travelBean.travelName)){
            tvTravelName.setText(travelBean.travelName);
        }else{
            tvTravelName.setText("默认");
        }

        //设置旅行天数
        travelBean.dataCount = TextUtils.isEmpty(travelBean.dataCount)?"6":travelBean.dataCount;
        if(travelBean.dataCount != null){
            int count = Integer.parseInt(travelBean.dataCount);
            for(int i = 0 ;i< count;i++){
                //此处需要设置llContent父容器
                View view = LayoutInflater.from(mContext).inflate(R.layout.day_item,llContent,false);
                TextView tvdata = (TextView) view.findViewById(R.id.tv_data);
                final int day = i + 1;
                tvdata.setText("第"+day+"天");
/*               LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
                view.setLayoutParams(params);*/
                llContent.addView(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent  intent = new Intent(mContext,TravelDetailActivity.class);
                        //传递天数信息
                        intent.putExtra("day",day);
                        //传递视图信息
                        intent.putExtra("data",dayMap.get(""+day));
                        //将天数作为requestCode
                        mContext.startActivityForResult(intent,day);
                    }
                });
                viewList.add(view);
            }
        }
    }

    @Override
    public void initData() {
        travelBean =  (TravelBean) getIntent().getSerializableExtra("travel");
        travelTotalBean.setTravelBean(travelBean);

        //初始化弹出菜单
        initPopMenu();
        //初始话父母对话框
        initParentDialog();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //requesyCode代表是第几天传回来的数据
        TravelDayBean dayBean = (TravelDayBean) data.getSerializableExtra("result");
        if(dayBean != null){
            //存储数据
            dayMap.put(""+requestCode,dayBean);
            //更新视图
            updataDayView(requestCode);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //更新第几天的具体视图信息
    private  void updataDayView(Integer day){
        //获取指定天数对应的View视图
        View view = viewList.get(day-1);
        //获取指定天数对应的数据
        TravelDayBean dayBean = dayMap.get(""+day);
        /**
         * 将数据同步到视图上，用户输入非空条件下展示数据
         */
        //1.找到视图控件
        //交通
        TrafficDayItem trafficDayItem = (TrafficDayItem) view.findViewById(R.id.traffic_item);
        //地点
        DetailDayItem placeDayItem = (DetailDayItem) view.findViewById(R.id.ddi_place);
        //餐饮
        DetailDayItem resDayItem = (DetailDayItem) view.findViewById(R.id.ddi_returant);
        //酒店
        DetailDayItem houseDayItem = (DetailDayItem) view.findViewById(R.id.ddi_house);
        //备注暂无

        //2.渲染交通视图
        if(dayBean.getTrafficBean()!=null){
            trafficDayItem.setFlightName(dayBean.getTrafficBean().getFlightName());
            trafficDayItem.setStartTime(dayBean.getTrafficBean().getStartTime());
            trafficDayItem.setEndPlace(dayBean.getTrafficBean().getEndPlace());
            trafficDayItem.setStartPlace(dayBean.getTrafficBean().getStartPlace());
            trafficDayItem.setVisibility(View.VISIBLE);
        }
        //3.渲染地点视图(目前只显示地点名称)
        if(dayBean.getPlaceBean()!=null){
            placeDayItem.setTitle(dayBean.getPlaceBean().getPlaceName());
            placeDayItem.setVisibility(View.VISIBLE);
        }
        //3.渲染餐饮(只显示餐馆名称)
        if(dayBean.getResBean()!=null){
            resDayItem.setTitle(dayBean.getResBean().getResName());
            resDayItem.setVisibility(View.VISIBLE);
        }
        //4.酒店(值显示酒店名称)
        if(dayBean.getHouseBean()!=null){
            houseDayItem.setTitle(dayBean.getHouseBean().getHouseName());
            houseDayItem.setVisibility(View.VISIBLE);
        }
    }

    //当前activity消失不见的时候，
    //将页面当前输入保存到本地Sqlite数据库当中
    @Override
    protected void onStop() {
        travelTotalBean.setTravelDayMap(dayMap);
        //save()将当前数据保存到数据库。
        super.onStop();
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
        menuView = LayoutInflater.from(mContext).inflate(R.layout.menu_share_layout, null);
        //在View上捕获到空间
        llParent = (LinearLayout) menuView.findViewById(R.id.ll_parent);
        llWeixin  = (LinearLayout) menuView.findViewById(R.id.ll_weixin);
        //跳转到分享亲属
        llParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrDismissDialogParent();
                showOrDismissPopMenu();
            }
        });
        //分享到朋友圈
        llWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        popMenu = new PopupWindow(menuView, DpUtil.dp2px(mContext,210),LinearLayout.LayoutParams.WRAP_CONTENT);
        //点击popWindows外让其消失
        popMenu.setOutsideTouchable(false);
        popMenu.setBackgroundDrawable(new ColorDrawable());
    }

    public  void initParentDialog(){

        viewShareParent = LayoutInflater.from(mContext).inflate(R.layout.view_share_parent,null);
        //找到View上对应的空间信息
        tvAddParent = (TextView) viewShareParent.findViewById(R.id.tv_addParent);
        btCancle = (Button) viewShareParent.findViewById(R.id.bt_cancle);
        cbFather = (CheckBox) viewShareParent.findViewById(R.id.cb_father);
        chMother = (CheckBox) viewShareParent.findViewById(R.id.cb_mother);

        btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(parentDialog.isShowing()){
                    parentDialog.dismiss();
                }
            }
        });
        btConfirm = (Button) viewShareParent.findViewById(R.id.bt_confirm);
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将当前信息推送到服务单
                //saveToServer():
                //isChecked
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext,R.style.CustomDialog);
        builder.setView(viewShareParent);
        parentDialog  = builder.create();
        //点击外部不消失
        parentDialog.setCanceledOnTouchOutside(false);
       /* parentPopWindow = new PopupWindow(viewShareParent,DpUtil.dp2px(mContext,259),DpUtil.dp2px(mContext,218));
        //点击popWindows外不让其消失
        popMenu.setOutsideTouchable(true);
        popMenu.setBackgroundDrawable(new ColorDrawable());*/
    }
    public void showOrDismissDialogParent(){
        if(parentDialog != null){
               if(parentDialog.isShowing()){
                   parentDialog.dismiss();
             }else{
                   //parentPopWindow.showAtLocation(llRoot,Gravity.CENTER,0,0);
                   parentDialog.show();
                   //改变对话框的大小
                   WindowManager.LayoutParams  lp= parentDialog.getWindow().getAttributes();
                  /* lp.width=DpUtil.dp2px(mContext,256);//定义宽度
                   lp.height=DpUtil.dp2px(mContext,218);//定义高度*/
                   lp.width = DpUtil.dp2px(mContext,259);
                   lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                   parentDialog.getWindow().setAttributes(lp);
               }
        }
    }
}
