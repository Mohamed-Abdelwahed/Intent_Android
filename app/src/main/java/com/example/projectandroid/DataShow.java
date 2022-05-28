package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DataShow extends AppCompatActivity {
Intent incommingIntent ;
TextView txtPhone;
TextView txtMessage ;

Button btnBack ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_show);
        incommingIntent = getIntent();

        txtPhone = (TextView) findViewById(R.id.textPhone);
        txtMessage = (TextView) findViewById(R.id.txtMessage);

        txtPhone.setText(incommingIntent.getStringExtra(MainActivity.PHONE));
        txtMessage.setText(incommingIntent.getStringExtra(MainActivity.MESSAGE));

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}