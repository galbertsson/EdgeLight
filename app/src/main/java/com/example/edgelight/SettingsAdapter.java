package com.example.edgelight;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;


class SettingsAdapter extends RecyclerView.Adapter<SettingsViewHolder> {

    SettingsHelper settingsHelper;

    public SettingsAdapter(SettingsHelper settingsHelper) {
        this.settingsHelper = settingsHelper;
    }

    @Override
    public SettingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SettingsViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.settings_content, parent, false), settingsHelper);
    }

    @Override
    public void onBindViewHolder(SettingsViewHolder holder, int position) {
        settingsHelper.onBindSettingRowViewAtPosition(position, holder);
    }

    @Override
    public int getItemCount() {
        return settingsHelper.getRowsCount();
    }
}
