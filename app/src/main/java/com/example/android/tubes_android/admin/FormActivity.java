package com.example.android.tubes_android.admin;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.android.tubes_android.ListKelasModel;
import com.example.android.tubes_android.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormActivity extends AppCompatActivity {

    private EditText RC, RN, time, day;
    private RadioButton status;
    private Button btnBook;
    private RadioGroup rg;
//    private RadioButton rbB, rbO;

    private FirebaseAuth mAuth;

    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Form for Book");
        setContentView(R.layout.activity_form);
        onRadioButtonClicked();

        mAuth=FirebaseAuth.getInstance();

        //Inisialisasi Komponen
//        RC = findViewById(R.id.lblRoomCode);
        RN = findViewById(R.id.lblRoomName);
        time = findViewById(R.id.startTime);
        day = findViewById(R.id.lblDay);
        rg = findViewById(R.id.rbg);
//        rbB = findViewById(R.id.rbBooked);
//        rbO = findViewById(R.id.rbOpened);
        btnBook = findViewById(R.id.book);

        ref = FirebaseDatabase.getInstance().getReference("classlist");

        //lblUser.setText(getIntent().getStringExtra("data1"));

    }


    public void onRadioButtonClicked() {
        btnBook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = rg.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                status = (RadioButton) findViewById(selectedId);

            }

        });
    }

    public void input(View view) {
        String key = ref.push().getKey();
        ref.child(key).setValue(new ListKelasModel(
                key,
                RC.getText().toString(),
                RN.getText().toString(),
                time.getText().toString(),
                day.getText().toString(),
                status.getText().toString()

        ));
        Log.d("RB",status.getText().toString());
        Toast.makeText(FormActivity.this, "Uploaded!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(FormActivity.this,MainAdmin.class));
        Log.d("upload", "sukses");
    }
}
