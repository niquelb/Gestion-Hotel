package com.example.gestionhotel;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

/**
 * Main menu of the app, accessed after logging in
 */
public class MainMenuFragment extends Fragment {

    private MainMenuFragmentListener listener;

    public interface MainMenuFragmentListener {
        void onInputSent(CharSequence input);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_main_menu, container, false);

        MaterialButton bookBtn=(MaterialButton) v.findViewById(R.id.mainMenuBookBtn);
        MaterialButton aboutBtn=(MaterialButton) v.findViewById(R.id.mainMenuAboutBtn);
        MaterialButton locationBtn=(MaterialButton) v.findViewById(R.id.mainMenuLocationBtn);

        bookBtn.setOnClickListener(view -> {
            CharSequence input="book";
            listener.onInputSent(input);
        });

        aboutBtn.setOnClickListener(view -> {
            CharSequence input="about";
            listener.onInputSent(input);
        });

        locationBtn.setOnClickListener(view -> {
            CharSequence input="location";
            listener.onInputSent(input);
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainMenuFragmentListener){
            listener=(MainMenuFragmentListener) context;
        } else throw new RuntimeException(context +" must implement MainMenuFragmentListener.");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }
}