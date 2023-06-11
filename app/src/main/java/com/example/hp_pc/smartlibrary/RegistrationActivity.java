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

public class RegistrationActivity extends AppCompatActivity {
    EditText username,password,email,mobno;
    Button Reg;
    
    ProgressDialog pd;


    String myuser,mypass,myemail,mymob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username=(EditText)findViewById(R.id.regusername);
        password=(EditText)findViewById(R.id.regpassword);
        email=(EditText)findViewById(R.id.regemail);
        mobno=(EditText)findViewById(R.id.regmob);
        Reg=(Button) findViewById(R.id.regbutton);



        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                myuser=username.getText().toString();
                mypass=password.getText().toString();
                myemail=email.getText().toString();
                mymob=mobno.getText().toString();



                new AsycRegister().execute(myuser,mypass,mymob,myemail);

                
            }
        });




    }



    class AsycRegister extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(RegistrationActivity.this);
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
            result = new HTTPRequestDAO().GetUserRegistration(params[0], params[1],params[2],params[3]);
            return result;
        }

        protected void onPostExecute(String result) {

            pd.dismiss();

            Gson gson=new Gson();
            try {

                LogInModel logInModel = gson.fromJson(result, LogInModel.class);

                if (logInModel.getData() == 200) {
                    // Toast.makeText(MainActivity.this, "" + Userid, Toast.LENGTH_SHORT).show();

                    Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);


                } else if (logInModel.getData() == 100) {
                    Toast.makeText(RegistrationActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(RegistrationActivity.this, "Check Your Connection", Toast.LENGTH_SHORT).show();


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }







    }
}

