package com.example.edgelight;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class SettingsViewHolder extends RecyclerView.ViewHolder{
    private final TextView packageName;
    private final Switch onOff;
    private final Spinner titlePos;
    private final Spinner textPos;

    public SettingsViewHolder(View itemView, final SettingsHelper settingsHelper) {
        super(itemView);
        onOff = itemView.findViewById(R.id.onOff);
        packageName = itemView.findViewById(R.id.package_name);
        titlePos = itemView.findViewById(R.id.title_pos);
        textPos = itemView.findViewById(R.id.text_pos);

        onOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                settingsHelper.update(packageName.getText().toString(),
                        isChecked,
                        titlePos.getSelectedItemPosition(),
                        textPos.getSelectedItemPosition());
            }
        });


        ArrayAdapter<CharSequence> titleAdapter = ArrayAdapter.createFromResource(itemView.getContext(),
                R.array.dropDown, android.R.layout.simple_spinner_item);


        titleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        titlePos.setAdapter(titleAdapter);

        titlePos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                settingsHelper.update(
                        packageName.getText().toString(),
                        onOff.isChecked(),
                        position,
                        textPos.getSelectedItemPosition()
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<CharSequence> textAdapter = ArrayAdapter.createFromResource(itemView.getContext(),
                R.array.dropDown, android.R.layout.simple_spinner_item);


        textAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        textPos.setAdapter(textAdapter);

        textPos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                settingsHelper.update(
                        packageName.getText().toString(),
                        onOff.isChecked(),
                        titlePos.getSelectedItemPosition(),
                        position
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setTitle(String title) {
        packageName.setText(title);
    }

    public void setOnOff(Boolean on) {
        onOff.setChecked(on);
    }

    public void setTitlePos(int pos) {
        titlePos.setSelection(pos);
    }

    public void setTextPos(int pos) {
        textPos.setSelection(pos);
    }
}
