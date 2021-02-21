package com.example.busyatri;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class MainFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Button btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonSelected();
            }
        });
        return view;

    }
    private onFragmentBtnSelected listener;
      public void onAttach(@NonNull Context context){
          super.onAttach(context);
          if(context instanceof onFragmentBtnSelected){
              listener = (onFragmentBtnSelected) context;
          }
          else{
              throw new ClassCastException(context.toString()+ "must implement listener");
          }
        }
      public interface onFragmentBtnSelected{
          public void onButtonSelected();
      }

    }

