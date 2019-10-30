package com.example.edgelight;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class Settings extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_settings);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            CheckBox checkBox = findViewById(R.id.system_applications_checkbox);
            EditText editText = findViewById(R.id.search);

            final RecyclerView mRecyclerView = findViewById(R.id.settingsList);
            mRecyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

            mRecyclerView.setLayoutManager(layoutManager);
            final SettingsHelper settingsHelper = new SettingsHelper(getPackageManager());
            final RecyclerView.Adapter mAdapter = new SettingsAdapter(settingsHelper);
            mRecyclerView.setAdapter(mAdapter);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    settingsHelper.setDisplaySystemApps(isChecked);
                    mAdapter.notifyDataSetChanged();
                }

            });

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    settingsHelper.textFilter(s.toString());
                    mAdapter.notifyDataSetChanged();
                }
            });
        }
}