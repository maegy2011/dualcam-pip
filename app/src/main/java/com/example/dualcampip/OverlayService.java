package com.example.dualcampip;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleService;
public class OverlayService extends LifecycleService {
  public IBinder onBind(Intent i){return null;}
  public int onStartCommand(Intent i,int f,int id){
    super.onStartCommand(i,f,id);
    WindowManager wm=(WindowManager)getSystemService(WINDOW_SERVICE);
    LinearLayout root=new LinearLayout(this); root.setOrientation(LinearLayout.HORIZONTAL);
    PreviewView front=new PreviewView(this); PreviewView back=new PreviewView(this);
    root.addView(front, new LinearLayout.LayoutParams(400,400));
    root.addView(back, new LinearLayout.LayoutParams(400,400));
    WindowManager.LayoutParams p=new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,PixelFormat.TRANSLUCENT);
    p.gravity=Gravity.TOP|Gravity.START;
    wm.addView(root,p);
    ProcessCameraProvider.getInstance(this).addListener(()->{
      try{
        ProcessCameraProvider cp=ProcessCameraProvider.getInstance(this).get();
        Preview pf=new Preview.Builder().build(); pf.setSurfaceProvider(front.getSurfaceProvider());
        Preview pb=new Preview.Builder().build(); pb.setSurfaceProvider(back.getSurfaceProvider());
        cp.bindToLifecycle(this, androidx.camera.core.CameraSelector.DEFAULT_FRONT_CAMERA, pf);
        cp.bindToLifecycle(this, androidx.camera.core.CameraSelector.DEFAULT_BACK_CAMERA, pb);
      }catch(Exception e){}
    }, ContextCompat.getMainExecutor(this));
    return START_STICKY;
  }
}
