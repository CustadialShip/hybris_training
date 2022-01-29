package org.training.facades;

import org.training.data.DishSummaryData;

import java.util.List;

public interface DishFacade {
    DishSummaryData getDish(String name);

    List<DishSummaryData> getDishes();
}
