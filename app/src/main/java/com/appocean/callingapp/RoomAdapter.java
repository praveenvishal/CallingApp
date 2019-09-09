package com.appocean.callingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appocean.callingapp.model.Room;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RoomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mInflator;
    private List<Room> rooms;
    public ClickListener clickListener;

    RoomAdapter(Context context) {
        mInflator = LayoutInflater.from(context);
        rooms = new ArrayList<>();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }


    public void setRooms(List<Room> list) {
        rooms.clear();
        rooms.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflator.inflate(R.layout.list_item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Room room = rooms.get(position);
        ((RoomViewHolder) holder).bindRoom(room);

    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    private class RoomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        MaterialButton tvRoomName;
        Room room;


        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRoomName = itemView.findViewById(R.id.tvRoomName);
            itemView.setOnClickListener(this);
        }

        public void bindRoom(Room room) {
            this.room = room;
            tvRoomName.setText(room.getRoomName());

        }


        @Override
        public void onClick(View view) {
            clickListener.onRoomClicked(room);
        }
    }


    public interface ClickListener {

        void onRoomClicked(Room room);
    }

}
