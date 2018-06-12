package bupt.com.travelandroid.Fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import bupt.com.travelandroid.Acvivity.MakingTraveActivity;
import bupt.com.travelandroid.Acvivity.MeTravelActivity;
import bupt.com.travelandroid.DesiginView.SlideViewPager;
import bupt.com.travelandroid.R;

/**
 * Created by Administrator on 2018/5/27 0027.
 */

public class XingchengFragment extends Fragment {

    public  View mView;
    public ImageView ivHeader;
    public RelativeLayout rlBackground;
    public SlideViewPager viewPager;
    public PagerAdapter viewPagerAdapter;
    public Button btMakeTravel;
    public Button btMeTravel;

    public ArrayList<ImageView> imageViewList = new ArrayList<ImageView>();
    public ArrayList<Integer> imageIdList = new ArrayList<>();

    //定时器
    Timer mTimer =new Timer();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*return super.onCreateView(inflater, container, savedInstanceState);*/
        mView  = inflater.inflate(R.layout.fragment_xingcheng,container,false);
        initData();
        initView();
        return mView;
    }

    public void initData(){
        //添加背景图片
        imageIdList.add(R.drawable.xingcheng_bg4);
        imageIdList.add(R.drawable.xingcheng_bg5);
        imageIdList.add(R.drawable.xingcheng_bg6);

    }

    public void initView(){
        btMakeTravel= (Button) mView.findViewById(R.id.bt_make_xingcheng);
        btMeTravel = (Button) mView.findViewById(R.id.bt_me_travel);
        ivHeader = (ImageView) (mView.findViewById(R.id.iv_header));
        rlBackground = (RelativeLayout) (mView.findViewById(R.id.rl_background));
        viewPager = (SlideViewPager) mView.findViewById(R.id.vp_content);
        //静止ViewPager滑动
        viewPager.setSlide(false);

        //圆形图
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.header);
        RoundedBitmapDrawable circleDrawable = RoundedBitmapDrawableFactory.create(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.header));
        circleDrawable.getPaint().setAntiAlias(true);
        circleDrawable.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()));
        ivHeader.setBackground(circleDrawable);

        //设置biewPager适配器
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                ImageView imageView = new ImageView(getActivity());
                imageView.setBackgroundResource(imageIdList.get(position%3));
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View)object);
            }
        });

        //制作行程跳转界面
        btMakeTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), MakingTraveActivity.class));
            }
        });

        btMeTravel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //跳转到我的行程页面
                getActivity().startActivity(new Intent(getActivity(), MeTravelActivity.class));
            }
        });
    }

    @Override
    public void onStart() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewPager.setCurrentItem((viewPager.getCurrentItem()+1));
                    }
                });
            }
        },1000,3000);
        super.onStart();
    }

    @Override
    public void onStop(){
        mTimer.cancel();
        mTimer = null;
        super.onStop();
    }
}
