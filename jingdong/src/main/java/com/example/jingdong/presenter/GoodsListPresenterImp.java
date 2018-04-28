package com.example.jingdong.presenter;

import com.example.jingdong.bean.GoodsListBean;
import com.example.jingdong.model.GoodsListModelImp;
import com.example.jingdong.net.OnNetListener;
import com.example.jingdong.ui.inter.GoodsListView;
import com.google.gson.Gson;

import java.util.List;

public class GoodsListPresenterImp implements GoodsListPresenter{

    private GoodsListView goodsListView;
    private final GoodsListModelImp goodsListModelImp;

    public GoodsListPresenterImp(GoodsListView goodsListView) {
        this.goodsListView=goodsListView;
        goodsListModelImp = new GoodsListModelImp();
    }

    @Override
    public void getGoods(String pscid) {
        goodsListModelImp.getGoods(pscid, new OnNetListener() {
            @Override
            public void onSuccess(String result) {
                GoodsListBean goodsListBean = new Gson().fromJson(result, GoodsListBean.class);
                List<GoodsListBean.DataBean> data = goodsListBean.getData();
                if (goodsListView!=null){
                    goodsListView.showGoods(data);
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    @Override
    public void detach() {
        if (goodsListView!=null){
            goodsListView=null;
        }
    }
}
