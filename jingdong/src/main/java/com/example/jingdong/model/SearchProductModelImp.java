package com.example.jingdong.model;

import com.example.jingdong.net.Api;
import com.example.jingdong.net.OkhttpUtils;
import com.example.jingdong.net.OnNetListener;

import java.util.HashMap;

public class SearchProductModelImp implements SearchProductModel {
    @Override
    public void searchProduct(String keywords, OnNetListener onNetListener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("keywords",keywords);
        OkhttpUtils.getInstance().doPost(Api.SEARCH_PRODUCT,map,onNetListener);
    }
}
