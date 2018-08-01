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

/**
 * {@link FoodsContract.Controller} subclass
 * responsible for providing {@link FoodsContract.Presenter} with data from webservice
 */
public class FoodsController extends FoodsContract.Controller {

    private static final String TAG = FoodsController.class.getSimpleName();

    public FoodsController(FoodsContract.Presenter listener) {
        super(listener);
    }

    /**
     * getting list of {@link Food} from webservice when response is success
     * or error message if it failed and give it back to
     * {@link FoodsContract.Presenter} listener
     */
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

    /**
     * sort list of {@link Food} by its name attribute alphabetically
     *
     * @param foods list of {@link Food}
     * @return foods sorted alphabetically
     */
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
