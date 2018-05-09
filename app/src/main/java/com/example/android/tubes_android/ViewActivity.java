package com.example.android.tubes_android;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    public static String EXTRA_ITEM = "EXTRA_VIEW_ITEM";

    //Attibut Komponen View
    private TextView vUser, vRoomcode, vRoomname, vTime, vDay, vStatus, vNeeds;
    private LinearLayout lNeeds;
    private Button btnBook;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Detail");
        setContentView(R.layout.activity_view);

        //ambil user
        Intent sini = getIntent();
        String useer = sini.getStringExtra("userr");

        //Inisialisasi Komponen View
        vUser = findViewById(R.id.viewUser);
        vRoomcode = findViewById(R.id.viewRoomcode);
        vRoomname = findViewById(R.id.viewRoomname);
        vTime = findViewById(R.id.viewTime);
        vDay = findViewById(R.id.viewDay);
        vStatus = findViewById(R.id.viewStatus);
        vNeeds = findViewById(R.id.viewNeeds);
        lNeeds = findViewById(R.id.lneeds);
        btnBook = findViewById(R.id.book);



        //Pengecekan Item yang dikirimkan intent Lain
        Intent ini = getIntent();
        if (ini.getParcelableExtra(EXTRA_ITEM) != null) {
            final ListKelasModel data = ini.getParcelableExtra(EXTRA_ITEM);
            vUser.setText(useer);
            vRoomcode.setText(data.getClasscode());
            vRoomname.setText(data.getClassname());
            vTime.setText(data.getTime());
            vDay.setText(data.getDay());
            vStatus.setText(data.getStatus());

            String a = "Booked";

            if (vStatus.getText().toString().equals(a)) {
                btnBook.setVisibility(View.INVISIBLE);
            } else {
                btnBook.setVisibility(View.VISIBLE);
                lNeeds.setVisibility(View.INVISIBLE);
            }

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
    }

    public void books(View view) {
        String a = "Booking Now!";
        if (!btnBook.getText().equals(a)) {
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
        }else {
            Intent sana = new Intent(ViewActivity.this, FormBooking.class);
            sana.putExtra("data1", vUser.getText().toString());
            sana.putExtra("data2", vRoomcode.getText().toString());
            sana.putExtra("data3", vRoomname.getText().toString());
            sana.putExtra("data4", vTime.getText().toString());
            sana.putExtra("data5", vDay.getText().toString());
            sana.putExtra("data6", vStatus.getText().toString());
            startActivity(sana);
        }
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