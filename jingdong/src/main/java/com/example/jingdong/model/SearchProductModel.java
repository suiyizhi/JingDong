package com.example.jingdong.model;

import com.example.jingdong.net.OnNetListener;

public interface SearchProductModel {
    void searchProduct(String keywords, OnNetListener onNetListener);
}
