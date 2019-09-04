package com.appocean.callingapp.model;

import java.io.Serializable;

public class Room implements Serializable {
    private String roomName;
    private boolean isInitiator;
    private boolean isCurrentlyActive;

    public boolean isCurrentlyActive() {
        return isCurrentlyActive;
    }

    public void setCurrentlyActive(boolean currentlyActive) {
        isCurrentlyActive = currentlyActive;
    }


    public boolean isInitiator() {
        return isInitiator;
    }

    public void setInitiator(boolean initiator) {
        isInitiator = initiator;
    }


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
