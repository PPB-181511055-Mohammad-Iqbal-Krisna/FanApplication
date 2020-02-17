package com.example.fanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggleButton;
    ImageView fan;
    ObjectAnimator  rotateAnimator;
    Switch switchButton;
    SeekBar speed;

    final int SPEED[]= {0,3000,1000,500,250};
    GradientDrawable gd = new GradientDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton= (ToggleButton) findViewById(R.id.toggleButton);
        fan = (ImageView) findViewById(R.id.fan);
        switchButton= (Switch) findViewById((R.id.switchButton));
        speed= (SeekBar) findViewById(R.id.speed);

        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(250);

        rotateAnimator=ObjectAnimator.ofFloat(fan, "rotation", 0,360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rotateAnimator.setDuration(SPEED[speed.getProgress()]);
                    rotateAnimator.start();
                }else{
                    rotateAnimator.end();
                }
            }
        });

       switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    gd.setColors(new int[]{  Color. RED, Color. TRANSPARENT});
                    fan.setBackground(gd);
                }else{
                    fan.setBackgroundColor(Color. TRANSPARENT );
                }
            }
        });

        speed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged (SeekBar speed, int Progress, boolean fromUser){
                if(toggleButton.isChecked()){
                    rotateAnimator.setDuration(SPEED[speed.getProgress()]);
                    rotateAnimator.start();
                }
            }

            @Override
            public void onStartTrackingTouch (SeekBar speed){

            }

            @Override
            public void onStopTrackingTouch (SeekBar speed) {

            }
        });
    }
}
