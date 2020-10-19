package com.android.camera.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@SuppressWarnings("ALL")
@Entity
public class CameraEntity {
    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo
    public String key;

    @ColumnInfo
    private String value;

    @ColumnInfo
    private String defaultValue;

    @ColumnInfo (name = "newInfo")
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public CameraEntity(String key, String value, String defaultValue) {
        this.key = key;
        this.value = value;
        this.defaultValue = defaultValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
