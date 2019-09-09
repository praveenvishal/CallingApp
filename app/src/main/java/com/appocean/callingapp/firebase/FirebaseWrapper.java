package com.appocean.callingapp.firebase;

import android.content.Context;

import com.appocean.callingapp.model.Room;
import com.appocean.callingapp.phonenumberui.utility.Utility;
import com.appocean.callingapp.util.SessionManager;
import com.google.android.exoplayer2.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import androidx.annotation.NonNull;

import static com.appocean.callingapp.util.PrefConstant.USER_ID;

public class FirebaseWrapper implements FirebaseUsecase {
    private final String uid;
    FirebaseFirestore firestore;
    String TAG = FirebaseWrapper.class.getSimpleName();

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

    @Override
    public void deleteUser(String roomName, String userId) {
        firestore.collection(roomName).document(userId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });

    }

    @Override
    public void addUserToRoom(Room room, String userId) {
        firestore.collection(room.getRoomName()).document(uid).set(room);

    }

    QuerySnapshot document = null;

    @Override
    public QuerySnapshot getUsersInRoom(Room room, QueryCallback queryCallback) {
        firestore.collection(room.getRoomName()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    document = task.getResult();
                    queryCallback.onQueryResult(document);
                } else {
                    Log.w("MainActivity", "Error getting documents.", task.getException());
                }
            }
        });
        firestore.collection(room.getRoomName()).get().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
        return document;
    }

    @Override
    public void updateUserInfo(Room room, String userId) {
        firestore.collection(room.getRoomName()).document(uid)
                .update("currentlyActive", true);
    }

    public interface QueryCallback {
        void onQueryResult(QuerySnapshot querySnapshot);
    }


}
