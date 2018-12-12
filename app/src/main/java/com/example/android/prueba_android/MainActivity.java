package com.example.android.prueba_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.android.prueba_android.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void stringReverse(View view){
        Intent stringReverseIntent = new Intent(this, StringReverse.class);
        startActivity(stringReverseIntent);
    }

    public void javaAnagrams(View view){
        Intent stringReverseIntent = new Intent(this, JavaAnagrams.class);
        startActivity(stringReverseIntent);
    }

    public void javaSort(View view){
        Intent stringReverseIntent = new Intent(this, JavaSort.class);
        startActivity(stringReverseIntent);
    }


}
