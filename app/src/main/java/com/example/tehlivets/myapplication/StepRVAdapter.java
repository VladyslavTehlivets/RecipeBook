package com.example.tehlivets.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vladyslav on 08.06.2017.
 */

public class StepRVAdapter extends RecyclerView.Adapter<StepRVAdapter.StepViewHolder> {
    private Context context;
    private List<Step> steps;

    public StepRVAdapter(Context context, List<Step> l_steps) {
        this.context = context;
        this.steps = l_steps;
    }

    public static class StepViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView stepDescription;
        ImageButton stepTimer;
        ImageView stepPhoto;

        public StepViewHolder(View itemView) {
            super(itemView);
            this.cv = (CardView) itemView.findViewById(R.id.stepCv);
            stepDescription = (TextView) itemView.findViewById(R.id.stepViewDescription);
            stepTimer = (ImageButton) itemView.findViewById(R.id.timerStepView);
            stepPhoto = (ImageView) itemView.findViewById(R.id.stepViewPhoto);
        }
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.step_card, viewGroup, false);
        StepViewHolder svh = new StepViewHolder(view);
        return svh;
    }

    @Override
    public void onBindViewHolder(StepViewHolder stepViewHolder, int position) {
        stepViewHolder.stepDescription.setText(steps.get(position).getStepNumber() + ". " + steps.get(position).getDescription());
        stepViewHolder.stepTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Cook.class);
                context.startActivity(intent);
            }
        });
        if (steps.get(position).isBitmap()) {
            Bitmap sPhoto = steps.get(position).getBitmap();
            stepViewHolder.stepPhoto.setImageBitmap(sPhoto);
        }
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
