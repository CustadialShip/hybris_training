/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.secureportaladdon.constants;

/**
 * Global class for all Secureportaladdon web constants. You can add global constants for your extension into this
 * class.
 */
public final class SecureportaladdonWebConstants
{
	private SecureportaladdonWebConstants()
	{
		//empty to avoid instantiating this constant class
	}

	/**
	 * List of any new request URIs defined and handled in this add on.
	 */
	public static final class RequestMappings
	{

		public static final String ACCOUNT_REGISTRATION = "/register";
		public static final String HOME = "/";
		public static final String LOGIN = "/secureLogin";

		private RequestMappings()
		{
		}
	}

	/**
	 * List of all new Views defined and used in this add on.
	 */
	public static final class Views
	{

		public static final String LOGIN_PAGE = VIEW_PAGE_PREFIX + "/pages/login";
		public static final String REGISTRATION_PAGE = VIEW_PAGE_PREFIX + "/pages/accountRegistration";

		private Views()
		{
		}

	}

	public final static String ADD_ON_PREFIX = "addon:";
	public final static String VIEW_PAGE_PREFIX = ADD_ON_PREFIX + "/" + SecureportaladdonConstants.EXTENSIONNAME;
	public final static String CMS_REGISTER_PAGE_NAME = "SecureCustomerPortalRegisterPage";
	public static final String REDIRECT_PREFIX = "redirect:";

}
