package com.example.jingdong.net;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUtils {
    private static OkhttpUtils instance;
    private final OkHttpClient okHttpClient;
    private final Handler handler;

    private OkhttpUtils() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)//连接超时
                .writeTimeout(20, TimeUnit.SECONDS)//写入超时
                .readTimeout(20, TimeUnit.SECONDS)//读取超时
                .addInterceptor(new MyIntercepter())
                .build();
        handler = new Handler(Looper.getMainLooper());
    }

    public static OkhttpUtils getInstance() {
        if (instance == null) {
            instance = new OkhttpUtils();
        }
        return instance;
    }

    /**
     * GET请求
     *
     * @param url
     */
    public void doGet(String url, final OnNetListener onNetListener) {
        //创建Request
        final Request request = new Request.Builder()
                .url(url)
                .build();
        //发送请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onFailed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //拿到服务器返回的数据
                final String string = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(string);
                    }
                });
            }
        });
    }

    //post请求方法
    public void doPost(String url, Map<String, String> params, final OnNetListener onNetListener) {
        if (params == null) {
            return;
        }
        //创建Requeset对象
        FormBody.Builder builder = new FormBody.Builder();
        //添加参数
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        //请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onNetListener.onFailed(e);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                final String string = response.body().string();

                //把回调放到主线程里
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(string);
                    }
                });

//                onNetListener.onSuccess(string);
            }
        });
    }
}
