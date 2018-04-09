package services;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class Application extends ResourceConfig {
    
    @Inject
    public Application(ServiceLocator locator) {
        ServiceLocatorUtilities.enableImmediateScope(locator);
        this.register(CORSFilter.class);
        this.register(Manager.class);
    }

}