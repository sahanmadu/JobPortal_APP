package com.example.jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShowJobs extends AppCompatActivity {

    TextView l1,l2;
    Button btnClick;

    ListView listView;

    List<Job> jobList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_jobs);

       l1=findViewById(R.id.txtline1);
        btnClick=findViewById(R.id.btnMore);

        listView = (ListView) findViewById(R.id.listViewJobs);

        jobList = new ArrayList<>();

       btnClick.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

           }
       });






    }



}