package com.example.fragmentmailicerik;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

public class MailListFragment extends ListFragment {

    private boolean isMultiPanel;
    private int currentPosition = 0;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        Activity activity = getActivity();
        String [] subjects = activity.getResources().getStringArray(R.array.mail_subjects);

        setListAdapter(new ArrayAdapter<String>(activity,android.R.layout.simple_list_item_activated_1, subjects));
        View mailIcerikPanel = activity.findViewById(R.id.mailContent);

        isMultiPanel = mailIcerikPanel != null && mailIcerikPanel.getVisibility() == View.VISIBLE;
        System.out.println(isMultiPanel);

        if(savedInstanceState != null){
            currentPosition = savedInstanceState.getInt("mevcutPozisyon",0);
        }

        if(isMultiPanel){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            icerikGoster(currentPosition);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("mevcutPozisyon",currentPosition);
    }


    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        icerikGoster(position);
        super.onListItemClick(l, v, position, id);

    }

    void icerikGoster(int index){
        currentPosition = index;

        if(isMultiPanel){
            getListView().setItemChecked(index, true);

            MailIcerikFragment icerik = (MailIcerikFragment) getFragmentManager().findFragmentById(R.id.mailContent);
            if(icerik == null || icerik.getShownIndex() != index){
                icerik = MailIcerikFragment.newInstance(index);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mailContent, icerik);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        }else  {

            Intent intent = new Intent(getActivity(),ContentActivity.class);

            intent.putExtra("index",index);
            startActivity(intent);
        }

    }
}
