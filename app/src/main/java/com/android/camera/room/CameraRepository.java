package com.android.camera.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

@SuppressWarnings("ALL")
class CameraRepository {
    private CameraDao mCameraDao;
    private LiveData<List<CameraEntity>> allCameraEntity;

    public CameraRepository(Context context) {
        CameraDatabase cameraDatabase = CameraDatabase.getCameraDatabase(context.getApplicationContext());
        mCameraDao = cameraDatabase.getDaoBasic();
        allCameraEntity = mCameraDao.getAllCameraEntityOfLiveData();
    }

    public LiveData<List<CameraEntity>> getAllCameraEntity() {
        return allCameraEntity;
    }

    public void insert(CameraEntity... cameraEntities) {
        new InsertAsyncTasked(mCameraDao).execute(cameraEntities);
    }

    public void clear() {
        new ClearAsyncTasked(mCameraDao).execute();
    }

    static class InsertAsyncTasked extends AsyncTask<CameraEntity, Void, Void> {
        private CameraDao daoBasic;

        public InsertAsyncTasked(CameraDao cameraDao) {
            this.daoBasic = cameraDao;
        }

        @Override
        protected Void doInBackground(CameraEntity... cameraEntities) {
            daoBasic.insert(cameraEntities);
            return null;
        }
    }

    static class ClearAsyncTasked extends AsyncTask<CameraEntity, Void, Void> {
        private CameraDao daoBasic;

        public ClearAsyncTasked(CameraDao cameraDao) {
            this.daoBasic = cameraDao;
        }

        @Override
        protected Void doInBackground(CameraEntity... cameraEntities) {
            daoBasic.clear();
            return null;
        }
    }
}
