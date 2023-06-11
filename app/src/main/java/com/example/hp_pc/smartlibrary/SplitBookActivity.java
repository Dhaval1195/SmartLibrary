package com.example.hp_pc.smartlibrary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.hp_pc.smartlibrary.Adapters.SplitBookAdapter;
import com.example.hp_pc.smartlibrary.Model.BookModel;
import com.example.hp_pc.smartlibrary.Model.BookSplitModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SplitBookActivity extends AppCompatActivity {


    ListView listView;

    ProgressDialog progress;

    SplitBookAdapter splitBookAdapter;
    String mybookid,myemoname;

    String All,newBookid;

    ArrayList<BookSplitModel> BookSplitList= new ArrayList<BookSplitModel>();
    public static final String SPLIT_BOOK_FETCH_URL = "http://192.168.0.132/project/service/getsplitbook.php?book_id=";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_book);



        listView=(ListView)findViewById(R.id.mysplitlist);


        Intent intent=getIntent();
        newBookid=   intent.getStringExtra("book_id");

        Toast.makeText(this, "book id is" + newBookid, Toast.LENGTH_SHORT).show();



        GetSplitBook();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BookSplitModel booksplitModel = (BookSplitModel) adapterView.getItemAtPosition(i);
                mybookid=booksplitModel.getPath();


                Toast.makeText(SplitBookActivity.this, "You Clicked On" + myemoname , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SplitBookActivity.this,BookList.class);
                intent.putExtra("book_id",mybookid);

                startActivity(intent);
            }
        });

    }



    private void GetSplitBook() {
        progress=new ProgressDialog(this);
        progress.setMessage(" Please Wait");
        //progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setCancelable(false);
        progress.show();


        All=SPLIT_BOOK_FETCH_URL + newBookid;
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
                                BookSplitModel bookSplitModel  = new BookSplitModel();
                                String SplitID = jsonObject.getString("book_split_id");
                                String SplitBookName = jsonObject.getString("book_split_name");
                                String Path = jsonObject.getString("path");
                                String Bookid = jsonObject.getString("book_id");







                                bookSplitModel.setBook_id(Bookid);
                                bookSplitModel.setBook_split_name(SplitBookName);
                                bookSplitModel.setBook_split_id(SplitID);
                                bookSplitModel.setPath(Path);




                                /*movie.setIcon(Logo);*/
                                BookSplitList.add(bookSplitModel);

                            }



                            splitBookAdapter=new SplitBookAdapter(SplitBookActivity.this,R.layout.mysplitbooklist, BookSplitList);
                            listView.setAdapter(splitBookAdapter);
                            splitBookAdapter.notifyDataSetChanged();











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
        RequestQueue request = Volley.newRequestQueue(SplitBookActivity.this);
        request.add(stringRequest);


    }
}
