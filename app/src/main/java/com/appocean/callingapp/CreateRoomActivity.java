package com.appocean.callingapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appocean.callingapp.databinding.ActivityCreateRoomBinding;
import com.appocean.callingapp.model.Room;
import com.appocean.callingapp.util.ItemDecorationAlbumColumns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateRoomActivity extends AppCompatActivity implements RoomAdapter.ClickListener {
    ActivityCreateRoomBinding mBinding;
    RoomAdapter roomAdapter;
    List<Room> roomList = new ArrayList<>();
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_room);
        setUpToolBar();
        initRecyclerView();
    }

    private void setUpToolBar() {
        Toolbar mToolBar = findViewById(R.id.toolbar);
        mToolBar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.toolbarTextHome));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initRecyclerView() {
        roomAdapter = new RoomAdapter(this);
        roomAdapter.setClickListener(this);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2, RecyclerView.VERTICAL, false);
        mBinding.roomList.setLayoutManager(gridLayoutManager);
        mBinding.roomList.addItemDecoration(new ItemDecorationAlbumColumns(this, R.dimen.item_offset));
        mBinding.roomList.setAdapter(roomAdapter);
        roomList = createRoomList();
        roomAdapter.setRooms(roomList);


    }

    public List<Room> createRoomList() {
        List<String> roomNames = Arrays.asList(getResources().getStringArray(R.array.roomList));
        for (int i = 0; i < roomNames.size(); i++) {
            Room room = new Room();
            room.setRoomName(roomNames.get(i));
            roomList.add(room);
        }
        return roomList;
    }


    @Override
    public void onRoomClicked(Room room) {
        RoomConnectionManager connectionManager = new RoomConnectionManager(this);
        connectionManager.connectToRoom(this, room, false, false, false, 0);


    }
}
