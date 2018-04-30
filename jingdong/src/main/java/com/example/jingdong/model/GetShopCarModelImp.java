package com.example.jingdong.model;

import com.example.jingdong.net.Api;
import com.example.jingdong.net.OkhttpUtils;
import com.example.jingdong.net.OnNetListener;

import java.util.HashMap;

public class GetShopCarModelImp implements GetShopCarModel{
    @Override
    public void getShopCar(String uid, String token, OnNetListener onNetListener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("token",token);
        OkhttpUtils.getInstance().doPost(Api.GETCARTS_URL,map,onNetListener);
    }
}
