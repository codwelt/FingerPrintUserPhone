package com.codwelt.fingerprintuserphone;

import android.content.Context;

import org.json.JSONObject;

public class FingerprintUserPhone {


    /**
     *
     * @return
     */
    public static JSONObject toJson(Context context,int AppVersion) throws Exception {
        Phone phone = Phone.getInstance(context);
        JSONObject jsObRoot = new JSONObject();


        //Phone
        JSONObject jsPhone = new JSONObject();
        jsPhone.put("imei",phone.getIMEI());

        JSONObject jsSIM = new JSONObject();
        jsSIM.put("serial", phone.getSIMSerial());
        jsSIM.put("countyISO", phone.getSIMCountryISO());

        JSONObject jsSimOperator  = new JSONObject();
        jsSimOperator.put("code", phone.getSIMOperatorCode());
        jsSimOperator.put("name",phone.getSIMOperatorName());
        jsSIM.put("operator", jsSimOperator);

        jsPhone.put("sim", jsSIM);

        JSONObject jsOS = new JSONObject();
        jsOS.put("versionSDK",phone.getVersionSDK());
        jsOS.put("name", "ANDROID");
        jsPhone.put("os",jsOS);

        jsPhone.put("model", phone.getModel());
        jsPhone.put("manufacturer", phone.getManufacturerName());
        jsObRoot.put("phone",jsPhone);



        //Network
        NetWork net = NetWork.getInstance(context);

        JSONObject jsNet = new JSONObject();
        jsNet.put("type",net.getNameType());
        jsNet.put("ssid",net.getSSIDWifi());
        jsObRoot.put("network",jsNet);

        //App

        App app = App.getInstance(context,AppVersion);

        JSONObject jsApp = new JSONObject();
        jsApp.put("versionCode", AppVersion);
        jsApp.put("guid",app.getGUID());

        return  jsObRoot;
    }
}
