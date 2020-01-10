package com.scti.barcode.config;

import java.util.HashMap;

public class IPContainer {

    private static HashMap<String,String> ipContainer;

    private IPContainer(){
    }

    private static void createContainer(){
        ipContainer = new HashMap<>();

        ipContainer.put("HOME_NODE_SERVER","http://192.168.123.101:3000/");
        ipContainer.put("localhost","http://10.0.2.2:3000/");
        ipContainer.put("SICT_ye","http://192.168.0.25:8088/Mobile/Views/login.aspx"); // 영은 로컬호스트
        ipContainer.put("Demo","http://demo.safetia.co.kr/SCTI/Mobile/Views/login.aspx"); // 데모서버
    }

    public static String getIP(String key){
        if(ipContainer==null){
            createContainer();
        }

        if(ipContainer.containsKey(key)){
            return ipContainer.get(key);
        }
        return  null;
    }
}
