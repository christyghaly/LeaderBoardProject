package com.example.leaderboard;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ApiUtil {
    private ApiUtil(){}
    public static final String BASE_API_URL="https://gadsapi.herokuapp.com";
    public static final String PATH_APPEND ="api";
    public static final String KEY="key";
    public static final String API_KEY="AIzaSyD9daw0_0YBJs_J3CbfoZXylttTk8L-868";






    public static URL buildUrl(String title) {
        URL url=null;


             Uri uri = Uri.parse(BASE_API_URL).buildUpon().appendPath(PATH_APPEND).appendEncodedPath(title)
                    .appendQueryParameter(KEY, API_KEY).build();//construct a new builder , // encodes key and value and then append the path
            try {
                url = new URL(uri.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        return url;
    }
    public static String getJson(URL url) throws IOException{
        //establish connection

        HttpURLConnection connection =(HttpURLConnection) url.openConnection();
        InputStream stream=connection.getInputStream();
        Scanner scanner=new Scanner(stream);
        scanner.useDelimiter("\\A");// to read everything
        try{

            boolean hasData=scanner.hasNext();
            if(hasData){
                return scanner.next();
            }
            else{
                return null;
            }

        }catch (Exception e){
            Log.d("Error",e.toString());
            return null;
        }
        finally {
            connection.disconnect();

        }

    }
    public static ArrayList<PersonData> getPersonDataFromJson(String json,int type){

        final String NAME="name";
        final String COUNTRY="country";
        final String BADGE="badgeUrl";
        String number="";
        if(type==0) {
            number = "hours";
        }else{
            number="score";
        }

        ArrayList<PersonData> personData=new ArrayList<PersonData>();
        try{
            JSONArray jsonPerson=new JSONArray(json);
            for(int i=0; i<jsonPerson.length();i++){
                JSONObject jsonObject=jsonPerson.getJSONObject(i);
                PersonData personInfo=new PersonData(jsonObject.getString(NAME),jsonObject.getInt(number),
                        jsonObject.getString(COUNTRY),jsonObject.getString(BADGE));
                personData.add(personInfo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return personData;
    }


}
