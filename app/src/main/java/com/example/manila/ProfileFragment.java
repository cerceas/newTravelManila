package com.example.manila;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class ProfileFragment extends Fragment implements onBackPressed {
    private View view;
    private DatabaseHelperForUsers mydb;
    private TextView Exp;
    private TextView Level;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        Exp=view.findViewById(R.id.TextViewExp);
        Level=view.findViewById(R.id.TextViewLevel);
        mydb = new DatabaseHelperForUsers(view.getContext());
        int User = getArguments().getInt("User");
        Exp.setText(String.valueOf(mydb.getExp(User)));
        Level.setText(String.valueOf(mydb.getLevel(User)));
        return view;
    }

    public void logout() {
        startActivity(new Intent(getActivity(),LoginActivity.class));
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(getActivity(), "Profile Nope", Toast.LENGTH_SHORT).show();
    }

}
