package com.example.jingdong.model;

import com.example.jingdong.net.OnNetListener;

public interface LoginModel {
    void login(String account, String pass, OnNetListener onNetListener);
}
