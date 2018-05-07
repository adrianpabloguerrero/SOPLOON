
package soploon.analyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Prolog;
import alice.tuprolog.Theory;
import soploon.analyzer.bugs.Bug;
import soploon.modeler.Mapper;
import soploon.modeler.PrologCode;
import soploon.modeler.converters.NodeConverterFactory;

public class PrologAnalyzer {

	private static final String MONITOR_TITLE = "Analyzing the Prolog representation";
	private static final String MONITOR_TITLE_PRE = "Creating threads for analysis";

	private RuleSet rule_set;
	private PredicateSet predicate_set;
	private List<Bug> bugs;

	public static final String BASE_PATH = Platform.getInstallLocation().getURL().getPath() + File.separator + "dropins" + File.separator + "plugins" + File.separator + "resources" + File.separator;
	public static final String RULES_PATH = BASE_PATH + "rules.xml";
	public static final String PREDICATES_PATH = BASE_PATH + "predicates.xml";

	public PrologAnalyzer() {
		this.bugs = new Vector<Bug>();
		this.init();
	}

	public void init() {
		if (this.rule_set == null || this.predicate_set == null) {
			this.readRules();
			this.readPredicates();
		}
	}


	public RuleSet getRuleSet() {
		return this.rule_set;
	}

	public PredicateSet getPredicateSet() {
		return predicate_set;
	}
	
	public List<Bug> getBugs() {
		return this.bugs;
	}
	
	public void readRules() {
		try {
			XStream xstream = new XStream(new DomDriver("UTF-8"));
			XStream.setupDefaultSecurity(xstream);
			xstream.allowTypes(new Class[] {RuleSet.class, Rule.class} );
			xstream.processAnnotations(RuleSet.class);

			this.rule_set = (RuleSet) xstream.fromXML(new FileInputStream(RULES_PATH));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	private void readPredicates() {
		try {
			XStream xstream = new XStream(new DomDriver("UTF-8"));
			XStream.setupDefaultSecurity(xstream);
			xstream.allowTypes(new Class[] {PredicateSet.class, Predicate.class} );
			xstream.processAnnotations(PredicateSet.class);

			this.predicate_set = (PredicateSet) xstream.fromXML(new FileInputStream(PREDICATES_PATH));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void saveRules() {
		try {
			XStream xstream = new XStream(new DomDriver("UTF-8"));
			XStream.setupDefaultSecurity(xstream);
			xstream.allowTypes(new Class[] {RuleSet.class, Rule.class} );
			xstream.processAnnotations(RuleSet.class);
			
			String rules_xml = xstream.toXML(this.rule_set);
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(RULES_PATH), StandardCharsets.UTF_8);
			out.write(rules_xml);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void savePredicates() {
		try {
			XStream xstream = new XStream(new DomDriver("UTF-8"));
			XStream.setupDefaultSecurity(xstream);
			xstream.allowTypes(new Class[] {PredicateSet.class, Predicate.class} );
			xstream.processAnnotations(PredicateSet.class);
			
			String predicates_xml = xstream.toXML(this.predicate_set);
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(PREDICATES_PATH), StandardCharsets.UTF_8);
			out.write(predicates_xml);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int process(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory, IProgressMonitor monitor) {
		this.bugs.clear();
		try {
			Theory code_theory = new Theory(code.toString());

			String rules_predicates = new String();

			List<Rule> rules = new Vector<Rule>();
			for (Rule rule: this.rule_set.getRules())
				if (rule.isActive()) {
					rules.add(rule);
					rules_predicates += rule.getPredicates() + System.lineSeparator();
				}
	
			String auxiliary_predicates = new String();
			for (Predicate predicate: this.predicate_set.getPredicates()) {
				auxiliary_predicates += predicate.getPredicates() + System.lineSeparator();
			}
			
			Theory rule_theory = new Theory(rules_predicates);
			Theory auxiliary_theory = new Theory(auxiliary_predicates);
			
			int cores = Runtime.getRuntime().availableProcessors();
			Vector<RuleRunnable> runnables = new Vector<RuleRunnable>();
			monitor.beginTask(MONITOR_TITLE_PRE, cores);

			for (int i = 0; i < cores; i++) {
				Prolog engine = new Prolog();
				engine.addTheory(rule_theory);
				engine.addTheory(auxiliary_theory);
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
					return -1;
				}
			}

			return 0;

		} catch (Exception e) {
			monitor.done();
			return -1;
		}

	}

	public String validateProlog(String code) {
		try {
			Prolog engine = new Prolog();
			engine.addTheory(new Theory(code));
			return null;
		} catch (InvalidTheoryException e) {
			return e.getMessage();
		}
	}


}
