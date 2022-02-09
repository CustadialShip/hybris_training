package org.training.service.impl;

import org.springframework.stereotype.Component;
import org.training.dao.CookDao;
import org.training.model.CookModel;
import org.training.service.CookService;

import java.util.List;

@Component
public class CookServiceImpl implements CookService {
    private final CookDao cookDao;

    public CookServiceImpl(CookDao cookDao) {
        this.cookDao = cookDao;
    }

    @Override
    public List<CookModel> getCooks(){
        return cookDao.findCooks();
    }
}
