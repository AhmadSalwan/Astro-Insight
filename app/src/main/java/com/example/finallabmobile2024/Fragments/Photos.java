package com.example.finallabmobile2024.Fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finallabmobile2024.Adapter.ImageAdapter;
import com.example.finallabmobile2024.Database.DatabaseContract;
import com.example.finallabmobile2024.Database.DatabaseHelper;
import com.example.finallabmobile2024.Models.Images;
import com.example.finallabmobile2024.R;

import java.util.ArrayList;
import java.util.Currency;

public class Photos extends Fragment {

    ImageAdapter adapter;

    ArrayList<Images> images=new ArrayList<>();
    DatabaseHelper Db;

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_photos,container,false);
        RecyclerView recyclerView=view.findViewById(R.id.RecyclerViewLayout);
        Db=new DatabaseHelper(requireContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter=new ImageAdapter(images,requireContext());
        recyclerView.setAdapter(adapter);

        displayData();

        return view;
    }
    private void displayData(){
        Cursor cursor=Db.getData();
        if(cursor.getCount()==0){
            Toast.makeText(requireContext(),"No Entry Yet",Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                int id=cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ImagesColumns._ID));
                String title=cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ImagesColumns.TITLE));
                String description=cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ImagesColumns.DESCRIPTION));
                String image=cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ImagesColumns.IMAGE));
                Images recImage=new Images(id,title,description,image);
                images.add(recImage);
                adapter.notifyDataSetChanged();
            }
            cursor.close();
        }
    }

}