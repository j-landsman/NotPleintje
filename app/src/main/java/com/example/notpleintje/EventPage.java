package com.example.notpleintje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class EventPage extends AppCompatActivity {
    private ImageView cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        Bundle bundle = getIntent().getExtras();

        String url = bundle.getString("url");
        String title = bundle.getString("title");
        String day = bundle.getString("day");
        String month = bundle.getString("month");
        String place = bundle.getString("place");
        String count = bundle.getString("count");
        String time = bundle.getString("time");
        String creator = bundle.getString("creator");
        String desc = bundle.getString("desc");

        ImageView eventImage = (ImageView)findViewById(R.id.event_img);
        Glide.with(eventImage).load(url).into(eventImage);

        TextView eventTitle = (TextView)findViewById(R.id.event_title);
        eventTitle.setText(title);

        TextView eventPlace = (TextView)findViewById(R.id.event_place);
        eventPlace.setText(place);

        TextView eventDate = (TextView)findViewById(R.id.event_date);
        eventDate.setText(dateNrToLtr(day,month));

        TextView eventTime = (TextView)findViewById(R.id.event_time);
        eventTime.setText(time);

        TextView eventDesc = (TextView)findViewById(R.id.event_desc);
        eventDesc.setText(desc);

        TextView eventCreator = (TextView)findViewById(R.id.event_creator);
        eventCreator.setText(creator);

        String countString = "+" + count;
        TextView eventCount = (TextView)findViewById(R.id.count);
        eventCount.setText(countString);

        cancel = (ImageView)findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EventPage.this, EventList.class));
            }
        });
    }

    private String dateNrToLtr(String day, String month){
        String month_nr = "00";
        switch (month){
            case "JAN":
                month_nr = "01";
                break;
            case "FEB":
                month_nr = "02";
                break;
            case "MAR":
                month_nr = "03";
                break;
            case "APR":
                month_nr = "04";
                break;
            case "MAY":
                month_nr = "05";
                break;
            case "JUN":
                month_nr = "06";
                break;
            case "JUL":
                month_nr = "07";
                break;
            case "AUG":
                month_nr = "08";
                break;
            case "SEP":
                month_nr = "09";
                break;
            case "OCT":
                month_nr = "10";
                break;
            case "NOV":
                month_nr = "11";
                break;
            case "DEC":
                month_nr = "12";
                break;
        }
        int cur_year = 2022;
        String date = day + "-" + month_nr + "-" + cur_year;
        return date;
    }
}