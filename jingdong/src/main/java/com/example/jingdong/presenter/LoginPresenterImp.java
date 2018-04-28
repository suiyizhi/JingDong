package com.example.jingdong.presenter;

import com.example.jingdong.bean.UserBean;
import com.example.jingdong.model.LoginModel;
import com.example.jingdong.model.LoginModelImp;
import com.example.jingdong.net.OnNetListener;
import com.example.jingdong.ui.inter.LoginView;
import com.google.gson.Gson;

public class LoginPresenterImp implements LoginPresenter {

    private LoginView loginView;
    private final LoginModelImp loginModelImp;

    public LoginPresenterImp(LoginView loginView) {
        this.loginView=loginView;
        loginModelImp = new LoginModelImp();
    }

    @Override
    public void login() {
        String account = loginView.getAccount();
        String pass = loginView.getPass();
        loginModelImp.login(account, pass, new OnNetListener() {
            @Override
            public void onSuccess(String result) {
                UserBean userBean = new Gson().fromJson(result, UserBean.class);
                if (userBean!=null){
                    loginView.loginSuccess(userBean);
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    @Override
    public void detach() {
        if (loginView!=null){
            loginView=null;
        }
    }
}
