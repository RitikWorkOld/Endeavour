package com.example.endeavour;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    public List<ExampleItem> mExampleList;

    public ExampleAdapter(List<ExampleItem> exampleList) {
        this.mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {

        ExampleItem currentItem = mExampleList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
        holder.mTextView3.setText(currentItem.getText3());

        boolean isExpanded = mExampleList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public LinearLayout expandableLayout;
        public RelativeLayout relativeLayout;

        public ExampleViewHolder(View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image_team);
            mTextView1 = itemView.findViewById(R.id.tv_team_name);
            mTextView2 = itemView.findViewById(R.id.tv_team_role);
            mTextView3 = itemView.findViewById(R.id.tv_team_desig);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            relativeLayout = itemView.findViewById(R.id.layout_team_card);

            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExampleItem exampleItem = mExampleList.get(getAdapterPosition());
                    exampleItem.setExpanded(!exampleItem.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}