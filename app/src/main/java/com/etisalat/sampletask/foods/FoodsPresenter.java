package com.etisalat.sampletask.foods;

import com.etisalat.sampletask.model.Food;

import java.util.List;

/**
 * {@link FoodsContract.Presenter} subclass
 */
public class FoodsPresenter extends FoodsContract.Presenter {

    FoodsPresenter(FoodsContract.View listener) {
        super(listener);
        controller = new FoodsController(this);
    }


    /**
     * callback called from view with user action open app or refresh list
     * presenter call controller to get foods list from webservice
     */
    @Override
    public void getFoods() {
        listener.showProgress();
        controller.getFoods();
    }


    /**
     * callback called from controller when data is coming from server
     * presenter tells view to update view with data
     */
    @Override
    public void onSuccess(List<Food> foods, String updateTime) {
        listener.hideProgress();
        listener.showFoods(foods);
        listener.updateTime(updateTime);
    }

    /**
     * callback called from controller when error occurred
     * presenter tells view to show error message
     */
    @Override
    public void onError(String errMsg) {
        listener.hideProgress();
        listener.showErrorMessage(errMsg);
    }
}
