package com.example.notpleintje;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.notpleintje.Repository.EventsRepo;
import com.google.android.material.button.MaterialButton;

import java.sql.Time;

public class CreateActivity extends AppCompatActivity {
    private ImageView backBtn;
    private TextView locationError;
    private Spinner eventTypeSpinner;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        backBtn = (ImageView) findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateActivity.this, HomeActivity.class));
            }
        });

        locationError = (TextView) findViewById(R.id.location_error);
        locationError.setVisibility(View.GONE);

        eventTypeSpinner = (Spinner) findViewById(R.id.event_type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.event_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventTypeSpinner.setAdapter(adapter);
        eventTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                title = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        EditText locationInputter = (EditText) findViewById(R.id.location_input);
        DatePicker dateInputter = (DatePicker) findViewById(R.id.date_picker);
        TimePicker timeInputter = (TimePicker) findViewById(R.id.time_picker);
        timeInputter.setIs24HourView(true);
        EditText descInputter = (EditText) findViewById(R.id.desc_input);

        MaterialButton createEvent = (MaterialButton) findViewById(R.id.create_event_btn);
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String locationInput = locationInputter.getText().toString();

                if (locationInput.isEmpty()){
                    locationError.setVisibility(View.VISIBLE);
                    NestedScrollView scroller = (NestedScrollView) findViewById(R.id.scroll_view);
                    scroller.scrollTo(0,0);
                }
                else {
                    String descInput = descInputter.getText().toString();

                    String time = Integer.toString(timeInputter.getHour()) + ":" + Integer.toString(timeInputter.getMinute());
                    String day = Integer.toString(dateInputter.getDayOfMonth());
                    int monthNum = dateInputter.getMonth() + 1;
                    String monthLtr = monthNumToLtr(monthNum);
                    String creator = "test creator";

                    AlertDialog.Builder builder = new AlertDialog.Builder(CreateActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Create event?");
                    String eventInfo = "Activity: " + title + "\nLocation: " + locationInput + "\nDate: " + day + "-" + monthNum + "-" + dateInputter.getYear() + "\nTime: " + time + "\nDescription: " + descInput;
                    builder.setMessage(eventInfo);
                    builder.setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    EventsRepo.getEventsRepo().addEvent(day, monthLtr, title, locationInput, time, creator, descInput);

                                    Toast.makeText(getApplicationContext(), "Event created!", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(CreateActivity.this, HomeActivity.class));
                                }
                            });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

    private String monthNumToLtr (int monthNum){
        String month_ltr = "JAN";
        switch (monthNum){
            case 1:
                month_ltr = "JAN";
                break;
            case 2:
                month_ltr = "FEB";
                break;
            case 3:
                month_ltr = "MAR";
                break;
            case 4:
                month_ltr = "APR";
                break;
            case 5:
                month_ltr = "MAY";
                break;
            case 6:
                month_ltr = "JUN";
                break;
            case 7:
                month_ltr = "JUL";
                break;
            case 8:
                month_ltr = "AUG";
                break;
            case 9:
                month_ltr = "SEP";
                break;
            case 10:
                month_ltr = "OCT";
                break;
            case 11:
                month_ltr = "NOV";
                break;
            case 12:
                month_ltr = "DEC";
                break;
        }
        return month_ltr;
    }
}