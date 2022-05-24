package com.example.notpleintje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notpleintje.Repository.EventsRepo;
import com.google.android.material.button.MaterialButton;

public class CreateActivity extends AppCompatActivity {
    private ImageView backBtn;
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
        EditText dateInputter = (EditText) findViewById(R.id.date_input);
        EditText timeInputter = (EditText) findViewById(R.id.time_input);
        EditText descInputter = (EditText) findViewById(R.id.desc_input);

        MaterialButton createEvent = (MaterialButton) findViewById(R.id.create_event_btn);
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String locationInput = locationInputter.getText().toString();
                String timeInput = timeInputter.getText().toString();
                String dateInput = dateInputter.getText().toString();
                String descInput = descInputter.getText().toString();


                String day = dateInput.substring(0,2);
                String monthNums = dateInput.substring(dateInput.length()-2);
                String monthLtr = monthNumToLtr(monthNums);
                String creator = "test creator";

                EventsRepo.getEventsRepo().addEvent(day, monthLtr, title, locationInput, timeInput, creator, descInput);

                Toast.makeText(getApplicationContext(), "Event created!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(CreateActivity.this, HomeActivity.class));
            }
        });
    }

    private String monthNumToLtr (String monthNums){
        String month_ltr = "JAN";
        switch (monthNums){
            case "01":
                month_ltr = "JAN";
                break;
            case "02":
                month_ltr = "FEB";
                break;
            case "03":
                month_ltr = "MAR";
                break;
            case "04":
                month_ltr = "APR";
                break;
            case "05":
                month_ltr = "MAY";
                break;
            case "06":
                month_ltr = "JUN";
                break;
            case "07":
                month_ltr = "JUL";
                break;
            case "08":
                month_ltr = "AUG";
                break;
            case "09":
                month_ltr = "SEP";
                break;
            case "10":
                month_ltr = "OCT";
                break;
            case "11":
                month_ltr = "NOV";
                break;
            case "12":
                month_ltr = "DEC";
                break;
        }
        return month_ltr;
    }
}