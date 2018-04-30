package com.example.jingdong.presenter;

import com.example.jingdong.model.UpdateCarModelImp;
import com.example.jingdong.net.OnNetListener;
import com.example.jingdong.ui.inter.UpdateCarView;

public class UpdateCarPresenterImp implements UpdateCarPresenter{

    private UpdateCarView updateCarView;
    private final UpdateCarModelImp updateCarModelImp;

    public UpdateCarPresenterImp(UpdateCarView updateCarView) {
        this.updateCarView=updateCarView;
        updateCarModelImp = new UpdateCarModelImp();
    }

    @Override
    public void updateCar(String uid, String sellerid, String pid, String num, String selected, String token) {
        updateCarModelImp.updateCar(uid, sellerid, pid, num, selected, token, new OnNetListener() {
            @Override
            public void onSuccess(String result) {
                updateCarView.updateSuccess();
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    @Override
    public void detach() {
        if (updateCarView!=null){
            updateCarView=null;
        }
    }
}
