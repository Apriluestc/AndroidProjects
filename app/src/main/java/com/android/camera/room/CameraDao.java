package com.android.camera.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@SuppressWarnings("ALL")
@Dao
public interface CameraDao {
    @Insert
    void insert(CameraEntity... cameraEntities);

    @Delete
    void delete(CameraEntity... cameraEntities);

    @Update
    void update(CameraEntity... cameraEntities);

    @Query("DELETE FROM CAMERAENTITY")
    void clear();

    @Query("SELECT * FROM CAMERAENTITY ORDER BY ID DESC")
    List<CameraEntity> getAllCameraEntityOfList();

    @Query("SELECT * FROM CAMERAENTITY ORDER BY ID DESC")
    LiveData<List<CameraEntity>> getAllCameraEntityOfLiveData();
}
