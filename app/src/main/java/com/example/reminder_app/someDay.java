package com.example.reminder_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class someDay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_some_day);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            String Value = extras.getString("key");
            Button someday = findViewById(R.id.choseDate);
            someday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                                someday.setText(Value);

                }
            });

//            Toast.makeText(this, Value, Toast.LENGTH_LONG).show();

        }
    }
}