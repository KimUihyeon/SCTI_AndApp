package com.scti.barcode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.m3.sdk.scannerlib.Barcode;
import com.m3.sdk.scannerlib.BarcodeListener;
import com.m3.sdk.scannerlib.BarcodeManager;
import com.scti.barcode.config.IPContainer;
import com.scti.barcode.util.BarcodeManagerEx;
import com.scti.barcode.util.ConstantValues;
import com.scti.barcode.util.NativeJavascriptBridge;
import com.scti.barcode.util.NetworkStatus;
import com.scti.barcode.util.WebViewClientImpl;

import java.util.HashMap;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private Barcode mBarcode = null;
    private BarcodeListener mListener = null;
    private BarcodeManagerEx mManager = null;
    private Barcode.Symbology mSymbology = null;

    private WebView webView = null;
    private NativeJavascriptBridge javascript = null;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int networkStatus = NetworkStatus.getConnectivityStatus(getApplicationContext());
        if(networkStatus == NetworkStatus.TYPE_WIFI || networkStatus== NetworkStatus.TYPE_MOBILE) {

            //String startUrl = IPContainer.getIP("SICT_ye");
            String startUrl = IPContainer.getIP("Demo");
            System.out.println(startUrl);
            createWebView(startUrl);
            scannerSetting();
        }
        else {
            showToast("인터넷이 연결되지 않았습니다.");
        }

    }

    public void scannerSetting(){
        mBarcode = new Barcode(this);
        mManager = new BarcodeManagerEx(this);
        mSymbology = mBarcode.getSymbologyInstance();
        mBarcode.setScanner(true);


        Intent intent = new Intent(ConstantValues.SCANNER_ACTION_SETTING_CHANGE);
        intent.putExtra("setting", "output_mode");
        intent.putExtra("output_mode_value", 2);
        this.sendOrderedBroadcast(intent, null);


        mListener = new BarcodeListener() {
            @Override
            public void onBarcode(String strBarcode) {
                Log.i("ScannerTest","result="+strBarcode);
            }

            @Override
            public void onBarcode(String barcode, String codeType) {

                if(javascript != null){
                    showToast("data: " + barcode + "  type:" + codeType);
                    javascript.onBarcode(barcode,codeType);
                }
            }

            @Override
            public void onGetSymbology(int i, int i1) {

            }
        };

        mManager.addListener(mListener);
    }


    public void createWebView(String startUrl){

        //showToast(startUrl);

        webView = (WebView)this.findViewById(R.id.webView);
        webView.loadUrl(startUrl); // == 로컬호스트
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClientImpl());

        javascript =new NativeJavascriptBridge(webView);
        webView.addJavascriptInterface(javascript, getString(R.string.browser_prop) );
    }


    private void showToast(String context){
        Toast toast = Toast.makeText(this, context, Toast.LENGTH_SHORT);
        toast.show();
    }

}
