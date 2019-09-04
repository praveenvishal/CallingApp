package com.appocean.callingapp.firebase;

import com.appocean.callingapp.model.Room;

import java.util.List;

public interface FirebaseUsecase {

    List<Room> getRoomList();

    void addRoom(Room room);

    void updateRoom(Room room);

    void deleteRoom(Room room);

     Room getRoom(String roomId);


}
