package com.example.sberteacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        final EditText mEmail = findViewById(R.id.email);
        final EditText mPassword = findViewById(R.id.password);

        Button mButtonEnter = findViewById(R.id.button_enter);
        mButtonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this, "Не все поля заполнены", Toast.LENGTH_SHORT).show();
                } else{
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent intent = new Intent(MainActivity.this, TeacherActivity.class);
                                startActivity(intent);
                            } else{
                                Toast.makeText(MainActivity.this, "Что-то не так. Попробуйте снова", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
;           }
        });

    }
}