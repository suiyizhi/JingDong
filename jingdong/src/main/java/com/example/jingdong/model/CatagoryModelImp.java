package com.example.jingdong.model;

import com.example.jingdong.net.Api;
import com.example.jingdong.net.OkhttpUtils;
import com.example.jingdong.net.OnNetListener;

public class CatagoryModelImp implements CatagoryModel {
    @Override
    public void getCatagory(OnNetListener onNetListener) {
        OkhttpUtils instance = OkhttpUtils.getInstance();
        instance.doGet(Api.CATAGORY_URL,onNetListener);
    }
}
