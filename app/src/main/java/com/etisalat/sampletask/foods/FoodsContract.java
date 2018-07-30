package com.etisalat.sampletask.foods;

import com.etisalat.sampletask.bases.BaseController;
import com.etisalat.sampletask.bases.BaseControllerListener;
import com.etisalat.sampletask.bases.BasePresenter;
import com.etisalat.sampletask.bases.BasePresenterListener;
import com.etisalat.sampletask.model.Food;

import java.util.List;

public interface FoodsContract {

    interface View extends BasePresenterListener {
        void showFoods(List<Food> foods);
        void updateTime(String updateTime);
    }

    abstract class Presenter extends BasePresenter<Controller, View> implements ControllerListener{

        public Presenter(View listener) {
            super(listener);
        }

        abstract void getFoods();
    }

    interface ControllerListener extends BaseControllerListener {
        void onSuccess(List<Food> foods, String updateTime);

        void onError(String errMsg);
    }

    abstract class Controller extends BaseController<Presenter> {

        public Controller(Presenter listener) {
            super(listener);
        }

        abstract void getFoods();
    }
}
