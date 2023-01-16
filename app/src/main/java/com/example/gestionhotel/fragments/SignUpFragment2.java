package com.example.gestionhotel.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestionhotel.R;
import com.google.android.material.button.MaterialButton;

/**
 * Fragment responsible for the second step
 * of the signup process,
 * accessible from the SignUpFragment fragment
 */
public class SignUpFragment2 extends Fragment {

    public interface SignUpFragment2Listener {
        void onSubmit2(String fName, String lName);
    }

    private SignUpFragment2Listener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_sign_up2, container, false);

        EditText fName=(EditText) v.findViewById(R.id.signUpNameET);
        EditText lName=(EditText) v.findViewById(R.id.signUpName2ET);
        TextView errMsg=(TextView) v.findViewById(R.id.signUpErrTV2);
        MaterialButton submit=(MaterialButton) v.findViewById(R.id.signUpBtn2);

        submit.setOnClickListener(view -> {
            if (true) {
                listener.onSubmit2(fName.getText().toString(),lName.getText().toString());
            } else //TODO validar si el usuario existe
                Toast.makeText(v.getContext(), "Este usuario ya existe.", Toast.LENGTH_SHORT).show();
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SignUpFragment2Listener){
            listener=(SignUpFragment2Listener) context;
        } else throw new RuntimeException(context +" must implement SignUpFragment2Listener.");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }
}