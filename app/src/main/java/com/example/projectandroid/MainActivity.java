package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent outIntent;
    EditText editPhone;
    EditText editMessage;
    Button btnSend;
    String tempText = "";

    public static final String PHONE = "Phone";
    public static final String MESSAGE = "Message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editPhone = (EditText) findViewById(R.id.phoneSend);
        editMessage = (EditText) findViewById(R.id.nameSend) ;

        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "ALl Right ! !", Toast.LENGTH_SHORT).show();
                outIntent = new Intent( MainActivity.this,DataShow.class);
                tempText = editPhone.getText().toString();
                outIntent.putExtra(PHONE , tempText);
                tempText = editMessage.getText().toString();
                outIntent.putExtra(MESSAGE , tempText);
                startActivity(outIntent);

            }
        });
    }

    public void sendClicked(View view) {

    }
}