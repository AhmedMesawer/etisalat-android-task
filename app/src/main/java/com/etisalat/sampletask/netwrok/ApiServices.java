package com.etisalat.sampletask.netwrok;

import com.etisalat.sampletask.model.FoodMenu;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("pizza/?format=xml")
    Call<FoodMenu> getFoodMenu();
}
