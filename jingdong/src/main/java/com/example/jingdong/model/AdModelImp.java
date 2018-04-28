package com.example.jingdong.model;

import com.example.jingdong.net.Api;
import com.example.jingdong.net.OkhttpUtils;
import com.example.jingdong.net.OnNetListener;

public class AdModelImp implements AdModel {
    @Override
    public void getAd(OnNetListener onNetListener) {
        OkhttpUtils okhttpUtils = OkhttpUtils.getInstance();
        okhttpUtils.doGet(Api.AD_URL,onNetListener);
    }
}
