
package teachingassistant.modeler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

public class PrologCode {
   
	private HashMap<Long,String> facts_map;
	private Vector<String> facts;
	private Mapper mapper;
	
    public PrologCode() {
        this.facts = new Vector<String>();
        this.facts_map = new HashMap<Long,String>();
        this.mapper = new Mapper();
    }
   
    public void addFact(String functor, String[] param) { 
    	String fact = this.term(functor, param);
    	this.facts_map.put(Long.parseLong(param[0]), fact);
    	this.facts.add(fact);
    }

    private String term(String functor, String[] args) {
    	String sb = "";
        sb += functor;
        sb += "(";
        int i = 0;
        while (i < args.length) {
            if (i > 0) {
                sb += " , ";
            }
            sb += args[i];
            ++i;
        }
        sb += ").";
        return sb;
    }

    public ArrayList<String> getFacts() {
    	ArrayList<Long> keys = new ArrayList<Long>(this.facts_map.keySet());
    	Collections.sort(keys);
    	ArrayList<String> out = new ArrayList<String>();
    	for (Long key: keys)
    		out.add(this.facts_map.get(key));
    	return out;
    }
    
    public String getFact(int index) {
    	if (index < 0 || index >= this.facts.size())
    		return null;
    	return this.facts.get(index);
    }

    public String toString() {
    	StringBuffer out = new StringBuffer();
    	for (String fact : this.facts) {
    		out.append(fact);
    		out.append(System.lineSeparator());
    	}
    	return out.toString();
    }
    
	public Mapper getMapper() {
		return mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}
    
}

