package com.example.not_in_my_back_yard;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.type.DateTime;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UploadNewReport extends AppCompatActivity {

    private Button btnChoose, btnUpload, btnPlaceReport;
    private ImageView imageView;
    private Uri filePath;
    private String String_path = "";
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private final int PICK_IMAGE_REQUEST = 71;
    private double lon = 0, lat = 0;
    private EditText editText;
    private boolean imageSelected = false;
    int stopId;

    FusedLocationProviderClient mFusedLocationClient;
    TextView stopIdTextView, stopNameTextView;
    int PERMISSION_ID = 44;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_new_report);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        findView();
        //getLastLocation();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            stopId = extras.getInt("StopId");
            String stopName = extras.getString("StopName");
            stopIdTextView.setText("מספר תחנה " + stopId);
            stopNameTextView.setText(stopName);
        }

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        MyFirebaseDB.CallBack_String callBack_string = new MyFirebaseDB.CallBack_String() {
            @Override
            public void dataReady(String value) {
                String_path = value;

                HttpRequest.PostReport(requestQueue, new PostReport(stopId, editText.getText().toString(), String_path));
                Toast.makeText(getApplicationContext(), "Created", Toast.LENGTH_LONG).show();

                finish();
            }
        };

        btnPlaceReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Report report = new Report();
                if(imageSelected)
                    MyFirebaseDB.uploadImage(filePath, storageReference, UploadNewReport.this, callBack_string);
                else{
                    HttpRequest.PostReport(requestQueue, new PostReport(stopId, editText.getText().toString()));
                    Toast.makeText(getApplicationContext(), "Created", Toast.LENGTH_LONG).show();

                    finish();
                }
            }
        });
    }


    private void findView() {
        btnChoose = findViewById(R.id.btnChoose);
        //btnUpload = findViewById(R.id.btnUpload);
        imageView = findViewById(R.id.imgView);
        editText = findViewById(R.id.editText_comment);
        btnPlaceReport = findViewById(R.id.Place_report);
        stopIdTextView = findViewById(R.id.textViewLocation_Lat);
        stopNameTextView = findViewById(R.id.textViewLocation_Lon);
    }


    ActivityResultLauncher<Intent> onActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK && result != null && result.getData() != null) {
                        filePath = result.getData().getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                            imageView.setImageBitmap(bitmap);
                            imageSelected = true;
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            });

    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        onActivityResult.launch(Intent.createChooser(intent, "Select Picture"));
    }

}