/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.setup;

import static org.training.constants.ExpertSoftSecondExtensionConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import org.training.constants.ExpertSoftSecondExtensionConstants;
import org.training.service.ExpertSoftSecondExtensionService;


@SystemSetup(extension = ExpertSoftSecondExtensionConstants.EXTENSIONNAME)
public class ExpertSoftSecondExtensionSystemSetup
{
	private final ExpertSoftSecondExtensionService expertSoftSecondExtensionService;

	public ExpertSoftSecondExtensionSystemSetup(final ExpertSoftSecondExtensionService expertSoftSecondExtensionService)
	{
		this.expertSoftSecondExtensionService = expertSoftSecondExtensionService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		expertSoftSecondExtensionService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return ExpertSoftSecondExtensionSystemSetup.class.getResourceAsStream("/expertSoftSecondExtension/sap-hybris-platform.png");
	}
}
