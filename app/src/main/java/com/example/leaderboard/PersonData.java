package com.example.leaderboard;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PersonData {

    public String name;
    public int number;
    public String country;
    public  String badge;

    public PersonData(String name, int number, String country,String badge) {

        this.name = name;
        this.number = number;
        this.country = country;
        this.badge = badge;
    }

}
