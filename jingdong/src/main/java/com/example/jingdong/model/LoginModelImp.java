package com.example.jingdong.model;

import com.example.jingdong.net.Api;
import com.example.jingdong.net.OkhttpUtils;
import com.example.jingdong.net.OnNetListener;

import java.util.HashMap;

public class LoginModelImp implements LoginModel{
    @Override
    public void login(String account, String pass, OnNetListener onNetListener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("mobile",account);
        map.put("password",pass);
        OkhttpUtils.getInstance().doPost(Api.LOGIN_URL,map,onNetListener);
    }
}
