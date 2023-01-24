package com.example.gestionhotel.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestionhotel.R;
import com.example.gestionhotel.data.entities.Habitacion;
import com.example.gestionhotel.data.viewmodels.RoomViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.List;


public class RoomSelectorFragment extends Fragment {

    private RoomSelectorListener listener;
    private RoomViewModel roomViewModel;
//    private List<Habitacion> rooms;

    private TextView item1Title, item1Descr, item2Title, item2Descr, item3Title, item3Descr;
    private ImageView item1IV, item2IV, item3IV;
    private MaterialButton item1Btn, item2Btn, item3Btn, nextPageBtn;
    private short page=0;

    public interface RoomSelectorListener{
        void onRoomSelected(String id);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roomViewModel=new ViewModelProvider(this).get(RoomViewModel.class);
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

        nextPageBtn=v.findViewById(R.id.nextPageBtn);
        nextPageBtn.setOnClickListener(view -> page++);

       /* do {
            rooms = roomViewModel.getAllRooms().getValue();
        } while (rooms==null);*/

        roomViewModel.getAllRooms().observe(getViewLifecycleOwner(),rooms->{
            if (rooms!=null) {
                if (rooms.size() < 4) {
                    nextPageBtn.setVisibility(View.INVISIBLE);
                }

                item1Title.setText(rooms.get(this.page).getDescrip());
                item1Descr.setText(String.format("%s por noche.", rooms.get(this.page).getPrecio()));

                item2Title.setText(rooms.get(this.page + 1).getDescrip());
                item2Descr.setText(String.format("%s por noche.", rooms.get(this.page + 1).getPrecio()));

                item3Title.setText(rooms.get(this.page + 2).getDescrip());
                item3Descr.setText(String.format("%s por noche.", rooms.get(this.page + 2).getPrecio()));
            } else Toast.makeText(v.getContext(), "null", Toast.LENGTH_SHORT).show();
            });
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