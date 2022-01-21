/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.training.service.BandService;

import javax.annotation.Resource;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * This is an example of how the integration test should look like. {@link ServicelayerBaseTest} bootstraps platform so
 * you have an access to all Spring beans as well as database connection. It also ensures proper cleaning out of items
 * created during the test after it finishes. You can inject any Spring service using {@link Resource} annotation. Keep
 * in mind that by default it assumes that annotated field name matches the Spring Bean ID.
 */
@IntegrationTest
public class DefaultBandServiceTest extends ServicelayerBaseTest {
    public static final String CHECKPOINT = "CHECKPOINT";
    @Resource
    private BandService bandService;

    private static final String BAND_NAME = "B1_name";

    @Before
    public void setUp() throws Exception {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            new JdbcTemplate(Registry.getCurrentTenant().getDataSource()).execute(CHECKPOINT);
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException exc) {
        }
    }

    @Test
    public void bandServiceShouldBeNotNull() {
        Assert.assertNotNull(bandService);
    }

    @Test
    public void shouldReturnNullBandWhenUseGetBrandMethod() {
        BandModel bandByName = bandService.getBand(BAND_NAME);
        Assert.assertTrue("bandsByName should be null", bandByName == null);
    }

    @Test
    public void shouldReturnNullBandWhenEmptyArgUseGetBrandMethod() {
        BandModel band = bandService.getBand("");
        Assert.assertTrue("No Band should be returned", band == null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testfindBands_NullParam() {
        bandService.getBand(null);
    }

}
