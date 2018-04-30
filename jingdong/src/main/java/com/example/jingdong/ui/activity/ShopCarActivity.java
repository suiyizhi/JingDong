package com.example.jingdong.ui.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.jingdong.R;
import com.example.jingdong.bean.SellerBean;
import com.example.jingdong.bean.ShopCarBean;
import com.example.jingdong.presenter.GetShopCarPresenterImp;
import com.example.jingdong.ui.adapter.ElvShopCarAdapter;
import com.example.jingdong.ui.inter.GetShopCarView;
import com.example.jingdong.util.DialogUtil;
import com.example.jingdong.util.SpUtil;

import java.util.List;

public class ShopCarActivity extends AppCompatActivity implements GetShopCarView{

    private GetShopCarPresenterImp getShopCarPresenterImp;
    private ExpandableListView elv_shopcar;
    private ProgressDialog progressDialog;
    private ElvShopCarAdapter elvShopCarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        //找控件
        elv_shopcar = findViewById(R.id.elv_shopcar);
        progressDialog = DialogUtil.getProgressDialog(this);
        //绑定
        getShopCarPresenterImp = new GetShopCarPresenterImp(this);
        getShopCarPresenterImp.getShopCar(SpUtil.getString(ShopCarActivity.this,"uid",""),SpUtil.getString(ShopCarActivity.this,"token",""));
    }

    //显示数据
    @Override
    public void showShopCar(List<SellerBean> groupList, List<List<ShopCarBean.DataBean.ListBean>> childList) {
        //设置适配器
        elvShopCarAdapter = new ElvShopCarAdapter(groupList, childList, ShopCarActivity.this,getShopCarPresenterImp,progressDialog);
        elv_shopcar.setAdapter(elvShopCarAdapter);

        //默认全部展开
        for (int i = 0; i < groupList.size(); i++) {
            elv_shopcar.expandGroup(i);
        }

        //关闭进度条
        progressDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getShopCarPresenterImp.detach();
    }
}
