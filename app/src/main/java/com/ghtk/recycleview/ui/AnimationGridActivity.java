package com.ghtk.recycleview.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ghtk.recycleview.R;
import com.ghtk.recycleview.adapter.AnimationGridAdapter;

/**
 * Created by SonNM on 6/5/2022.
 */

public class AnimationGridActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    protected AnimationGridAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        //Setup Spinner

        //Setup RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        mAdapter = new AnimationGridAdapter(this);
//        AlphaAnimatorAdapter animatorAdapter = new AlphaAnimatorAdapter(mAdapter, mRecyclerView);
//        animatorAdapter.getViewAnimator().setInitialDelayMillis(500);
//        mRecyclerView.setAdapter(animatorAdapter);

    }



}