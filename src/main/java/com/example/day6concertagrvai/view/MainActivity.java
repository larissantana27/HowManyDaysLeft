package com.example.day6concertagrvai.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.day6concertagrvai.R;
import com.example.day6concertagrvai.constants.ConcertConstants;
import com.example.day6concertagrvai.data.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;
    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDaysLeft = findViewById(R.id.text_days_left);
        this.mViewHolder.button = findViewById(R.id.button);

        this.mViewHolder.button.setOnClickListener(this);

        this.mViewHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));
        String dayLeft = String.format("%s %s", String.valueOf(this.getDaysLeft()), getString(R.string.dias));
        this.mViewHolder.textDaysLeft.setText(dayLeft);
    }

    @Override
    public void onClick(View v) {
        if ((v.getId() == R.id.button)) {
            Intent intent = new Intent(this, DetailsActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onResume() {

        super.onResume();
        this.verifyPresence();
    }

    private void verifyPresence() {

        String precence = this.mSecurityPreferences.getStoredString(ConcertConstants.PRESENCE_KEY);

        if (precence.equals(ConcertConstants.CONFIRMED)){

            this.mViewHolder.button.setText(getString(R.string.confirmado));
            this.mViewHolder.button.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        else if (precence.equals(ConcertConstants.NOT_CONFIRMED)){

            this.mViewHolder.button.setText(getString(R.string.nao_confirmado));
            this.mViewHolder.button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }

    }

    private int getDaysLeft() {

        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);

        Calendar calendarLastDay = Calendar.getInstance();
        int dayMax = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return dayMax - (today + 38);
    }

    private static class ViewHolder {
        TextView textToday;
        TextView textDaysLeft;
        Button button;
    }
}
