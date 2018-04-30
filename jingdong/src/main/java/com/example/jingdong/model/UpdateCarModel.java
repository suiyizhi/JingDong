package com.example.jingdong.model;

import com.example.jingdong.net.OnNetListener;

public interface UpdateCarModel {
    void updateCar(String uid, String sellerid, String pid, String num, String selected, String token,
                     OnNetListener onNetListener);
}
