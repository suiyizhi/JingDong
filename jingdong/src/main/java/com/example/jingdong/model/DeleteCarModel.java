package com.example.jingdong.model;

import com.example.jingdong.net.OnNetListener;

public interface DeleteCarModel {
    void deleteCar(String uid, String pid, String token, OnNetListener onNetListener);
}
