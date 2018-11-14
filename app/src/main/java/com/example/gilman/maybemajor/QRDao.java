package com.example.gilman.maybemajor;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;

public interface QRDao {

    @Insert
    void addEntity(SavedData data);

    @Insert
    void addEntity(String data);

    @Delete
    void deleteEntity(SavedData data);

    @Query("select * from savedQR")
    ArrayList<SavedData> getData();
}
