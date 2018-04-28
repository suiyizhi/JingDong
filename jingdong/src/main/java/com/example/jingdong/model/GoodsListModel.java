package com.example.jingdong.model;

import com.example.jingdong.net.OnNetListener;

public interface GoodsListModel {
    void getGoods(String pscid, OnNetListener onNetListener);
}
