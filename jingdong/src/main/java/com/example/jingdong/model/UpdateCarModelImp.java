package com.example.jingdong.model;

import com.example.jingdong.net.Api;
import com.example.jingdong.net.OkhttpUtils;
import com.example.jingdong.net.OnNetListener;

import java.util.HashMap;

public class UpdateCarModelImp implements UpdateCarModel {
    @Override
    public void updateCar(String uid, String sellerid, String pid, String num, String selected, String token, OnNetListener onNetListener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("sellerid",sellerid);
        map.put("pid",pid);
        map.put("num",num);
        map.put("selected",selected);
        map.put("token",token);
        OkhttpUtils.getInstance().doPost(Api.UPDATECARTS_URL,map,onNetListener);
    }
}
