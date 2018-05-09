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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tubes_android.ListKelasModel;
import com.example.android.tubes_android.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormActivity extends AppCompatActivity {

    private EditText time1, time2;
    private TextView RC, day;
    private String status, rName, time;
    private Button btnBook;
    private Spinner RN;
    private RadioButton rbB, rbO;

    private FirebaseAuth mAuth;

    private DatabaseReference ref;

    private String[] room = {
            "KU2.01.01", "KU2.01.02", "KU2.01.03", "KU2.01.04", "KU2.01.05", "KU2.01.06", "KU2.01.07", "KU2.01.08", "KU2.01.09",
            "KU2.02.01", "KU2.02.02", "KU2.02.03", "KU2.02.04", "KU2.02.05", "KU2.02.06", "KU2.02.07", "KU2.02.08", "KU2.02.09",
            "KU2.03.01", "KU2.03.02", "KU2.03.03", "KU2.03.04", "KU2.03.05", "KU2.03.06", "KU2.03.07", "KU2.03.08", "KU2.03.09"
    };

    private String[] cRoom = {
            "B101", "B102", "B103", "B104", "B105", "B106", "B107", "B108", "B109",
            "B201", "B202", "B203", "B204", "B205", "B206", "B207", "B208", "B209",
            "B101", "B302", "B303", "B304", "B305", "B306", "B307", "B308", "B309"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Form for Create");
        setContentView(R.layout.activity_form);

        mAuth=FirebaseAuth.getInstance();

        //Inisialisasi Komponen
        RC = findViewById(R.id.lblRoomCode);
        RN = findViewById(R.id.lblRoomName);
        time1 = findViewById(R.id.startTime);
        time2 = findViewById(R.id.finishTime);
        day = findViewById(R.id.lblDay);
        rbB = findViewById(R.id.rbBooked);
        rbO = findViewById(R.id.rbOpened);
//        room = findViewById(R.id.rbg);
        btnBook = findViewById(R.id.book);


        Intent ini = getIntent();
        String hari = ini.getStringExtra("HARI");
        day.setText(hari);

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
//                mengeset kode ruangan yang sesuai dengan nama ruangan ke textview kode ruangan
                RC.setText(cRoom[i]);
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

        time = time1 +" - "+ time2;

        String key = ref.push().getKey();
        ref.child(key).setValue(new ListKelasModel(
                key,
                RC.getText().toString(),
                rName,
                time,
                day.getText().toString(),
                status

        ));
//        Log.d("RB",status);
        Toast.makeText(FormActivity.this, "Uploaded!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(FormActivity.this,MainAdmin.class));
        Log.d("upload", "sukses");
    }
}
