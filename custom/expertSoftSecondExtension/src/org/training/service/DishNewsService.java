package org.training.service;

import org.training.model.DishNewsModel;

import java.util.Date;
import java.util.List;

public interface DishNewsService {
    List<DishNewsModel> getNewsOfDay(Date date);
}
