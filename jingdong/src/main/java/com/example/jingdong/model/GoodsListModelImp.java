package com.example.jingdong.model;

import com.example.jingdong.net.Api;
import com.example.jingdong.net.OkhttpUtils;
import com.example.jingdong.net.OnNetListener;

public class GoodsListModelImp implements GoodsListModel {
    @Override
    public void getGoods(String pscid,OnNetListener onNetListener) {
        OkhttpUtils instance = OkhttpUtils.getInstance();
        instance.doGet(Api.PRODUCTS_URL+"?pscid="+pscid,onNetListener);
    }
}
