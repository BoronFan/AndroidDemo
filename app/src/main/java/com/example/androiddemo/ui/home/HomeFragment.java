package com.example.androiddemo.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.androiddemo.R;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        root.findViewById(R.id.count_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击按钮，计数
                homeViewModel.doSomething();
                Log.d(TAG,"点击计数按钮");
            }
        });
        root.findViewById(R.id.reset_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击按钮，重置
                homeViewModel.reset();
                Log.d(TAG,"点击重置按钮");
            }
        });
        root.findViewById(R.id.img_view1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击按钮
                homeViewModel.doSomething();
                Log.d(TAG,"点击计数按钮");
            }
        });

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
