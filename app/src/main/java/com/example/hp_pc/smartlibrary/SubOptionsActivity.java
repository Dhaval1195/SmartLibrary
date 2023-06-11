package com.example.hp_pc.smartlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubOptionsActivity extends AppCompatActivity {



    Button mybook,myvideo,mysongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_options);

        mybook=(Button)findViewById(R.id.buttonbook);
        myvideo=(Button)findViewById(R.id.buttonvideo);
        mysongs=(Button)findViewById(R.id.buttonsongs);


        mybook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(SubOptionsActivity.this,BookCategoryAndEmotionOptionActivity.class);
                startActivity(intent);

            }
        });



        myvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        mysongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
