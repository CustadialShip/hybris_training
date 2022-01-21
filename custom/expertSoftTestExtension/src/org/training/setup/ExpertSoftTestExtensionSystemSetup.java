/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.setup;

import static org.training.constants.ExpertSoftTestExtensionConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import de.hybris.platform.servicelayer.impex.ImportConfig;
import de.hybris.platform.servicelayer.impex.ImportResult;
import de.hybris.platform.servicelayer.impex.ImportService;
import de.hybris.platform.servicelayer.impex.impl.StreamBasedImpExResource;
import org.training.constants.ExpertSoftTestExtensionConstants;
import org.training.service.ExpertSoftTestExtensionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SystemSetup(extension = ExpertSoftTestExtensionConstants.EXTENSIONNAME)
public class ExpertSoftTestExtensionSystemSetup
{
	public static final String LOADING_CUSTOM_ESSENTIAL = "Loading custom essential data loading for expertSoftTestExtension";
	public static final String LOADING_CUSTOM_PROJECT = "Loading custom project data loading for expertSoftTestExtension";
	public static final String IMPEX_DATA_BANDS_PATH = "impex/data-bands.impex";
	public static final String IMPEX_DATA_Y_BAND_TOUR_PATH = "impex/data-yBandTour.impex";
	public static final String FAILED = "FAILED";

	private static final Logger LOG = LoggerFactory.getLogger(ExpertSoftTestExtensionSystemSetup.class);
	private final ExpertSoftTestExtensionService expertSoftTestExtensionService;
	private ImportService importService;

	public ExpertSoftTestExtensionSystemSetup(final ExpertSoftTestExtensionService expertSoftTestExtensionService)
	{
		this.expertSoftTestExtensionService = expertSoftTestExtensionService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		expertSoftTestExtensionService.createLogo(PLATFORM_LOGO_CODE);
	}

	@SystemSetup(type = SystemSetup.Type.ESSENTIAL)
	public boolean putInMyEssentialData(){
		LOG.info(LOADING_CUSTOM_ESSENTIAL);
		return true;
	}

	@SystemSetup(type = SystemSetup.Type.ESSENTIAL)
	public boolean addMyProjectData(){
		LOG.info(LOADING_CUSTOM_PROJECT);
		impexImport(IMPEX_DATA_BANDS_PATH);
		impexImport(IMPEX_DATA_Y_BAND_TOUR_PATH);
		return true;
	}

	protected boolean impexImport(final String filename)
	{
		final String message = "expertSoftTest impexing [" + filename + "]...";
		try (final InputStream resourceAsStream = getClass().getResourceAsStream(filename))
		{
			if(resourceAsStream == null){
				throw new Exception();
			}
			LOG.info(message);
			final ImportConfig importConfig = new ImportConfig();
			importConfig.setScript(new StreamBasedImpExResource(resourceAsStream, "UTF-8"));
			importConfig.setLegacyMode(Boolean.FALSE);
			final ImportResult importResult = getImportService().importData(importConfig);
			if (importResult.isError())
			{
				LOG.error(message + " " + FAILED);
				return false;
			}
		}
		catch (final Exception e)
		{
			LOG.error(message + " " + FAILED, e);
			return false;
		}
		return true;
	}

	private InputStream getImageStream()
	{
		return ExpertSoftTestExtensionSystemSetup.class.getResourceAsStream("/expertSoftTestExtension/sap-hybris-platform.png");
	}

	public ImportService getImportService() {
		return importService;
	}

	public void setImportService(ImportService importService) {
		this.importService = importService;
	}
}
