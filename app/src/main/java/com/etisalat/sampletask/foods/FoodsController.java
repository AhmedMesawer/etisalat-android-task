package com.etisalat.sampletask.foods;

import android.support.annotation.NonNull;
import android.util.Log;

import com.etisalat.sampletask.model.Food;
import com.etisalat.sampletask.model.FoodMenu;
import com.etisalat.sampletask.netwrok.ApiClient;
import com.etisalat.sampletask.netwrok.ApiServices;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodsController extends FoodsContract.Controller {

    private static final String TAG = FoodsController.class.getSimpleName();

    public FoodsController(FoodsContract.Presenter listener) {
        super(listener);
    }

    @Override
    void getFoods() {
        ApiClient.getClient().create(ApiServices.class).getFoodMenu()
                .enqueue(new Callback<FoodMenu>() {
                    @Override
                    public void onResponse(@NonNull Call<FoodMenu> call,
                                           @NonNull Response<FoodMenu> response) {
                        if (response.isSuccessful()) {
                            FoodMenu foodMenu = response.body();
                            if (foodMenu != null) {
                                Log.d(TAG, "onResponse: success " + foodMenu.getFoods().size());
                                SimpleDateFormat dateFormat =
                                        new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.ENGLISH);
                                String updateTime = dateFormat.format(response.headers().getDate("Date"));
                                listener.onSuccess(sortingFoods(foodMenu.getFoods()), updateTime);
                            }
                        } else {
                            Log.d(TAG, "onResponse: response is not successful");
                            listener.onError("Data not found");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<FoodMenu> call, @NonNull Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                        listener.onError("Connection error");
                    }
                });
    }

    public List<Food> sortingFoods(List<Food> foods) {
        Collections.sort(foods, new Comparator<Food>() {
            @Override
            public int compare(Food o1, Food o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return foods;
    }
}
