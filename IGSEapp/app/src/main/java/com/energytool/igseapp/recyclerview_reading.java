package com.energytool.igseapp;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.widget.Adapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import java.util.ArrayList;

public class recyclerview_reading extends RecyclerView.Adapter<recyclerview_reading.readingviewholder> {
    Context cont;
    ArrayList<dSubmitreading> arrayList;

    public recyclerview_reading(Context cont, ArrayList<dSubmitreading> arrayList) {
        this.cont = cont;
       this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public readingviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view=LayoutInflater.from(cont).inflate(R.layout.item,parent,false);
    return new readingviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull readingviewholder holder, int position) {
        dSubmitreading Dsubmitreading=arrayList.get(position);
        holder.NightReadings.setText(Dsubmitreading.getNightreadings());
        holder.GasReadings.setText(Dsubmitreading.getGasreadings());
        holder.DayReadings.setText(Dsubmitreading.getDayreadings());
        holder.Allreadings.setText(Dsubmitreading.getDate());
        holder.Customeremailid.setText(Dsubmitreading.getCustomerid());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class readingviewholder extends RecyclerView.ViewHolder
    {
         TextView Allreadings;
         TextView DayReadings;
         TextView NightReadings;
         TextView GasReadings;
         TextView Customeremailid;

        public readingviewholder(@NonNull View itemView) {
            super(itemView);
            DayReadings=itemView.findViewById(R.id.day_reading1);
            Allreadings=itemView.findViewById(R.id.allreading);
            GasReadings=itemView.findViewById(R.id.gas_reading1);
            NightReadings=itemView.findViewById(R.id.nigt_reading1);
            Customeremailid=itemView.findViewById(R.id.date);


        }
    }
}












  /*  public Class readingview extends RecyclerView.ViewHolder
   {

  /*    private String k;
public  readingview(@NonNull @org.jetbrains.annotations.NotNull View itemview)
{
    super(itemview)
   super(LayoutInflater.from(cont).inflate(R.layout.meter_reading,parent,false));

   Allreadings= (TextView)itemView.fi
      }

   }
}
*/