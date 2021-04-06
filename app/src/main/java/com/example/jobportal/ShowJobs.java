package com.example.jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowJobs extends AppCompatActivity {

    public static final String URL_SHOW_JOBS = "http://192.168.136.1/JobPortal/MyApi/showJobs.php";

   private TextView txt1,txt2,txt3,txt4;
    private EditText idt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_jobs);

        txt1=findViewById(R.id.showtitle);
        txt2=findViewById(R.id.showdesc);
        txt3=findViewById(R.id.showreq);
        txt4=findViewById(R.id.showaddress);

        idt=findViewById(R.id.id);

    }

    public void show_jobs(View view){


        class showJobs extends AsyncTask<Void, Void, String> {
            final String id  = idt.getText().toString();

            ProgressDialog pdLoading = new ProgressDialog(ShowJobs.this);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pdLoading.setMessage("\tLoading...");
                pdLoading.setCancelable(false);
                pdLoading.show();
            }

            @Override
            protected String doInBackground(Void... voids) {

                RequestHandler requestHandler2 = new RequestHandler();


                HashMap<String, String> params = new HashMap<>();
                params.put("id", id);


                return requestHandler2.sendPostRequest(URL_SHOW_JOBS, params);
            }

            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                pdLoading.dismiss();

                try{

                    JSONObject obj = new JSONObject(s);


                    if (!obj.getBoolean("error")){
                        Toast.makeText(ShowJobs.this, obj.getString("message"), Toast.LENGTH_LONG).show();
                        //Make TextViews Visible
                        txt1.setVisibility(View.VISIBLE);
                        txt2.setVisibility(View.VISIBLE);
                        txt3.setVisibility(View.VISIBLE);
                        txt4.setVisibility(View.VISIBLE);


                        //Set retrieved text to TextViews
                        txt1.setText("Title: "+obj.getString("title"));
                        txt2.setText("Descripiton: "+obj.getString("descj"));
                        txt3.setText("Requirements: "+obj.getString("req"));
                        txt4.setText("Salary: "+obj.getString("salary"));

                    }
                } catch (Exception e ){
                    Toast.makeText(ShowJobs.this, "Exception: "+e, Toast.LENGTH_SHORT).show();
                }
            }
        }

        showJobs show = new showJobs();
        show.execute();
    }

}