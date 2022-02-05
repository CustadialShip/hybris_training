package org.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.training.dao.TempItemDao;
import org.training.model.TempItemModel;
import org.training.service.TempItemService;

@Component
public class TempItemServiceImpl implements TempItemService {
    private final TempItemDao itemDao;

    public TempItemServiceImpl(TempItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public TempItemModel getOneTempItem() {
        return itemDao.getTempItem().get(0);
    }
}
