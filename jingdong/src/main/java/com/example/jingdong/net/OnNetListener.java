package com.example.jingdong.net;

public interface OnNetListener {
    void onSuccess(String result);

    void onFailed(Exception e);
}
