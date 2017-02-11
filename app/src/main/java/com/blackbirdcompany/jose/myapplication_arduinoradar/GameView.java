package com.blackbirdcompany.jose.myapplication_arduinoradar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by jose on 09/02/2017.
 */

public class GameView extends SurfaceView {
    private Bitmap bmp;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    float cx,cy;
    private String TAG="AVISO";
    boolean ok=false;
    boolean a,b;
    int angulo=0;
    boolean detectaObjeto;
    boolean posA,posB,posC,posD;

    public GameView(final Context context) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cx==v.getX() && cy==v.getY()){
                    Log.e(TAG,"has tocado la bola");
                }
            }
        });

        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
                a=true;
                b=false;
                detectaObjeto=true;

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });
        //bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onDraw(Canvas canvas) {

        //en primer lugar limpio el canvas antes de empezar a pintar
        Paint pincel=new Paint();

        basePrincipal(canvas,pincel);
        pincel.setStrokeWidth(3);
        //pincel.setARGB(255,172,249,89);
        pincel.setColor(Color.CYAN);
        pincel.setStrokeWidth(5);
        if(a) {
            if (angulo <= 180) {
                pincel.setARGB(255,254, 95, 95);
                pincel.setStrokeWidth(2);
                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                angulo++;
                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                angulo++;
                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                angulo++;


                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                angulo++;
                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                angulo++;
                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                angulo++;
                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                angulo++;
                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                angulo++;
                if(detectaObjeto){
                    objet(canvas,pincel,((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * ((getWidth()/2)-100)))),(float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))));
                    detectaObjeto=false;
                }
                pincel.setARGB(255,255, 0, 0);
                pincel.setStrokeWidth(5);
                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                angulo++;
                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                angulo++;
            }
            else{
                b=true;
                a=false;
                angulo=180;
            }
        }
        if(b) {
            if (angulo > 0) {
                pincel.setARGB(255,254, 95, 95);
                pincel.setStrokeWidth(2);
                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                angulo--;
                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                angulo--;
                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                angulo--;
                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                angulo--;
                pincel.setARGB(255,255, 0, 0);
                pincel.setStrokeWidth(5);
                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                canvas.drawLine(getWidth() / 2, getHeight(), ((float) (getWidth() / 2 - (Math.cos(Math.toRadians(angulo)) * (getWidth() / 2)))), (float) ((getHeight()) - (Math.sin(Math.toRadians(angulo)) * (getWidth() / 2))), pincel);
                angulo--;
            } else {
                b=false;
                a=true;
                angulo = 0;
                detectaObjeto=true;
            }
        }

        /*if(posA) {
            if (incr >0) {
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx - incr, getHeight() / 2 - ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))), pincel);
                //dibujar lineas detras
                incr=incr-5;
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx - incr, getHeight() / 2 - ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))), pincel);
                incr=incr-5;
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx - incr, getHeight() / 2 - ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))), pincel);
                incr=incr-5;
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx - incr, getHeight() / 2 - ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))), pincel);
                incr=incr-5;
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx - incr, getHeight() / 2 - ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))), pincel);
                incr=incr-5;
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx - incr, getHeight() / 2 - ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))), pincel);
                incr=incr-5;

            } else {
                incr=0;
                posA=false;
                posB=true;
            }
        }
        if(posB){
            if (incr < 530) {
                //dibujamos las lineas
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx + incr, getHeight() / 2 - ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))), pincel);
                incr=incr+5;
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx + incr, getHeight() / 2 - ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))), pincel);
                incr=incr+5;
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx + incr, getHeight() / 2 - ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))), pincel);
                incr=incr+5;
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx + incr, getHeight() / 2 - ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))), pincel);
                incr=incr+5;
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx + incr, getHeight() / 2 - ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))), pincel);
                incr=incr+5;
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx + incr, getHeight() / 2 - ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))), pincel);
                incr=incr+5;

            } else {
                incr=530;
                posB=false;
                posC=true;
            }
        }
        if(posC){
            if (incr > 0) {
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx + incr, ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))) + (getHeight() / 2), pincel);
                //dibujamos las lineas
                incr=incr-5;
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx + incr, ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))) + (getHeight() / 2), pincel);
                //dibujamos las lineas
                incr=incr-5;
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx + incr, ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))) + (getHeight() / 2), pincel);
                //dibujamos las lineas
                incr=incr-5;
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx + incr, ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))) + (getHeight() / 2), pincel);
                //dibujamos las lineas
                incr=incr-5;
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx + incr, ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))) + (getHeight() / 2), pincel);
                //dibujamos las lineas
                incr=incr-5;
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx + incr, ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))) + (getHeight() / 2), pincel);
                //dibujamos las lineas
                incr=incr-5;
            } else {
                incr=0;
                posC=false;
                posD=true;
            }
        }
        if(posD){
            if (incr < 530) {
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx - incr, ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))) + getHeight() / 2, pincel);
                //dibujamos las lineas
                incr=incr+5;
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx - incr, ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))) + getHeight() / 2, pincel);
                //dibujamos las lineas
                incr=incr+5;
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx - incr, ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))) + getHeight() / 2, pincel);
                //dibujamos las lineas
                incr=incr+5;
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx - incr, ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))) + getHeight() / 2, pincel);
                //dibujamos las lineas
                incr=incr+5;
                canvas.drawLine(getWidth() / 2, getHeight() / 2, cx - incr, ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))) + getHeight() / 2, pincel);
                //dibujamos las lineas
                incr++;canvas.drawLine(getWidth() / 2, getHeight() / 2, cx - incr, ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(incr, 2))) + getHeight() / 2, pincel);
                //dibujamos las lineas
                incr=incr+5;


            } else {
                incr=530;
                posD=false;
                posA=true;
            }
        }
            /*abajo derecha
            canvas.drawLine(getWidth() / 2, getHeight() / 2, (getWidth() / 2) + i, ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(i, 2))) + getHeight() / 2, pincel);*/
            /*abajo izquierda
            canvas.drawLine(getWidth() / 2, getHeight() / 2, (getWidth() / 2) - i, ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(i, 2))) + getHeight() / 2, pincel);*/
            /*arriba derecha
            canvas.drawLine(getWidth() / 2, getHeight() / 2, (getWidth() / 2) + i, getHeight() / 2 - ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(i, 2))), pincel);*/
            /*arriba izquierda
            canvas.drawLine(getWidth() / 2, getHeight() / 2, (getWidth() / 2) - i, getHeight() / 2 - ((int) Math.sqrt(Math.pow(530, 2) - Math.pow(i, 2))), pincel);*/

    }
    private void objet(Canvas canvas,Paint pincel,float x, float y){
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x,y,50,pincel);
    }
    private void basePrincipal(Canvas canvas,Paint pincel){
        pincel.setColor(Color.BLACK);
        //bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.cone);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), pincel);
        //ahora dibujo el circulo con las coordenadas
        /*
        canvas.drawLine(getWidth()/2, getHeight(),((float) (getWidth()/2-(Math.cos(Math.toRadians(30))*(getWidth()/2)))),(float)((getHeight())-(Math.sin(Math.toRadians(30))*(getWidth()/2))),pincel);
        canvas.drawLine(getWidth()/2, getHeight(),((float) (getWidth()/2-(Math.cos(Math.toRadians(60))*(getWidth()/2)))),(float)((getHeight())-(Math.sin(Math.toRadians(60))*(getWidth()/2))),pincel);
        canvas.drawLine(getWidth()/2, getHeight(),((float) (getWidth()/2-(Math.cos(Math.toRadians(92))*(getWidth()/2)))),(float)((getHeight())-(Math.sin(Math.toRadians(92))*(getWidth()/2))),pincel);
        canvas.drawLine(getWidth()/2, getHeight(),((float) (getWidth()/2-(Math.cos(Math.toRadians(120))*(getWidth()/2)))),(float)((getHeight())-(Math.sin(Math.toRadians(120))*(getWidth()/2))),pincel);
        canvas.drawLine(getWidth()/2, getHeight(),((float) (getWidth()/2-(Math.cos(Math.toRadians(150))*(getWidth()/2)))),(float)((getHeight())-(Math.sin(Math.toRadians(150))*(getWidth()/2))),pincel);
        canvas.drawLine(getWidth()/2, getHeight(),((float) (getWidth()/2-(Math.cos(Math.toRadians(178))*(getWidth()/2)))),(float)((getHeight())-(Math.sin(Math.toRadians(178))*(getWidth()/2))),pincel);
*/
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth(5);
        pincel.setColor(Color.GREEN);
        canvas.drawLine(getWidth()/2, getHeight(),((float) (getWidth()/2-(Math.cos(Math.toRadians(30))*((getWidth()/2)+50)))),(float)((getHeight())-(Math.sin(Math.toRadians(30))*((getWidth()/2)+50))),pincel);
        canvas.drawLine(getWidth()/2, getHeight(),((float) (getWidth()/2-(Math.cos(Math.toRadians(60))*((getWidth()/2)+50)))),(float)((getHeight())-(Math.sin(Math.toRadians(60))*((getWidth()/2)+50))),pincel);
        canvas.drawLine(getWidth()/2, getHeight(),((float) (getWidth()/2-(Math.cos(Math.toRadians(92))*(getWidth()/2)+50))),(float)((getHeight())-(Math.sin(Math.toRadians(92))*(getWidth()/2)+50)),pincel);
        canvas.drawLine(getWidth()/2, getHeight(),((float) (getWidth()/2-(Math.cos(Math.toRadians(120))*((getWidth()/2)+50)))),(float)((getHeight())-(Math.sin(Math.toRadians(120))*((getWidth()/2)+50))),pincel);
        canvas.drawLine(getWidth()/2, getHeight(),((float) (getWidth()/2-(Math.cos(Math.toRadians(150))*((getWidth()/2)+50)))),(float)((getHeight())-(Math.sin(Math.toRadians(150))*((getWidth()/2)+50))),pincel);

        //canvas.drawLine(getWidth()/2,getHeight(),getWidth()/2,getHeight()-getWidth()/2 -50,pincel);
        canvas.drawLine(0,getHeight()-5,getWidth(),getHeight()-5,pincel);
        pincel.setStrokeWidth(5);
        canvas.drawCircle(getWidth()/2,getHeight(),getWidth()/6,pincel);
        canvas.drawCircle(getWidth()/2,getHeight(),getWidth()/3,pincel);
        canvas.drawCircle(getWidth()/2,getHeight(),getWidth()/2,pincel);

    }


}
