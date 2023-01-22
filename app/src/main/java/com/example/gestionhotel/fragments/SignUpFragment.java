package com.example.gestionhotel.fragments;

import static com.example.gestionhotel.EmailPasswordChecker.validatePassword;
import static com.example.gestionhotel.EmailPasswordChecker.validateUName;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestionhotel.R;
import com.example.gestionhotel.data.viewmodels.UserViewModel;
import com.google.android.material.button.MaterialButton;

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
    private UserViewModel userViewModel;
    private boolean checkEmailBool;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userViewModel =new ViewModelProvider(this).get(UserViewModel.class);
    }

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
                && validatePassword(passwd.getText().toString())) {

                userViewModel.getUser(email.getText().toString())
                        .observe((LifecycleOwner) v.getContext(),
                        checkEmail -> {
                            if (checkEmail!=null) {
                                checkEmailBool=true;
                            } else checkEmailBool=false;
                            if (checkEmailBool) {
                                errMsg.setText("Este email ya esta registrado, por favor, introduce otro.");
                            } else
                                listener.onSubmit1(email.getText().toString(),passwd.getText().toString());
                        });
            }
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