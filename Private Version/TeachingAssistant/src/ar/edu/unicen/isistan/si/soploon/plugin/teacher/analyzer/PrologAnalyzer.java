package ar.edu.unicen.isistan.si.soploon.plugin.teacher.analyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Prolog;
import alice.tuprolog.Theory;
import ar.edu.unicen.isistan.si.soploon.plugin.Soploon;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.analyzer.bugs.Bug;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.PrologCode;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.converters.NodeConverterFactory;

public class PrologAnalyzer {

	private static final String MONITOR_TITLE = "Analyzing the Prolog representation";
	private static final String MONITOR_TITLE_PRE = "Creating threads for analysis";
	private static final String FOLDER_NAME = "config";
	private static final String RULES_FILE_NAME = "rules.xml";
	private static final String PREDICATES_FILE_NAME = "predicates.xml";

	private RuleSet rule_set;
	private PredicateSet predicate_set;
	private List<Bug> bugs;

	private String base_path;
	private String rules_path;
	private String predicates_path;

	public PrologAnalyzer() {
		IPath path = Soploon.getDefault().getStateLocation();
		this.base_path = path.toOSString() + File.separator + FOLDER_NAME;

		File root = new File(this.base_path);
		if (!root.exists())
			root.mkdirs();

		this.rules_path = this.base_path + File.separator + RULES_FILE_NAME;
		this.predicates_path = this.base_path + File.separator + PREDICATES_FILE_NAME;

		File rules = new File(rules_path);
		if (!rules.exists()) {
			this.rule_set = new RuleSet();
			this.saveRules();
		}

		File predicates = new File(predicates_path);
		if (!predicates.exists()) {
			this.predicate_set = new PredicateSet();
			this.savePredicates();
		}

		this.bugs = new ArrayList<Bug>();
		this.init();
	}

	public void init() {
		if (this.rule_set == null || this.predicate_set == null) {

			RuleSet aux_rules = this.readRules(this.rules_path);
			if (aux_rules != null)
				this.rule_set = aux_rules;

			PredicateSet aux_predicates = this.readPredicates(this.predicates_path);
			if (aux_predicates != null)
				this.predicate_set = aux_predicates;
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

	public RuleSet readRules(String path) {
		try {
			XStream xstream = new XStream(new DomDriver("UTF-8"));
			XStream.setupDefaultSecurity(xstream);
			xstream.allowTypes(new Class[] { RuleSet.class, Rule.class });
			xstream.processAnnotations(RuleSet.class);

			return (RuleSet) xstream.fromXML(new FileInputStream(path));
		} catch (Exception e) {
			return null;
		}
	}

	private PredicateSet readPredicates(String path) {
		try {
			XStream xstream = new XStream(new DomDriver("UTF-8"));
			XStream.setupDefaultSecurity(xstream);
			xstream.allowTypes(new Class[] { PredicateSet.class, Predicate.class });
			xstream.processAnnotations(PredicateSet.class);

			return (PredicateSet) xstream.fromXML(new FileInputStream(path));
		} catch (Exception e) {
			return null;
		}
	}

	public void saveRules() {
		try {
			XStream xstream = new XStream(new DomDriver("UTF-8"));
			XStream.setupDefaultSecurity(xstream);
			xstream.allowTypes(new Class[] { RuleSet.class, Rule.class });
			xstream.processAnnotations(RuleSet.class);

			String rules_xml = xstream.toXML(this.rule_set);
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(rules_path), StandardCharsets.UTF_8);
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
			xstream.allowTypes(new Class[] { PredicateSet.class, Predicate.class });
			xstream.processAnnotations(PredicateSet.class);

			String predicates_xml = xstream.toXML(this.predicate_set);
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(predicates_path),
					StandardCharsets.UTF_8);
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
			for (Rule rule : this.rule_set.getRules())
				if (rule.isActive()) {
					rules.add(rule);
					rules_predicates += rule.getPredicates() + System.lineSeparator();
				}

			String auxiliary_predicates = new String();
			for (Predicate predicate : this.predicate_set.getPredicates()) {
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

	public boolean setRules(String path) {
		RuleSet aux = this.readRules(path);
		if (aux != null) {
			this.rule_set = aux;
			this.saveRules();
			return true;
		}
		return false;
	}

	public void setRules(RuleSet rules_set) {
		this.rule_set = rules_set;
		this.saveRules();
	}
	
	public boolean setPredicates(String path) {
		PredicateSet aux = this.readPredicates(path);
		if (aux != null) {
			this.predicate_set = aux;
			this.savePredicates();
			return true;
		}
		return false;
	}

	public void setPredicates(PredicateSet predicate_set) {
		this.predicate_set = predicate_set;
		this.savePredicates();
	}

}
