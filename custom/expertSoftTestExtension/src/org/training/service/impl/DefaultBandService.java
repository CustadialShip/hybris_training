package org.training.service.impl;

import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.springframework.stereotype.Service;
import org.training.model.BandModel;
import org.training.service.BandService;

@Service
public class DefaultBandService implements BandService {
    public static final String QUERY_SEARCH_BY_NAME = "SELECT {PK} FROM {Band} WHERE {name} = ?name";
    public static final String NAME = "name";
    private final FlexibleSearchService flexibleSearchService;

    public DefaultBandService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public BandModel getBand(final String name) {
        if(name == null){
            throw new IllegalArgumentException("name should be not null");
        }
        final FlexibleSearchQuery query = new FlexibleSearchQuery(QUERY_SEARCH_BY_NAME);
        query.addQueryParameter(NAME, name);
        final SearchResult<BandModel> result = this.flexibleSearchService.search(query);
        final int resultCount = result.getTotalCount();
        if (resultCount == 0) {
            throw new UnknownIdentifierException(
                    "Band with name '" + name + "' not found!"
            );
        } else if (resultCount > 1) {
            throw new AmbiguousIdentifierException(
                    "Band with name '" + name + "' is not unique, " + resultCount
                            + " band found!"
            );
        }
        return result.getResult().get(0);
    }
}
