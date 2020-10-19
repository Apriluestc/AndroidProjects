package com.android.camera.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.camera.R;
import com.android.camera.room.CameraEntity;

import java.util.ArrayList;
import java.util.List;

public class CameraAdapter extends RecyclerView.Adapter<CameraAdapter.CameraViewHolder> {

    List<CameraEntity> allCameraEntity = new ArrayList<>();

    public void setAllCameraEntity(List<CameraEntity> allCameraEntity) {
        this.allCameraEntity = allCameraEntity;
    }

    @NonNull
    @Override
    public CameraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.main_activity, parent, false);
        return new CameraViewHolder(itemView);
    }

    /*
    * */

    @Override
    public void onBindViewHolder(@NonNull final CameraViewHolder holder, int position) {
        CameraEntity cameraEntity = allCameraEntity.get(position);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /*
    * 返回列表数据个数
    * */
    @Override
    public int getItemCount() {
        return allCameraEntity.size();
    }

    static class CameraViewHolder extends RecyclerView.ViewHolder {
        Button button;

        public CameraViewHolder(@NonNull View itemView) {
            super(itemView);
            // 关联组件
            button = itemView.findViewById(R.id.bt_button);
        }
    }
}
