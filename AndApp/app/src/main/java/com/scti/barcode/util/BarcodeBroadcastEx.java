//package com.scti.barcode.util;
//
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//import com.m3.sdk.scannerlib.Barcode.Symbology;
//import com.m3.sdk.scannerlib.BarcodeManager;
//
//public class BarcodeBroadcastEx extends BroadcastReceiver {
//    private String barcode;
//    private String type;
//    private String module;
//    private BarcodeManagerEx bm;
//    private String TAG = "BarcodeBroadcast";
//
//    BarcodeBroadcastEx(BarcodeManagerEx bm) {
//        this.bm = bm;
//    }
//
//    public void onReceive(Context context, Intent intent) {
//        Log.i(this.TAG, "onReceive [" + intent.getAction() + "]");
//        if (intent.getAction().equals("com.android.server.scannerservice.broadcast")) {
//            this.barcode = intent.getExtras().getString("m3scannerdata");
//            this.type = intent.getExtras().getString("m3scanner_code_type");
//            this.module = intent.getExtras().getString("m3scanner_module_type");
//            if (this.barcode != null) {
//                this.bm.sendBarcode(this.barcode);
//                this.bm.sendBarcode(this.barcode, this.type, this.module);
//            } else {
//                int nSymbol = intent.getExtras().getInt("symbology", -1);
//                int nValue = intent.getExtras().getInt("value", -1);
//                Log.i(this.TAG, "getSymbology [" + nSymbol + "][" + nValue + "]");
//                if (nSymbol != -1) {
//                    this.bm.sendSymbology(nSymbol, nValue);
//                    Symbology.setCodeType(nSymbol, nValue);
//                }
//            }
//        }
//
//    }
//}
