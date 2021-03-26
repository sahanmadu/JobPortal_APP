package com.example.jobportal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class Load_Frag extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load__frag);


        viewPager=findViewById(R.id.viewPager);
        IntroAdapter adapter=new IntroAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
}