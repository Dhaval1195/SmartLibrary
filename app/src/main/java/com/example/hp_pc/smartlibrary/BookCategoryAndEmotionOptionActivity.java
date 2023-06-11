package com.example.hp_pc.smartlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookCategoryAndEmotionOptionActivity extends AppCompatActivity {


    Button categorybtn,emotionbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_category_and_emotion_option);



        categorybtn=(Button)findViewById(R.id.bookbycategory);
        emotionbtn=(Button)findViewById(R.id.bookbyemotions);




        categorybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        emotionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(BookCategoryAndEmotionOptionActivity.this,BookCategotyList.class);
                startActivity(intent);

            }
        });
    }
}
