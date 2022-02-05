package org.training.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.training.dao.DishNewsDao;
import org.training.model.DishNewsModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class DishNewsDaoImpl implements DishNewsDao {
    public static final String DATE_PATTERN = "yyy-MM-dd";
    private final FlexibleSearchService searchService;

    public DishNewsDaoImpl(FlexibleSearchService searchService) {
        this.searchService = searchService;
    }

    @Override
    public List<DishNewsModel> getNewsOfDay(Date date) {
        if (date == null) {
            return Collections.emptyList();
        }
        String day = new SimpleDateFormat(DATE_PATTERN).format(date);
        String nextDay = new SimpleDateFormat(DATE_PATTERN).format(oneDayAfter(date));
        String query = "SELECT {p:" + DishNewsModel.PK + "} FROM {" + DishNewsModel._TYPECODE + " AS p} "
                + "WHERE {date} >= DATE " + day + " AND {date} <= DATE " + nextDay;
        FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
        return searchService.<DishNewsModel>search(searchQuery).getResult();
    }

    private Date oneDayAfter(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }
}
