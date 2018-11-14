package com.example.gilman.maybemajor;

import java.util.ArrayList;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "savedQR")
public class SavedData {
    @PrimaryKey
    int id;

    @ColumnInfo
    String savedData;

    public SavedData(String savedData) {
        this.savedData = savedData;
    }

    public SavedData(int id, String savedData) {
        this.id = id;
        this.savedData = savedData;
    }

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
