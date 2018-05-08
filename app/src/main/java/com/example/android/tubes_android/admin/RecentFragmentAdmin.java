package com.example.android.tubes_android.admin;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.android.tubes_android.ListKelasModel;
import com.example.android.tubes_android.ListKelasAdapter;
import com.example.android.tubes_android.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecentFragmentAdmin extends Fragment {

    private TextView textViewDay, hari;
    private Button btn;
    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat dayFormatter;
    private FloatingActionButton fab;

    private RecyclerView recyclerView;
    //private List<PhotoModel> datas;
    private ListKelasAdapter dataAdapter;

    private List<ListKelasModel> listing;

    public RecentFragmentAdmin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loadData();
        return inflater.inflate(R.layout.admin_recent_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=(RecyclerView)view.findViewById(R.id.listRecentRecycler2);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));

        dateFormatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        dayFormatter = new SimpleDateFormat("EEEE", Locale.US);
        textViewDay = view.findViewById(R.id.textViewdate2);
        hari = view.findViewById(R.id.hari2);
        btn = view.findViewById(R.id.btn2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        FloatingActionButton myFab = (FloatingActionButton) view.findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),FormActivity.class));
            }
        });
    }

    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        final Calendar newCalendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Date date = new Date();
                String a = dateFormatter.format(newDate.getTime());
                final String b = dayFormatter.format(newDate.getTime());
                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                textViewDay.setText("" + ", " + a +"");
                hari.setText(b);
                if(hari.getText() != null){
//                    Toast.makeText(getActivity(),"Date was changed",Toast.LENGTH_LONG).show();
                    final ProgressDialog loading=new ProgressDialog(getContext());
                    loading.setMessage("Getting Data ...");
                    loading.setCancelable(false);
                    loading.show();
                    FirebaseDatabase mFirebaseDatabase;
                    DatabaseReference mDatabase;
                    mFirebaseDatabase=FirebaseDatabase.getInstance();
                    mDatabase=mFirebaseDatabase.getReference("classlist");
                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            List<ListKelasModel> list = new ArrayList<>();
                            for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                                ListKelasModel item = postSnapshot.getValue(ListKelasModel.class);
                                if(item.getDay().equals(b)){
                                    list.add(item);
                                }
                            }
                            Log.d("FIREBASE::MDATA","num:"+list.size());

                            ListKelasAdapter dataAdapter = new ListKelasAdapter(list, "Sunday", "Monday");
                            recyclerView.setAdapter(dataAdapter);

                            loading.dismiss();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            loading.dismiss();
                        }
                    });
                }
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }
    /*
    * Method LoadData digunakan untuk mengambil data dari FireBase
    * */
    private void loadData(){
        final ProgressDialog loading=new ProgressDialog(getContext());
        loading.setMessage("Getting Data ...");
        loading.setCancelable(false);
        loading.show();

        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference mDatabase;
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mDatabase=mFirebaseDatabase.getReference("classlist");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<ListKelasModel> list = new ArrayList<>();
                SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");
                Date date = new Date();
                String dayName = sdf_.format(date);
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    ListKelasModel item = postSnapshot.getValue(ListKelasModel.class);
                    if(item.getDay() != null){
                        list.add(item);
                    }
                }
                Log.d("FIREBASE::MDATA","num:"+list.size());

                ListKelasAdapter dataAdapter = new ListKelasAdapter(list, "Sunday", "Monday");
                recyclerView.setAdapter(dataAdapter);

                loading.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                loading.dismiss();
            }
        });
    }
}
