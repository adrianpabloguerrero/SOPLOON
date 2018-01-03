package teachingassistant.views;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class TranslationView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "TeachingAssistant.translation";

	Text text;

	public TranslationView() {
		this.text = null;
	}

	public void createPartControl(Composite parent) {
		this.text = new Text(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
	}

	public void setContent(int ast_nodes, ArrayList<String> facts)  {
		String text = "Analyzed AST nodes: " + ast_nodes + System.lineSeparator();
		text += "Generated Prolog facts: " + facts.size() + System.lineSeparator();
		for (String fact: facts)
			text += System.lineSeparator() + fact;
		this.text.setText(text);
	}
	
	public void setFocus() {

	}
}
