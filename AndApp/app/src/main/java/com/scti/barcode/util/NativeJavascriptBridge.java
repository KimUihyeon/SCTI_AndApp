package com.scti.barcode.util;

import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class NativeJavascriptBridge {

    private  final Handler handler = new Handler();

    private WebView webView;


    public NativeJavascriptBridge(WebView webView){
        this.webView = webView;
    }

    @JavascriptInterface
    public void sendData(final String log){
        handler.post(new Runnable() {
            @Override
            public void run() {
                System.out.println(log);
                // WEB -> APP
            }
        });
    }

    public void onBarcode (final String msg, final String type){
        handler.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:scanBarcode('" + msg+ "','"+type+"')");
                // App - > Web
            }
        });
    }
}
