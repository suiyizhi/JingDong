package com.example.jingdong.model;

import com.example.jingdong.net.Api;
import com.example.jingdong.net.OkhttpUtils;
import com.example.jingdong.net.OnNetListener;

public class ChildCataModelImp implements ChildCataModel {
    @Override
    public void getChildCatagory(String cid, OnNetListener onNetListener) {
        OkhttpUtils okhttpUtils = OkhttpUtils.getInstance();
        okhttpUtils.doGet(Api.PRODUCTCATAGORY_URL+"?cid="+cid,onNetListener);
    }
}
