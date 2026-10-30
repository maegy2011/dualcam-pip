package com.example.dualcampip;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
public class OverlayService extends Service {
  public IBinder onBind(Intent i){return null;}
  public int onStartCommand(Intent i,int f,int id){
    WindowManager wm=(WindowManager)getSystemService(WINDOW_SERVICE);
    LinearLayout v=new LinearLayout(this); v.setOrientation(LinearLayout.HORIZONTAL);
    v.addView(new TextView(this){{setText("Front");}});
    v.addView(new TextView(this){{setText("Back");}});
    WindowManager.LayoutParams p=new WindowManager.LayoutParams(400,200,WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,PixelFormat.TRANSLUCENT);
    p.gravity=Gravity.TOP|Gravity.START;
    wm.addView(v,p);
    return START_STICKY;
  }
}
