package com.example.jingdong.model;

import com.example.jingdong.net.Api;
import com.example.jingdong.net.OkhttpUtils;
import com.example.jingdong.net.OnNetListener;

import java.util.HashMap;

public class DeleteCarModelImp implements DeleteCarModel {
    @Override
    public void deleteCar(String uid, String pid, String token, OnNetListener onNetListener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("pid",pid);
        map.put("token",token);
        OkhttpUtils.getInstance().doPost(Api.DELETECART_URL,map,onNetListener);
    }
}
