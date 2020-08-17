package ar.edu.unicen.isistan.si.soploon.server.services;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.server.ResourceConfig;

import ar.edu.unicen.isistan.si.soploon.server.providers.CORSFilter;
import ar.edu.unicen.isistan.si.soploon.server.providers.GsonServerProvider;
import ar.edu.unicen.isistan.si.soploon.server.providers.AuthenticationFilter;

@ApplicationPath("/api")
public class SoploonApplication extends ResourceConfig {

	@Inject
	public SoploonApplication(ServiceLocator locator) {
		ServiceLocatorUtilities.enableImmediateScope(locator);
		this.register(CORSFilter.class);
		this.register(GsonServerProvider.class);
		this.register(AuthenticationFilter.class);
		this.register(SoploonManager.class);
	}
}
