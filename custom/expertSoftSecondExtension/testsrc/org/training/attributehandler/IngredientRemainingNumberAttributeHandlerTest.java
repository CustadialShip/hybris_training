package org.training.attributehandler;

import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.training.model.IngredientModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@UnitTest
public class IngredientRemainingNumberAttributeHandlerTest {

    public static final String TEST_NAME = "TestName";

    @Test
    public void shouldGetAttributeWhenModelOk() {
        IngredientModel ingredientModel = new IngredientModel();
        ingredientModel.setName(TEST_NAME);
        IngredientRemainingNumberAttributeHandler handler = new IngredientRemainingNumberAttributeHandler();
        Long expected = handler.get(ingredientModel);
        Assert.assertNotNull(expected);
        assertNotEquals(0, (long) expected);
    }

    @Test
    public void shouldGetNullWhenModelNameIsNull() {
        IngredientModel ingredientModel = new IngredientModel();
        IngredientRemainingNumberAttributeHandler handler = new IngredientRemainingNumberAttributeHandler();
        Long expected = handler.get(ingredientModel);
        Assert.assertNull(expected);
    }

    @Test
    public void shouldGetNullWhenModelIsNull() {
        IngredientModel ingredientModel = null;
        IngredientRemainingNumberAttributeHandler handler = new IngredientRemainingNumberAttributeHandler();
        Long expected = handler.get(ingredientModel);
        Assert.assertNull(expected);
    }
}