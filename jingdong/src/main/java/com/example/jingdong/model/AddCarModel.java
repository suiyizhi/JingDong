package com.example.jingdong.model;

import com.example.jingdong.net.OnNetListener;

public interface AddCarModel {
    void addCart(String uid, String pid, String token, OnNetListener onNetListener);
}
