package com.example.dualcampip;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
public class MainActivity extends Activity {
  @Override protected void onCreate(Bundle b){
    super.onCreate(b);
    Button btn=new Button(this); btn.setText("Start Overlay");
    btn.setOnClickListener(v->{
      if(!Settings.canDrawOverlays(this)){
        startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION));
      } else startService(new Intent(this,OverlayService.class));
    });
    setContentView(btn);
  }
}
