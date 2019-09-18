package com.appocean.callingapp;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appocean.callingapp.databinding.ActivityCreateRoomBinding;
import com.appocean.callingapp.firebase.FirebaseUsecase;
import com.appocean.callingapp.firebase.FirebaseWrapper;
import com.appocean.callingapp.model.Room;
import com.appocean.callingapp.util.BaseActivity;
import com.appocean.callingapp.util.ItemDecorationAlbumColumns;
import com.appocean.callingapp.util.SessionManager;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.appocean.callingapp.util.PrefConstant.USER_ID;

public class CreateRoomActivity extends BaseActivity implements RoomAdapter.ClickListener {
    private static final int PERMISSION_REQUEST_CODE = 1009;
    ActivityCreateRoomBinding mBinding;
    RoomAdapter roomAdapter;
    List<Room> roomList = new ArrayList<>();
    GridLayoutManager gridLayoutManager;
    private FirebaseUsecase mFirebase;
    private String uid;
    private FirebaseFirestore db;
    public String roomId = "";
    // List of mandatory application permissions.
    private static final String[] MANDATORY_PERMISSIONS = {"android.permission.MODIFY_AUDIO_SETTINGS",
            "android.permission.RECORD_AUDIO", "android.permission.INTERNET"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_room);
        setUpToolBar();
        initRecyclerView();
        mFirebase = new FirebaseWrapper(this);
        db = FirebaseFirestore.getInstance();
    }

    private void setUpToolBar() {
        Toolbar mToolBar = findViewById(R.id.toolbar);
        mToolBar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(mToolBar);
        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setDisplayShowHomeEnabled(true);
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
        checkRequiredPermission();
        RoomConnectionManager connectionManager = new RoomConnectionManager(this);
        connectionManager.connectToRoom(this, room, false, false, false, 0);
        uid = SessionManager.getInstance().getString(USER_ID);
        if (!roomId.equals("") && uid != null && !uid.equals(""))
            mFirebase.deleteUser(roomId, uid);
        roomId = room.getRoomName();
        addUserToRoom(room);
        getRoomUser(room);
    }

    private void getRoomUser(Room room) {
        mFirebase.getUsersInRoom(room, new FirebaseWrapper.QueryCallback() {
            @Override
            public void onQueryResult(QuerySnapshot querySnapshot) {
                for (DocumentSnapshot documentSnapshot : querySnapshot) {
                    Log.e("User Info", documentSnapshot.toString());
                }
            }
        });
    }

    private void addUserToRoom(Room room) {
        mFirebase.addUserToRoom(room, uid);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!roomId.equals("") && uid != null && !uid.equals(""))
            mFirebase.deleteUser(roomId, uid);
    }

    private boolean checkRequiredPermission() {
        for (String permission : MANDATORY_PERMISSIONS) {
            if (checkCallingOrSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(MANDATORY_PERMISSIONS, PERMISSION_REQUEST_CODE);
                }
            }
        }
        return false;
    }
}
