package com.android.camera.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/*
* 数据库版本迁移办法
* 表数据字段的增加删除操作等（删除较为麻烦）
* 1、在 Entity 中添加或者删除相关字段
* 2、在 Database 中修改版本号，并且创建 Migration 实例，重写 migrate 方法，执行 SQLite 语句
* 3、build 之前调用 addMigrations 方法，备份迁移数据库即可
* 其中 fallbackToDestructiveMigration 是破坏性迁移，不保存原有数据库副本
* */

@SuppressWarnings("ALL")
@Database(entities = CameraEntity.class, version = 2, exportSchema = false)
public abstract class CameraDatabase extends RoomDatabase {
    private static CameraDatabase mInstance;
    static synchronized CameraDatabase getCameraDatabase(Context context) {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(context.getApplicationContext(), CameraDatabase.class, "camera_data")
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return mInstance;
    }

    public abstract CameraDao getDaoBasic();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE CameraEntity ADD COLUMN newInfo INTEGER NOT NULL DEFAULT 1");
        }
    };
}
