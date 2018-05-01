package com.example.jingdong.ui.inter;

import com.example.jingdong.bean.GoodsListBean;

import java.util.List;

public interface SearchProductView {
    void searchSuccess(List<GoodsListBean.DataBean> data);
    String getkeywords();
}
