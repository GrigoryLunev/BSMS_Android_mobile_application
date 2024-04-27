package com.example.not_in_my_back_yard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowExistingReport extends AppCompatActivity {
    private ImageView image;
    private TextView location_lat;
    private TextView location_lng;
    private TextView comment;
    private Button BTN_treated;
    private Button BTN_not_treated;
    private double lat;
    private double lng;
    private Report my_report;
    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_existing_report);

        find();

/*        lat = getIntent().getDoubleExtra("Lat", 0);
        lng = getIntent().getDoubleExtra("Lng", 0);

        location_lat.setText("Latitude:    " + lat);
        location_lng.setText("Longitude: " + lng);*/

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int stopId = extras.getInt("StopId");
            String stopName = extras.getString("StopName");
            location_lat.setText("Stop ID # " + stopId);
            location_lng.setText(stopName);
        }

        MyFirebaseDB.CallBack_Single_Report callBack_single_report = new MyFirebaseDB.CallBack_Single_Report() {
            @Override
            public void dataReady(Report report) {
                    my_report = report;
                     comment.setText("Comment:  " + report.getComment());
                     Glide.with(ShowExistingReport.this)
                        .load(report.getUrl())
                        .into(image);

                     url = report.getUrl();

                     if(my_report.getIsActive() == 1)
                        BTN_treated.setVisibility(View.VISIBLE);
                     else
                         BTN_not_treated.setVisibility(View.VISIBLE);
            }
        };
        MyFirebaseDB.getSingleReports(lat, lng, callBack_single_report);



        BTN_treated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(my_report.getIsActive() == 1)
                    my_report.setIsActive(0);
                MyFirebaseDB.addReport(my_report);
                finish();
            }
        });

        BTN_not_treated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(my_report.getIsActive() == 0)
                    my_report.setIsActive(1);
                MyFirebaseDB.addReport(my_report);

                finish();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(url.compareTo("") != 0){
                    Intent myIntent = new Intent(ShowExistingReport.this, FullScreenActivity.class);
                    myIntent.putExtra("url", url);
                    startActivity(myIntent);
                }
            }
        });

    }

    private void find() {
        image = findViewById(R.id.imgView);
        location_lat = findViewById(R.id.textViewLocation_Lat);
        location_lng = findViewById(R.id.textViewLocation_Lon);
        comment = findViewById(R.id.TextView_Comment);
        BTN_treated = findViewById(R.id.BTN_has_been_treated);
        BTN_treated.setVisibility(View.INVISIBLE);
        BTN_not_treated = findViewById(R.id.BTN_yet_not_treated);
        BTN_not_treated.setVisibility(View.INVISIBLE);
    }

}