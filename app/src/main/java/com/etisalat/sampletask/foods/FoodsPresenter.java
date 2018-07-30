package com.etisalat.sampletask.foods;

import com.etisalat.sampletask.model.Food;

import java.util.List;

public class FoodsPresenter extends FoodsContract.Presenter {

    public FoodsPresenter(FoodsContract.View listener) {
        super(listener);
        controller = new FoodsController(this);
    }

    @Override
    public void getFoods() {
        listener.showProgress();
        controller.getFoods();
    }

    @Override
    public void onSuccess(List<Food> foods, String updateTime) {
        listener.hideProgress();
        listener.showFoods(foods);
        listener.updateTime(updateTime);
    }

    @Override
    public void onError(String errMsg) {
        listener.hideProgress();
        listener.showErrorMessage(errMsg);
    }
}
