package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView date,country,activecases,recoveredcases,deathcases,cofirmedcases;
    ProgressDialog dailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dailog=new ProgressDialog(this);
        dailog.setTitle("Data Fetching..");
        dailog.setMessage("please wait....");
        dailog.show();
        date=findViewById(R.id.tv_date);
        country=findViewById(R.id.tv_country);
        activecases=findViewById(R.id.tv_activecases);
        cofirmedcases=findViewById(R.id.tv_confirmedcases);
        deathcases=findViewById(R.id.tv_deathcases);
        recoveredcases=findViewById(R.id.tv_recoveredcases);
        EndpointInterface ei=RetrofitInstance.getRetrofit().create(EndpointInterface.class);
        Call<String> c=ei.getData();
        c.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("ding",response.body());
                Toast.makeText(MainActivity.this, ""+response.body(), Toast.LENGTH_SHORT).show();
                try {
                    JSONArray rootArray=new JSONArray(response.body());
                    JSONObject rootobj=rootArray.getJSONObject(rootArray.length()-1);
                    String res_country=rootobj.getString("Country");
                    String res_date=rootobj.getString("Date");
                    String res_activecases=rootobj.getString("Active");
                    String res_confirmedcases=rootobj.getString("Confirmed");
                    String res_deathcases=rootobj.getString("Deaths");
                    String res_recoveredcases=rootobj.getString("Recovered");
                    country.setText("Country:"+res_country);
                    date.setText("Date:"+res_date);
                    activecases.setText("Active:"+res_activecases);
                    cofirmedcases.setText("Confirmed:"+res_confirmedcases);
                    deathcases.setText("Deaths:"+res_deathcases);
                    recoveredcases.setText("Recovered:"+res_recoveredcases);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failed to load",Toast.LENGTH_LONG).show();



            }
        });
    }
}
