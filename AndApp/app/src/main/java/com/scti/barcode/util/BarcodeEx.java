//package com.scti.barcode.util;
//
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.provider.Settings.System;
//import android.util.Log;
//
//public class BarcodeEx {
//    public static final String SCN_CUST_ACTION_SWITCH = "com.android.server.scannerservice.m3onoff";
//    public static final String SCN_CUST_EX_SWITCH = "scanneronoff";
//    public static final String SCN_CUST_ACTION_ICON_STATE = "android.intent.action.ACTION_SCANNER_ENABLE";
//    public static final String SCN_CUST_ACTION_START = "android.intent.action.M3SCANNER_BUTTON_DOWN";
//    public static final String SCN_CUST_ACTION_CANCEL = "android.intent.action.M3SCANNER_BUTTON_UP";
//    public static final String SCANNER_BARCODE_DATA = "m3scannerdata";
//    public static final String SCANNER_BARCODE_CODE_TYPE = "m3scanner_code_type";
//    public static final String SCANNER_MODULE_TYPE = "m3scanner_module_type";
//    public static final String SCN_CUST_ACTION_SETTING_CHANGE = "com.android.server.scannerservice.settingchange";
//    public static final String SCN_CUST_ACTION_PARAM = "android.intent.action.SCANNER_PARAMETER";
//    public static final String SCN_CUST_ACTION_SCODE = "com.android.server.scannerservice.broadcast";
//    private static String TAG = "Barcode";
//    private Context mContext;
//    private SymbologyEx mSymbology;
//
//    public BarcodeEx(Context mContext) {
//        this.mContext = mContext;
//        this.mSymbology = new SymbologyEx(mContext);
//    }
//
//    public SymbologyEx getSymbologyInstance() {
//        return this.mSymbology;
//    }
//
//    public void scanStart() {
//        Intent intent = new Intent("android.intent.action.M3SCANNER_BUTTON_DOWN", (Uri)null);
//        this.mContext.sendOrderedBroadcast(intent, (String)null);
//    }
//
//    public void scanDispose() {
//        Intent intent = new Intent("android.intent.action.M3SCANNER_BUTTON_UP", (Uri)null);
//        this.mContext.sendOrderedBroadcast(intent, (String)null);
//    }
//
//    private boolean isScannerEnable() {
//        int enable = System.getInt(this.mContext.getContentResolver(), "M3SCANNER_POWER_ON", 1);
//        return enable == 1;
//    }
//
//    public void setScanner(boolean enable) {
//        int onOff = 0;
//        if (enable) {
//            onOff = 1;
//        }
//
//        Intent intentSwitch = new Intent("com.android.server.scannerservice.m3onoff", (Uri)null);
//        intentSwitch.putExtra("scanneronoff", onOff);
//        this.mContext.sendOrderedBroadcast(intentSwitch, (String)null);
//    }
//
//    protected void setBarcodeAll(byte[] params) {
//        Intent intent = new Intent("com.android.server.scannerservice.setallparameter");
//        intent.putExtra("scannersetallparameter", params);
//        intent.putExtra("scannersetallparameterlen", 17);
//        this.mContext.sendBroadcast(intent);
//    }
//
//    protected void setBarcodeOpenAll() {
//        Intent intent = new Intent("com.android.server.scannerservice.setallparameter");
//        intent.putExtra("scannersetallparameter", new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1});
//        intent.putExtra("scannersetallparameterlen", 17);
//        this.mContext.sendBroadcast(intent);
//    }
//
//    protected void setBarcodeCloseAll() {
//        Intent intent = new Intent("com.android.server.scannerservice.setallparameter");
//        intent.putExtra("scannersetallparameter", new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
//        intent.putExtra("scannersetallparameterlen", 17);
//        this.mContext.sendBroadcast(intent);
//    }
//
//    public static class SymbologyEx {
//        private int[] symbol = new int[]{1, 2, 12, 4, 3, 7, 0, 8, 9, 10, 11, 6, 5, 408, 338, 340, 339};
//        private Context mContext = null;
//
//        public SymbologyEx(Context context) {
//            this.mContext = context;
//        }
//
//        public boolean setSymbology(int symbology, int paramVal) {
//            boolean ret = false;
//
//            for(int i = 0; i < this.symbol.length; ++i) {
//                if (this.symbol[i] == symbology) {
//                    ret = true;
//                    break;
//                }
//            }
//
//            setCodeType(symbology, paramVal);
//            Intent intent = new Intent("android.intent.action.SCANNER_PARAMETER");
//            intent.putExtra("symbology", symbology);
//            intent.putExtra("value", paramVal);
//            Log.i(com.m3.sdk.scannerlib.Barcode.TAG, "setSymbology [" + symbology + "][" + paramVal + "]");
//            this.mContext.sendOrderedBroadcast(intent, (String)null);
//            return ret;
//        }
//
//        public int getSymbology(int symbology) {
//            int nValue = false;
//            int nValue = getCodeType(symbology);
//            Log.i(com.m3.sdk.scannerlib.Barcode.TAG, "getSymbology [" + symbology + "][" + nValue + "]");
//            Intent intent = new Intent("android.intent.action.SCANNER_PARAMETER");
//            intent.putExtra("symbology", symbology);
//            intent.putExtra("value", -1);
//            this.mContext.sendOrderedBroadcast(intent, (String)null);
//            return nValue;
//        }
//
//        protected static Boolean setCodeType(int Symbology, int value) {
//            Boolean bRet = true;
//            switch(Symbology) {
//                case 0:
//                    com.m3.sdk.scannerlib.Barcode.Symbology.CODE_39.nValue = value;
//                    break;
//                case 1:
//                    com.m3.sdk.scannerlib.Barcode.Symbology.UPC_A.nValue = value;
//                    break;
//                case 2:
//                    com.m3.sdk.scannerlib.Barcode.Symbology.UPC_E.nValue = value;
//                    break;
//                case 3:
//                    com.m3.sdk.scannerlib.Barcode.Symbology.EAN_13.nValue = value;
//                    break;
//                case 4:
//                    com.m3.sdk.scannerlib.Barcode.Symbology.EAN_8.nValue = value;
//                    break;
//                case 5:
//                    com.m3.sdk.scannerlib.Barcode.Symbology.Discrete_2of5.nValue = value;
//                    break;
//                case 6:
//                    com.m3.sdk.scannerlib.Barcode.Symbology.Interleaved_2of5.nValue = value;
//                    break;
//                case 7:
//                    com.m3.sdk.scannerlib.Barcode.Symbology.CODABAR.nValue = value;
//                    break;
//                case 8:
//                    com.m3.sdk.scannerlib.Barcode.Symbology.CODE_128.nValue = value;
//                    break;
//                case 9:
//                    com.m3.sdk.scannerlib.Barcode.Symbology.CODE_93.nValue = value;
//                    break;
//                case 10:
//                    com.m3.sdk.scannerlib.Barcode.Symbology.CODE_11.nValue = value;
//                    break;
//                case 11:
//                    com.m3.sdk.scannerlib.Barcode.Symbology.MSI.nValue = value;
//                    break;
//                case 12:
//                    com.m3.sdk.scannerlib.Barcode.Symbology.UPC_E1.nValue = value;
//                    break;
//                case 338:
//                    com.m3.sdk.scannerlib.Barcode.Symbology.GS1_DATABAR_14.nValue = value;
//                    break;
//                case 339:
//                    com.m3.sdk.scannerlib.Barcode.Symbology.GS1_DATABAR_LIMITED.nValue = value;
//                    break;
//                case 340:
//                    com.m3.sdk.scannerlib.Barcode.Symbology.GS1_DATABAR_EXPANED.nValue = value;
//                    break;
//                case 408:
//                    com.m3.sdk.scannerlib.Barcode.Symbology.Chinese_2of5.nValue = value;
//                    break;
//                default:
//                    bRet = false;
//            }
//
//            return bRet;
//        }
//
//        protected static int getCodeType(int Symbology) {
//            int nRetValue = 0;
//            switch(Symbology) {
//                case 0:
//                    nRetValue = com.m3.sdk.scannerlib.Barcode.Symbology.CODE_39.nValue;
//                    break;
//                case 1:
//                    nRetValue = com.m3.sdk.scannerlib.Barcode.Symbology.UPC_A.nValue;
//                    break;
//                case 2:
//                    nRetValue = com.m3.sdk.scannerlib.Barcode.Symbology.UPC_E.nValue;
//                    break;
//                case 3:
//                    nRetValue = com.m3.sdk.scannerlib.Barcode.Symbology.EAN_13.nValue;
//                    break;
//                case 4:
//                    nRetValue = com.m3.sdk.scannerlib.Barcode.Symbology.EAN_8.nValue;
//                    break;
//                case 5:
//                    nRetValue = com.m3.sdk.scannerlib.Barcode.Symbology.Discrete_2of5.nValue;
//                    break;
//                case 6:
//                    nRetValue = com.m3.sdk.scannerlib.Barcode.Symbology.Interleaved_2of5.nValue;
//                    break;
//                case 7:
//                    nRetValue = com.m3.sdk.scannerlib.Barcode.Symbology.CODABAR.nValue;
//                    break;
//                case 8:
//                    nRetValue = com.m3.sdk.scannerlib.Barcode.Symbology.CODE_128.nValue;
//                    break;
//                case 9:
//                    nRetValue = com.m3.sdk.scannerlib.Barcode.Symbology.CODE_93.nValue;
//                    break;
//                case 10:
//                    nRetValue = com.m3.sdk.scannerlib.Barcode.Symbology.CODE_11.nValue;
//                    break;
//                case 11:
//                    nRetValue = com.m3.sdk.scannerlib.Barcode.Symbology.MSI.nValue;
//                    break;
//                case 12:
//                    nRetValue = com.m3.sdk.scannerlib.Barcode.Symbology.UPC_E1.nValue;
//                    break;
//                case 338:
//                    nRetValue = com.m3.sdk.scannerlib.Barcode.Symbology.GS1_DATABAR_14.nValue;
//                    break;
//                case 339:
//                    nRetValue = com.m3.sdk.scannerlib.Barcode.Symbology.GS1_DATABAR_LIMITED.nValue;
//                    break;
//                case 340:
//                    nRetValue = com.m3.sdk.scannerlib.Barcode.Symbology.GS1_DATABAR_EXPANED.nValue;
//                    break;
//                case 408:
//                    nRetValue = com.m3.sdk.scannerlib.Barcode.Symbology.Chinese_2of5.nValue;
//            }
//
//            Log.i(com.m3.sdk.scannerlib.Barcode.TAG, "getCodeType [" + Symbology + "][" + nRetValue + "]");
//            return nRetValue;
//        }
//
//        public static class GS1_DATABAR_EXPANED {
//            public static final int nCode = 340;
//            public static int nValue = 0;
//
//            public GS1_DATABAR_EXPANED() {
//            }
//        }
//
//        public static class GS1_DATABAR_LIMITED {
//            public static final int nCode = 339;
//            public static int nValue = 0;
//
//            public GS1_DATABAR_LIMITED() {
//            }
//        }
//
//        public static class GS1_DATABAR_14 {
//            public static final int nCode = 338;
//            public static int nValue = 0;
//
//            public GS1_DATABAR_14() {
//            }
//        }
//
//        public static class Chinese_2of5 {
//            public static final int nCode = 408;
//            public static int nValue = 0;
//
//            public Chinese_2of5() {
//            }
//        }
//
//        public static class Discrete_2of5 {
//            public static final int nCode = 5;
//            public static int nValue = 0;
//
//            public Discrete_2of5() {
//            }
//        }
//
//        public static class Interleaved_2of5 {
//            public static final int nCode = 6;
//            public static int nValue = 0;
//
//            public Interleaved_2of5() {
//            }
//        }
//
//        public static class MSI {
//            public static final int nCode = 11;
//            public static int nValue = 0;
//
//            public MSI() {
//            }
//        }
//
//        public static class CODE_11 {
//            public static final int nCode = 10;
//            public static int nValue = 0;
//
//            public CODE_11() {
//            }
//        }
//
//        public static class CODE_93 {
//            public static final int nCode = 9;
//            public static int nValue = 0;
//
//            public CODE_93() {
//            }
//        }
//
//        public static class CODE_128 {
//            public static final int nCode = 8;
//            public static int nValue = 0;
//
//            public CODE_128() {
//            }
//        }
//
//        public static class CODE_39 {
//            public static final int nCode = 0;
//            public static int nValue = 0;
//
//            public CODE_39() {
//            }
//        }
//
//        public static class CODABAR {
//            public static final int nCode = 7;
//            public static int nValue = 0;
//
//            public CODABAR() {
//            }
//        }
//
//        public static class EAN_13 {
//            public static final int nCode = 3;
//            public static int nValue = 0;
//
//            public EAN_13() {
//            }
//        }
//
//        public static class EAN_8 {
//            public static final int nCode = 4;
//            public static int nValue = 0;
//
//            public EAN_8() {
//            }
//        }
//
//        public static class UPC_E1 {
//            public static final int nCode = 12;
//            public static int nValue = 0;
//
//            public UPC_E1() {
//            }
//        }
//
//        public static class UPC_E {
//            public static final int nCode = 2;
//            public static int nValue = 0;
//
//            public UPC_E() {
//            }
//        }
//
//        public static class UPC_A {
//            public static final int nCode = 1;
//            public static int nValue;
//
//            public UPC_A() {
//            }
//        }
//    }
//}
