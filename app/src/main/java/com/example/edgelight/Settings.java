package com.example.edgelight;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class Settings extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_settings);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);


 //           ChoosingDeckPresenter presenter = new ChoosingDeckPresenter(this);

            setContentView(R.layout.activity_settings);
            RecyclerView mRecyclerView = findViewById(R.id.settingsList);
            mRecyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

            mRecyclerView.setLayoutManager(layoutManager);

            RecyclerView.Adapter mAdapter = new SettingsAdapter(new SettingsHelper(getPackageManager()));
            mRecyclerView.setAdapter(mAdapter);
        }
}