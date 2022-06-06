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
import com.ghtk.recycleview.adapter.CustomizeAdapter;
import com.ghtk.recycleview.adapter.SimpleAdapter;
import com.ghtk.recycleview.object.ItemObject;
import com.ghtk.recycleview.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SonNM on 6/5/2022.
 */

public class ListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    SimpleAdapter mAdapter;
    CustomizeAdapter mAdapterCus;
    List<ItemObject> itemObjectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        setTitle("ListActivity");
        setupData();
        mRecyclerView = findViewById(R.id.list);
        /** xác định vị trí hướng vẽ item */
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        /** thêm divider giữa các item*/
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new SimpleAdapter(this,itemObjectList);
        mAdapterCus = new CustomizeAdapter(this,itemObjectList);
        setupEvent();
        setupRcvNormal();

    }

    private void setupRcvNormal(){
        mRecyclerView.setHasFixedSize(true); //  false: sẽ liên lục vẽ lại bố cục.
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setupRcvCustomize(){
        mRecyclerView.setHasFixedSize(true); //  false: sẽ liên lục vẽ lại bố cục.
        mRecyclerView.setAdapter(mAdapterCus);
    }

    private void setupRcvVertical(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void setupRcvHorizontal(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }
    private void setupEvent(){
        mAdapter.setOnCallback(new SimpleAdapter.OnCallback() {
            @Override
            public void onRemoveItem(int position) {
                mAdapter.remove(position);
            }

            @Override
            public void onAddItem(int position) {
                mAdapter.add(new ItemObject("Label "+ (mAdapterCus.getItemCount()+1), Utils.getColorRamdom() ),(position+1));
            }
        });

        mAdapterCus.setOnCallback(new CustomizeAdapter.OnCallback() {
            @Override
            public void onRemoveItem(int position) {
                mAdapterCus.remove(position);
            }

            @Override
            public void onAddItem(int position) {
                mAdapterCus.add(new ItemObject("Label "+ (mAdapterCus.getItemCount()+1), Utils.getColorRamdom() ),(position+1));
            }
        });
    }


   private void setupData(){
        for (int i = 1; i < 20; i++){
            itemObjectList.add(new ItemObject("Label "+i, Utils.getColorRamdom()));
        }
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.recycler, menu);
        MenuItem menuItem1 = menu.findItem(R.id.action_customize);
        MenuItem menuItem2 = menu.findItem(R.id.action_normal);
        MenuItem menuItem3 = menu.findItem(R.id.action_vertical);
        MenuItem menuItem4 = menu.findItem(R.id.action_horizontal);
        menuItem1.setVisible(true);
        menuItem2.setVisible(true);
        menuItem3.setVisible(true);
        menuItem4.setVisible(true);
//        menu.removeItem(R.id.action_customize);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        if (id == R.id.action_customize) {
           setupRcvCustomize();
            return true;
        }
        if (id == R.id.action_normal) {
            setupRcvNormal();
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
        return super.onOptionsItemSelected(item);
    }

}
