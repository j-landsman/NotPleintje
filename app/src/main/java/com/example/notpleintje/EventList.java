package com.example.notpleintje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.notpleintje.Adapters.EventsRecyclerViewAdapter;
import com.example.notpleintje.Repository.EventsRepo;

public class EventList extends AppCompatActivity {
    RecyclerView recyclerView;
    EventsRecyclerViewAdapter eventsRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_list);
        recyclerView = findViewById(R.id.recyclerView);
        initRecyclerView();
    }

    void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        eventsRecyclerViewAdapter = new EventsRecyclerViewAdapter(EventsRepo.getEventsRepo().getEventModelList());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(eventsRecyclerViewAdapter);
    }
}

/*binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        NavHostFragment.findNavController(SecondFragment.this)
        .navigate(R.id.action_SecondFragment_to_FirstFragment);
        }
        });*/