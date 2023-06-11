package com.example.hp_pc.smartlibrary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp_pc.smartlibrary.Helpers.HTTPRequestDAO;
import com.example.hp_pc.smartlibrary.Model.LogInModel;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button Reg,login;
    ProgressDialog  pd;

    String myuser,mypass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username=(EditText)findViewById(R.id.loginusername);
        password=(EditText)findViewById(R.id.loginpassword);
        login=(Button) findViewById(R.id.btnlogin);
        Reg=(Button)findViewById(R.id.buttonregister);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              myuser=username.getText().toString();
              mypass=password.getText().toString();

              new AsycLogin().execute(myuser,mypass);


            }
        });

        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);

            }
        });
    }



    class AsycLogin extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(LoginActivity.this);
            pd.setMessage("Shanti Rakho");
            pd.setTitle("Exibition");
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setCancelable(true);
            pd.setCanceledOnTouchOutside(true);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            result = new HTTPRequestDAO().GetUserLogin(params[0], params[1]);
            return result;
        }

        protected void onPostExecute(String result) {

            pd.dismiss();

            Gson gson=new Gson();
            try{

                LogInModel  logInModel=gson.fromJson(result,LogInModel.class);

                if(logInModel.getData()==200){
                    // Toast.makeText(MainActivity.this, "" + Userid, Toast.LENGTH_SHORT).show();


                    Toast.makeText(LoginActivity.this, "successfully logged in", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this,OptionsActivity.class);
                    startActivity(intent);




                }
               else if(logInModel.getData()==100){
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }

                else if(logInModel.getData()==300){
                    Toast.makeText(LoginActivity.this, "Trial Period over", Toast.LENGTH_SHORT).show();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }







    }
}
