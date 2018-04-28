package com.example.jingdong.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jingdong.R;
import com.example.jingdong.bean.UserBean;
import com.example.jingdong.presenter.LoginPresenterImp;
import com.example.jingdong.ui.inter.LoginView;
import com.example.jingdong.util.SpUtil;

public class LoginActivity extends AppCompatActivity implements LoginView {

    /**
     * 请输入手机号
     */
    private EditText edit_phone;
    /**
     * 请输入密码
     */
    private EditText edit_pwd;
    private LoginPresenterImp loginPresenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        loginPresenterImp = new LoginPresenterImp(this);
    }
    //点击按钮登录
    public void login(View view){
        loginPresenterImp.login();
    }

    @Override
    public String getAccount() {
        return edit_phone.getText().toString().trim();
    }

    @Override
    public String getPass() {
        return edit_pwd.getText().toString().trim();
    }

    @Override
    public void loginSuccess(UserBean userBean) {
        String msg = userBean.getMsg();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        //登录成功后把主要信息存入sp
        SpUtil.saveString(LoginActivity.this,"uid",userBean.getData().getUid()+"");
        SpUtil.saveString(LoginActivity.this,"username",userBean.getData().getUsername());
        SpUtil.saveString(LoginActivity.this,"token",userBean.getData().getToken());
        SpUtil.saveString(LoginActivity.this,"mobile",userBean.getData().getMobile());
        SpUtil.saveString(LoginActivity.this,"icon",userBean.getData().getIcon()+"");
        //关闭页面
        this.finish();
    }

    private void initView() {
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        edit_pwd = (EditText) findViewById(R.id.edit_pwd);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenterImp.detach();
    }
}
