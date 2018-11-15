package com.example.gilman.maybemajor;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "savedQR")
public class SavedData {
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo
    String savedData;

    public SavedData(String savedData) {
        this.savedData = savedData;
    }

//    public SavedData(int id, String savedData) {
//        this.id = id;
//        this.savedData = savedData;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSavedData() {
        return savedData;
    }

    public void setSavedData(String savedData) {
        this.savedData = savedData;
    }
}
