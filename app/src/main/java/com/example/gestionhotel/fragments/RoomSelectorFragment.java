package com.example.gestionhotel.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gestionhotel.R;
import com.google.android.material.button.MaterialButton;


public class RoomSelectorFragment extends Fragment {

    private RoomSelectorListener listener;
    private TextView item1Title, item1Descr, item2Title, item2Descr, item3Title, item3Descr;
    private ImageView item1IV, item2IV, item3IV;
    private MaterialButton item1Btn, item2Btn, item3Btn;

    public interface RoomSelectorListener{
        void onRoomSelected(String id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_room_selector, container, false);

        item1Title=v.findViewById(R.id.item1TV);
        item1Descr=v.findViewById(R.id.item1TV2);
        item1IV=v.findViewById(R.id.item1IV);
        item1Btn=v.findViewById(R.id.item1Btn);

        item2Title=v.findViewById(R.id.item2TV);
        item2Descr=v.findViewById(R.id.item2TV2);
        item2IV=v.findViewById(R.id.item2IV);
        item2Btn=v.findViewById(R.id.item2Btn);

        item3Title=v.findViewById(R.id.item3TV);
        item3Descr=v.findViewById(R.id.item3TV2);
        item3IV=v.findViewById(R.id.item3IV);
        item3Btn=v.findViewById(R.id.item3Btn);

        item1Btn.setOnClickListener(view -> listener.onRoomSelected("1"));
        item2Btn.setOnClickListener(view -> listener.onRoomSelected("2"));
        item3Btn.setOnClickListener(view -> listener.onRoomSelected("3"));

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof RoomSelectorListener){
            listener=(RoomSelectorListener) context;
        } else throw new RuntimeException(context +" must implement RoomSelectorListener.");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }
}