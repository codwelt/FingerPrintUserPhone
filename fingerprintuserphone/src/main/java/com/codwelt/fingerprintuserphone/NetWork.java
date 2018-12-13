package com.codwelt.fingerprintuserphone;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

public class NetWork {


    private static NetWork instance;
    private Context context;
    private ConnectivityManager connecManager;

    /**
     * Constantes de los tipos de conexion a internet
     */
    public static final String TYPE_WIFI = "WIFI";
    public static final String TYPE_MOBILE = "MOBILE";
    public static final String NONE_NETWORK = "NO_NETWORK"; //No tiene ninguna de las anteriores conexiones
    public static final String NONE_SSID = "NO_SSID!"; //No tiene ninguna de las anteriores conexiones


    private NetWork()
    {

    }

    private Context getContext()
    {
        return this.context;
    }

    /**
     * Construlle la instancia del manejador de la conexion
     */
    private void buildConnectManager()
    {
        this.connecManager = (ConnectivityManager) this.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static NetWork getInstance(Context context)
    {
        if(NetWork.instance == null){
            NetWork.instance = new NetWork();
        }
        NetWork.instance.context = context;
        NetWork.instance.buildConnectManager();
        return NetWork.instance;
    }

    /**
     * Valida si se tiene conexion de intenet
     * @return
     */
    public boolean existConnection()
    {
        if(this.connecManager != null){
            NetworkInfo infonet = this.connecManager.getActiveNetworkInfo();
            if(infonet != null && infonet.isConnected()){
                return true;
            }
        }
        return false;
    }

    /**
     * Devuelve el nombrede del tipo de conexion a internet que tiene el dispositivo
     * @return
     */
    public String getNameType(){

        if(this.existConnection()){
            NetworkInfo netInfo = this.connecManager.getActiveNetworkInfo();

            //Se verifica que tenga una version anterior a la version que se depreca el metodo
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.P){
                return netInfo.getTypeName().toUpperCase();
            }

            Network activeNet = this.connecManager.getActiveNetwork();
            NetworkCapabilities netCpas = this.connecManager.getNetworkCapabilities(activeNet);
            if(netCpas.hasCapability(NetworkCapabilities.NET_CAPABILITY_WIFI_P2P)){
                return NetWork.TYPE_WIFI;
            }else{
                return NetWork.TYPE_MOBILE;
            }
        }
        return null;
    }

    public String getSSIDWifi()
    {
        if(this.existConnection()){
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.P){
                final android.net.NetworkInfo wifi = this.connecManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if(wifi.isConnected()){
                    WifiManager wifiManager = (WifiManager) this.context.getSystemService (Context.WIFI_SERVICE);
                    WifiInfo info = wifiManager.getConnectionInfo ();
                    return info.getSSID().replace("\"","");
                }
            }
        }

        return null;
    }


}