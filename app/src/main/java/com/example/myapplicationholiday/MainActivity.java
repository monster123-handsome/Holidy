package com.example.myapplicationholiday;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
public Button button;
private ImageView iv1,iv2,iv3;
private Mat scrmat,scrmat2,dismat;
private Bitmap bitmap;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scrmat.release();
        scrmat2.release();
        dismat.release();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniLoadDpenCV();
        init();
    }
    private void  init(){
        iv1=findViewById(R.id.imageView4);
        iv2=findViewById(R.id.imageView5);
        iv3=findViewById(R.id.imageView6);
        button=findViewById(R.id.button2);
        scrmat=new Mat();
        scrmat2=new Mat();
        dismat=new Mat();
        try {
            scrmat= Utils.loadResource(this,R.drawable.bk1);
            scrmat2= Utils.loadResource(this,R.drawable.bk1);
        }catch (IOException e) {
            e.printStackTrace();
        }
        button.setOnClickListener(this);

    }
    private void iniLoadDpenCV(){
        boolean success= OpenCVLoader.initDebug();
        if (success){
            Toast.makeText(this.getApplicationContext(),"Loading OpenCV Libraries...",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this.getApplicationContext(),"WARNING:Could not load OpenCV Libraies!",Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onClick(View v) {
        Core.bitwise_and(scrmat,scrmat2,dismat);
        bitmap=Bitmap.createBitmap(dismat.width(),dismat.height(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(dismat,bitmap);
        iv3.setImageBitmap(bitmap);
    }
}