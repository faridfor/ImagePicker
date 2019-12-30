package com.nguyenhoanglam.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import java.util.ArrayList;

/**
 * Created by hoanglam on 8/28/17.
 */

public class MainFragment extends Fragment {

    private static final String EXTRA_CONFIG = "Config";


    private Config config;
    private ImageAdapter adapter;


    static MainFragment newInstance(Config config) {
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_CONFIG, config);

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        config = getArguments().getParcelable(EXTRA_CONFIG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        Button pickImageButton = view.findViewById(R.id.button_pick_image);

        pickImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });

        adapter = new ImageAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void start() {

        ImagePicker.with(this)
                .setFolderMode(config.isFolderMode())
                .setCameraOnly(config.isCameraOnly())
                .setFolderTitle(config.getFolderTitle())
                .setMultipleMode(config.isMultipleMode())
                .setSelectedImages(config.getSelectedImages())
                .setMaxSize(config.getMaxSize())
                .start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Config.RC_PICK_IMAGES && resultCode == Activity.RESULT_OK && data != null) {
            ArrayList<Image> images = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES);
            adapter.setData(images);
        }
    }
}
