package com.example.level6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private EditText memail,mpassword,mconfirmPassword;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn=findViewById(R.id.btn_signup);
        memail=findViewById(R.id.txt_username);
        mpassword=findViewById(R.id.txt_password);
        mconfirmPassword=findViewById(R.id.txt_confirm_password);

        firebaseAuth=FirebaseAuth.getInstance();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });
    }
    private void registerNewUser(){
        String email=memail.getText().toString().trim();
        String password=mpassword.getText().toString().trim();
        String confirmPassword=mconfirmPassword.getText().toString().trim();

        if (email.isEmpty()){
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
        }
        if (password.isEmpty()){
            Toast.makeText(this, "Please Enter Passwor", Toast.LENGTH_SHORT).show();
        }
        if (password.length()>6){
            Toast.makeText(this, "Password is To Short", Toast.LENGTH_SHORT).show();
        }
        if (password.equals(confirmPassword)){
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Signup Comp", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Signup Failed ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}