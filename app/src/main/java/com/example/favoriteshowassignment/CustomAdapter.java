package com.example.favoriteshowassignment;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> implements Filterable {
    private ArrayList<DataModel> dataset;
    private ArrayList<DataModel> datasetFull;

    public CustomAdapter(ArrayList<DataModel> dataSet) {
        this.dataset = dataSet;
        this.datasetFull = new ArrayList<>(dataSet);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView TextViewInfo;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            TextViewInfo = itemView.findViewById(R.id.textViewInfo);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView textViewName = holder.textViewName;
        TextView textViewInfo = holder.TextViewInfo;
        ImageView imageView = holder.imageView;

        DataModel currentItem = dataset.get(position);

        textViewName.setText(currentItem.getName());
        textViewInfo.setText(currentItem.getInfo());
        imageView.setImageResource(currentItem.getImage());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailedActivity.class);
            intent.putExtra("name", currentItem.getName());
            intent.putExtra("image", currentItem.getImage());
            intent.putExtra("detailedInfo", MyData.detailedInfoArray[currentItem.getId_()]);
            v.getContext().startActivity(intent);

            if (v.getContext() instanceof MainActivity) {
                MainActivity mainActivity = (MainActivity) v.getContext();
                if (mainActivity.searchView != null) {
                    mainActivity.searchView.clearFocus();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<DataModel> filteredList = new ArrayList<>();

                if (charSequence == null || charSequence.length() == 0) {
                    filteredList.addAll(datasetFull);
                } else {
                    String filterPattern = charSequence.toString().toLowerCase().trim();

                    for (DataModel item : datasetFull) {
                        if (item.getName().toLowerCase().contains(filterPattern) ||
                                item.getInfo().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataset.clear();
                dataset.addAll((List<DataModel>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }
}



