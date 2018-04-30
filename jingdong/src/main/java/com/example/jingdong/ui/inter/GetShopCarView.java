package com.example.jingdong.ui.inter;

import com.example.jingdong.bean.SellerBean;
import com.example.jingdong.bean.ShopCarBean;

import java.util.List;

public interface GetShopCarView {
    void showShopCar(List<SellerBean> groupList,List<List<ShopCarBean.DataBean.ListBean>> childList);
}
