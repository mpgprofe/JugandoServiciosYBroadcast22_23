package com.example.jugandoserviciosybroadcast22_23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SiLaBateriaCambia siLaBateriaCambia = new SiLaBateriaCambia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Para las diferentes actions : https://chromium.googlesource.com/android_tools/+/refs/heads/main/sdk/platforms/android-28/data/broadcast_actions.txt
        IntentFilter intentFilter = new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        intentFilter.addAction("android.intent.action.BATTERY_LOW");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");


        getBaseContext().registerReceiver(siLaBateriaCambia, intentFilter);

    }

    private class SiLaBateriaCambia extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
                Toast.makeText(context, "Acabas de conectar el cable de carga al móvil.", Toast.LENGTH_SHORT).show();
                Log.d("RECEIVER", "Acabas de conectar el cable de carga al móvil");
            }
            if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
                Toast.makeText(context, "Acabas de desconectar el cable de carga al móvil.", Toast.LENGTH_SHORT).show();
                Log.d("RECEIVER", "Acabas de desconectar el cable de carga al móvil");
            }
            if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)){
                Toast.makeText(context, "Batería baja.", Toast.LENGTH_SHORT).show();
                Log.d("RECEIVER", "Batería baja");
            }
            if (intent.getAction().equals("android.net.wifi.STATE_CHANGE")){
                Toast.makeText(context, "Ha cambiado el estado de la wifi.", Toast.LENGTH_SHORT).show();
                Log.d("RECEIVER", "Ha cambiado el estado de la wifi");
            }
        }
    }
}