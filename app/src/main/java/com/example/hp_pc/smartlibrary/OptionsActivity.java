package com.example.hp_pc.smartlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionsActivity extends AppCompatActivity {

    Button detectemo,selectemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);


        detectemo=(Button)findViewById(R.id.buttondetct);
        selectemo=(Button)findViewById(R.id.buttonchoose);



        selectemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OptionsActivity.this,SubOptionsActivity.class);
                startActivity(intent);
            }
        });
    }
}
