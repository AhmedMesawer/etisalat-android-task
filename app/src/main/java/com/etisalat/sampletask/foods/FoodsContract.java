package com.etisalat.sampletask.foods;

import com.etisalat.sampletask.bases.BaseController;
import com.etisalat.sampletask.bases.BaseControllerListener;
import com.etisalat.sampletask.bases.BasePresenter;
import com.etisalat.sampletask.bases.BasePresenterListener;
import com.etisalat.sampletask.model.Food;

import java.util.List;

/**
 * interface contains foods module interfaces
 */
public interface FoodsContract {

    /**
     * {@link BasePresenterListener} subclass
     */
    interface View extends BasePresenterListener {
        void showFoods(List<Food> foods);

        void updateTime(String updateTime);
    }

    /**
     * {@link BasePresenter} subclass
     */
    abstract class Presenter extends BasePresenter<Controller, View> implements ControllerListener {

        Presenter(View listener) {
            super(listener);
        }

        abstract void getFoods();
    }

    /**
     * {@link BaseControllerListener} subclass
     */
    interface ControllerListener extends BaseControllerListener {
        void onSuccess(List<Food> foods, String updateTime);

        void onError(String errMsg);
    }

    /**
     * {@link BaseController} subclass
     */
    abstract class Controller extends BaseController<Presenter> {

        Controller(Presenter listener) {
            super(listener);
        }

        abstract void getFoods();
    }
}
