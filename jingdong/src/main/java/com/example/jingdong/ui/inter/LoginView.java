package com.example.jingdong.ui.inter;

import com.example.jingdong.bean.UserBean;

public interface LoginView {
    String getAccount();
    String getPass();
    void loginSuccess(UserBean userBean);
}
