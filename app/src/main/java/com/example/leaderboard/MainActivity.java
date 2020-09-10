package com.example.leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;
    private ProgressBar mLoadingLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // to hide the action bar
        setContentView(R.layout.activity_main_progress_pager);
        mLoadingLauncher=(ProgressBar)findViewById(R.id.progressBar);

        Button button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSubActivity();
            }
        });



        mTabLayout = (TabLayout) findViewById(R.id.tabLayout_id);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_id);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        // Adding the Fragments
        String[] paths={"hours","skilliq"};


        for (int i=0;i<paths.length;i++) {
            try {
               URL leader_url = ApiUtil.buildUrl(paths[i]);
                new LeaderQueryTask(i).execute(leader_url);

            } catch (Exception e) {
                Log.d("error", e.getMessage());
            }
        }

    }

    private void openSubActivity() {
        Intent intent=new Intent(this,SubmissionActivity.class);
        startActivity(intent);
    }

    public  class LeaderQueryTask extends AsyncTask<URL, Void, String> {
         private int type=0;
         public LeaderQueryTask(int mytype){
             this.type=mytype;
         }



        @Override
        protected String doInBackground(URL... urls) { //has no access to the ui
            URL searchURL = urls[0];
            String result = null;
            try {
                result = ApiUtil.getJson(searchURL);
            } catch (IOException e) {
                Log.d("error", e.getMessage());
            }
            return result;
        }
    @Override
    protected void onPostExecute(String s) {
             new Handler().postDelayed(new Runnable() {
                 @Override
                 public void run() {
                     mLoadingLauncher.setVisibility(View.INVISIBLE);

                 }
             },2000);
        ArrayList<PersonData> dataArrayList =ApiUtil.getPersonDataFromJson(s,type);

        if(type==0) {
            mAdapter.addFragment(new FragmentLearning(dataArrayList), "Learning Leaders");
        }else if(type==1) {
            mAdapter.addFragment(new FragmentSkill(dataArrayList), "Skill IQ Leaders");
        }
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingLauncher.setVisibility(View.VISIBLE);
        }
    }
}