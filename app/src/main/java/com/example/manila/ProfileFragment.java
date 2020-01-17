package com.example.manila;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import pub.devrel.easypermissions.EasyPermissions;


public class ProfileFragment extends Fragment implements onBackPressed {
    private View view;
    private DatabaseHelperForUsers mydb;
    private TextView Exp;
    private static int RESULT_LOAD_IMAGE = 1;
    private TextView Level;
    private ImageView imageView;
    private TextView Profilename;
    private TextView password;
    private TextView email;
    int User;
    private Button image;
    private Button editProfile;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
         User = getArguments().getInt("User");
        Exp=view.findViewById(R.id.TextViewExp);
        Level=view.findViewById(R.id.TextViewLevel);
        imageView=view.findViewById(R.id.ImageViewProfile);
        image=view.findViewById(R.id.LoadImage);
        mydb = new DatabaseHelperForUsers(view.getContext());
        Profilename=view.findViewById(R.id.TextViewEditUsername);
        password=view.findViewById(R.id.TextViewEditPass);
        email=view.findViewById(R.id.TextViewEditEmail);
        Cursor UserPassEmail=mydb.getUsernamePassEmail(User);


        Profilename.setText(UserPassEmail.getString(1)+" "+UserPassEmail.getString(2));
        password.setText(UserPassEmail.getString(4));
        email.setText(UserPassEmail.getString(3));

        Exp.setText(String.valueOf(mydb.getExp(User)));
        Level.setText(String.valueOf(mydb.getLevel(User)));
        editProfile=view.findViewById(R.id.UpdatePicture);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

                if (EasyPermissions.hasPermissions(view.getContext(), galleryPermissions)) {
                    Intent i = new Intent(
                            Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                } else {
                    EasyPermissions.requestPermissions(view.getContext(), "Access for storage",
                            101, galleryPermissions);
                }
            }
        });
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfileActivity.class);
                intent.putExtra("User", User);
                startActivity(intent);
            }
        });

        return view;
    }

    public void logout() {
        startActivity(new Intent(getActivity(),LoginActivity.class));
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(getActivity(), "Profile Nope", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == getActivity().RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            // String picturePath contains the path of selected Image
        }

    }
}
