package com.example.sainaveen.trail1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    Button bt,bt2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        et1=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);
        bt=(Button)findViewById(R.id.button2);
        bt2=(Button)findViewById(R.id.button3);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = et1.getText().toString();
                String password = et2.getText().toString();
                if (user.matches(" ") || password.matches("")) {
                    Toast.makeText(MainActivity.this, "please provide credentials", Toast.LENGTH_SHORT).show();
                }
                else{
                final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "",
                        "Loging in. Please wait...", true);
                mAuth.signInWithEmailAndPassword(user, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser usern = mAuth.getCurrentUser();
                                    Toast.makeText(MainActivity.this, "login success", Toast.LENGTH_SHORT).show();
                                    Intent in = new Intent(MainActivity.this, Home.class);
                                    in.putExtra("user", usern.getDisplayName());
                                    startActivity(in);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    dialog.cancel();
                                    Toast.makeText(MainActivity.this, "login failed", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = et1.getText().toString();
                String password = et2.getText().toString();
                if (user.matches(" ") || password.matches("")) {
                    Toast.makeText(MainActivity.this, "please provide credentials", Toast.LENGTH_SHORT).show();
                }
                else {
                    createuser(user, password);
                }
            }
            private void createuser(String user, String password) {
                final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "",
                        "Creating account. Please wait...", true);
                mAuth.createUserWithEmailAndPassword(user, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser usern = mAuth.getCurrentUser();
                                    Toast.makeText(MainActivity.this, "login success", Toast.LENGTH_SHORT).show();
                                    Intent in = new Intent(MainActivity.this, Home.class);
                                    in.putExtra("user", usern.getDisplayName());
                                    startActivity(in);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    dialog.cancel();
                                    Toast.makeText(MainActivity.this, "fails", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
        });
    }

    /*
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
       if(currentUser.getEmail().matches("")){
           Toast.makeText(this, "kjhkad", Toast.LENGTH_SHORT).show();
           Intent in=new Intent(MainActivity.this,Home.class);
           in.putExtra("user",currentUser.getEmail());
           startActivity(in);
        }
        else{
           Intent in=new Intent(MainActivity.this,Home.class);
           in.putExtra("user",currentUser.getEmail());
           startActivity(in);
       }
    }*/
}
