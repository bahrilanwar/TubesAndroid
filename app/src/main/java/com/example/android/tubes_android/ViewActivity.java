package com.example.android.tubes_android;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    public static String EXTRA_ITEM = "EXTRA_VIEW_ITEM";

    //Attibut Komponen View
    private TextView vRoomcode, vRoomname, vTime, vDay, vStatus, vNeeds;
    private Button btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Detail");
        setContentView(R.layout.activity_view);

        //Inisialisasi Komponen View
        vRoomcode = findViewById(R.id.viewRoomcode);
        vRoomname = findViewById(R.id.viewRoomname);
        vTime = findViewById(R.id.viewTime);
        vDay = findViewById(R.id.viewDay);
        vStatus = findViewById(R.id.viewStatus);
        vNeeds = findViewById(R.id.viewNeeds);
        btnBook = findViewById(R.id.book);

        //Pengecekan Item yang dikirimkan intent Lain
        Intent ini = getIntent();
        if (ini.getParcelableExtra(EXTRA_ITEM) != null) {
            final ListKelasModel data = ini.getParcelableExtra(EXTRA_ITEM);;
            vRoomcode.setText(data.getClasscode());
            vRoomname.setText(data.getClassname());
            vTime.setText(data.getTime());
            vDay.setText(data.getDay());
            vStatus.setText(data.getStatus());

            String a = "Booked";

            if (vNeeds.getText().toString().length() > 0) {
                btnBook.setBackgroundColor(Color.parseColor("#555555"));
                btnBook.setText(a);
            } else {
                btnBook.setBackgroundColor(Color.parseColor("#A9EBB0"));
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