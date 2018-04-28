package com.example.jingdong.presenter;

import com.example.jingdong.bean.AdBean;
import com.example.jingdong.model.AdModelImp;
import com.example.jingdong.net.OnNetListener;
import com.example.jingdong.ui.inter.AdView;
import com.google.gson.Gson;

public class AdPresenterImp implements AdPresenter{

    private AdView adView;
    private final AdModelImp adModelImp;

    public AdPresenterImp(AdView adView) {
        this.adView=adView;
        adModelImp = new AdModelImp();
    }

    @Override
    public void getAd() {
        adModelImp.getAd(new OnNetListener() {
            @Override
            public void onSuccess(String result) {
                AdBean adBean = new Gson().fromJson(result, AdBean.class);
                if ("0".equals(adBean.getCode())){
                    if (adView!=null){
                        adView.showBanner(adBean);
                    }
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    @Override
    public void detach() {
        if (adView!=null){
            adView=null;
        }
    }
}
