/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.setup;

import static org.training.constants.ExpertSoftTestExtensionConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import org.training.constants.ExpertSoftTestExtensionConstants;
import org.training.service.ExpertSoftTestExtensionService;


@SystemSetup(extension = ExpertSoftTestExtensionConstants.EXTENSIONNAME)
public class ExpertSoftTestExtensionSystemSetup
{
	private final ExpertSoftTestExtensionService expertSoftTestExtensionService;

	public ExpertSoftTestExtensionSystemSetup(final ExpertSoftTestExtensionService expertSoftTestExtensionService)
	{
		this.expertSoftTestExtensionService = expertSoftTestExtensionService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		expertSoftTestExtensionService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return ExpertSoftTestExtensionSystemSetup.class.getResourceAsStream("/expertSoftTestExtension/sap-hybris-platform.png");
	}
}
