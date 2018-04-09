package soploon.handlers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.dialogs.MessageDialog;

import com.thoughtworks.xstream.XStream;

import soploon.analyzer.PrologAnalyzer;
import soploon.analyzer.RuleSet;
import soploon.teacher.Teacher;

public class UpdateHandler extends AbstractHandler {

	public UpdateHandler() {
		
	}

	@Override
	public Object execute(ExecutionEvent event) {
		String rulesxml = getURL("http://si.isistan.unicen.edu.ar:8080/Rules/rules.xml");
		if (rulesxml == null && event.getTrigger() != null) {
			MessageDialog.openInformation(null, "Teaching Assistant", "No se pudo actualizar el Ayudante Virtual (servidor inalcanzable)");
			return null;
		}
		XStream xstream = new XStream();
		xstream.processAnnotations(RuleSet.class);
		RuleSet remote_rules = (RuleSet) xstream.fromXML(rulesxml);

		PrologAnalyzer analyzer = Teacher.getInstance().getAnalyzer();
		RuleSet local_rules = analyzer.getRules();
		if (local_rules == null || !local_rules.getVersion().equals(remote_rules.getVersion())) {
			String basic = getURL("http://si.isistan.unicen.edu.ar:8080/Rules/basic_knowledge.pl");
			String specific = getURL("http://si.isistan.unicen.edu.ar:8080/Rules/specific_knowledge.pl");
			String rules = getURL("http://si.isistan.unicen.edu.ar:8080/Rules/rules_knowledge.pl");

			if ((basic == null || specific == null || rules == null)) {
				if (event.getTrigger() != null)
					MessageDialog.openInformation(null, "Ayudante Virtual", "No se pudo actualizar el Ayudante Virtual (servidor inalcanzable)");
			} else {
				if (analyzer.setRules(remote_rules, basic, specific, rules)) {
					File dir = new File(PrologAnalyzer.BASE_PATH);
					if (!dir.exists())
						dir.mkdirs();
					saveToFile(rulesxml, PrologAnalyzer.RULES_PATH);
					saveToFile(basic, PrologAnalyzer.BASIC_KNOWLEDGE_PATH);
					saveToFile(specific, PrologAnalyzer.SPECIFIC_KNOWLEDGE_PATH);
					saveToFile(rules, PrologAnalyzer.RULES_KNOWLEDGE_PATH);
					if (event.getTrigger() != null)
						MessageDialog.openInformation(null, "Ayudante Virtual", "Ayudante actualizado!");
				} else if (event.getTrigger() != null) {		
					MessageDialog.openInformation(null, "Ayudante Virtual", "No se pudo actualizar el Ayudante Virtual (error en la actualización)");
				}
			}
		} else if (event.getTrigger() != null) {
				MessageDialog.openInformation(null, "Ayudante Virtual", "Usted ya dispone de la última version del Ayudante!");
		}

		return null;
	}

	private String getURL(String path) {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(path);
			HttpResponse response = httpClient.execute(httpget);
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
			return null;
		}
	}

	private boolean saveToFile(String content, String path) {
		try {
			PrintWriter out;
			out = new PrintWriter(path);
			out.write(content);
			out.flush();
			out.close();
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}

}
