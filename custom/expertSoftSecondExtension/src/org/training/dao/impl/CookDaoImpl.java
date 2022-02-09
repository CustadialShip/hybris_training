package org.training.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.stereotype.Component;
import org.training.dao.CookDao;
import org.training.model.CookModel;

import java.util.List;
@Component
public class CookDaoImpl implements CookDao {

    private final FlexibleSearchService searchService;

    public CookDaoImpl(FlexibleSearchService searchService) {
        this.searchService = searchService;
    }

    @Override
    public List<CookModel> findCooks() {
        final String queryString = "SELECT {p:" + CookModel.PK + "} FROM {" + CookModel._TYPECODE + " AS p}";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        return searchService.<CookModel>search(query).getResult();
    }
}
