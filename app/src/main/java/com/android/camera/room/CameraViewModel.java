package com.android.camera.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

@SuppressWarnings("ALL")
public class CameraViewModel extends AndroidViewModel {
    private CameraRepository mCameraRepository;
    public CameraViewModel(@NonNull Application application) {
        super(application);
        mCameraRepository = new CameraRepository(application);
    }

    public LiveData<List<CameraEntity>> getAllCameraEntity() {
        return mCameraRepository.getAllCameraEntity();
    }

    public void insert(CameraEntity... cameraEntities) {
        mCameraRepository.insert(cameraEntities);
    }

    public void clear() {
        mCameraRepository.clear();
    }
}
