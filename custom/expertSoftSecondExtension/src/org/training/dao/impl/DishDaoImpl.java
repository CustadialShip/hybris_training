package org.training.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.training.dao.DishDao;
import org.training.model.BandModel;
import org.training.model.DishModel;

import java.util.List;

@Component
public class DishDaoImpl implements DishDao {
    public static final String CODE = "code";
    private final FlexibleSearchService searchService;

    public DishDaoImpl(FlexibleSearchService searchService) {
        this.searchService = searchService;
    }

    @Override
    public List<DishModel> findDishes() {
        final String queryString = "SELECT {p:" + DishModel.PK + "} FROM {" + DishModel._TYPECODE + " AS p} ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        return searchService.<DishModel>search(query).getResult();
    }

    @Override
    public List<DishModel> findDishByCode(final String code) {
        final String queryString = "SELECT {p:" + DishModel.PK + "} FROM {" + DishModel._TYPECODE + " AS p} "
                + "WHERE {p:" + BandModel.CODE + "}=?code ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter(CODE, code);
        return searchService.<DishModel>search(query).getResult();
    }
}
