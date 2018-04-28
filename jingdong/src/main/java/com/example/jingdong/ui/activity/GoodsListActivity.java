package com.example.jingdong.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jingdong.R;
import com.example.jingdong.bean.GoodsListBean;
import com.example.jingdong.presenter.GoodsListPresenterImp;
import com.example.jingdong.ui.adapter.GoodsListRvAdapter;
import com.example.jingdong.ui.inter.GoodsListView;

import java.util.List;

public class GoodsListActivity extends AppCompatActivity implements GoodsListView{

    private RecyclerView rv_goodslist;
    private GoodsListPresenterImp goodsListPresenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        initView();
        //获得传来的值
        Intent intent = getIntent();
        int pscid = intent.getIntExtra("pscid", 0);

        goodsListPresenterImp = new GoodsListPresenterImp(this);
        goodsListPresenterImp.getGoods(pscid+"");
    }

    private void initView() {
        rv_goodslist = (RecyclerView) findViewById(R.id.rv_goodslist);
        rv_goodslist.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showGoods(List<GoodsListBean.DataBean> data) {
        GoodsListRvAdapter goodsListRvAdapter = new GoodsListRvAdapter(data, this);
        rv_goodslist.setAdapter(goodsListRvAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        goodsListPresenterImp.detach();
    }
}
