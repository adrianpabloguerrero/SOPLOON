package ar.edu.unicen.isistan.si.soploon.plugin.teacher.analyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.IProgressMonitor;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Prolog;
import alice.tuprolog.Theory;
import ar.edu.unicen.isistan.si.soploon.plugin.storage.Configuration;
import ar.edu.unicen.isistan.si.soploon.plugin.storage.StorageManager;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.analyzer.bugs.Bug;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.Mapper;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.PrologCode;
import ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler.converters.NodeConverterFactory;
import ar.edu.unicen.isistan.si.soploon.server.models.Predicate;
import ar.edu.unicen.isistan.si.soploon.server.models.Rule;
import ar.edu.unicen.isistan.si.soploon.server.models.Error;

public class PrologAnalyzer {

	private static final String MONITOR_TITLE = "Analyzing the Prolog representation";
	private static final String MONITOR_TITLE_PRE = "Creating threads for analysis";

	private List<PrologRule> rules;
	private List<PrologPredicate> predicates;
	private List<Bug> bugs;

	public PrologAnalyzer() {
		this.bugs = new ArrayList<Bug>();
		this.rules = new ArrayList<PrologRule>();
		this.predicates = new ArrayList<PrologPredicate>();
	}
	
	public List<Bug> getBugs() {
		return this.bugs;
	}

	public int process(Mapper mapper, PrologCode code, NodeConverterFactory converter_factory, IProgressMonitor monitor) {
		this.bugs.clear();
		this.prepare();
		
		try {
			Theory code_theory = new Theory(code.toString());

			String rules_predicates = new String();

			List<PrologRule> rules = new Vector<PrologRule>();
			for (PrologRule rule : this.rules)
				if (rule.isActivated()) {
					rules.add(rule);
					rules_predicates += rule.getPredicates() + System.lineSeparator();
				}

			String auxiliary_predicates = new String();
			
			for (PrologPredicate predicate : this.predicates)
				auxiliary_predicates += predicate.getCode() + System.lineSeparator();

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

			monitor.beginTask(MONITOR_TITLE, this.rules.size());

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
			e.printStackTrace();
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

	public ArrayList<Error> toErrors() {
		ArrayList<Error> errors = new ArrayList<Error>();
		for (Bug bug: this.bugs)
			errors.add(bug.toError());
		return errors;
	}
	
	private void prepare() {
		Configuration configuration = StorageManager.getInstance().getConfiguration();
		
		this.rules.clear();
		for (Rule rule: configuration.getRules())
			this.rules.add(new PrologRule(rule));
		
		this.predicates.clear();
		for (Predicate predicate: configuration.getPredicates()) {
			this.predicates.add(new PrologPredicate(predicate));
		}	
	}

}
