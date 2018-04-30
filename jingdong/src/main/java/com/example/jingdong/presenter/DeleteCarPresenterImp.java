package com.example.jingdong.presenter;

import com.example.jingdong.model.DeleteCarModelImp;
import com.example.jingdong.net.OnNetListener;
import com.example.jingdong.ui.inter.DeleteCarView;

public class DeleteCarPresenterImp implements DeleteCarPresenter {

    private DeleteCarView deleteCarView;
    private final DeleteCarModelImp deleteCarModelImp;

    public DeleteCarPresenterImp(DeleteCarView deleteCarView) {
        this.deleteCarView=deleteCarView;
        deleteCarModelImp = new DeleteCarModelImp();
    }

    @Override
    public void deleteCar(String uid, String pid, String token) {
        deleteCarModelImp.deleteCar(uid, pid, token, new OnNetListener() {
            @Override
            public void onSuccess(String result) {
                deleteCarView.delSuccess();
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
