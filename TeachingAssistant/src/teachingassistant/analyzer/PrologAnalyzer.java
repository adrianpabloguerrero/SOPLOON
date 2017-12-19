
package teachingassistant.analyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;

import com.thoughtworks.xstream.XStream;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Prolog;
import alice.tuprolog.Theory;
import teachingassistant.analyzer.bugs.Bug;
import teachingassistant.modeler.Mapper;
import teachingassistant.modeler.PrologCode;
import teachingassistant.modeler.converters.NodeConverterFactory;

public class PrologAnalyzer {

	private static final String MONITOR_TITLE = "Analizando el C�digo Prolog";
	private static final String MONITOR_TITLE_PRE = "Preparando Analisis";
	private static final String LOG_HEADER = System.lineSeparator() + "Analizando el Modelo Prolog"
			+ System.lineSeparator();
	private static final String LOG_FOOTER = System.lineSeparator() + "Modelo Prolog Analizado Correctamente"
			+ System.lineSeparator();
	private static final String LOG_ERROR = System.lineSeparator() + "Error:" + System.lineSeparator();
	private static final String LOG_BUGS_HEADER = "===== Errores Detectados =====" + System.lineSeparator();
	private static final String LOG_BUGS_FOOTER = System.lineSeparator() + "==============================";

	private RuleSet rule_set;
	private List<Bug> bugs;
	private Theory basic_theory;
	private Theory specific_theory;
	private Theory rules_theory;

	public static final String BASE_PATH = Platform.getInstallLocation().getURL().getPath() + File.separator + "dropins"
			+ File.separator + "plugins" + File.separator + "resources" + File.separator;
	public static final String RULES_PATH = BASE_PATH + "rules.xml";
	public static final String BASIC_KNOWLEDGE_PATH = BASE_PATH + "basic_knowledge.pl";
	public static final String SPECIFIC_KNOWLEDGE_PATH = BASE_PATH + "specific_knowledge.pl";
	public static final String RULES_KNOWLEDGE_PATH = BASE_PATH + "rules_knowledge.pl";

	public PrologAnalyzer() {
		this.bugs = new Vector<Bug>();
		this.init();
	}

	public void init() {
		this.readRules();
	}

	public RuleSet getRules() {
		return this.rule_set;
	}

	public List<Bug> getBugs() {
		return this.bugs;
	}

	public boolean setRules(RuleSet remote_rules, String basic, String specific, String rules) {
		try {
			Theory new_basic = new Theory(basic);
			Theory new_specific = new Theory(specific);
			Theory new_rules = new Theory(rules);
			this.rule_set = remote_rules;
			this.basic_theory = new_basic;
			this.specific_theory = new_specific;
			this.rules_theory = new_rules;
			return true;
		} catch (InvalidTheoryException e) {
			return false;
		}
	}

	private String readFile(String path) {
		BufferedReader reader = null;
		try {
			File file = new File(path);
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			String ls = System.getProperty("line.separator");

			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			return stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return null;
	}

	public void readRules() {
		try {
			XStream xstream = new XStream();
			xstream.processAnnotations(RuleSet.class);
			this.rule_set = (RuleSet) xstream.fromXML(new FileInputStream(RULES_PATH));

			this.basic_theory = new Theory(readFile(BASIC_KNOWLEDGE_PATH));
			this.specific_theory = new Theory(readFile(SPECIFIC_KNOWLEDGE_PATH));
			this.rules_theory = new Theory(readFile(RULES_KNOWLEDGE_PATH));

		} catch (FileNotFoundException | InvalidTheoryException e) {
			
		}
	}

	public int process(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory, IProgressMonitor monitor,
			PrintWriter logger) {
		this.bugs.clear();
		try {
			logger.println(LOG_HEADER);

			logger.println(this.rule_set.toString());

			Theory code_theory = new Theory(code.toString());

			int cores = Runtime.getRuntime().availableProcessors();
			Vector<RuleRunnable> runnables = new Vector<RuleRunnable>();
			List<Rule> rules = new Vector<Rule>(this.rule_set.getRules());

			monitor.beginTask(MONITOR_TITLE_PRE, cores);

			for (int i = 0; i < cores; i++) {
				Prolog engine = new Prolog();
				engine.addTheory(basic_theory);
				engine.addTheory(specific_theory);
				engine.addTheory(rules_theory);
				engine.addTheory(code_theory);
				runnables.add(new RuleRunnable(monitor, engine, mapper, converter_factory, rules, bugs));
				monitor.worked(1);
			}

			ExecutorService thread_pool = Executors.newFixedThreadPool(cores);

			monitor.beginTask(MONITOR_TITLE, this.rule_set.getRules().size());

			for (Runnable runnable : runnables)
				thread_pool.submit(runnable);

			boolean working = true;
			thread_pool.shutdown();

			while (working) {
				try {
					if (thread_pool.awaitTermination(2, TimeUnit.SECONDS)) {
						working = false;
					} else {
						if (monitor.isCanceled()) {
							thread_pool.shutdownNow();
							monitor.done();
							return 1;
						}
					}
				} catch (InterruptedException e) {
					thread_pool.shutdownNow();
					monitor.done();
					logger.println(LOG_ERROR);
					logger.println(e.toString());
					return -1;
				}
			}

			logger.println(LOG_FOOTER);

			logger.println(LOG_BUGS_HEADER);

			for (Bug bug : this.bugs)
				logger.println(bug.toString());

			logger.println(LOG_BUGS_FOOTER);

			return 0;

		} catch (Exception e) {
			monitor.done();
			logger.println(LOG_ERROR);
			logger.println(e.toString());
			return -1;
		}

	}

}
