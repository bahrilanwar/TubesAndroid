package com.example.android.tubes_android;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private DatePicker datePicker;
    TextView textViewDay;
    Calendar calendar;
    TextView hari;

    //button untuk menampilkan kalender
    private Button btn;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat dayFormatter;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProgressDialog loading;

    //Attribut untuk FireBase Object
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabase;

    public static List<ListKelasModel> photosList;

    public static String sharedValue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new ListKelasAdapter(photosList, "Sunday", "Monday");

        dateFormatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        dayFormatter = new SimpleDateFormat("EEEE", Locale.US);
        textViewDay = findViewById(R.id.textViewdate);
        hari = findViewById(R.id.hari);

//        btn = findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showDateDialog();
//            }
//        });

        //Inisialisasi Komponen View Loading
        loading=new ProgressDialog(this);
        photosList=new ArrayList<>();

        //Inisialisasi Auth untuk FireBase Sebagai Lib untuk membantu User Login/Register
        mAuth=FirebaseAuth.getInstance();
        //Pengecekan User, jika belum ada diarahkan ke intent SignIn
        if(mAuth.getCurrentUser()==null){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }

        //Inisialisasi Komponen View bagian Tab
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        //Inisialisasi Komponen View
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_list);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_history);

        mFirebaseDatabase=FirebaseDatabase.getInstance();

    }

//
//    private void showDateDialog(){
//
//        /**
//         * Calendar untuk mendapatkan tanggal sekarang
//         */
//        final Calendar newCalendar = Calendar.getInstance();
//
//        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//
//                /**
//                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
//                 */
//
//                /**
//                 * Set Calendar untuk menampung tanggal yang dipilih
//                 */
//                Calendar newDate = Calendar.getInstance();
//                newDate.set(year, monthOfYear, dayOfMonth);
//                Date date = new Date();
//                String a = dateFormatter.format(newDate.getTime());
//                String b = dayFormatter.format(newDate.getTime());
//                /**
//                 * Update TextView dengan tanggal yang kita pilih
//                 */
//                textViewDay.setText("" + ", " + a +"");
//                hari.setText(b);
//                if(hari.getText() != null){
//                    Toast.makeText(MainActivity.this,"Date was changed",Toast.LENGTH_LONG).show();
//                }
//            }
//
//        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//
//        /**
//         * Tampilkan DatePicker dialog
//         */
//        datePickerDialog.show();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        calendar = Calendar.getInstance();
//        //date format is:  "Date-Month-Year Hour:Minutes am/pm"
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy"); //Date and time
//        String currentDate = sdf.format(calendar.getTime());
//
//        //Day of Name in full form like,"Saturday", or if you need the first three characters you have to put "EEE" in the date format and your result will be "Sat".
//        SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");
//        Date date = new Date();
//        String dayName = sdf_.format(date);
//        hari.setText(dayName);
//        textViewDay.setText("" + ", " + currentDate + "");
//    }
    /*
    * Method untuk keperluan Menu
    * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
    * Method untuk keperluan menu
    * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_options_menu, menu);
        if(mAuth.getCurrentUser()!=null){menu.findItem(R.id.action_myuser).setTitle(makeUsername(mAuth.getCurrentUser().getEmail()));}
        return true;
    }

    /*
        * Method untuk keperluan menu
        * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.logout:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("Log Out");
                builder.setMessage("Are you sure you want to log out?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        //noinspection SimplifiableIfStatement

                        mAuth.signOut();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();

                        dialog.dismiss();
                    }

                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // I do not need any action here you might
                        dialog.dismiss();
                    }
                });


                AlertDialog alert = builder.create();
                alert.show();
                break;

            case R.id.about:
                Intent Options = new Intent(this, AboutActivity.class);
                this.startActivity(Options);
                break;
        }
            return super.onOptionsItemSelected(item);
    }





    /*
    * Method untuk mengatur konfigurasi awal tampilan Tab
    * */
    private void setupViewPager(ViewPager pager){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new RecentFragment(), "Class List");
        viewPagerAdapter.addFragment(new MyFragment(), "History");
        pager.setAdapter(viewPagerAdapter);
    }

    /*
    * Class sebagai Adapter ViewPager
    * Berisikan keperluan yang dibutuhkan untuk KOmponen Tab Layout
    * */
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }

    /*
    * Method untuk mengubah Email menjadi DisplayName tanpa @...
    * */
    public String makeUsername(String email){
        return email.substring(0,email.lastIndexOf("@"));
    }

//    public void onBackPressed(){
//        // do something here and don't write super.onBackPressed()
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        builder.setTitle("Exit");
//        builder.setMessage("Are you sure you want to exit?");
//
//        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//
//            public void onClick(DialogInterface dialog, int which) {
//
//                //noinspection SimplifiableIfStatement
//
//                mAuth.signOut();
//                moveTaskToBack(true);
//                finish();
//
//                dialog.dismiss();
//            }
//
//        });
//
//        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // I do not need any action here you might
//                dialog.dismiss();
//            }
//        });
//
//
//        AlertDialog alert = builder.create();
//        alert.show();
//    }

}
