package com.etisalat.sampletask.foods;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.etisalat.sampletask.KotlinActivity;
import com.etisalat.sampletask.R;
import com.etisalat.sampletask.bases.BaseFragment;
import com.etisalat.sampletask.model.Food;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.etisalat.sampletask.utils.Util.isNotNullOrEmpty;

/**
 * A simple {@link (Fragment, BaseFragment)} subclass shows a list of {@link Food}
 */
public class FoodsFragment extends BaseFragment<FoodsContract.Presenter>
        implements FoodsContract.View {

    private static final String TAG = FoodsFragment.class.getSimpleName();
    @BindView(R.id.rv_foods)
    RecyclerView rvFoods;
    Unbinder unbinder;
    @BindView(R.id.foodsLayout)
    LinearLayout foodsLayout;
    @BindView(R.id.updateTimeTextView)
    TextView updateTimeTextView;
    @BindView(R.id.refreshIcon)
    ImageView refreshIcon;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.kotlinIcon)
    ImageView kotlinIcon;
    private FoodsAdapter foodsAdapter;

    public FoodsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_foods, container, false);
        unbinder = ButterKnife.bind(this, view);
        super.view = foodsLayout;
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        }
        setupFoodsRecyclerView();
        presenter.getFoods();
        return view;
    }

    /**
     * setup FoodsRecyclerView
     */
    private void setupFoodsRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvFoods.setLayoutManager(llm);
        foodsAdapter = new FoodsAdapter(new ArrayList<Food>());
        rvFoods.setAdapter(foodsAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * callback called when presenter get data
     * show list of foods
     *
     * @param foods list of {@link Food} from presenter
     */
    @Override
    public void showFoods(List<Food> foods) {
        foodsAdapter.setFoods(foods);
    }

    /**
     * callback with last update time of foods list
     * show update time in toolbar
     *
     * @param updateTime date as a string from presenter
     */
    @Override
    public void updateTime(String updateTime) {
        if (isNotNullOrEmpty(updateTime))
            updateTimeTextView.setText(updateTime);
    }


    /**
     * instantiating presenter object
     *
     * @return {@link FoodsPresenter} object
     */
    @Override
    protected FoodsPresenter setupPresenter() {
        return new FoodsPresenter(this);
    }

    /**
     * click listener of some views
     */
    @OnClick({R.id.refreshIcon, R.id.kotlinIcon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.refreshIcon:
                presenter.getFoods();
                break;
            case R.id.kotlinIcon:
                Intent intent = new Intent(getContext(), KotlinActivity.class);
                startActivity(intent);
                break;
        }
    }
}
