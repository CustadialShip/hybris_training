package org.training.service.impl;

import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.dao.DishDao;
import org.training.model.BandModel;
import org.training.model.DishModel;
import org.training.service.DishService;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    public static final String EXCEPTION_MESSAGE_UNKNOWN_IDENTIFIER = "Dish not fount. Code is ";
    public static final String EXCEPTION_MESSAGE_AMBIGUOUS_IDENTIFIER = "Dish code is not unique. Code - ";
    private final DishDao dishDao;

    public DishServiceImpl(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    public List<DishModel> getDishes() {
        return dishDao.findDishes();
    }

    @Override
    public DishModel getDishByCode(String code) throws UnknownIdentifierException, AmbiguousIdentifierException{
        List<DishModel> dishModelList = dishDao.findDishByCode(code);
        if (dishModelList.size() == 0) {
            throw new UnknownIdentifierException(EXCEPTION_MESSAGE_UNKNOWN_IDENTIFIER + code);
        } else if (dishModelList.size() > 1) {
            throw new AmbiguousIdentifierException(EXCEPTION_MESSAGE_AMBIGUOUS_IDENTIFIER + code);
        }
        return dishModelList.get(0);
    }

}
