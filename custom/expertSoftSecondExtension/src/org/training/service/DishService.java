package org.training.service;

import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.training.model.DishModel;

import java.util.List;

public interface DishService {
    List<DishModel> getDishes();

    DishModel getDishByCode(final String code) throws UnknownIdentifierException, AmbiguousIdentifierException;
}
