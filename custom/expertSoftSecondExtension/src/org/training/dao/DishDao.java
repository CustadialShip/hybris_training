package org.training.dao;

import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.training.model.DishModel;

import java.util.List;

public interface DishDao {
    List<DishModel> findDishes();

    List<DishModel> findDishByCode(final String code);
}
