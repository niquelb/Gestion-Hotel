package com.example.gestionhotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.gestionhotel.data.entities.Usuario;
import com.example.gestionhotel.data.viewmodels.UserViewModel;
import com.example.gestionhotel.fragments.SignUpFragment;
import com.example.gestionhotel.fragments.SignUpFragment2;

/**
 * Activity responsible for containing the signup fragments,
 * accessible from Login activity
 */
public class SignUp extends AppCompatActivity implements SignUpFragment.SignUpFragmentListener, SignUpFragment2.SignUpFragment2Listener {

    private String email, password, fName, lName;
    private UserViewModel uViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ViewModelProvider.AndroidViewModelFactory factory=
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());

        uViewModel=new ViewModelProvider(this, (ViewModelProvider.Factory) factory).get(UserViewModel.class);
    }

    /**
     * Method responsible to pass the inserted user to
     * the viewModel to be saved in the DB and returns
     * the user to the Login activity
     * @param usuario User to be saved.
     */
    public void saveUser(Usuario usuario) {
        uViewModel.insertUser(usuario);

        Intent i=new Intent(SignUp.this, Login.class);
        startActivity(i);
        finish();
    }

    /**
     * Method that gets executed on the first step of the
     * signup process that gets the email and password of the
     * user
     * @param email User's email
     * @param password User's password
     */
    @Override
    public void onSubmit1(String email, String password) {
//        Toast.makeText(this, "Email: "+email+" Password: "+password, Toast.LENGTH_SHORT).show();
        SignUpFragment2 signUpFragment2=new SignUpFragment2();
        this.email=email;
        this.password=password;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.signUpFragmentContainer, signUpFragment2).commit();
    }

    /**
     * Method that gets executed on the second step of the
     * signup process that gets the first and last names of
     * the user
     * @param fName User's first name
     * @param lName User's last names
     */
    @Override
    public void onSubmit2(String fName, String lName) {
//        Toast.makeText(this, fName+' '+lName, Toast.LENGTH_SHORT).show();
        this.fName=fName;
        this.lName =lName;
        Usuario u=new Usuario(this.email,this.fName,this.lName,this.password);

        saveUser(u);
    }
}