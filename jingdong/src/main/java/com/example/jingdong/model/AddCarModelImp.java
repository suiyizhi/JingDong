package com.example.jingdong.model;

import com.example.jingdong.net.Api;
import com.example.jingdong.net.OkhttpUtils;
import com.example.jingdong.net.OnNetListener;

import java.util.HashMap;

public class AddCarModelImp implements AddCarModel {
    @Override
    public void addCart(String uid, String pid, String token, OnNetListener onNetListener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Uid",uid);
        map.put("Pid",pid);
        map.put("Token",token);
        OkhttpUtils.getInstance().doPost(Api.ADDCART_RUL,map,onNetListener);
    }
}
