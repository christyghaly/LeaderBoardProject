package com.example.leaderboard;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class SubmissionActivity extends AppCompatActivity {



    SubmitViewModel mSubmitViewModel;
    public String mFstname;
    public String mLstName;
    public String mEmail;
    public String mGitLink;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setContentView(R.layout.submit_activity);


         EditText mFName=findViewById(R.id.first_name);
         EditText mLName = findViewById(R.id.last_name);
         EditText mEAddress = findViewById(R.id.email_address);
         EditText mGitHubLink= findViewById(R.id.github_link);

        mFstname = mFName.getText().toString();
        mLstName = String.valueOf(mLName.getText());
        mEmail = String.valueOf(mEAddress.getText());
        mGitLink = String.valueOf(mGitHubLink.getText());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.gadsimage);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        Button submitButton=(Button) findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitResponse();

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void submitResponse() {

        final Dialog maindialog=new Dialog(this,android.R.style.Theme_Dialog);
        maindialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        maindialog.setContentView(R.layout.alert);
        maindialog.setCanceledOnTouchOutside(true);
        maindialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        maindialog.show();
        Button yes_button=(Button) ((Dialog) maindialog).findViewById(R.id.alert_yes);
        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maindialog.dismiss();
                mSubmitViewModel= ViewModelProviders.of(SubmissionActivity.this).get(SubmitViewModel.class);
                mSubmitViewModel.getResponse(mFstname,mLstName,mEmail,mGitLink);
                mSubmitViewModel.status.observe(SubmissionActivity.this, new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        if(integer==1){
                            Dialog dialog=new Dialog(SubmissionActivity.this,android.R.style.Theme_Dialog);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.submission_status2);
                            dialog.setCanceledOnTouchOutside(true);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dialog.show();

                        }
                        else{

                            Dialog dialog=new Dialog(SubmissionActivity.this,android.R.style.Theme_Dialog);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.submission_status);
                            dialog.setCanceledOnTouchOutside(true);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dialog.show();


                        }
                    }
                });

            }
        });


    }


}
