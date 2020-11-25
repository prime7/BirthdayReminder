package com.csis_3175_070.birthdayreminderapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList  bdayId,bdayFname, bdayLname, bdayDate;

    CustomAdapter(Activity activity, Context context,ArrayList bdayid, ArrayList bdayFname, ArrayList bdayLname,
                  ArrayList bdayDate){
        this.bdayId=bdayid;
        this.activity = activity;
        this.context = context;
        this.bdayFname = bdayFname;
        this.bdayLname = bdayLname;
        this.bdayDate = bdayDate;
        //
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.bday_id_txt.setText(String.valueOf(bdayId.get(position)));
        holder.bday_fname_txt.setText(String.valueOf(bdayFname.get(position)));
        holder.bday_lname_txt.setText(String.valueOf(bdayLname.get(position)));
        holder.bday_date_txt.setText(String.valueOf(bdayDate.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(bdayId.get(position)));
                intent.putExtra("firstname", String.valueOf(bdayFname.get(position)));
                intent.putExtra("lastname", String.valueOf(bdayLname.get(position)));
                intent.putExtra("date", String.valueOf(bdayDate.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return bdayFname.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bday_id_txt, bday_fname_txt, bday_lname_txt, bday_date_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bday_id_txt = itemView.findViewById(R.id.bday_id_txt);
            bday_fname_txt = itemView.findViewById(R.id.bday_fname_txt);
            bday_lname_txt = itemView.findViewById(R.id.bday_lname_txt);
            bday_date_txt = itemView.findViewById(R.id.bday_date_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
