package com.codwelt.fingerprintuserphoneapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.codwelt.fingerprintuserphone.FingerprintUserPhone;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            JSONObject js = FingerprintUserPhone.toJson(this,1);
            Log.d("Accccc", js.toString());
        } catch (Exception e) {
            Log.e("Accccc", "" + e.getMessage());
        }
    }
}
