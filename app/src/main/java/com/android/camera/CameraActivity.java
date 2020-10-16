package com.android.camera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.camera.room.CameraDao;
import com.android.camera.room.CameraDatabase;
import com.android.camera.room.CameraEntity;
import com.android.camera.settings.CameraSettingsActivity;

import java.util.List;

@SuppressWarnings("ALL")
public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    CameraDao mCameraDao;
    CameraDatabase mCameraDatabase;
    LiveData<List<CameraEntity>> allCameraEntity;

    TextView textView;
    Button bt_test, bt_insert, bt_clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_activity);
        mCameraDatabase = Room.databaseBuilder(this, CameraDatabase.class, "camera_data")
                .build();
        mCameraDao = mCameraDatabase.getDaoBasic();
        allCameraEntity = mCameraDao.getAllCameraEntityOfLiveData();
        initView();
        allCameraEntity.observe(this, new Observer<List<CameraEntity>>() {
            @Override
            public void onChanged(List<CameraEntity> entityBasics) {
                StringBuilder text = new StringBuilder();
                for (int i = 0; i < entityBasics.size(); i++) {
                    CameraEntity entityBasic = entityBasics.get(i);
                    text.append(entityBasic.getId()).append(":").append(entityBasic.getKey()).append("=").append(entityBasic.getValue()).append('\n');
                }
                textView.setText(text.toString());
            }
        });
    }

    void initView() {
        bt_insert = findViewById(R.id.insert);
        bt_insert.setOnClickListener(this);

        bt_clear = findViewById(R.id.clear);
        bt_clear.setOnClickListener(this);

        textView = findViewById(R.id.text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insert:
                new InsertAsyncTasked(mCameraDao)
                        .execute(new CameraEntity("hello", "你好", "1"));
                 break;
            case R.id.clear:
                new ClearAsyncTasked(mCameraDao)
                        .execute();
                break;
            default:
                break;
        }
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