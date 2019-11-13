package com.example.edgelight;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.edgelight.util.Debounce;

class SettingsViewHolder extends RecyclerView.ViewHolder{
    private final TextView packageName;
    private final Switch onOff;
    private final Spinner titlePos;
    private final Spinner textPos;
    private final EditText text;

    private Debounce textDebounce = new Debounce(1000);

    public SettingsViewHolder(View itemView, final SettingsHelper settingsHelper) {
        super(itemView);
        onOff = itemView.findViewById(R.id.onOff);
        packageName = itemView.findViewById(R.id.package_name);
        titlePos = itemView.findViewById(R.id.title_pos);
        textPos = itemView.findViewById(R.id.text_pos);
        text = itemView.findViewById(R.id.header);

        onOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                settingsHelper.update(packageName.getText().toString(),
                        isChecked,
                        titlePos.getSelectedItemPosition(),
                        textPos.getSelectedItemPosition(),
                        text.getText().toString()
                );
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
                        textPos.getSelectedItemPosition(),
                        text.getText().toString()
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
                        position,
                        text.getText().toString()
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textDebounce.execute(new Runnable() {
                    @Override
                    public void run() {
                        settingsHelper.update(
                                packageName.getText().toString(),
                                onOff.isChecked(),
                                titlePos.getSelectedItemPosition(),
                                textPos.getSelectedItemPosition(),
                                text.getText().toString()
                        );
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

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

    public void setHeader(String header) {
        text.setText(header);
    }
}
