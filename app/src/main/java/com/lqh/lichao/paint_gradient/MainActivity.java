package com.lqh.lichao.paint_gradient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RadarView mRadarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ZoomImageView view = new ZoomImageView(this);
//        setContentView(view);
        mRadarView = (RadarView) findViewById(R.id.radarview);
    }

    public void start(View view){
        mRadarView.startScan();
    }

    public void stop(View view){
        mRadarView.stopScan();
    }
}
