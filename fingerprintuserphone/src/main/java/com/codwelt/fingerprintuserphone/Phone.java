package com.codwelt.fingerprintuserphone;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;


public class Phone {

    private static Phone instance;
    private Context contex;
    private TelephonyManager telephonyManager;

    private Phone(){

    }

    /**
     * Construlle el telefony Manager para la obtencion de los datos
     */
    private void buildTeleponyManager()
    {
        this.telephonyManager = (TelephonyManager) this.contex.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public static Phone getInstance(Context contex)
    {
        if(Phone.instance == null){
            Phone.instance = new Phone();
        }
        Phone.instance.contex = contex;
        Phone.instance.buildTeleponyManager();
        return Phone.instance;
    }


    /**
     * Devuelve el Contexto de la instancia
     * @return Conext contexto
     */
    private Context getContex()
    {
        return this.contex;
    }


    /**
     * Se encarga de verificar que el la aplicacion tenga el permiso
     */
    private void checkPermission() throws Exception {
        if (ActivityCompat.checkSelfPermission(this.getContex(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            throw new Exception("No tiene los permisos necesarios!!");
        }
    }

    /**
     * Devuelve el Imai del telefono
     * @return string IMEI
     * @throws Exception
     */
    public String getIMEI() throws Exception {
        this.checkPermission();
        String emai;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            emai = this.telephonyManager.getImei();
        }else{
            emai = this.telephonyManager.getDeviceId();
        }
        return emai;
    }


    /**
     * Devuelve el serial de la sim del telefono
     * @return String simserial
     * @throws Exception
     */
    public String getSIMSerial() throws Exception {
        this.checkPermission();
        return this.telephonyManager.getSimSerialNumber();
    }

    /**
     * Devuelve el pais en el formato ISO
     * @return String country ISO
     */
    public String getSIMCountryISO(){
        return this.telephonyManager.getSimCountryIso();
    }

    /**
     * Devuelve el codigo del Operador de la SIM
     * @return String Operador code
     */
    public String getSIMOperatorCode(){

        return this.telephonyManager.getSimOperator();
    }

    /**
     * Devuelve el nombre del operador de la SIM
     * @return String
     */
    public String getSIMOperatorName()
    {
        return this.telephonyManager.getSimOperatorName();
    }

    /**
     * Devuelve la version del SDK del sistema Operativo
     * @return String SDK version
     */
    public Integer getVersionSDK()
    {
        return Build.VERSION.SDK_INT;
    }

    /**
     * Devuelve el modelo del equipo
     * @return
     */
    public String getModel()
    {
        return Build.MODEL;
    }

    /**
     * Devuelve el nombre del Manufacturador
     * @return
     */
    public String getManufacturerName()
    {
        return Build.MANUFACTURER;
    }




}