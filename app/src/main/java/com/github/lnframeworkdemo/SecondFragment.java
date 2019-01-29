package com.github.lnframeworkdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.github.fragmention.SupportFragment;

/**
 * 类描述
 * 创建者:tiny
 * 日期:17/10/30
 */

public class SecondFragment extends SupportFragment {

    private WebView wv_test;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.second_fragment, container, false);
        TextView tv_test = (TextView) view.findViewById(R.id.tv_test);
        wv_test = (WebView) view.findViewById(R.id.wv_test);
        WebSettings settings = wv_test.getSettings();
        settings.setJavaScriptEnabled(true);

        wv_test.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        wv_test.loadUrl("http://www.baidu.com");
        tv_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wv_test.canGoBack()) {
                    wv_test.goBack();
                }
            }
        });
        return view;
    }
}
