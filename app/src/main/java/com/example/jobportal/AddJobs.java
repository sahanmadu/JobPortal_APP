package com.example.jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.view.View.GONE;

public class AddJobs extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    EditText editTextJobId, editTextTitle, editTextDesc, editTextReq, editTextSalary;


    ProgressBar progressBar;
    ListView listView;
    Button btnAddUpdate;

    List<Job> jobList;

    boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_jobs);

        editTextJobId = (EditText) findViewById(R.id.txtJobID);
        editTextTitle = (EditText) findViewById(R.id.txtjTitle);
        editTextDesc = (EditText) findViewById(R.id.txtjDesc);
        editTextReq = (EditText) findViewById(R.id.txtjRe);
        editTextSalary = (EditText) findViewById(R.id.txtJsalary);


        btnAddUpdate = (Button) findViewById(R.id.btnAdd);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listView = (ListView) findViewById(R.id.listViewJobs);

        jobList = new ArrayList<>();

        btnAddUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isUpdating) {
                    updateJob();
                } else {
                    createJob();
                }
            }
        });
        readJobs();


    }

    private void createJob() {
        String title = editTextTitle.getText().toString().trim();
        String descj = editTextDesc.getText().toString().trim();
        String req = editTextReq.getText().toString().trim();
        String salary = editTextSalary.getText().toString().trim();


        if (TextUtils.isEmpty(title)) {
            editTextTitle.setError("Please enter title");
            editTextTitle.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(descj)) {
            editTextDesc.setError("Please enter job description");
            editTextDesc.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(req)) {
            editTextReq.setError("Please enter job Requirements list");
            editTextReq.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(salary)) {
            editTextSalary.setError("Please enter job Salary");
            editTextSalary.requestFocus();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("title", title);
        params.put("descj", descj);
        params.put("req", req);
        params.put("salary", salary);

        PerformNetworkRequest request = new PerformNetworkRequest(API.URL_CREATE_JOB, params, CODE_POST_REQUEST);
        request.execute();
    }



    private void readJobs() {
        PerformNetworkRequest request = new PerformNetworkRequest(API.URL_READ_JOBS, null, CODE_GET_REQUEST);
        request.execute();
    }

    private void updateJob() {
        String id = editTextJobId.getText().toString();
        String title = editTextTitle.getText().toString().trim();
        String descj = editTextDesc.getText().toString().trim();
        String req = editTextReq.getText().toString().trim();
        String salary  = editTextSalary.getText().toString().trim();


        if (TextUtils.isEmpty(title)) {
            editTextTitle.setError("Please enter title");
            editTextTitle.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(descj)) {
            editTextDesc.setError("Please enter job description");
            editTextDesc.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(req)) {
            editTextReq.setError("Please enter job Requirements list");
            editTextReq.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(salary)) {
            editTextSalary.setError("Please enter job Salary");
            editTextSalary.requestFocus();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("title", title);
        params.put("descj", descj);
        params.put("req", req);
        params.put("salary", salary);



        PerformNetworkRequest request = new PerformNetworkRequest(API.URL_UPDATE_JOB, params, CODE_POST_REQUEST);
        request.execute();

        btnAddUpdate.setText("Add");

        editTextTitle.setText("");
        editTextDesc.setText("");
        editTextReq.setText("");
        editTextSalary.setText("");


        isUpdating = false;
    }

    private void deleteJob(int id) {
        PerformNetworkRequest request = new PerformNetworkRequest(API.URL_DELETE_JOB + id, null, CODE_GET_REQUEST);
        request.execute();
    }

    private void refreshjobList(JSONArray jobs) throws JSONException {
        jobList.clear();

        for (int i = 0; i < jobs.length(); i++) {
            JSONObject obj = jobs.getJSONObject(i);

            jobList.add(new Job(
                    obj.getInt("id"),
                    obj.getString("title"),
                    obj.getString("descj"),
                    obj.getString("req"),
                    obj.getString("salary")
            ));
        }

        JobAdapter adapter = new JobAdapter(jobList);
        listView.setAdapter(adapter);
    }

    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {
        String url;
        HashMap<String, String> params;
        int requestCode;

        PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(GONE);
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                    refreshjobList(object.getJSONArray("jobs"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();

            if (requestCode == CODE_POST_REQUEST)
                return requestHandler.sendPostRequest(url, params);


            if (requestCode == CODE_GET_REQUEST)
                return requestHandler.sendGetRequest(url);

            return null;
        }
    }

    //show jobs

    class JobAdapter extends ArrayAdapter<Job> {
        List<Job> jobList;

        public JobAdapter(List<Job> jobList) {
            super(AddJobs.this, R.layout.job_list, jobList);
            this.jobList = jobList;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.job_list, null, true);

            TextView textViewName = listViewItem.findViewById(R.id.textViewName);

            TextView textViewUpdate = listViewItem.findViewById(R.id.textViewUpdate);
            TextView textViewDelete = listViewItem.findViewById(R.id.textViewDelete);

            final Job job = jobList.get(position);

            textViewName.setText(job.getTitle());

            textViewUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isUpdating = true;
                    editTextJobId.setText(String.valueOf(job.getId()));
                    editTextTitle.setText(job.getTitle());
                    editTextDesc.setText(job.getDesc());
                    editTextReq.setText(job.getReq());
                    editTextSalary.setText(job.getSalary());

                    btnAddUpdate.setText("Update");
                }
            });

            textViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(AddJobs.this);

                    builder.setTitle("Delete " + job.getTitle())
                            .setMessage("Are you sure you want to delete this job?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteJob(job.getId());
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                }
            });

            return listViewItem;
        }
    }
}

