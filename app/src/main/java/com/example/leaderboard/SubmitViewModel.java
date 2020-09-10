package com.example.leaderboard;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitViewModel extends ViewModel {
    final MutableLiveData status=new MutableLiveData<>();

    public void getResponse(String textFn, String textLn, String textMail, String textLink){

                Call<ResponseBody> submissioncall=RetrofitClient.getInstance().getSubmissionApi().Submission(textFn,textLn,textMail,textLink);
        submissioncall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful())
                    status.postValue(1);
                else
                    status.postValue(-1);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                status.postValue(-1);

            }
        });
    }

}
