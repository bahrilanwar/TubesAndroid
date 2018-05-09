package com.example.android.tubes_android;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tubes_android.admin.MainAdmin;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormBooking extends AppCompatActivity {

    public static String EXTRA_ITEM = "EXTRA_VIEW_ITEM";

    //Attibut Komponen View
    private TextView xUser, xRoomcode, xRoomname, xTime, xDay, xStatus;
    private EditText xIdentity, xNeeds, xPhone;
    private Button btnBook;

    private FirebaseAuth mAuth;

    private DatabaseReference ref;
//    private ListKelasModel data;

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

        mAuth=FirebaseAuth.getInstance();

        ref = FirebaseDatabase.getInstance().getReference("classlist");

        final String cc = mAuth.getCurrentUser().getEmail().toString();
        final String dd = cc.substring(0,cc.lastIndexOf("@"));

        Intent ini = getIntent();
        if (ini.getParcelableExtra(EXTRA_ITEM) != null) {
            final ListKelasModel data = ini.getParcelableExtra(EXTRA_ITEM);
            xUser.setText(data.getUser());
            xRoomcode.setText(data.getClasscode());
            xRoomname.setText(data.getClassname());
            xTime.setText(data.getTime());
            xDay.setText(data.getDay());
            xStatus.setText(data.getStatus());
            xNeeds.setText(data.getNeeds());
            xIdentity.setText(data.getIdnumber());
            xPhone.setText(data.getPn());

            final String z = "booked";

            if(xNeeds.getText().toString().length() > 0){
                if(mAuth.getCurrentUser().getEmail().equals("admin@admin.com")){
                    btnBook.setText("Unbook");
                    btnBook.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            String unbook = "Open";
                            String unuser = "";
                            String unneeds = "";
                            String unidentity = "";
                            String unphone = "";
                            String key = data.getId();
                            ref.child(key).setValue(new ListKelasModel(
                                    key,
                                    unuser.toString(),
                                    xRoomcode.getText().toString(),
                                    xRoomname.getText().toString(),
                                    xTime.getText().toString(),
                                    xDay.getText().toString(),
                                    unbook.toString(),
                                    unneeds.toString(),
                                    unidentity.toString(),
                                    unphone.toString()));
                            Toast.makeText(FormBooking.this, "Unbook", Toast.LENGTH_SHORT).show();
                            if(mAuth.getCurrentUser().getEmail().equals("admin@admin.com")) {
                                startActivity(new Intent(FormBooking.this, MainAdmin.class));
                                Log.d("upload admin", "sukses");
                            } else {
                                startActivity(new Intent(FormBooking.this, MainActivity.class));
                                Log.d("upload", "sukses");
                            }
                        }

                    });

                } else {
                    btnBook.setBackgroundColor(Color.parseColor("#555555"));
                    btnBook.setText(z);
                    btnBook.setEnabled(false);
                    AlertDialog alertDialog = new AlertDialog.Builder(FormBooking.this).create();
                    alertDialog.setTitle("Apologize");
                    alertDialog.setMessage("Sorry, this class was used/booked!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            } else if(xNeeds == null) {
                btnBook.setError("Needs required for booking class!");
            } else {
                btnBook.setBackgroundColor(Color.parseColor("#A9EBB0"));
                btnBook.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        String key = data.getId();
                        ref.child(key).setValue(new ListKelasModel(
                                key,
                                cc.toString(),
                                xRoomcode.getText().toString(),
                                xRoomname.getText().toString(),
                                xTime.getText().toString(),
                                xDay.getText().toString(),
                                z.toString(),
                                xNeeds.getText().toString(),
                                xIdentity.getText().toString(),
                                xPhone.getText().toString()));
                        Toast.makeText(FormBooking.this, "Uploaded!", Toast.LENGTH_SHORT).show();
                        if(mAuth.getCurrentUser().getEmail().equals("admin@admin.com")) {
                            startActivity(new Intent(FormBooking.this, MainAdmin.class));
                            Log.d("upload admin", "sukses");
                        } else {
                            startActivity(new Intent(FormBooking.this, MainActivity.class));
                            Log.d("upload", "sukses");
                        }

                    }

                });
            }
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

