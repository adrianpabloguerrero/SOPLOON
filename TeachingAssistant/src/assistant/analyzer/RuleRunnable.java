package assistant.analyzer;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import alice.tuprolog.Prolog;
import assistant.analyzer.bugs.Bug;
import assistant.modeler.Mapper;
import assistant.modeler.converters.NodeConverterFactory;

public class RuleRunnable implements Runnable {

	IProgressMonitor monitor;
	Prolog engine;
	Mapper mapper;
	NodeConverterFactory converter_factory;
	List<Rule> rules;
	List<Bug> bugs;
	int total_rules;
	
	public RuleRunnable(IProgressMonitor monitor, Prolog engine, Mapper mapper, NodeConverterFactory converter_factory, List<Rule> rules, List<Bug> bugs) {
		this.monitor = monitor;
		this.engine = engine;
		this.mapper = mapper;
		this.rules = rules;
		this.bugs = bugs;
		this.converter_factory = converter_factory;
		this.total_rules = rules.size();
	}
	
	
	@Override
	public void run() {
		Rule rule = null;
		
		synchronized(rules) {
			if (!rules.isEmpty())
				rule = rules.remove(0);
		}
		
		while (rule != null) {
			List<Bug> bugs = rule.execute(engine, mapper, converter_factory);
			if (bugs != null) {
				synchronized(this.bugs) {
					this.bugs.addAll(bugs);
				}
			}
			
			if (monitor.isCanceled())
				break;
			
			synchronized(rules) {
				rule = null;
				if (!rules.isEmpty()) {
					monitor.subTask("Progreso: " + (100 - (( rules.size()*100)/total_rules)) + "%");
					rule = rules.remove(0);
				}
				monitor.worked(1);
			}
		}		

	}

}
