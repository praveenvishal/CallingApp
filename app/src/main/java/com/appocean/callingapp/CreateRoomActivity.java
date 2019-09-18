package com.appocean.callingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.appocean.callingapp.databinding.ActivityCreateRoomBinding;
import com.appocean.callingapp.firebase.FirebaseUsecase;
import com.appocean.callingapp.firebase.FirebaseWrapper;
import com.appocean.callingapp.googleads.GoogleAds;
import com.appocean.callingapp.model.Room;
import com.appocean.callingapp.util.BaseActivity;
import com.appocean.callingapp.util.ItemDecorationAlbumColumns;
import com.appocean.callingapp.util.SessionManager;
import com.appocean.callingapp.util.ThemePreferenceActivity;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.appocean.callingapp.util.PrefConstant.USER_ID;

public class CreateRoomActivity extends BaseActivity implements RoomAdapter.ClickListener {
    ActivityCreateRoomBinding mBinding;
    RoomAdapter roomAdapter;
    List<Room> roomList = new ArrayList<>();
    GridLayoutManager gridLayoutManager;
    private FirebaseUsecase mFirebase;
    private String uid;
    private FirebaseFirestore db;
    public String roomId = "";
    private int SETTINGS_ACTION = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_room);
        setUpToolBar();
        initRecyclerView();
        mFirebase = new FirebaseWrapper(this);
        db = FirebaseFirestore.getInstance();
        GoogleAds.setInterstitialAd(this);
    }

    private void setTheme() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String themeName = pref.getString("theme", "Theme1");
        if (themeName.equals("Theme1")) {
            setTheme(R.style.Theme1);
        } else if (themeName.equals("Theme2")) {
            Toast.makeText(this, "set theme", Toast.LENGTH_SHORT).show();
            setTheme(R.style.Theme2);
        }
        Toast.makeText(this, "Theme has been reset to " + themeName,
                Toast.LENGTH_SHORT).show();
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
   /* private boolean checkRequiredPermission() {
        for (String permission : MANDATORY_PERMISSIONS) {
            if (checkCallingOrSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(MANDATORY_PERMISSIONS, PERMISSION_REQUEST_CODE);
                }
            }
        }
        return false;
    }*/

    private void addUserToRoom(Room room) {
        mFirebase.addUserToRoom(room, uid);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!roomId.equals("") && uid != null && !uid.equals(""))
            mFirebase.deleteUser(roomId, uid);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.settings:
                startActivityForResult(new Intent(this,
                        ThemePreferenceActivity.class), SETTINGS_ACTION);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SETTINGS_ACTION) {
            if (resultCode == ThemePreferenceActivity.RESULT_CODE_THEME_UPDATED) {
                finish();
                startActivity(getIntent());
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
