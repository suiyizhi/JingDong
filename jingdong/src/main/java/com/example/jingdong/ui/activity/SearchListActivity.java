package com.example.jingdong.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jingdong.R;
import com.example.jingdong.bean.GoodsListBean;
import com.example.jingdong.presenter.SearchProductPresenterImp;
import com.example.jingdong.ui.adapter.GoodsListRvAdapter;
import com.example.jingdong.ui.inter.SearchProductView;

import java.util.List;

public class SearchListActivity extends AppCompatActivity implements SearchProductView{

    private String keyword;
    private ImageView img_searchback;
    /**
     * 请输入搜索内容
     */
    private TextView tv_keywords;
    private RecyclerView rv_goodslist;
    private SearchProductPresenterImp searchProductPresenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
        //获取传过来的数据
        Intent intent = getIntent();
        keyword = intent.getStringExtra("keyword");
        initView();

        //设置搜索栏的关键字
        tv_keywords.setText(keyword);

        //绑定搜索p层
        searchProductPresenterImp = new SearchProductPresenterImp(this);
        searchProductPresenterImp.search();
    }

    private void initView() {
        img_searchback = (ImageView) findViewById(R.id.img_searchback);
        //点击回退的监听
        img_searchback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_keywords = (TextView) findViewById(R.id.tv_keywords);
        rv_goodslist = (RecyclerView) findViewById(R.id.rv_goodslist);
        rv_goodslist.setLayoutManager(new LinearLayoutManager(this));
    }

    //搜索成功的回调
    @Override
    public void searchSuccess(List<GoodsListBean.DataBean> data) {
        GoodsListRvAdapter goodsListRvAdapter = new GoodsListRvAdapter(data, SearchListActivity.this);
        //设置适配器
        rv_goodslist.setAdapter(goodsListRvAdapter);
        //设置条目点击事件
        goodsListRvAdapter.setOnListItemClickListener(new GoodsListRvAdapter.OnListItemClickListener() {
            @Override
            public void OnItemClick(GoodsListBean.DataBean dataBean) {
                Intent intent = new Intent(SearchListActivity.this, ListDetailsActivity.class);
                intent.putExtra("bean",dataBean);
                startActivity(intent);
            }
        });
    }

    @Override
    public String getkeywords() {
        return keyword;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchProductPresenterImp.detach();
    }
}
