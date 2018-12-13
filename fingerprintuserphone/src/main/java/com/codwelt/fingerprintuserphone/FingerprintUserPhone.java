package com.codwelt.fingerprintuserphone;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

public class FingerprintUserPhone {


    /**
     *  Hace la construccion del json de los datos obtenidos del equipo 
     * @return
     */
    public static JSONObject toJson(Context context,int AppVersion) {
        Phone phone = Phone.getInstance(context);
        JSONObject jsObRoot = new JSONObject();


        //Phone
        JSONObject jsPhone = new JSONObject();

        try {
            jsPhone.put("imei",phone.getIMEI());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsSIM = new JSONObject();
        try {
            jsSIM.put("serial", phone.getSIMSerial());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            jsSIM.put("countyISO", phone.getSIMCountryISO());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsSimOperator  = new JSONObject();
        try {
            jsSimOperator.put("code", phone.getSIMOperatorCode());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsSimOperator.put("name",phone.getSIMOperatorName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsSIM.put("operator", jsSimOperator);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            jsPhone.put("sim", jsSIM);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsOS = new JSONObject();
        try {
            jsOS.put("versionSDK",phone.getVersionSDK());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsOS.put("name", "ANDROID");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsPhone.put("os",jsOS);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            jsPhone.put("model", phone.getModel());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsPhone.put("manufacturer", phone.getManufacturerName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsObRoot.put("phone",jsPhone);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //Network
        NetWork net = NetWork.getInstance(context);

        JSONObject jsNet = new JSONObject();
        try {
            jsNet.put("type",net.getNameType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsNet.put("ssid",net.getSSIDWifi());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsObRoot.put("network",jsNet);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //App

        App app = App.getInstance(context,AppVersion);

        JSONObject jsApp = new JSONObject();
        try {
            jsApp.put("versionCode", AppVersion);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsApp.put("guid",app.getGUID());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  jsObRoot;
    }
}
