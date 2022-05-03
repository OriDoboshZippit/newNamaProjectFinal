package com.example.newnamaproject.ui.login;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.newnamaproject.R;
import com.example.newnamaproject.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private TextView register;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;
    private String users, email, password;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private ProgressBar progressBar;
    public String nameFromDB;
    public Query currentUser;
    //    ActivityReadDataBinding binding;
    DatabaseReference databaseReference;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        System.out.println("On create login");
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
//        signIn = (Button) getView().findViewById(R.id.login_btn);
        editTextEmail = (EditText) getView().findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = (EditText) getView().findViewById(R.id.editTextTextPassword);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        currentUser = databaseReference.orderByChild("adressText");
        Button b = (Button) getView().findViewById(R.id.login_btn);
        mAuth = FirebaseAuth.getInstance();
        b.setOnClickListener(mButtonClickListener);


    }
//
    private View.OnClickListener mButtonClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            email = editTextEmail.getText().toString().trim();
            password = editTextPassword.getText().toString().trim();
            System.out.println(email + " IS " + password);
            System.out.println(mAuth.signInWithEmailAndPassword(email, password));
            System.out.println(email + " IS " + password);

//            currentUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                try {
//                    nameFromDB = dataSnapshot.child(mAuth.getCurrentUser().getUid()).child("fullName").getValue(String.class);
//                    System.out.println("NameFromDb is " +  nameFromDB);
//                }
//                catch (Exception e) {
//                    System.out.println("Oops you need to login again!");
////                    Toast.makeText(Login.this, "Oops you need to login again! ", Toast.LENGTH_LONG).show();
//                }
//
//                if(dataSnapshot.exists()){
//                    System.out.println("Data snap shoot work" );
//                    System.out.println("Password is " +  nameFromDB);
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        }
    };


    public void mAuthFunc() {
        System.out.println("HHHHHEYYY "+ email + password);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //redirect to the activity profile
                    System.out.println("Yeeeeey we got in to the system with name !!!! " + nameFromDB);
                    Intent i = new Intent(getActivity(),HomeFragment.class);
                    i.putExtra("id", nameFromDB);
                    System.out.println(nameFromDB);
                    if(nameFromDB!=null) {
                        startActivity(i);
                    }
                    System.out.println("HHHHHEYYY IT IS GET AUTH "+ email + password);
                } else {
                    System.out.println("Yeeeeey we DONT got in to the system with name because nameFromDb is " + nameFromDB);

                }

            }
        });
    }
}
//    public void loginFunc(View view) {
//        email = editTextEmail.getText().toString().trim();
//        password = editTextPassword.getText().toString().trim();
//        System.out.println(email + " IS " +password);
        //pull the current user

//        System.out.println("IM in LOGIN");
//    }
//}