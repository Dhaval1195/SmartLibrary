package com.example.hp_pc.smartlibrary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hp_pc.smartlibrary.Adapters.BookAdapter;
import com.example.hp_pc.smartlibrary.Adapters.EmotionAdapter;
import com.example.hp_pc.smartlibrary.Model.BookModel;
import com.example.hp_pc.smartlibrary.Model.EmotionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookList extends AppCompatActivity {

    ListView listView;

    ProgressDialog progress;

    BookAdapter bookAdapter;
    String mybookid,myemoname;

    String All,newemoid;

    ArrayList<BookModel> BookList= new ArrayList<BookModel>();
    public static final String EMOTION_FETCH_URL = "http://192.168.0.132/project/service/getbookbyemotion.php?emo_id=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        listView=(ListView)findViewById(R.id.mybooklist);


        Intent intent=getIntent();
         newemoid=   intent.getStringExtra("emoid");


        Toast.makeText(this, "" + newemoid, Toast.LENGTH_SHORT).show();
        GetBook();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BookModel bookModel = (BookModel) adapterView.getItemAtPosition(i);
                mybookid=bookModel.getBook_id();


                Toast.makeText(BookList.this, "You Clicked On" + myemoname , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BookList.this,SplitBookActivity.class);
                intent.putExtra("book_id",mybookid);

                startActivity(intent);
            }
        });



    }



    private void GetBook() {
        progress=new ProgressDialog(this);
        progress.setMessage(" Please Wait");
        //progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setCancelable(false);
        progress.show();


        All=EMOTION_FETCH_URL + newemoid;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, All ,

                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        progress.dismiss();

                        try {

                            JSONObject obj = new JSONObject(response);
                            JSONArray jsonArray = obj.getJSONArray("book");
                            for(int i=0;i<jsonArray.length();i++){
                                //Declaring a json object corresponding to every pdf object in our json Array
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                //Declaring a Pdf object to add it to the ArrayList  pdfList
                                BookModel bookModel  = new BookModel();
                                String EmoID = jsonObject.getString("emo_id");
                                String BookName = jsonObject.getString("book_name");
                                String Author = jsonObject.getString("author");
                                String Bookid = jsonObject.getString("book_id");
                                String BookCatID = jsonObject.getString("book_cat_id");
                                String Publication = jsonObject.getString("publication");
                                String Path = jsonObject.getString("path");
                                String Edition = jsonObject.getString("edition");
                                




                                bookModel.setEmo_id(EmoID);
                                bookModel.setBook_name(BookName);
                                bookModel.setAuthor(Author);
                                bookModel.setBook_id(Bookid);
                                bookModel.setBook_cat_id(BookCatID);
                                bookModel.setPublication(Publication);
                                bookModel.setPath(Path);
                                bookModel.setEdition(Edition);



                                /*movie.setIcon(Logo);*/
                                BookList.add(bookModel);

                            }



                            bookAdapter=new BookAdapter(BookList.this,R.layout.mybooklist, BookList);
                            listView.setAdapter(bookAdapter);
                            bookAdapter.notifyDataSetChanged();











                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        RequestQueue request = Volley.newRequestQueue(BookList.this);
        request.add(stringRequest);


    }
}
