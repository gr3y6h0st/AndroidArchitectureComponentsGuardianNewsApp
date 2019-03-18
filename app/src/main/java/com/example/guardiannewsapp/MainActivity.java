package com.example.guardiannewsapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.guardiannewsapp.adapters.GuardianNewsMainAdapter;
import com.example.guardiannewsapp.models.Results;
import com.example.guardiannewsapp.viewmodels.NewsDataViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private NewsDataViewModel mNewsDataViewModel;
    @BindView(R.id.recycler_view_main)
    RecyclerView recyclerViewMain;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    GuardianNewsMainAdapter guardianNewsMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        mNewsDataViewModel = ViewModelProviders.of(this).get(NewsDataViewModel.class);

        //retrieve data from repo
        mNewsDataViewModel.init();

        mNewsDataViewModel.getAllNews().observe(this, new Observer<List<Results>>() {
            @Override
            public void onChanged(@Nullable final List<Results> results) {
                // Update the cached copy of the words in the adapter.
                guardianNewsMainAdapter.notifyDataChanged(results);
                //tv.setText(results.get(6).getWebUrl());
            }
        });
        guardianNewsMainAdapter = new GuardianNewsMainAdapter(this, mNewsDataViewModel.getAllNews().getValue());
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerViewMain.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewMain.setHasFixedSize(true);
        recyclerViewMain.setAdapter(guardianNewsMainAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
