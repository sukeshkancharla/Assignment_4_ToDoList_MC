package com.example.user.todolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by user on 06-Nov-16.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>{
    String[] countryNames,capitalNames;
    Context cntxt;
    public RecyclerAdapter(Context cntxt){
        this.countryNames=countryNames;
        this.capitalNames=capitalNames;
        this.cntxt=cntxt;
    }
    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        RecyclerHolder recyclerHolder=new RecyclerHolder(view,cntxt);
        return recyclerHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        holder.txt_country.setText(countryNames[position]);
        holder.txt_capital.setText(capitalNames[position]);
    }

    @Override
    public int getItemCount() {
        return countryNames.length;
    }
    public static class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txt_country,txt_capital;
        Context ctx;
        public RecyclerHolder(View itemView,Context ctx) {
            super(itemView);
            this.ctx=ctx;
            itemView.setOnClickListener(this);
            txt_country=(TextView)itemView.findViewById(R.id.text_title);
            txt_capital=(TextView)itemView.findViewById(R.id.text_date);
        }

        @Override
        public void onClick(View view) {
            TextView text=(TextView) view.findViewById(R.id.text_title);
            Intent intent=new Intent(this.ctx,SecondActivty.class);
            intent.putExtra("textpassed",text.getText().toString());
            Toast.makeText(view.getContext(),text.getText().toString() + " clicked!", Toast.LENGTH_SHORT).show();
            this.ctx.startActivity(intent);
        }
    }
}
