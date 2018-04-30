package com.example.jingdong.presenter;

public interface UpdateCarPresenter {
    void updateCar(String uid, String sellerid, String pid, String num, String selected, String token);
    void detach();
}
