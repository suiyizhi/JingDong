package com.example.jingdong.ui.inter;

import com.example.jingdong.bean.ChildCataBean;

import java.util.List;

public interface ChildCataView {
    void showChildCata(List<String> groupList,List<List<ChildCataBean.DataBean.ListBean>> list);
}
