package com.codwelt.fingerprintuserphone;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.UUID;

public class App {

    private static final String KEY_GUID = "INSTANCE_GUID_CODWELT";

    private Context context;
    private static App instance;

    private int versionCode;

    public static App getInstance(Context context,int AppVersionCode)
    {
        if(App.instance == null){
            App.instance = new App();
        }
        App.instance.context = context;
        App.instance.versionCode = AppVersionCode;
        return App.instance;
    }


    /**
     * Devuelve el GUID de la instalacion
     * @return
     */
    public String getGUID()
    {
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this.context);

        String uniqueID = preferencias.getString(App.KEY_GUID,App.KEY_GUID);
        //Como no existe en el storage compartido de crea la variable
        if(uniqueID.equals(App.KEY_GUID)){

            //Se obtiene instancia del editor
            SharedPreferences.Editor editor = preferencias.edit();
            uniqueID = UUID.randomUUID().toString();
            String secretKeyEncrip = uniqueID;
            //Se sube la frase secreta a las preferencias
            editor.putString(App.KEY_GUID,secretKeyEncrip);
            Boolean resltado = editor.commit();
            editor.apply();
            return uniqueID;
        }

        return uniqueID;

    }


    public int getVersionCode()
    {
        return this.versionCode;
    }
}
