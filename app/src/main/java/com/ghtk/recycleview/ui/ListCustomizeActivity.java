package com.ghtk.recycleview.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ghtk.recycleview.R;
import com.ghtk.recycleview.adapter.SimpleAdapter;
import com.ghtk.recycleview.object.ItemObject;
import com.ghtk.recycleview.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SonNM on 6/5/2022.
 */

public class ListCustomizeActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    SimpleAdapter mAdapter;
    List<ItemObject> itemObjectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        setupData();
        setTitle("ListActivity");
        //Setup RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true); //  false: sẽ liên lục vẽ lại bố cục.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new SimpleAdapter(this,itemObjectList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnCallback(new SimpleAdapter.OnCallback() {
            @Override
            public void onRemoveItem(int position) {
                mAdapter.remove(position);
            }

            @Override
            public void onAddItem(int position) {
                mAdapter.add(new ItemObject("Label "+ (mAdapter.getItemCount()+1), Utils.getColorRamdom() ),(position+1));
            }
        });
    }


    private void setupData(){
        for (int i = 1; i < 30; i++){
            itemObjectList.add(new ItemObject("Label "+i, Utils.getColorRamdom()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.recycler, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_add) {
            mAdapter.add(new ItemObject("Label "+ (mAdapter.getItemCount()+1), Utils.getColorRamdom() ),mAdapter.getItemCount());
            return true;
        }
        if (id == R.id.action_remove) {
            if(mAdapter.getItemCount()>0){
                mAdapter.remove(mAdapter.getItemCount() -1);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
