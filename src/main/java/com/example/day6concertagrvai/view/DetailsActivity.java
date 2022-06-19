package com.example.day6concertagrvai.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.day6concertagrvai.R;
import com.example.day6concertagrvai.constants.ConcertConstants;
import com.example.day6concertagrvai.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkParticipate = findViewById(R.id.check_participate);
        this.mViewHolder.checkParticipate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.check_participate) {

             if(this.mViewHolder.checkParticipate.isChecked()) {

                 this.mSecurityPreferences.storeString(ConcertConstants.PRESENCE_KEY, ConcertConstants.CONFIRMED);
             }
             else {

                 this.mSecurityPreferences.storeString(ConcertConstants.PRESENCE_KEY, ConcertConstants.NOT_CONFIRMED);
             }
        }
    }

    @Override
    public void onResume() {

        super.onResume();

        String precence = this.mSecurityPreferences.getStoredString(ConcertConstants.PRESENCE_KEY);

        if (precence.equals(ConcertConstants.CONFIRMED)){

            this.mViewHolder.checkParticipate.setChecked(true);
        }
        else if (precence.equals(ConcertConstants.NOT_CONFIRMED)){

            this.mViewHolder.checkParticipate.setChecked(false);
        }
    }

    private static class ViewHolder {

        CheckBox checkParticipate;
    }
}
