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

import java.util.regex.Pattern;

/**
 * Fragment responsible for the first step
 * of the signup process,
 * accessed from the Login activity
 */
public class SignUpFragment extends Fragment {

    public interface SignUpFragmentListener {
        void onSubmit1(String email, String password);
    }

    private SignUpFragmentListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_sign_up, container, false);

        EditText email=(EditText) v.findViewById(R.id.signUpUNameET);
        EditText passwd=(EditText) v.findViewById(R.id.signUpPwdET);
        EditText passwd2=(EditText) v.findViewById(R.id.signUpPwdRepeatET);
        TextView errMsg=(TextView) v.findViewById(R.id.signUpErrTV);
        MaterialButton submit=(MaterialButton) v.findViewById(R.id.signUpBtn1);

        submit.setOnClickListener(view -> {
            if (validateUName(email.getText().toString())
                && passwd.getText().toString().equals(passwd2.getText().toString())
                && validatePassword(passwd.getText().toString()))
                listener.onSubmit1(email.getText().toString(),passwd.getText().toString());
            else {
                Toast.makeText(v.getContext(), "Los datos son incorrectos", Toast.LENGTH_SHORT).show();
                errMsg.setText("Asegurate que los datos cumplan con las siguientes guias:" +
                        "\n\tLa direccion de correo debe ser valida." +
                        "\n\tLa contraseña debe ser de 8 caracteres o mas." +
                        "\n\tLa contraseña debe contener 1 digito, 1 mayuscula y 1 minuscula.");
            }
        });

        return v;
    }

    /**
     * Method for validating password:
     *  Min 8 characters
     *  At least 1 uppercase and 1 lowercase
     *  At least 1 number
     * @param target String to compare to
     * @return True if string matches, false if not
     */
    public final static boolean validatePassword(String target) {
        return Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$").matcher(target).matches();
    }

    /**
     * Method for validating usernames (emails):
     *  [a-zA-Z0-9\-\.]@[a-zA-Z\-].[a-zA-Z\-]
     *  ^ Simplified version
     * @param target String to compare to
     * @return True if string matches, false if not
     */
    public final static boolean validateUName(String target) {
        return Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(target).matches();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SignUpFragmentListener){
            listener=(SignUpFragmentListener) context;
        } else throw new RuntimeException(context +" must implement SignUpFragmentListener.");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }
}