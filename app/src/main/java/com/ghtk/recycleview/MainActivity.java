package com.ghtk.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ghtk.recycleview.ui.GridActivity;
import com.ghtk.recycleview.ui.ListActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void linear(View viw){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    public void grid(View viw){
        Intent intent = new Intent(this, GridActivity.class);
        startActivity(intent);
    }
    

}