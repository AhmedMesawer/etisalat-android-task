package com.etisalat.sampletask.foods;

import android.os.Bundle;

import com.etisalat.sampletask.R;
import com.etisalat.sampletask.bases.BaseActivity;

public class FoodsActivity extends BaseActivity<FoodsContract.Presenter> {

    private FoodsFragment foodsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);
        foodsFragment = new FoodsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, foodsFragment).commit();
    }

    @Override
    protected FoodsPresenter setupPresenter() {
        return null;
    }
}
