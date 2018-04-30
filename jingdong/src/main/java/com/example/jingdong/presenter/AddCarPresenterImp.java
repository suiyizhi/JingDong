package com.example.jingdong.presenter;

import com.example.jingdong.bean.BaseBean;
import com.example.jingdong.model.AddCarModelImp;
import com.example.jingdong.net.OnNetListener;
import com.example.jingdong.ui.inter.AddCarView;
import com.example.jingdong.util.SpUtil;
import com.google.gson.Gson;

public class AddCarPresenterImp implements AddCarPresenter {

    private AddCarView addCarView;
    private final AddCarModelImp addCarModelImp;

    public AddCarPresenterImp(AddCarView addCarView) {
        this.addCarView = addCarView;
        addCarModelImp = new AddCarModelImp();
    }

    @Override
    public void addCar(String uid, String pid, String token) {

        addCarModelImp.addCart(uid, pid, token, new OnNetListener() {
            @Override
            public void onSuccess(String result) {
                if (addCarView != null) {
                    BaseBean baseBean = new Gson().fromJson(result, BaseBean.class);
                    addCarView.showMsg(baseBean.getMsg());
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });

    }

    @Override
    public void detach() {
        if (addCarView != null) {
            addCarView = null;
        }
    }
}
