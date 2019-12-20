package ar.edu.unicen.isistan.si.soploon.plugin;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Soploon extends AbstractUIPlugin {

	public static final String BASE_HOST = "http://localhost:8080/soploon/api";
	
	public static final String PLUGIN_ID = "TeachingAssistant"; //$NON-NLS-1$
	public static final String VERSION = "1.1";
	
	private static Soploon plugin;

	public Soploon() {

	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static Soploon getDefault() {
		return plugin;
	}

	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

}
