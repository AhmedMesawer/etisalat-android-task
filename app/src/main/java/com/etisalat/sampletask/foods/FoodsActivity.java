package com.etisalat.sampletask.foods;

import android.os.Bundle;

import com.etisalat.sampletask.R;
import com.etisalat.sampletask.bases.BaseActivity;

/**
 * {@link BaseActivity} subclass
 * main activity of the app
 * contains {@link FoodsFragment}
 */
public class FoodsActivity extends BaseActivity<FoodsContract.Presenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);
        FoodsFragment foodsFragment = new FoodsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, foodsFragment).commit();
    }

    @Override
    protected FoodsPresenter setupPresenter() {
        return null;
    }
}
