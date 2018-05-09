package com.example.android.tubes_android;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter Photo dignakan untuk keperluan RecyclerView, mengubah Data menjadi Item Komponen View
 * Created by ASUS on 31/03/2018.
 */

public class ListKelasAdapter extends RecyclerView.Adapter<ListKelasAdapter.PhotoViewHolder>{

    private List<ListKelasModel> list;

    //Mengisi Daftar data saat Class dipanggil
    public ListKelasAdapter(List<ListKelasModel> list) {
        this.list = list;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, int position) {
        final ListKelasModel item = list.get(position);
        holder.bindTo(item);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.toDetail(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder{
        private TextView classcode, classname, time, day, status;
        private Context context;
        private CardView cv;

        /*
        * View Holder Constructor
        * */
        public PhotoViewHolder(View v) {
            super(v);

            context=v.getContext();
            classcode= v.findViewById(R.id.cvClasscode);
            classname= v.findViewById(R.id.cvClassname);
            time= v.findViewById(R.id.cvTime);
            day= v.findViewById(R.id.cvDay);
            status = v.findViewById(R.id.cvStatus);
            cv = v.findViewById(R.id.cv);
        }

        /*
        * ViewHolder Behavior
        * untuk melakukan set text pada Komponen View
        * */
        public void bindTo(ListKelasModel model){
            ListKelasModel curr = model;
            classcode.setText(curr.getClasscode());
            classname.setText(curr.getClassname());
            time.setText(curr.getTime());
            day.setText(curr.getDay());
            status.setText(curr.getStatus());

            if(status.getText().length() == 4) {
                cv.setCardBackgroundColor(Color.parseColor( "#AA5585"));
                status.setTextColor(Color.parseColor("#FFFFFF"));
                status.setBackgroundColor(Color.parseColor("#144D53"));
            } else if(status.getText().length() == 6){
                cv.setCardBackgroundColor(Color.parseColor("#1C1124"));
                status.setTextColor(Color.parseColor("#959595"));
            } else {
                cv.setCardBackgroundColor(Color.parseColor("#448EF6"));
                status.setBackgroundColor(Color.parseColor("#75C2F6"));
            }

            /*
            SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");
            Date date = new Date();
            String dayName = sdf_.format(date);
            if(!lblHari.getText().equals(dayName)){
                cv.removeAllViews();
                cv.setVisibility(View.GONE);
                int pos;
                pos = (int) itemView.getTag();
            } */
        }
        
        /*
        * Method yang digunakan untuk menuju intent detail sesuai item yang dipilih
        * */
        public void toDetail(ListKelasModel model){
            Intent i = new Intent(context.getApplicationContext(),FormBooking.class);
            i.putExtra(ViewActivity.EXTRA_ITEM, model);
            context.startActivity(i);
        }
    }
}
