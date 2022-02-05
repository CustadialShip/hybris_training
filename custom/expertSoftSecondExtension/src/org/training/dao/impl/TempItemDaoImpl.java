package org.training.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.training.dao.TempItemDao;
import org.training.model.TempItemModel;

import java.util.List;

@Component
public class TempItemDaoImpl implements TempItemDao {
    private final FlexibleSearchService searchService;

    public TempItemDaoImpl(FlexibleSearchService searchService) {
        this.searchService = searchService;
    }

    @Override
    public List<TempItemModel> getTempItem() {
        String queryString = "SELECT {p:" + TempItemModel.PK + "} FROM {" + TempItemModel._TYPECODE + "AS p}";
        FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(queryString);
        return searchService.<TempItemModel>search(flexibleSearchQuery).getResult();
    }
}
