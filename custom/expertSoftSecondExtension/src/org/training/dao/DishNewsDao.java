package org.training.dao;

import org.training.model.DishNewsModel;

import java.util.Date;
import java.util.List;

public interface DishNewsDao {
    List<DishNewsModel> getNewsOfDay(Date date);
}
