package com.example.jingdong.model;

import com.example.jingdong.net.OnNetListener;

public interface GetShopCarModel {
    void getShopCar(String uid, String token, OnNetListener onNetListener);
}
