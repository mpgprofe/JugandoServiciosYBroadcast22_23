package com.example.jugandoserviciosybroadcast22_23;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Timer;
import java.util.TimerTask;

public class ServicioCrono extends Service {
    Timer temporizador = new Timer();
    Handler handler;
    float contador;
    int INTERVALO = 10;
    static MainActivity mainActivity;

    public static void setMainActivity(MainActivity ma){
        mainActivity = ma;
    }

    public ServicioCrono() {
    }



    @Override
    public void onCreate() {
        super.onCreate();

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                mainActivity.actualizarCrono(String.format("%.2f",contador));
            }
        };
        iniciarCrono();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        temporizador.cancel();
        super.onDestroy();
    }

    private void iniciarCrono() {
        temporizador.schedule(new TimerTask() {
            @Override
            public void run() {
                contador+=0.01;
                handler.sendEmptyMessage(0);
                Log.d("CRONO", "C:"+contador);
            }
        }, 0, INTERVALO);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}