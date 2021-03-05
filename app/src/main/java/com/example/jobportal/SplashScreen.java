package com.example.jobportal;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private static int myscreen=5000;

    Animation topSideani,bottomSideani;
    ImageView image;
    TextView text,text1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        topSideani= AnimationUtils.loadAnimation(this,R.anim.top_side_animation);
        bottomSideani= AnimationUtils.loadAnimation(this,R.anim.bottom_side_animation);

        image=findViewById(R.id.imageView);
        text=findViewById(R.id.textView);
        text1=findViewById(R.id.textView3);


        image.setAnimation(topSideani);
        text.setAnimation(bottomSideani);
        text1.setAnimation(bottomSideani);

        //to handle the delay process
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent second=new Intent(SplashScreen.this,AdminPanel.class);
                startActivity(second);
                finish();
            }
        },myscreen);
    }

}

