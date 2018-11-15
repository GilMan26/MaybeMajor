package com.example.gilman.maybemajor;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface QRDao {

    @Insert
    void addEntity(SavedData data);

    @Delete
    void deleteEntity(SavedData data);

    @Query("select * from savedQR")
    List<SavedData> getData();
}
