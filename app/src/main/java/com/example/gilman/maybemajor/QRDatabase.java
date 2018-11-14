package com.example.gilman.maybemajor;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {SavedData.class}, version = 1)
public abstract class QRDatabase extends RoomDatabase {

    abstract QRDao getQrDao();
}
