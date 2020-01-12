package com.example.manila;


import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RewardsFragment extends Fragment {
    private DatabaseHelperForUsers mydb;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Rewards> rewards;
        private View view;
        private int User;
    public RewardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rewards, container, false);
        mydb = new DatabaseHelperForUsers(view.getContext());
        Cursor res = mydb.getAllDataForRewards();
         User = getArguments().getInt("User");
        if (res.getCount() == 0) {
            Toast.makeText(view.getContext(), "EMPTY", Toast.LENGTH_SHORT).show();
            return view;
        } else {
            view = loadDatabase(view);
        }
        return view;
    }
    public View loadDatabase(View view) {
        recyclerView = view.findViewById(R.id.ListRewards);
        mydb = new DatabaseHelperForUsers(getActivity());
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        rewards = new ArrayList<Rewards>();
        Cursor res = mydb.getAllDataForRewards();
        if (res.getCount() == 0) {
            rewards.clear();
        } else {
            while (res.moveToNext()) {
                rewards.add(new Rewards(res.getString(1),res.getString(1)));
            }
        }

        myAdapter = new RewardsAdaptor(getActivity(), rewards,User);
        recyclerView.setAdapter(myAdapter);
        return view;
    }

}
