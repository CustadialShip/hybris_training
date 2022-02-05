package org.training.facades.impl;

import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.training.data.DishSummaryData;
import org.training.facades.DishFacade;
import org.training.model.DishModel;
import org.training.service.DishService;

import java.util.ArrayList;
import java.util.List;

@Component
public class DishFacadeImpl implements DishFacade {
    public static final String ERROR_EMPTY_CODE_OF_DISH = "error.emptyCode";
    private final DishService dishService;
    private final ConfigurationService configurationService;

    public DishFacadeImpl(DishService dishService, ConfigurationService configurationService) {
        this.dishService = dishService;
        this.configurationService = configurationService;
    }

    @Override
    public DishSummaryData getDish(String code) {
        if (code == null) {
            throw new IllegalArgumentException(configurationService.getConfiguration().getString(ERROR_EMPTY_CODE_OF_DISH));
        }
        final DishSummaryData dishSummaryData;
        try {
            DishModel dishModel = dishService.getDishByCode(code);
            dishSummaryData = new DishSummaryData();
            setDishSummaryDataFields(dishModel, dishSummaryData);
        } catch (UnknownIdentifierException | AmbiguousIdentifierException e) {
            return null;
        }
        return dishSummaryData;
    }

    @Override
    public List<DishSummaryData> getDishes() {
        final List<DishModel> dishModelList = dishService.getDishes();
        final List<DishSummaryData> dishSummaryDataList = new ArrayList<>();
        for (DishModel dishModel : dishModelList) {
            final DishSummaryData temp = new DishSummaryData();
            setDishSummaryDataFields(dishModel, temp);
            dishSummaryDataList.add(temp);
        }
        return dishSummaryDataList;
    }

    private void setDishSummaryDataFields(DishModel dishModel, DishSummaryData temp) {
        temp.setId(dishModel.getCode());
        temp.setName(dishModel.getName());
        temp.setCalories(dishModel.getCalories());
        temp.setCookingTime(dishModel.getCookingTime());
        temp.setDescription(dishModel.getName() + " in " + dishModel.getCookingTime() + " hours");
    }
}
