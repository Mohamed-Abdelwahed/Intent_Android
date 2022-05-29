package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataShow extends AppCompatActivity {
Intent incommingIntent ;
TextView txtPhone;
TextView txtMessage ;

Button btnBack ;

Button btnWriteShared;
Button btnReadShared;
SharedPreferences sharedPreferences;

Button btnWriteToFile;
Button btnReadFromFile;

FileInputStream fis;
DataInputStream dis;
FileOutputStream fos;
DataOutputStream dos;

public static final String FILE_NAME = "PHONE_MESSAGE";


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

        btnWriteShared = (Button) findViewById(R.id.writeSharedPrefrences);
        btnWriteShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(MainActivity.PHONE,txtPhone.getText().toString());
                editor.putString(MainActivity.MESSAGE,txtMessage.getText().toString());

                editor.commit();

                txtPhone.setText("");
                txtMessage.setText("");
            }
        });


        btnReadShared = (Button) findViewById(R.id.readSharedPrefrences);
        btnReadShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getPreferences(Context.MODE_PRIVATE);
                txtPhone.setText(sharedPreferences.getString(MainActivity.PHONE,"Not Available"));
                txtMessage.setText(sharedPreferences.getString(MainActivity.MESSAGE , "NOT Available"));
            }
        });

        btnWriteToFile = (Button) findViewById(R.id.writeToFile);
        btnWriteToFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    fos = openFileOutput(DataShow.FILE_NAME,Context.MODE_PRIVATE);
                    dos = new DataOutputStream(fos);
                    dos.writeUTF(txtPhone.getText().toString());
                    dos.writeUTF(txtMessage.getText().toString());

                    dos.close();
                    fos.close();

                    txtPhone.setText("");
                    txtMessage.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        btnReadFromFile = (Button) findViewById(R.id.readToFile);
        btnReadFromFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fis = openFileInput(DataShow.FILE_NAME);
                    dis = new DataInputStream(fis);
                    txtPhone.setText(dis.readUTF());
                    txtMessage.setText(dis.readUTF());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}