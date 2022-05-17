package com.example.notpleintje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.notpleintje.Adapters.EventsRecyclerViewAdapter;
import com.example.notpleintje.Models.EventModel;
import com.example.notpleintje.Repository.EventsRepo;

import java.util.ArrayList;

public class EventList extends AppCompatActivity {
    RecyclerView recyclerView;
    EventsRecyclerViewAdapter eventsRecyclerViewAdapter;
    private ArrayList<EventModel> eventModelArrayList;
    private TextView eventAmtTxt;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_list);
        eventModelArrayList = EventsRepo.getEventsRepo().getEventModelList();
        recyclerView = findViewById(R.id.recyclerView);
        initRecyclerView();

        int eventAmt = eventModelArrayList.size();
        String eventAmtString = eventAmt + " events in my area";
        eventAmtTxt = (TextView) findViewById(R.id.event_amt_text);
        eventAmtTxt.setText(eventAmtString);

        backBtn = (ImageView) findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EventList.this, HomeActivity.class));
            }
        });
    }

    void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        eventsRecyclerViewAdapter = new EventsRecyclerViewAdapter(eventModelArrayList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(eventsRecyclerViewAdapter);
    }
}