package ar.edu.unicen.isistan.si.teachingassistant.plugin;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class TeachingAssistant extends AbstractUIPlugin {

	public static final String BASE_HOST = "https://si.isistan.unicen.edu.ar/soploon/api";
	
	public static final String PLUGIN_ID = "TeachingAssistant";
	public static final String VERSION = "2.0";
	
	private static TeachingAssistant plugin;

	public TeachingAssistant() {

	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static TeachingAssistant getDefault() {
		return plugin;
	}

	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

}
