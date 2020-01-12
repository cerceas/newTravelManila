package com.example.manila;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.plus.PlusOneButton;

import java.util.ArrayList;

public class ListFragment extends Fragment implements onBackPressed{
    private DatabaseHelperForUsers mydb;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Landmarks> landmarks;
    private View view;
    private long count;
    private Toast backToast;
    public static View passdata;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        passdata = view;
        int User = getArguments().getInt("User");
        mydb = new DatabaseHelperForUsers(view.getContext());
        Cursor res = mydb.getAllDataForList(User);
        if (res.getCount() == 0) {
            Toast.makeText(view.getContext(), "EMPTY", Toast.LENGTH_SHORT).show();
            return view;
        } else {
            view = loadDatabase(view, User);
        }

        return view;

    }


    public View loadDatabase(View view, int User) {
        recyclerView = view.findViewById(R.id.List);
        mydb = new DatabaseHelperForUsers(getActivity());
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        landmarks = new ArrayList<Landmarks>();
        Cursor res = mydb.getAllDataForList(User);
        if (res.getCount() == 0) {
            landmarks.clear();
        } else {
            while (res.moveToNext()) {
                String temp = mydb.getLandmarksBaseOnID(res.getInt(1));
                landmarks.add(new Landmarks(temp, "Add Details", temp));
            }
        }

        myAdapter = new LandmarksAdapter(getActivity(), landmarks);
        recyclerView.setAdapter(myAdapter);
        return view;
    }

    @Override
    public void onBackPressed() {
        if(count+2000>System.currentTimeMillis()) {
             backToast.cancel();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("LOGOUT", true);
            startActivity(intent);

            getActivity().finish();
        }else{
            backToast=Toast.makeText(getActivity(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        count=System.currentTimeMillis();
    }
}
