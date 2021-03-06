
package ar.edu.unicen.isistan.si.soploon.plugin.teacher.modeler;

import java.util.ArrayList;

public class PrologCode {
  	
	private ArrayList<String> facts;
	private Mapper mapper;
	
    public PrologCode() {
        this.facts = new ArrayList<String>();
        this.mapper = new Mapper();
    }
   
    public void addFact(String functor, String[] param) {   
    	String fact = this.term(functor, param);
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
    	return new ArrayList<String>(this.facts);
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

