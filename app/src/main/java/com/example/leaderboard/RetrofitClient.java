package com.example.leaderboard;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static  final String BASE_GOOGLE_URL="https://docs.google.com/forms/d/e/";
    private static  RetrofitClient mInstance;
    private static Retrofit mRetrofit;
    // initializing retrofit object
    private RetrofitClient()
    {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_GOOGLE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
    // synchronized function to get the instance of the singelton instance of RetrofitClient class
    public static synchronized RetrofitClient getInstance(){
        if(mInstance ==null ){
            mInstance=new RetrofitClient();

        }
        return mInstance;
    }
    public GoogleFormsSubmission getSubmissionApi(){
        return mRetrofit.create(GoogleFormsSubmission.class);
    }

}
