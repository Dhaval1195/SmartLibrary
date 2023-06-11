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
import com.example.hp_pc.smartlibrary.Adapters.EmotionAdapter;
import com.example.hp_pc.smartlibrary.Model.EmotionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookCategotyList extends AppCompatActivity {

    ListView listView;

    ProgressDialog progress;

    EmotionAdapter emotionAdapter;
    String myemoid,myemoname;

    ArrayList<EmotionModel> EmotionList= new ArrayList<EmotionModel>();
    public static final String EMOTION_FETCH_URL = "http://192.168.0.132/project/service/getemotion.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_categoty_list);

        listView=(ListView)findViewById(R.id.mylist);

        GetEmotion();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EmotionModel emomodel = (EmotionModel) adapterView.getItemAtPosition(i);
                myemoid=emomodel.getEmo_id();
                myemoname=emomodel.getEmo_name();

                Toast.makeText(BookCategotyList.this, "You Clicked On" + myemoname , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BookCategotyList.this,BookList.class);
                intent.putExtra("emoid",myemoid);

                startActivity(intent);
            }
        });



    }



    private void GetEmotion() {
        progress=new ProgressDialog(this);
        progress.setMessage(" Please Wait");
        //progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setCancelable(false);
        progress.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, EMOTION_FETCH_URL,

                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        progress.dismiss();

                        try {

                            JSONObject obj = new JSONObject(response);
                            JSONArray jsonArray = obj.getJSONArray("category");
                            for(int i=0;i<jsonArray.length();i++){
                                //Declaring a json object corresponding to every pdf object in our json Array
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                //Declaring a Pdf object to add it to the ArrayList  pdfList
                                EmotionModel emotionModel  = new EmotionModel();
                                String EmoID = jsonObject.getString("emo_id");
                                String EmoName = jsonObject.getString("emo_name");




                                emotionModel.setEmo_id(EmoID);
                                emotionModel.setEmo_name(EmoName);


                                /*movie.setIcon(Logo);*/
                                EmotionList.add(emotionModel);

                            }



                            emotionAdapter=new EmotionAdapter(BookCategotyList.this,R.layout.mybookcategorylist, EmotionList);
                            listView.setAdapter(emotionAdapter);
                            emotionAdapter.notifyDataSetChanged();











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
        RequestQueue request = Volley.newRequestQueue(BookCategotyList.this);
        request.add(stringRequest);


    }
}
