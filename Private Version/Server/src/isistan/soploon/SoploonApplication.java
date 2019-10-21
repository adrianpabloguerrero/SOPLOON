package isistan.soploon;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.server.ResourceConfig;

import isistan.soploon.services.utils.CORSFilter;
import isistan.soploon.services.utils.GsonProvider;


@ApplicationPath("/api")
public class SoploonApplication extends ResourceConfig {
	
	 @Inject
	    public SoploonApplication(ServiceLocator locator) {
	        ServiceLocatorUtilities.enableImmediateScope(locator);
	        this.register(CORSFilter.class);
	        this.register(GsonProvider.class);
	        this.register(SoploonManager.class);    
	    }
}
