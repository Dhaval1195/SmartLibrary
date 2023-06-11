package com.example.hp_pc.smartlibrary.Helpers;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class HTTPRequestDAO {



    public String GetUserLogin(String Username, String Password) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("username", Username));
        param.add(new BasicNameValuePair("pass", Password));



        String json = new HttpRequestHelper().postRequest("/login.php", param);
        return json;

    }

    public String GetUserRegistration(String username, String pass, String mobile, String email) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("username", username));
        param.add(new BasicNameValuePair("pass", pass));
        param.add(new BasicNameValuePair("mobile", mobile));
        param.add(new BasicNameValuePair("email", email));





        String json = new HttpRequestHelper().postRequest("/register.php", param);
        return json;

    }

    public String getbrandproduct(String search, String code, String start, String end, String sid) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("search", search));
        param.add(new BasicNameValuePair("code", code));
        param.add(new BasicNameValuePair("startprice", start));
        param.add(new BasicNameValuePair("endprice", end));
        param.add(new BasicNameValuePair("storeid", sid));




        String json = new HttpRequestHelper().postRequest("/getbrandproduct.php", param);
        return json;

    }



    public String GetChild(String name) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("user_id", name));


        String json = new HttpRequestHelper().postRequest("/getchild.php", param);
        return json;

    }


    public String AddChild(String userid, String name, String dob) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("user_id", userid));
        param.add(new BasicNameValuePair("name", name));
        param.add(new BasicNameValuePair("dob", dob));


        String json = new HttpRequestHelper().postRequest("/addchild.php", param);
        return json;

    }
}

