package com.example.android.tubes_android;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tubes_android.admin.MainAdmin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsername, txtPassword;
    private TextView signup;
    private Button btnSignin;
    private ProgressDialog loading;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.inuser);
        txtPassword = findViewById(R.id.inpass);
        btnSignin = findViewById(R.id.btnlgn);
        signup = findViewById(R.id.signup);
        loading= new ProgressDialog(this);

//        txtUsername.setText("SI3907@telkomuniversity.ac.id");
//        txtPassword.setText("si3907");

        txtUsername.setText("admin@admin.com");
        txtPassword.setText("admin123");

        mAuth = FirebaseAuth.getInstance();

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        }

    public void login(){
        final String user = txtUsername.getText().toString();
        final String pin = txtPassword.getText().toString();
        Log.d("CREDENTIAL::LOGIN","u:"+user+", p:"+pin);
        if(TextUtils.isEmpty(user)){txtUsername.setError("Required"); return;}
        if(TextUtils.isEmpty(pin)){txtPassword.setError("Required"); return;}

        loading.setMessage("Checking User ...");
        loading.show();
        mAuth.signInWithEmailAndPassword(user, pin)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(user.equals("admin@admin.com")){
                            task.isSuccessful();
                            loading.dismiss();
                            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(LoginActivity.this,MainAdmin.class));
                            Log.d("Account", "admin");
                        } else if(user == null){
                            loading.dismiss();
                            Toast.makeText(LoginActivity.this, "Wrong Username/Password", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            task.isSuccessful();
                            loading.dismiss();
                            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            Log.d("Account", "user");
                        }
                    }
                });
    }

    public void click(View view) {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }
}