/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.service.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.training.dao.DishDao;
import org.training.model.DishModel;
import org.training.service.DishService;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * This is an example of how the integration test should look like. {@link ServicelayerBaseTest} bootstraps platform so
 * you have an access to all Spring beans as well as database connection. It also ensures proper cleaning out of items
 * created during the test after it finishes. You can inject any Spring service using {@link Resource} annotation. Keep
 * in mind that by default it assumes that annotated field name matches the Spring Bean ID.
 */
@UnitTest
public class DishServiceUnitTest extends ServicelayerBaseTest {
    public static final String DISH_CODE = "D_TEST";
    public static final int DISH_CALORIES = 100;
    public static final double DISH_COOKING_TIME = 2.0;
    public static final String DISH_NAME = "DISH_NAME";
    private DishService dishService;
    private DishDao dishDao;
    private DishModel dishModel;

    @Before
    public void setUp() throws Exception {
        dishDao = mock(DishDao.class);
        dishService = new DishServiceImpl(dishDao);
        dishModel = new DishModel();
        dishModel.setCalories(DISH_CALORIES);
        dishModel.setCode(DISH_CODE);
        dishModel.setCookingTime(DISH_COOKING_TIME);
        dishModel.setName(DISH_NAME);
    }

    @Test
    public void shouldGetAllDish() {
        final List<DishModel> actual = List.of(dishModel);
        when(dishDao.findDishes()).thenReturn(actual);
        final List<DishModel> expected = dishService.getDishes();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldGetDish(){
        final List<DishModel> actual = List.of(dishModel);
        when(dishDao.findDishByCode(DISH_CODE)).thenReturn(actual);
        final DishModel expected = dishService.getDishByCode(DISH_CODE);
        Assert.assertEquals(expected, dishModel);
    }

    @Test
    public void shouldThrowUnknownIdentifierExceptionWhenDishNotFind() {
        final List<DishModel> emptyList = new ArrayList<>();
        when(dishDao.findDishByCode(DISH_CODE)).thenReturn(emptyList);
        try{
            DishModel dishModel = dishService.getDishByCode(DISH_CODE);
            Assert.fail();
        } catch (UnknownIdentifierException e){
            Assert.assertNull(null);
        }
    }

    @Test
    public void shouldThrowAmbiguousIdentifierExceptionWhenMultipleDish() {
        final List<DishModel> dishModels = List.of(dishModel, dishModel);
        when(dishDao.findDishByCode(DISH_CODE)).thenReturn(dishModels);
        try{
            DishModel dishModel = dishService.getDishByCode(DISH_CODE);
            Assert.fail();
        } catch (AmbiguousIdentifierException e){
            Assert.assertNull(null);
        }
    }
}
