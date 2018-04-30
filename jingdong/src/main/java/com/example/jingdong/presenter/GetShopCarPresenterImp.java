package com.example.jingdong.presenter;

import com.example.jingdong.bean.SellerBean;
import com.example.jingdong.bean.ShopCarBean;
import com.example.jingdong.model.GetShopCarModelImp;
import com.example.jingdong.net.OnNetListener;
import com.example.jingdong.ui.inter.GetShopCarView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class GetShopCarPresenterImp implements GetShopCarPresenter{

    private GetShopCarView getShopCarView;
    private final GetShopCarModelImp getShopCarModelImp;

    public GetShopCarPresenterImp(GetShopCarView getShopCarView) {
        this.getShopCarView=getShopCarView;
        getShopCarModelImp = new GetShopCarModelImp();
    }

    @Override
    public void getShopCar(String uid, String token) {
        getShopCarModelImp.getShopCar(uid, token, new OnNetListener() {
            @Override
            public void onSuccess(String result) {

                ShopCarBean shopCarBean = new Gson().fromJson(result, ShopCarBean.class);

                if (getShopCarView!=null){
                    //用于封装一级列表
                    List<SellerBean> groupList = new ArrayList<>();
                    //用于封装二级列表
                    List<List<ShopCarBean.DataBean.ListBean>> childList = new ArrayList<>();
                    //获取数据
                    List<ShopCarBean.DataBean> data = shopCarBean.getData();
                    //循环获取数据
                    for (int i = 0; i < data.size(); i++) {
                        ShopCarBean.DataBean dataBean = data.get(i);
                        //添加父集合
                        SellerBean sellerBean = new SellerBean();
                        sellerBean.setSellerName(dataBean.getSellerName());
                        sellerBean.setSellerid(dataBean.getSellerid());
                        //设置是否全部选中
                        sellerBean.setSelected(isSellerProductAllSeleect(dataBean));
                        groupList.add(sellerBean);
                        //添加子集合
                        childList.add(data.get(i).getList());
                    }
                    //展示数据
                    getShopCarView.showShopCar(groupList,childList);
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    //判断该商家下面的商品是否全选
    private boolean isSellerProductAllSeleect(ShopCarBean.DataBean dataBean){
        //获取该商家下的所有商品
        List<ShopCarBean.DataBean.ListBean> list = dataBean.getList();
        for (int i = 0; i < list.size(); i++) {
            ShopCarBean.DataBean.ListBean listBean = list.get(i);
            if (0==listBean.getSelected()){
                return false;
            }
        }
        return true;
    }

    @Override
    public void detach() {
        if (getShopCarView!=null){
            getShopCarView=null;
        }
    }
}
