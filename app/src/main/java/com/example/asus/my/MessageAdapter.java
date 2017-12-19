package com.example.asus.my;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ReplicaAHolder>{

    public static final String MY_TAG = "my_logs";

    Context context;

    List<Message> replicasA;
    ArrayList<Boolean> isUserA;

    MessageAdapter(List<Message> replicasA, ArrayList<Boolean> isUserA, Context context){
        this.replicasA = replicasA;
        this.isUserA = isUserA;
        this.context = context;
    }



    @Override
    public ReplicaAHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.replica_a, parent, false);
        return new ReplicaAHolder(v);
    }

    @Override
    public void onBindViewHolder(ReplicaAHolder holder, int position) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        if(isUserA.get(position)){
            params.setMarginEnd(40);
            params.weight = 1.0f;
            params.gravity = Gravity.START;
            holder.cv.setLayoutParams(params);

//            int color = holder.cv.getContext().getColor(holder.cv.getContext() ,R.color.colorB);

            int color = holder.cv.getResources().getColor(R.color.colorB);

            holder.cv.setCardBackgroundColor(color);
        }else {
            params.setMarginStart(40);
            params.weight = 1.0f;
            params.gravity = Gravity.END;
            holder.cv.setLayoutParams(params);

//            int color = holder.cv.getContext().getColor(R.color.colorA);

            int color = holder.cv.getResources().getColor(R.color.colorA);
            holder.cv.setCardBackgroundColor(color);

        }
        holder.replica.setText(replicasA.get(position).getMessage());

        holder.setItemLongClickListener(new ItemLongClickListener() {
            @Override
            public void onClick(View view, final int position) {


                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage("Видалити?")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        replicasA.remove(position);
                        isUserA.remove(position);
                        notifyDataSetChanged();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }

        });
    }

    @Override
    public int getItemCount() {
        return replicasA.size();
    }


    public static class ReplicaAHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        ItemLongClickListener itemLongClickListener;

        CardView cv;
        TextView replica;

        ReplicaAHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv_a);
            replica = (TextView)itemView.findViewById(R.id.tv_replica_a);
            itemView.setOnLongClickListener(this);

        }

        public void setItemLongClickListener(ItemLongClickListener itemLongClickListener){
            this.itemLongClickListener = itemLongClickListener;
        }

        @Override
        public boolean onLongClick(View view) {
            itemLongClickListener.onClick(view, getAdapterPosition());

            return false;
        }
    }

}
