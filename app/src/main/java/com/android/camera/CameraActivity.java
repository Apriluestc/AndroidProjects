package com.android.camera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
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
import com.android.camera.room.CameraViewModel;
import com.android.camera.settings.CameraSettingsActivity;
import com.android.camera.settings.SettingsActivity;

import java.util.List;

@SuppressWarnings("ALL")
public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    CameraViewModel mCameraViewModel;

    TextView textView;
    Button bt_test, bt_insert, bt_clear, bt_settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_activity);
        mCameraViewModel = ViewModelProviders.of(this).get(CameraViewModel.class);
        initView();
        mCameraViewModel.getAllCameraEntity().observe(this, new Observer<List<CameraEntity>>() {
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

        bt_settings = findViewById(R.id.settings);
        bt_settings.setOnClickListener(this);

        textView = findViewById(R.id.text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insert:
                mCameraViewModel.insert(new CameraEntity("hello", "你好", "1"));
                 break;
            case R.id.clear:
                mCameraViewModel.clear();
                break;
            case R.id.settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                break;
            default:
                break;
        }
    }
}