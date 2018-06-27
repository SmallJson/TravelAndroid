package bupt.com.travelandroid.Acvivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Text;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import bupt.com.travelandroid.Acvivity.CallBack.IICallBack;
import bupt.com.travelandroid.Acvivity.Presenter.AttractionPresenter;
import bupt.com.travelandroid.Acvivity.Presenter.MapPresenter;
import bupt.com.travelandroid.Bean.PlaceBean;
import bupt.com.travelandroid.Bean.response.AddressInterface;
import bupt.com.travelandroid.Bean.response.AttractionBean;
import bupt.com.travelandroid.Bean.response.LocationBean;
import bupt.com.travelandroid.R;

/**
 * 经典的地图信息
 */
public class AttractionMapActivity extends  BaseActivity{

    private MapView mMapView;
    private BaiduMap mBaiduMap;

    public double mLatitude;
    public  double mLongitude;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();

    MapPresenter mapPresenter = new MapPresenter(mContext);


    PlaceBean placeBean = new PlaceBean();

    AttractionPresenter attractionPresenter = new AttractionPresenter(mContext);

    /**
     *景点信息
     */
    //工具栏标题
    TextView tvTitle;
    //工具栏返回按钮
    ImageView ivBack;
    //景点介绍
    TextView tvIntroduction;
    //景点班车路线
    TextView tvTraffic;
    //景点名称
    TextView tvPlaceName;
    //景点开放时间
    TextView tvOpenData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivity_attraction_information);

        //注册监听函数
        initView();
        initData();
    }

    public void initBaiduMap(){
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，设置定位模式，默认高精度
        //LocationMode.Hight_Accuracy：高精度；
        //LocationMode. Battery_Saving：低功耗；
        //LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("bd09ll");
         //可选，设置返回经纬度坐标类型，默认gcj02
        //gcj02：国测局坐标；
        //bd09ll：百度经纬度坐标；
        //bd09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标

        option.setScanSpan(2000);
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(true);
        //可选，定位SDK内部是一个service，并放到了独立进程。
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
        //可选，设置是否收集Crash信息，默认收集，即参数为false
        option.setWifiCacheTimeOut(5*60*1000);
        //可选，7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位
        option.setEnableSimulateGps(false);
        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false
        mLocationClient.setLocOption(option);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
    }
    @Override
    public void initView() {
        super.initView();
        mMapView = (MapView) findViewById(R.id.mv_map);
        mBaiduMap = mMapView.getMap();
        //删除百度地图log
        mMapView.removeViewAt(1);

        //工具栏标题
        tvTitle   = findViewById(R.id.tv_placeName);
        //工具栏返回按钮
        ivBack = findViewById(R.id.iv_back);
        //景点介绍
        tvIntroduction = findViewById(R.id.attraction_introduction);
        //景点班车路线
        tvTraffic = findViewById(R.id.attraction_traffic);
        //景点名称
        tvPlaceName = findViewById(R.id.attraction_position);
        //景点开放时间
        tvOpenData =  findViewById(R.id.attraction_open_time);

    }


    @Override
    public void initData() {

        placeBean.setPlaceName("北京市天安门");
        Intent intent = getIntent();
        PlaceBean bean = (PlaceBean) intent.getSerializableExtra("address");
        if(bean != null){
            placeBean = bean;
        }

        //2.设置景点名称
        tvPlaceName.setText(placeBean.getPlaceName());
        tvTitle.setText(placeBean.getPlaceName());
        //3.获取景点介绍
        attractionPresenter.getAttractionInfo(placeBean.getPlaceName(), new IICallBack<AttractionBean>() {
            @Override
            public void getData(AttractionBean response) {
                if(response !=null){
                    tvIntroduction.setText(response.getSummary());
                    tvPlaceName.setText(response.getName());
                    loadAddress(response.getName());
                }
            }
            @Override
            public void error(String msg) {
                Toast.makeText(mContext, msg,Toast.LENGTH_SHORT).show();
            }
        });

        //4.获取班车路线
        Log.e("mAddress",placeBean.getPlaceName());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    public void loadAddress(String addressName){
        mapPresenter.selectAddress(addressName, new IICallBack<List<LocationBean>>() {
            @Override
            public void getData(List<LocationBean> response) {
                    //将地图缩放到目前位置
                    drawLocation(response.get(0));
                    //在地图上标记出来
                    drawLocation(response);
            }
            @Override
            public void error(String msg) {
                Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void drawLocation(LocationBean locationBean){
        LatLng point1 = new LatLng(locationBean.getLat(), locationBean.getLng());
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(point1)
                .zoom(18)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化

        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }


    public void drawLocation(List<LocationBean> list){

        Log.e("location",list.toString());
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.location_icon);
        //创建OverlayOptions的集合
        List<OverlayOptions> options = new ArrayList<OverlayOptions>();
        for(int i = 0 ;i < list.size() ; i++){
            //设置坐标点
            LatLng point1 = new LatLng(list.get(i).getLat(), list.get(i).getLng());
            OverlayOptions option1 =  new MarkerOptions()
                    .position(point1)
                    .icon(bitmap);
            options.add(option1);
        }
        //在地图上批量添加
        mBaiduMap.addOverlays(options);
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
             mLatitude = location.getLatitude();    //获取纬度信息
             mLongitude = location.getLongitude();    //获取经度信息
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
            String coorType = location.getCoorType();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            int errorCode = location.getLocType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
        }
    }
}
