package soploon.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import soploon.views.EditWindow;

public class EditHandler extends AbstractHandler {


	public EditHandler() {
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		new EditWindow();
		return null;
	}

}
