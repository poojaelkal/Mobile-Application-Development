package com.example.midterm;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SortRecyclerViewAdapter extends RecyclerView.Adapter<SortRecyclerViewAdapter.AppViewHolder> {

    ArrayList<String> attributes;
    public static String TAG="demo";

    public SortRecyclerViewAdapter(ArrayList<String> data, SortByAttributesInterface mListenerSort){
        this.attributes = new ArrayList<String>();
        this.attributes = data;
        this.mListenerSort = mListenerSort;
    }
    SortByAttributesInterface mListenerSort;

    public interface SortByAttributesInterface{
        void sortByAttributeDesc(String attribute);
        void sortByAttributeAsc(String attribute);
    }
    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sort_row_layout, parent, false);
        SortRecyclerViewAdapter.AppViewHolder userViewHolder = new SortRecyclerViewAdapter.AppViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        holder.sortAttributes.setText(attributes.get(position));
        holder.imageViewAsc.setImageResource(R.drawable.ic_sort_ascending);
        holder.imageViewDesc.setImageResource(R.drawable.ic_sort_descending);
        final String attributeClicked = attributes.get(position);
        holder.imageViewAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListenerSort.sortByAttributeAsc(attributeClicked);
            }
        });
        holder.imageViewDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListenerSort.sortByAttributeDesc(attributeClicked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.attributes.size();
    }

    public static class AppViewHolder extends RecyclerView.ViewHolder {

        TextView sortAttributes;
        ImageView imageViewAsc;
        ImageView imageViewDesc;


        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            sortAttributes = itemView.findViewById(R.id.sortAttribute);
            imageViewAsc = itemView.findViewById(R.id.imageViewAsc);
            imageViewDesc = itemView.findViewById(R.id.imageViewDesc);


        }
    }

}
