package com.example.fragmentmailicerik;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MailIcerikFragment extends Fragment {

    public static MailIcerikFragment newInstance(int index) {
        MailIcerikFragment fragment = new MailIcerikFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    public int getShownIndex(){
        return getArguments().getInt("index",0);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.content, container,false);
        TextView mailIcerikTextView = view.findViewById(R.id.mailIcerikTextView);
        Activity activity = getActivity();
        String [] mailContents = activity.getResources().getStringArray(R.array.mail_content);
        mailIcerikTextView.setText(mailContents[getShownIndex()]);


        return view;
    }



}
