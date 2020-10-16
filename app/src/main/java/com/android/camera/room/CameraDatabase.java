package com.android.camera.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@SuppressWarnings("ALL")
@Database(entities = CameraEntity.class, version = 1, exportSchema = false)
public abstract class CameraDatabase extends RoomDatabase {
    private static CameraDatabase mInstance;
    static synchronized CameraDatabase getCameraDatabase(Context context) {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(context.getApplicationContext(), CameraDatabase.class, "camera_data")
                    .build();
        }
        return mInstance;
    }

    public abstract CameraDao getDaoBasic();

}
