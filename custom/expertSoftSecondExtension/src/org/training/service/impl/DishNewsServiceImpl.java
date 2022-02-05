package org.training.service.impl;

import org.springframework.stereotype.Service;
import org.training.dao.DishNewsDao;
import org.training.model.DishNewsModel;
import org.training.service.DishNewsService;

import java.util.Date;
import java.util.List;

@Service
public class DishNewsServiceImpl implements DishNewsService {
    private final DishNewsDao dishNewsDao;

    public DishNewsServiceImpl(DishNewsDao dishNewsDao) {
        this.dishNewsDao = dishNewsDao;
    }

    @Override
    public List<DishNewsModel> getNewsOfDay(Date date) {
        return dishNewsDao.getNewsOfDay(date);
    }
}
