package com.example.android.tubes_android.admin;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.tubes_android.ListKelasModel;
import com.example.android.tubes_android.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormActivity extends AppCompatActivity {

    private EditText RC, time, day;
    private String status, rName;
    private Button btnBook;
    private Spinner RN;
    private RadioButton rbB, rbO;

    private FirebaseAuth mAuth;

    private DatabaseReference ref;

    private String[] room = {
            "KU2.01.01", "KU2.01.02", "KU2.01.03", "KU2.01.04", "KU2.01.05", "KU2.01.06", "KU2.01.07", "KU2.01.08", "KU2.01.09"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Form for Book");
        setContentView(R.layout.activity_form);

        mAuth=FirebaseAuth.getInstance();

        //Inisialisasi Komponen
//        RC = findViewById(R.id.lblRoomCode);
        RN = findViewById(R.id.lblRoomName);
        time = findViewById(R.id.startTime);
        day = findViewById(R.id.lblDay);
        rbB = findViewById(R.id.rbBooked);
        rbO = findViewById(R.id.rbOpened);
//        room = findViewById(R.id.rbg);
        btnBook = findViewById(R.id.book);



        spinnerListener();

        ref = FirebaseDatabase.getInstance().getReference("classlist");

        //lblUser.setText(getIntent().getStringExtra("data1"));

    }

    public void spinnerListener() {
        // inisialiasi Array Adapter dengan memasukkan string array di atas
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, room);

        // mengeset Array Adapter tersebut ke Spinner
        RN.setAdapter(adapter);

        // mengeset listener untuk mengetahui saat item dipilih
        RN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // mengambil value Spinner yang dipilih (diambil dari adapter)
                rName = adapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void input(View view) {
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbBooked:
                    status = getString(R.string.booked);
                break;
            case R.id.rbOpened:
                    status = getString(R.string.opened);
                break;
        }

        String key = ref.push().getKey();
        ref.child(key).setValue(new ListKelasModel(
                key,
                RC.getText().toString(),
                rName,
                time.getText().toString(),
                day.getText().toString(),
                status

        ));
        Log.d("RB",status);
        Toast.makeText(FormActivity.this, "Uploaded!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(FormActivity.this,MainAdmin.class));
        Log.d("upload", "sukses");
    }
}
