package com.appocean.callingapp.firebase;

import com.appocean.callingapp.model.Room;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public interface FirebaseUsecase {

    List<Room> getRoomList();

    void addRoom(Room room);

    void updateRoom(Room room);

    void deleteRoom(Room room);

    Room getRoom(String roomId);

    void deleteUser(String room, String userId);

    void addUserToRoom(Room room, String userId);

    QuerySnapshot getUsersInRoom(Room room, FirebaseWrapper.QueryCallback queryCallback);

    void updateUserInfo(Room room, String userId);
}
