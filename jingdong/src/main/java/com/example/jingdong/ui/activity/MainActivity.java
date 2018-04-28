package com.example.jingdong.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.jingdong.R;
import com.example.jingdong.ui.fragment.FaXianFragment;
import com.example.jingdong.ui.fragment.FenLeiFragment;
import com.example.jingdong.ui.fragment.HomePageFragment;
import com.example.jingdong.ui.fragment.MyFragment;
import com.example.jingdong.ui.fragment.ShopCarFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frame_layout;
    private RadioGroup rg_main;
    private HomePageFragment homePageFragment;
    private FenLeiFragment fenLeiFragment;
    private FaXianFragment faXianFragment;
    private ShopCarFragment shopCarFragment;
    private MyFragment myFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        homePageFragment = new HomePageFragment();
        fenLeiFragment = new FenLeiFragment();
        faXianFragment = new FaXianFragment();
        shopCarFragment = new ShopCarFragment();
        myFragment = new MyFragment();

        //底部按钮监听
        setListener();

        fragmentManager = getSupportFragmentManager();

        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, homePageFragment)
                .add(R.id.frame_layout, fenLeiFragment)
                .add(R.id.frame_layout, faXianFragment)
                .add(R.id.frame_layout, shopCarFragment)
                .add(R.id.frame_layout, myFragment)
                .show(homePageFragment).hide(fenLeiFragment).hide(faXianFragment)
                .hide(shopCarFragment).hide(myFragment).commit();


    }

    private void setListener() {
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_homepage:
                        fragmentManager.beginTransaction().show(homePageFragment).hide(fenLeiFragment).hide(faXianFragment).hide(shopCarFragment).hide(myFragment).commit();
                        break;
                    case R.id.rb_fenlei:
                        fragmentManager.beginTransaction().show(fenLeiFragment).hide(homePageFragment).hide(faXianFragment).hide(shopCarFragment).hide(myFragment).commit();
                        break;
                    case R.id.rb_faxian:
                        fragmentManager.beginTransaction().show(faXianFragment).hide(fenLeiFragment).hide(homePageFragment).hide(shopCarFragment).hide(myFragment).commit();
                        break;
                    case R.id.rb_shopcar:
                        fragmentManager.beginTransaction().show(shopCarFragment).hide(fenLeiFragment).hide(homePageFragment).hide(faXianFragment).hide(myFragment).commit();
                        break;
                    case R.id.rb_my:
                        fragmentManager.beginTransaction().show(myFragment).hide(fenLeiFragment).hide(homePageFragment).hide(shopCarFragment).hide(faXianFragment).commit();
                        break;

                }
            }
        });
    }

    private void initView() {
        frame_layout = (FrameLayout) findViewById(R.id.frame_layout);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
    }
}
