package ar.edu.unicen.isistan.si.teachingassistant.plugin.teacher.parser;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;

public class Visitor extends ASTVisitor {

	private int count;
	
	public Visitor() {
		count = 0;
	}
	
	public void preVisit(ASTNode node) {
		count++;
	}
	
	public int get() {
		return count;
	}
}
