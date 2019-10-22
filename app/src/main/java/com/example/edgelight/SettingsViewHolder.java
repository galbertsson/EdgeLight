package com.example.edgelight;

import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class SettingsViewHolder extends RecyclerView.ViewHolder{
    private final TextView packageName;
    private final Switch onOff;
    private final TextView titlePos;
    private final TextView textPos;

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
                        Integer.parseInt(titlePos.getText().toString()),
                        Integer.parseInt(textPos.getText().toString()));
            }
        });

        itemView.setOnClickListener(new View.OnClickListener() { //TODO: Create more for the different settings
            @Override
            public void onClick(View v) {
                //presenter.deckClicked(getAdapterPosition());
                Log.d("EdgeLightning", "Clickicle lick");
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
        titlePos.setText(pos+"");
    }

    public void setTextPos(int pos) {
        textPos.setText(pos+"");
    }
}
