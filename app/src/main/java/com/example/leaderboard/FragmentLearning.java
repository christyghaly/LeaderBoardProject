package com.example.leaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class FragmentLearning extends Fragment {
    View mView;
    private RecyclerView myrecyclerview;
    private ArrayList<PersonData> mPersonDataArrayList;
    public FragmentLearning (ArrayList<PersonData> dataList) {
        mPersonDataArrayList=dataList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_learning,container,false);
        myrecyclerview=(RecyclerView) mView.findViewById(R.id.learning_recyclerview);
        RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(getContext(),mPersonDataArrayList,0);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerViewAdapter);
        return mView;
    }

}
