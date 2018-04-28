package com.example.jingdong.presenter;

import com.example.jingdong.bean.ChildCataBean;
import com.example.jingdong.model.ChildCataModelImp;
import com.example.jingdong.net.OnNetListener;
import com.example.jingdong.ui.inter.ChildCataView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ChildCataPresenterImp implements ChildCataPresenter{

    private ChildCataView childCataView;
    private final ChildCataModelImp childCataModelImp;

    public ChildCataPresenterImp(ChildCataView childCataView) {
        this.childCataView=childCataView;
        childCataModelImp = new ChildCataModelImp();
    }

    @Override
    public void getChildCatagory(String cid) {
        childCataModelImp.getChildCatagory(cid, new OnNetListener() {
            @Override
            public void onSuccess(String result) {
                ChildCataBean childCataBean = new Gson().fromJson(result, ChildCataBean.class);
                if ("0".equals(childCataBean.getCode()) && childCataView!=null){
                    //定义数组存放数据
                    List<String> list_group = new ArrayList<>();
                    ArrayList<List<ChildCataBean.DataBean.ListBean>> child_lists = new ArrayList<>();
                    //添加分组的数据
                    List<ChildCataBean.DataBean> data = childCataBean.getData();

                    for (int i = 0; i < data.size(); i++) {
                        list_group.add(data.get(i).getName());
                        child_lists.add(data.get(i).getList());
                    }
                    childCataView.showChildCata(list_group,child_lists);

                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    @Override
    public void detach() {
        if (childCataView!=null){
            childCataView=null;
        }
    }
}
