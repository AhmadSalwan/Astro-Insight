package com.example.finallabmobile2024.Activity;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.finallabmobile2024.Fragments.AddPhotos;
import com.example.finallabmobile2024.Fragments.Main;
import com.example.finallabmobile2024.Fragments.Photos;
import com.example.finallabmobile2024.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new Main());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.buttonleft) {
                replaceFragment(new Main());
            } else if (item.getItemId() == R.id.buttonmid) {
                replaceFragment(new Photos());
            }else if (item.getItemId() == R.id.buttonright) {
                replaceFragment(new AddPhotos());}
            return true;
        });
}
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}