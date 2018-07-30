package com.etisalat.sampletask;

import com.etisalat.sampletask.foods.FoodsController;
import com.etisalat.sampletask.foods.FoodsPresenter;
import com.etisalat.sampletask.model.Food;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class FoodsControllerUnitTest {

    @Mock
    private
    FoodsPresenter foodsPresenter;
    private FoodsController foodsController;
    private List<Food> foods;

    @Before
    public void setupFoodsController() {
        foodsController = new FoodsController(foodsPresenter);
        foods = new ArrayList<>();
        foods.add(new Food("potato"));
        foods.add(new Food("rice"));
        foods.add(new Food("koshari"));
    }

    @Test
    public void testSortingFoods_isCorrect() {
        List<Food> sortedFoods = foodsController.sortingFoods(foods);
        assertEquals("koshari", sortedFoods.get(0).getName());
        assertEquals("potato", sortedFoods.get(1).getName());
        assertEquals("rice", sortedFoods.get(2).getName());
    }

    @Test
    public void testSortingFoods_isNotCorrect() {
        List<Food> sortedFoods = foodsController.sortingFoods(foods);
        assertNotEquals("potato", sortedFoods.get(0).getName());
        assertNotEquals("rice", sortedFoods.get(1).getName());
        assertNotEquals("koshari", sortedFoods.get(2).getName());
    }
}
