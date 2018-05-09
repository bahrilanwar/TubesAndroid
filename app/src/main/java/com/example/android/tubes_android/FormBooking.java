package com.example.android.tubes_android;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class FormBooking extends AppCompatActivity {

    public static String EXTRA_ITEM = "EXTRA_VIEW_ITEM";

    //Attibut Komponen View
    private TextView xUser, xRoomcode, xRoomname, xTime, xDay, xStatus;
    private EditText xIdentity, xNeeds, xPhone;
    private Button btnBook;

    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Form Booking");
        setContentView(R.layout.activity_form_booking);
        //Inisialisasi Komponen View
        xUser = findViewById(R.id.viewUser1);
        xRoomcode = findViewById(R.id.viewRoomcode1);
        xRoomname = findViewById(R.id.viewRoomname1);
        xTime = findViewById(R.id.viewTime1);
        xDay = findViewById(R.id.viewDay1);
        xStatus = findViewById(R.id.viewStatus1);

        xNeeds = findViewById(R.id.viewNeeds1);
        xIdentity = findViewById(R.id.iCode);
        xPhone = findViewById(R.id.pNum);

        btnBook = findViewById(R.id.bookk);

        //Pengecekan Item yang dikirimkan intent Lain
        Intent sini = getIntent();
        String a = sini.getStringExtra("data1");
        String b = sini.getStringExtra("data2");
        String c = sini.getStringExtra("data3");
        String d = sini.getStringExtra("data4");
        String e = sini.getStringExtra("data5");
        String f = sini.getStringExtra("data6");

        //Set Intent ke Textview
        xUser.setText(a);
        xRoomcode.setText(b);
        xRoomname.setText(c);
        xTime.setText(d);
        xDay.setText(e);
        xStatus.setText(f);


            /* btnBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String a = "Booking Now!";
                    if (btnBook.getText().equals(a)) {
                        Toast.makeText(ViewActivity.this, "Please, fill the form", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ViewActivity.this, FormActivity.class);
                        intent.putExtra("data1", "goblog");
                        /*intent.putExtra("data2", lblCaption.getText().toString());
                        intent.putExtra("data3", lblRoom.getText().toString());
                        intent.putExtra("data4", lblJam.getText().toString());
                        intent.putExtra("data5", lblDay.getText().toString());
                        intent.putExtra("data6", lblStatus.getText().toString());
                        startActivity(intent);
                    } else {
                        AlertDialog alertDialog = new AlertDialog.Builder(ViewActivity.this).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("Sorry, this class was used/booked!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                }
            }); */

    }

    public void books(View view) {

        xStatus.setText("Booked");

        String key = ref.push().getKey();
        ref.child(key).setValue(new ListKelasModel(
                key,
                xRoomcode.getText().toString(),
                xRoomname.getText().toString(),
                xTime.getText().toString(),
                xDay.getText().toString(),
                xStatus.getText().toString()

        ));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
