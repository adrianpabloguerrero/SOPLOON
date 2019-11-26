package ar.edu.unicen.isistan.si.soploon.plugin.eclipse.handlers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.dialogs.MessageDialog;

import com.thoughtworks.xstream.XStream;

import ar.edu.unicen.isistan.si.soploon.plugin.teacher.Teacher;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.analyzer.PredicateSet;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.analyzer.PrologAnalyzer;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.analyzer.RuleSet;

public class UpdateHandler extends AbstractHandler {

	public UpdateHandler() {

	}

	@Override
	public Object execute(ExecutionEvent event) {
		try {
			
			String rules_xml = getURL("http://si.isistan.unicen.edu.ar:8080/assistant/api/rules");
			String predicates_xml = getURL("http://si.isistan.unicen.edu.ar:8080/assistant/api/predicates");
			
			if ((rules_xml == null || predicates_xml == null) && event.getTrigger() != null) {
				MessageDialog.openInformation(null, "Asistente Virtual", "El Ayudante no pudo comunicarse con el servidor.");
				return null;
			}
			
			XStream xstream = new XStream();
			xstream.processAnnotations(RuleSet.class);
			xstream.processAnnotations(PredicateSet.class);
			RuleSet remote_rules = (RuleSet) xstream.fromXML(rules_xml);
			PredicateSet remote_predicates = (PredicateSet) xstream.fromXML(predicates_xml);

			PrologAnalyzer analyzer = Teacher.getInstance().getAnalyzer();
			
			RuleSet local_rules = analyzer.getRuleSet();

			//MessageDialog.openInformation(null, "Asistente Virtual", local_rules == null ? "null" : "local_rules");
			//MessageDialog.openInformation(null, "Asistente Virtual", local_rules.getVersion() == null ? "null" : "local_version");
			
			//MessageDialog.openInformation(null, "Asistente Virtual", !local_rules.getVersion().equals(remote_rules.getVersion()) ? "distintos" : "iguales");

			if (local_rules == null || local_rules.getVersion() == null || !local_rules.getVersion().equals(remote_rules.getVersion())) {
				analyzer.setRules(remote_rules);
				analyzer.setPredicates(remote_predicates);
				if (event.getTrigger() != null)
					MessageDialog.openInformation(null, "Ayudante Virtual", "Ayudante actualizado!");
			} else if (event.getTrigger() != null) {
				MessageDialog.openInformation(null, "Ayudante Virtual", "Usted ya dispone de la �ltima version del Ayudante!");
			}

			return null;
		} catch (Exception e) {
			MessageDialog.openInformation(null, "Asistente Virtual", "Excepcion " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	private String getURL(String path) {
		try {
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(2000).build();
			HttpClient http_client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
			HttpGet get = new HttpGet(path);
			HttpResponse response = http_client.execute(get);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream input = entity.getContent();
				ByteArrayOutputStream result = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len;
				while ((len = input.read(buffer)) != -1)
					result.write(buffer, 0, len);
				return result.toString();
			} else {
				return null;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
	}

}
