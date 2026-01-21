package com.example.menus;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHold> {
    Context context;
    static ArrayList<EventModel> events;
    private static final boolean[] checkedItems = new boolean[3];
    private static String selectedItem;
    public EventAdapter(Context context, ArrayList<EventModel> events) {
        this.context = context;
        this.events = events;
    }

    @NonNull
    @Override
    public EventAdapter.ViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cv_row, parent, false);
        return new EventAdapter.ViewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.ViewHold holder, int position) {
        holder.tvTitle.setText(events.get(position).getEventTitle());
        holder.tvDate.setText(events.get(position).getEventDate());
        holder.tvLocation.setText(events.get(position).getEventLocation());
        holder.setEventDetails(position);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHold extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate, tvLocation;
        CardView cv;

        public ViewHold(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            cv = itemView.findViewById(R.id.row_color);
        }

        public void setEventDetails(int position) {
            itemView.setOnClickListener(v -> {
                Random random = new Random();
                String[] date = tvDate.getText().toString().split(" ");

                String item1, item2, item3;
                int rand2 = Integer.parseInt(date[0]) + random.nextInt(1000) - random.nextInt(1000);
                int rand3 = Integer.parseInt(date[0]) + random.nextInt(1000) - random.nextInt(1000);

                if (date.length > 1) {
                    item1 = date[0] + " " + date[1];
                    item2 = rand2 + " " + date[1];
                    item3 = rand3 + " " + date[1];
                } else {
                    item1 = date[0];
                    item2 = String.valueOf(rand2);
                    item3 = String.valueOf(rand3);
                }

                // AlertDialog content
                String[] listItems = {item1, item2, item3};
                List<String> l = Arrays.asList(listItems);
                Collections.shuffle(l);
                listItems = l.toArray(new String[0]);
                String[] finalListItems = listItems;

                AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext())
                        .setIcon(R.drawable.baseline_menu_book_24dp)
                        .setTitle(tvTitle.getText())
                        .setSingleChoiceItems(listItems, -1, (dialog, which) -> selectedItem = finalListItems[which])
                        .setPositiveButton("Done", (dialog, which) -> {
                            EventModel selectedEvent = events.get(position);
                            if (Objects.equals(selectedItem, tvDate.getText().toString())) {
                                events.remove(selectedEvent);
                                notifyItemRemoved(position);
                                Toast.makeText(itemView.getContext(), "Correcto", Toast.LENGTH_SHORT).show();
                            } else {
                                cv.setCardBackgroundColor(itemView.getContext().getResources().getColor(R.color.red));
                                Toast.makeText(itemView.getContext(), "Incorrecto", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("CANCEL", (dialog, which) -> dialog.dismiss());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            });
        }
    }

}
