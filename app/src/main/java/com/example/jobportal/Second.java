package com.example.jobportal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


public class Second extends Fragment {

    TextView txt2,back;

    ViewPager viewPager;

    public Second() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_second, container, false);

        viewPager =getActivity().findViewById(R.id.viewPager);

        txt2=view.findViewById(R.id.txtNext2);
        back=view.findViewById(R.id.txtBack2);



        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });


        return view;
    }
}