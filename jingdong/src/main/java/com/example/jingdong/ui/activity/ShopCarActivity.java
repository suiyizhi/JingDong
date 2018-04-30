package com.example.jingdong.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.jingdong.R;
import com.example.jingdong.bean.SellerBean;
import com.example.jingdong.bean.ShopCarBean;
import com.example.jingdong.presenter.GetShopCarPresenterImp;
import com.example.jingdong.ui.adapter.ElvShopCarAdapter;
import com.example.jingdong.ui.inter.GetShopCarView;
import com.example.jingdong.util.DialogUtil;
import com.example.jingdong.util.SpUtil;

import java.util.List;

public class ShopCarActivity extends AppCompatActivity implements GetShopCarView {

    private GetShopCarPresenterImp getShopCarPresenterImp;
    private ExpandableListView elv_shopcar;
    private ProgressDialog progressDialog;
    private ElvShopCarAdapter elvShopCarAdapter;
    private TextView tv_money;
    private TextView tv_total;
    /**
     * 全选
     */
    private CheckBox ck_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        initView();
        //找控件
        elv_shopcar = findViewById(R.id.elv_shopcar);
        tv_money = findViewById(R.id.tv_Money);
        tv_total = findViewById(R.id.tv_Total);
        progressDialog = DialogUtil.getProgressDialog(this);
        //绑定
        getShopCarPresenterImp = new GetShopCarPresenterImp(this);
        getShopCarPresenterImp.getShopCar(SpUtil.getString(ShopCarActivity.this, "uid", ""), SpUtil.getString(ShopCarActivity.this, "token", ""));
    }

    private void initView() {
        ck_all = (CheckBox) findViewById(R.id.ck_all);
        //点击全选按钮的监听
        ck_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (elvShopCarAdapter!=null){
                    progressDialog.show();
                    elvShopCarAdapter.changeAllState(ck_all.isChecked());
                }
            }
        });
    }

    //显示数据
    @Override
    public void showShopCar(List<SellerBean> groupList, List<List<ShopCarBean.DataBean.ListBean>> childList) {
        //判断所有商家是否全部选中
        ck_all.setChecked(isSellerAllSelected(groupList));
        //设置适配器
        elvShopCarAdapter = new ElvShopCarAdapter(groupList, childList, ShopCarActivity.this, getShopCarPresenterImp, progressDialog);
        elv_shopcar.setAdapter(elvShopCarAdapter);

        //获取商品总价和数量
        String[] strings = elvShopCarAdapter.computeMoneyAndNum();
        tv_money.setText("总计:" + strings[0] + "元");
        tv_total.setText("去结算" + strings[1] + "个");

        //默认全部展开
        for (int i = 0; i < groupList.size(); i++) {
            elv_shopcar.expandGroup(i);
        }

        //关闭进度条
        progressDialog.dismiss();
    }

    private boolean isSellerAllSelected(List<SellerBean> groupList){
        for (int i = 0; i < groupList.size(); i++) {
            SellerBean sellerBean = groupList.get(i);
            if (!sellerBean.isSelected()){
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getShopCarPresenterImp.detach();
    }

}
