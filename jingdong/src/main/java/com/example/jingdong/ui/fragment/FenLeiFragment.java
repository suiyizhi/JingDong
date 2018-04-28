package com.example.jingdong.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.jingdong.R;
import com.example.jingdong.bean.CatagoryBean;
import com.example.jingdong.bean.ChildCataBean;
import com.example.jingdong.presenter.CatagoryPresenterImp;
import com.example.jingdong.presenter.ChildCataPresenterImp;
import com.example.jingdong.ui.adapter.ElvAdapter;
import com.example.jingdong.ui.adapter.RvLeftAdapter;
import com.example.jingdong.ui.inter.CatagoryView;
import com.example.jingdong.ui.inter.ChildCataView;
import com.example.jingdong.ui.inter.OnItemClickListener;

import java.util.List;

public class FenLeiFragment extends Fragment implements CatagoryView,ChildCataView {
    private View view;
    private RecyclerView rvLeft;
    private ExpandableListView elv;
    private CatagoryPresenterImp catagoryPresenterImp;
    private ChildCataPresenterImp childCataPresenterImp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fenlei, container, false);
        //初始化数据
        initView(view);
        //绑定左侧数据
        catagoryPresenterImp = new CatagoryPresenterImp(this);
        catagoryPresenterImp.getCatagory();
        //绑定右侧数据
        childCataPresenterImp = new ChildCataPresenterImp(this);
        return view;
    }


    private void initView(View view) {
        rvLeft = (RecyclerView) view.findViewById(R.id.rvLeft);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvLeft.setLayoutManager(linearLayoutManager);
        rvLeft.addItemDecoration(new DividerItemDecoration(getContext(),RecyclerView.VERTICAL));
        elv = (ExpandableListView) view.findViewById(R.id.elv);
    }
    //显示左侧分类
    @Override
    public void showCatagory(final List<CatagoryBean.DataBean> dataBeans) {
        final RvLeftAdapter rvLeftAdapter = new RvLeftAdapter(dataBeans, getContext());
        rvLeft.setAdapter(rvLeftAdapter);
        //默认显示第一个cid的数据
        childCataPresenterImp.getChildCatagory(dataBeans.get(0).getCid()+"");
        //默认选中第一个数据
        rvLeftAdapter.changeCheck(0,true);
        //点击的监听
        rvLeftAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                rvLeftAdapter.changeCheck(position,true);
                int cid = dataBeans.get(position).getCid();
                childCataPresenterImp.getChildCatagory(cid+"");
            }

            @Override
            public void onItemLongClick(int position) {

            }
        });
    }

    //显示子分类
    @Override
    public void showChildCata(List<String> groupList,List<List<ChildCataBean.DataBean.ListBean>> list) {
        ElvAdapter elvAdapter = new ElvAdapter(groupList, list, getContext());
        elv.setAdapter(elvAdapter);
        //默认展开列表
        for (int i = 0; i < groupList.size(); i++) {
            elv.expandGroup(i);
        }

        elv.setGroupIndicator(null);

        elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });

    }

    //销毁时解绑数据
    @Override
    public void onDestroy() {
        super.onDestroy();
        catagoryPresenterImp.detach();
        childCataPresenterImp.detach();
    }


}
