package com.ghtk.recycleview.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.ghtk.recycleview.R;
import com.ghtk.recycleview.adapter.GridAdapter;
import com.ghtk.recycleview.adapter.StaggerdGridAdapter;

/**
 * Created by SonNM on 6/5/2022.
 */

public class GridActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private StaggerdGridAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        //Setup RecyclerView
        mRecyclerView =  findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new StaggerdGridAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setupRcvVertical(){
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));
    }
    private void setupRcvHorizontal(){
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false));
    }
    private void setupRcvStaggered(){
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager( 5, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recycler, menu);
        MenuItem menuItem3 = menu.findItem(R.id.action_vertical);
        MenuItem menuItem4 = menu.findItem(R.id.action_horizontal);
        MenuItem menuItem5 = menu.findItem(R.id.action_staggered);
        menuItem3.setVisible(true);
        menuItem4.setVisible(true);
        menuItem5.setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            mAdapter.addItem(0);
            return true;
        }
        if (id == R.id.action_remove) {
            mAdapter.removeItem(mAdapter.getItemCount()-1);
            return true;
        }
        if (id == R.id.action_vertical) {
            setupRcvVertical();
            return true;
        }
        if (id == R.id.action_horizontal) {
            setupRcvHorizontal();
            return true;
        }
        if (id == R.id.action_staggered) {
            setupRcvStaggered();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
