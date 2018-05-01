package com.example.jingdong.presenter;

import com.example.jingdong.bean.GoodsListBean;
import com.example.jingdong.model.SearchProductModelImp;
import com.example.jingdong.net.OnNetListener;
import com.example.jingdong.ui.inter.SearchProductView;
import com.google.gson.Gson;

public class SearchProductPresenterImp implements SearchProductPresenter{

    private SearchProductView searchProductView;
    private final SearchProductModelImp searchProductModelImp;

    public SearchProductPresenterImp(SearchProductView searchProductView) {
        this.searchProductView=searchProductView;
        searchProductModelImp = new SearchProductModelImp();
    }

    @Override
    public void search() {
        String keywords = searchProductView.getkeywords();
        searchProductModelImp.searchProduct(keywords, new OnNetListener() {
            @Override
            public void onSuccess(String result) {
                GoodsListBean goodsListBean = new Gson().fromJson(result, GoodsListBean.class);
                if (searchProductView!=null){
                    searchProductView.searchSuccess(goodsListBean.getData());
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    @Override
    public void detach() {
        if (searchProductView!=null){
            searchProductView=null;
        }
    }
}
