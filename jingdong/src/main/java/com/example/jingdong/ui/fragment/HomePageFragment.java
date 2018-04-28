package com.example.jingdong.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.dash.zxinglibrary.activity.CaptureActivity;
import com.dash.zxinglibrary.activity.CodeUtils;
import com.example.jingdong.R;
import com.example.jingdong.bean.AdBean;
import com.example.jingdong.bean.CatagoryBean;
import com.example.jingdong.presenter.CatagoryPresenterImp;
import com.example.jingdong.presenter.AdPresenterImp;
import com.example.jingdong.ui.adapter.RvClassAdapter;
import com.example.jingdong.ui.adapter.RvRecommendAdapter;
import com.example.jingdong.ui.adapter.RvSecKillAdapter;
import com.example.jingdong.ui.inter.AdView;
import com.example.jingdong.ui.inter.CatagoryView;
import com.example.jingdong.util.GlideImageLoader;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment implements AdView, CatagoryView {
    private View view;
    private Banner ad_banner;
    private AdPresenterImp adPresenterImp;
    private RecyclerView rvClass;
    private CatagoryPresenterImp catagoryPresenterImp;
    private MarqueeView marqueeView;
    private RecyclerView rvSecKill;
    private RecyclerView rvRecommend;
    private ImageView img_zxing;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        //初始化
        initView(view);
        //创建p层
        adPresenterImp = new AdPresenterImp(this);
        adPresenterImp.getAd();
        catagoryPresenterImp = new CatagoryPresenterImp(this);
        catagoryPresenterImp.getCatagory();
        //设置zxing监听
        setListener();
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        ad_banner.stopAutoPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adPresenterImp.detach();
        catagoryPresenterImp.detach();
    }

    private void initView(View view) {
        ad_banner = (Banner) view.findViewById(R.id.ad_banner);
        ad_banner.setImageLoader(new GlideImageLoader());
        rvClass = (RecyclerView) view.findViewById(R.id.rvClass);
        //创建设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false);
        rvClass.setLayoutManager(gridLayoutManager);
        marqueeView = (MarqueeView) view.findViewById(R.id.marqueeView);

        //初始化跑马灯数据
        initMarqueeView();

        rvSecKill = (RecyclerView) view.findViewById(R.id.rvSecKill);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 1, RecyclerView.HORIZONTAL, false);
        rvSecKill.setLayoutManager(gridLayoutManager1);

        rvRecommend = (RecyclerView) view.findViewById(R.id.rvRecommend);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        rvRecommend.setLayoutManager(gridLayoutManager2);
        img_zxing = (ImageView) view.findViewById(R.id.img_zxing);

    }

    public void setListener(){
        //扫描二维码的监听
        img_zxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }

    //回调显示结果
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1 && data!=null){
            Bundle bundle = data.getExtras();
            if (bundle!=null){
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String string = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getContext(), "扫描内容是：" + string, Toast.LENGTH_SHORT).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getContext(), "扫描失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //显示banner
    @Override
    public void showBanner(AdBean adBean) {
        List<AdBean.DataBean> data = adBean.getData();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            list.add(data.get(i).getIcon());
        }
        ad_banner.setImages(list);
        ad_banner.start();
        //显示秒杀
        showSeckill(adBean);
        //显示为您推荐
        showRecommend(adBean);
    }

    private void showRecommend(AdBean adBean) {
        AdBean.TuijianBean tuijian = adBean.getTuijian();
        List<AdBean.TuijianBean.ListBean> list = tuijian.getList();
        RvRecommendAdapter rvRecommendAdapter = new RvRecommendAdapter(getContext(), list);
        rvRecommend.setAdapter(rvRecommendAdapter);
    }

    private void showSeckill(AdBean adBean) {
        AdBean.MiaoshaBean miaosha = adBean.getMiaosha();
        List<AdBean.MiaoshaBean.ListBeanX> list = miaosha.getList();
        RvSecKillAdapter rvSecKillAdapter = new RvSecKillAdapter(list, getContext());
        rvSecKill.setAdapter(rvSecKillAdapter);
    }

    //显示中间分类
    @Override
    public void showCatagory(List<CatagoryBean.DataBean> dataBeans) {
        RvClassAdapter rvClassAdapter = new RvClassAdapter(dataBeans, getContext());
        rvClass.setAdapter(rvClassAdapter);
    }

    //初始化跑马灯数据
    private void initMarqueeView() {
        List<String> info = new ArrayList<>();
        info.add("欢迎访问京东app");
        info.add("大家有没有在 听课");
        info.add("是不是还有人在睡觉");
        info.add("你妈妈在旁边看着呢");
        info.add("赶紧的好好学习吧 马上毕业了");
        info.add("你没有时间睡觉了");
        marqueeView.startWithList(info);
    }


}
