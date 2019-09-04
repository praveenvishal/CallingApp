package com.appocean.callingapp.firebase;

import android.content.Context;

import androidx.annotation.NonNull;

import com.appocean.callingapp.model.Room;
import com.appocean.callingapp.phonenumberui.utility.Utility;
import com.appocean.callingapp.util.SessionManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import static com.appocean.callingapp.util.PrefConstant.USER_ID;

public class FirebaseWrapper implements FirebaseUsecase {
    private final String uid;
    FirebaseFirestore firestore;

    public FirebaseWrapper(Context context) { 
        uid = SessionManager.getInstance().getString(USER_ID);
        firestore = FirebaseFirestore.getInstance();

    }

    @Override
    public List<Room> getRoomList() {
        return null;
    }

    @Override
    public void addRoom(Room room) {
        DocumentReference collectionReference = firestore.collection(room.getRoomName()).document(uid);
        collectionReference.set(room).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Utility.logFirebase(e.getMessage());
            }
        });
    }

    public void updateRoom(Room room) {


    }

    @Override
    public void deleteRoom(Room room) {

    }

    @Override
    public Room getRoom(String roomId) {
        return null;
    }




}
