package com.example.notpleintje.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.notpleintje.EventList;
import com.example.notpleintje.EventPage;
import com.example.notpleintje.Models.EventModel;
import com.example.notpleintje.R;

import java.util.ArrayList;

public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EventsRecyclerViewAdapter.ViewHolder>{
    ArrayList<EventModel> eventModelArrayList = new ArrayList<>();

    public EventsRecyclerViewAdapter(ArrayList<EventModel> eventModelArrayList) {
        this.eventModelArrayList = eventModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = eventModelArrayList.get(position).getTitle();
        String day = eventModelArrayList.get(position).getDay();
        String month = eventModelArrayList.get(position).getMonth();
        String place = eventModelArrayList.get(position).getPlace();
        String count = eventModelArrayList.get(position).getCount();
        String time = eventModelArrayList.get(position).getTime();
        String creator = eventModelArrayList.get(position).getCreator();
        String url = eventModelArrayList.get(position).getUrl();
        String desc = eventModelArrayList.get(position).getDesc();

        holder.title.setText(title);
        holder.day.setText(day);
        holder.month.setText(month);
        holder.place.setText(place);
        holder.count.setText(count);
        Glide.with(holder.imageView).load(url).into(holder.imageView);
        holder.itemView.setClickable(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.context, EventPage.class);
                Bundle bundle = new Bundle();
                bundle.putString("title",title);
                bundle.putString("day", day);
                bundle.putString("month", month);
                bundle.putString("place", place);
                bundle.putString("count", count);
                bundle.putString("time", time);
                bundle.putString("creator", creator);
                bundle.putString("url", url);
                bundle.putString("desc", desc);
                intent.putExtras(bundle);
                holder.context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventModelArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView day, month, title, place, count;
        ImageView imageView;
        private final Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            day = itemView.findViewById(R.id.day);
            month = itemView.findViewById(R.id.month);
            title = itemView.findViewById(R.id.eventTitle);
            place = itemView.findViewById(R.id.location);
            count = itemView.findViewById(R.id.count);
            imageView = itemView.findViewById(R.id.card_image);
        }
    }
}
