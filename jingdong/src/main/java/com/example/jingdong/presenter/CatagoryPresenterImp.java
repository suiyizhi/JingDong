package com.example.jingdong.presenter;

import com.example.jingdong.bean.CatagoryBean;
import com.example.jingdong.model.CatagoryModelImp;
import com.example.jingdong.net.OnNetListener;
import com.example.jingdong.ui.inter.CatagoryView;
import com.google.gson.Gson;

public class CatagoryPresenterImp implements CatagoryPresenter{

    private CatagoryView catagoryView;
    private final CatagoryModelImp catagoryModelImp;

    public CatagoryPresenterImp(CatagoryView catagoryView) {
        this.catagoryView=catagoryView;
        catagoryModelImp = new CatagoryModelImp();
    }

    @Override
    public void getCatagory() {
        catagoryModelImp.getCatagory(new OnNetListener() {
            @Override
            public void onSuccess(String result) {
                CatagoryBean catagoryBean = new Gson().fromJson(result, CatagoryBean.class);
                if ("0".equals(catagoryBean.getCode())){
                    if (catagoryView!=null){
                        catagoryView.showCatagory(catagoryBean.getData());
                    }
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    public void detach(){
        if (catagoryView!=null){
            catagoryView=null;
        }
    }
}
