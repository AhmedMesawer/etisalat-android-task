package com.etisalat.sampletask.foods;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.etisalat.sampletask.R;
import com.etisalat.sampletask.model.Food;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * {@link RecyclerView.Adapter} subclass adapts data (list of {@link Food}) to view (item_food.xml)
 * */
public class FoodsAdapter extends RecyclerView.Adapter<FoodsAdapter.ViewHolder> {

    private List<Food> foods;

    FoodsAdapter(List<Food> foods) {
        this.foods = foods;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = foods.get(position);
        holder.foodNameTextView.setText(food.getName());
        holder.foodCostTextView.setText(String.valueOf(food.getCost()));
        holder.foodDescriptionTextView.setText(food.getDescription());
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.foodNameTextView)
        TextView foodNameTextView;
        @BindView(R.id.foodDescriptionTextView)
        TextView foodDescriptionTextView;
        @BindView(R.id.foodCostTextView)
        TextView foodCostTextView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
