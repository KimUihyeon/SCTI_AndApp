package com.scti.barcode.util;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;

import com.m3.sdk.scannerlib.BarcodeBroadcast;
import com.m3.sdk.scannerlib.BarcodeListener;
import com.m3.sdk.scannerlib.BarcodeManager;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class BarcodeManagerEx extends BarcodeManager {

    public BarcodeManagerEx(Context context){
        super(context);
    }

    @Override
    public String getBarcode() {
        return "";
    }
}
