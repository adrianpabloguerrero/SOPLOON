package soploon.views;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import soploon.Activator;
import soploon.analyzer.Predicate;
import soploon.analyzer.Rule;
import soploon.teacher.Teacher;

public class EditWindow {

	private static final int HEIGHT = 700;
	private static final int WIDTH = 1400;
	private static final String ERROR_INVALID_FILE = "Invalid file content";

	private Teacher teacher;
	private Composite parent;
	private StackLayout layout;
	private Display display;
	private Shell shell;

	private Rule selected_rule;
	private ScrolledComposite rules_left_scroll;
	private Composite rules_grid;
	private Text rule_type_text;
	private Text rule_description_text;
	private Text rule_query_text;
	private Text rule_predicates_text;

	private Predicate selected_predicate;
	private ScrolledComposite predicates_left_scroll;
	private Composite predicates_grid;
	private Text predicate_type_text;
	private Text predicate_description_text;
	private Text predicate_predicates_text;

	public EditWindow() {
		this.teacher = Teacher.getInstance();
		if (this.shell == null || shell.isDisposed()) {
			this.prepareShell();
			this.shell.open();
		}
	}

	private void prepareShell() {
		this.display = PlatformUI.getWorkbench().getDisplay();
		this.shell = new Shell(this.display, SWT.TITLE | SWT.MIN | SWT.CLOSE);
		this.shell.setSize(WIDTH, HEIGHT);

		Image image = Activator.getDefault().getImageRegistry().get(Activator.EDIT_ICON);
		this.shell.setImage(image);
		this.shell.setText("Soploon Editor");

		this.shell.setLayout(new GridLayout());

		this.parent = new Composite(this.shell, SWT.NONE);
		this.parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		this.layout = new StackLayout();
		this.parent.setLayout(this.layout);

		Composite rule_editor = this.prepareRuleEditor();
		Composite predicates_editor = this.preparePredicateEditor();
		this.layout.topControl = rule_editor;

		Menu menu = new Menu(this.shell, SWT.BAR);

		MenuItem rules = new MenuItem(menu, SWT.PUSH);
		rules.setText("Rules");
		rules.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				EditWindow.this.layout.topControl = rule_editor;
				parent.layout(true, true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		MenuItem rules_predicates = new MenuItem(menu, SWT.PUSH);
		rules_predicates.setText("Predicates");
		rules_predicates.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				EditWindow.this.layout.topControl = predicates_editor;
				parent.layout(true, true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		this.shell.setMenuBar(menu);
		this.parent.layout(true, true);
	}

	private Composite preparePredicateEditor() {
		Composite parent = new Composite(this.parent, SWT.NONE);
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		parent.setLayout(new GridLayout(2, false));

		Group left = new Group(parent, SWT.NONE);
		left.setText("Predicates Set");
		left.setLayout(new GridLayout(1, false));
		GridData left_data = new GridData(GridData.FILL_VERTICAL);
		left.setLayoutData(left_data);

		Group right = new Group(parent, SWT.NONE);
		right.setText("Edition");
		right.setLayout(new GridLayout(1, false));
		GridData second_data = new GridData(GridData.FILL_BOTH);
		right.setLayoutData(second_data);

		Label predicate_type_label = new Label(right, SWT.NONE);
		predicate_type_label.setText("Predicate Set name:");
		this.predicate_type_text = new Text(right, SWT.BORDER | SWT.MULTI);
		this.predicate_type_text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

		Label predicate_description_label = new Label(right, SWT.NONE);
		predicate_description_label.setText("Predicate Set description:");
		this.predicate_description_text = new Text(right, SWT.BORDER | SWT.MULTI);
		this.predicate_description_text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

		Label predicate_predicates_label = new Label(right, SWT.NONE);
		predicate_predicates_label.setText("Prolog code (predicates):");
		this.predicate_predicates_text = new Text(right, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		this.predicate_predicates_text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		this.predicates_left_scroll = new ScrolledComposite(left, SWT.V_SCROLL);
		this.predicates_left_scroll.setLayout(new GridLayout(1, false));
		this.predicates_left_scroll.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Button button_save = new Button(right, SWT.PUSH);
		button_save.setText("Save Predicate Set");
		button_save.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false));
		button_save.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String msg = EditWindow.this.teacher.getAnalyzer().validateProlog(EditWindow.this.predicate_predicates_text.getText());
				if (msg != null) {
					MessageDialog.openInformation(null, "Soploon","Error in Prolog code:" + System.lineSeparator() + msg);
				} else {
					EditWindow.this.selected_predicate.setTitle(EditWindow.this.predicate_type_text.getText());
					EditWindow.this.selected_predicate.setDescription(EditWindow.this.predicate_description_text.getText());
					EditWindow.this.selected_predicate.setPredicates(EditWindow.this.predicate_predicates_text.getText());
					if (!EditWindow.this.teacher.getAnalyzer().getPredicateSet().getPredicates().contains(EditWindow.this.selected_predicate)) {
						EditWindow.this.teacher.getAnalyzer().getPredicateSet().addPredicate(EditWindow.this.selected_predicate);
						EditWindow.this.addPredicate(EditWindow.this.selected_predicate);
						EditWindow.this.predicates_left_scroll.layout(true, true);
						EditWindow.this.predicates_left_scroll.setMinSize(EditWindow.this.predicates_grid.computeSize(SWT.DEFAULT, SWT.DEFAULT));
						EditWindow.this.predicates_left_scroll.setOrigin(0, Integer.MAX_VALUE);
					} else {
						Label label = (Label) EditWindow.this.predicates_grid.getChildren()[(EditWindow.this.teacher.getAnalyzer().getPredicateSet().getPredicates().indexOf(EditWindow.this.selected_predicate) + 1) * 3 + 1];
						label.setText(EditWindow.this.selected_predicate.getTitle());
						EditWindow.this.predicates_grid.layout(true, true);
					}
					EditWindow.this.teacher.getAnalyzer().savePredicates();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}

		});

		this.predicates_grid = new Composite(predicates_left_scroll, SWT.NONE);
		this.predicates_grid.setLayout(new GridLayout(3, false));
		this.predicates_grid.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));

		Label empty_label = new Label(this.predicates_grid, SWT.CENTER);
		empty_label.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, true, true, 1, 1));

		Label predicate_label = new Label(this.predicates_grid, SWT.CENTER);
		predicate_label.setText("Predicate Set");
		predicate_label.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, true, true, 1, 1));

		Label empty_label_2 = new Label(this.predicates_grid, SWT.NONE);
		empty_label_2.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, true, true, 1, 1));

		Composite group = new Composite(left, SWT.NONE);
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		group.setLayout(new GridLayout(2, false));

		Button button_load_predicates = new Button(group, SWT.PUSH);
		button_load_predicates.setText("Load Predicates");
		button_load_predicates.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true));
		button_load_predicates.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shell, SWT.OPEN);
				dialog.setFilterExtensions(new String[] { "*.xml" });
				String selected_path = dialog.open();
				if (selected_path != null) {
					if (teacher.getAnalyzer().setPredicates(selected_path))
						loadPredicates();
					else
						MessageDialog.openInformation(null, "Soploon", ERROR_INVALID_FILE);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}

		});

		Button button_new_predicate = new Button(group, SWT.PUSH);
		button_new_predicate.setText("New Predicate Set");
		button_new_predicate.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		button_new_predicate.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Predicate predicate = new Predicate();
				predicate.setTitle("New Predicate Set");
				predicate.setDescription("Please write a description for this set of predicates");
				predicate.setPredicates("/* this is a comment */" + System.lineSeparator() + System.lineSeparator() + "new_predicate(PARAM) :- fail. " + System.lineSeparator() + "new_predicate_2(PARAM) :- fail. " + System.lineSeparator() + System.lineSeparator() + "/* here you can define as many predicates as you want */");
				selectPredicate(predicate);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}

		});

		this.loadPredicates();

		return parent;
	}

	private Composite prepareRuleEditor() {

		Composite parent = new Composite(this.parent, SWT.NONE);
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		parent.setLayout(new GridLayout(2, false));

		Group left = new Group(parent, SWT.NONE);
		left.setText("Rules");
		left.setLayout(new GridLayout(1, false));
		GridData left_data = new GridData(GridData.FILL_VERTICAL);
		left.setLayoutData(left_data);

		Group right = new Group(parent, SWT.NONE);
		right.setText("Edition");
		right.setLayout(new GridLayout(1, false));
		GridData secondData = new GridData(GridData.FILL_BOTH);
		right.setLayoutData(secondData);

		Label rule_type_label = new Label(right, SWT.NONE);
		rule_type_label.setText("Rule name:");
		this.rule_type_text = new Text(right, SWT.BORDER | SWT.MULTI);
		this.rule_type_text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

		Label rule_description_label = new Label(right, SWT.NONE);
		rule_description_label.setText("Rule description:");
		this.rule_description_text = new Text(right, SWT.BORDER | SWT.MULTI);
		this.rule_description_text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

		Label rule_query_label = new Label(right, SWT.NONE);
		rule_query_label.setText("Predicate for query:");
		this.rule_query_text = new Text(right, SWT.BORDER | SWT.MULTI);
		this.rule_query_text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

		Label rule_predicates_label = new Label(right, SWT.NONE);
		rule_predicates_label.setText("Prolog code:");
		this.rule_predicates_text = new Text(right, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		this.rule_predicates_text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		this.rules_left_scroll = new ScrolledComposite(left, SWT.V_SCROLL);
		this.rules_left_scroll.setLayout(new GridLayout(1, false));
		this.rules_left_scroll.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Button button_save = new Button(right, SWT.PUSH);
		button_save.setText("Save Rule");
		button_save.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false));
		button_save.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String msg = EditWindow.this.teacher.getAnalyzer().validateProlog(EditWindow.this.rule_predicates_text.getText());
				if (msg != null) {
					MessageDialog.openInformation(null, "Soploon", "Error in Prolog code:" + System.lineSeparator() + msg);
				} else {
					EditWindow.this.selected_rule.setType(EditWindow.this.rule_type_text.getText());
					EditWindow.this.selected_rule.setQuery(EditWindow.this.rule_query_text.getText());
					EditWindow.this.selected_rule.setDescription(EditWindow.this.rule_description_text.getText());
					EditWindow.this.selected_rule.setPredicates(EditWindow.this.rule_predicates_text.getText());
					if (!EditWindow.this.teacher.getAnalyzer().getRuleSet().getRules().contains(EditWindow.this.selected_rule)) {
						EditWindow.this.teacher.getAnalyzer().getRuleSet().addRule(EditWindow.this.selected_rule);
						EditWindow.this.addRule(EditWindow.this.selected_rule);
						EditWindow.this.rules_left_scroll.layout(true, true);
						EditWindow.this.rules_left_scroll.setMinSize(EditWindow.this.rules_grid.computeSize(SWT.DEFAULT, SWT.DEFAULT));
						EditWindow.this.rules_left_scroll.setOrigin(0, Integer.MAX_VALUE);
					} else {
						Label label = (Label) EditWindow.this.rules_grid.getChildren()[(EditWindow.this.teacher.getAnalyzer().getRuleSet().getRules().indexOf(EditWindow.this.selected_rule) + 1) * 3 + 1];
						label.setText(EditWindow.this.selected_rule.getType());
						EditWindow.this.rules_grid.layout(true, true);
					}
					EditWindow.this.teacher.getAnalyzer().saveRules();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}

		});

		this.rules_grid = new Composite(rules_left_scroll, SWT.NONE);
		this.rules_grid.setLayout(new GridLayout(3, false));
		this.rules_grid.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		
		Label active_label = new Label(this.rules_grid, SWT.CENTER);
		active_label.setText("Active");
		active_label.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, true, true, 1, 1));
		
		Label rule_label = new Label(this.rules_grid, SWT.CENTER);
		rule_label.setText("Rule");
		rule_label.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, true, true, 1, 1));

		Label empty_label = new Label(this.rules_grid, SWT.NONE);
		empty_label.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, true, true, 1, 1));

		Composite group = new Composite(left, SWT.NONE);
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		group.setLayout(new GridLayout(2, false));

		Button button_load_rules = new Button(group, SWT.PUSH);
		button_load_rules.setText("Load Rules");
		button_load_rules.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true));
		button_load_rules.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shell, SWT.OPEN);
				dialog.setFilterExtensions(new String[] { "*.xml" });
				String selected_path = dialog.open();
				if (selected_path != null) {
					if (teacher.getAnalyzer().setRules(selected_path))
						loadRules();
					else
						MessageDialog.openInformation(null, "Soploon", ERROR_INVALID_FILE);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}

		});

		Button button_new_rule = new Button(group, SWT.PUSH);
		button_new_rule.setText("New Rule");
		button_new_rule.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, true));
		button_new_rule.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Rule rule = new Rule();
				rule.setType("New rule");
				rule.setDescription("Please write a description for this rule");
				rule.setQuery("new_rule");
				rule.setPredicates("/* this is a comment */" + System.lineSeparator() + System.lineSeparator() + "new_rule(ID) :- fail. ");
				rule.setActive(true);
				selectRule(rule);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}

		});

		this.loadRules();

		return parent;
	}

	protected void addPredicate(Predicate predicate) {
		Button button_delete = new Button(this.predicates_grid, SWT.PUSH);
		button_delete.setText("Delete");
		button_delete.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));

		Label label = new Label(this.predicates_grid, SWT.LEFT);
		label.setText(predicate.getTitle());
		label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));

		Button button_edit = new Button(this.predicates_grid, SWT.PUSH);
		button_edit.setText("Edit");
		button_edit.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		button_edit.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				selectPredicate(predicate);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

		});

		button_delete.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				button_delete.dispose();
				label.dispose();
				button_edit.dispose();
				teacher.getAnalyzer().getPredicateSet().removePredicate(predicate);
				teacher.getAnalyzer().savePredicates();
				
				predicates_left_scroll.setContent(predicates_grid);
				predicates_left_scroll.setExpandHorizontal(true);
				predicates_left_scroll.setExpandVertical(true);
				predicates_left_scroll.setMinSize(predicates_grid.computeSize(SWT.DEFAULT, SWT.DEFAULT));

				EditWindow.this.parent.layout(true, true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

		});

		this.predicates_left_scroll.setContent(predicates_grid);
		this.predicates_left_scroll.setExpandHorizontal(true);
		this.predicates_left_scroll.setExpandVertical(true);
		this.predicates_left_scroll.setMinSize(predicates_grid.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		this.parent.layout(true, true);
	}

	private void addRule(Rule rule) {
		Button button_check = new Button(this.rules_grid, SWT.CHECK);
		button_check.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		button_check.setSelection(rule.isActive());
		button_check.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Button btn = (Button) e.getSource();
				rule.setActive(btn.getSelection());
				teacher.getAnalyzer().saveRules();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

		});

		Label label = new Label(this.rules_grid, SWT.LEFT);
		label.setText(rule.getType());
		label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));

		Button button_edit = new Button(this.rules_grid, SWT.PUSH);
		button_edit.setText("Edit");
		button_edit.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		button_edit.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				selectRule(rule);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

		});
		
		this.rules_left_scroll.setContent(this.rules_grid);
		this.rules_left_scroll.setExpandHorizontal(true);
		this.rules_left_scroll.setExpandVertical(true);
		this.rules_left_scroll.setMinSize(this.rules_grid.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		this.parent.layout(true, true);
	}

	private void loadRules() {
		this.rule_type_text.setText("");
		this.rule_description_text.setText("");
		this.rule_query_text.setText("");
		this.rule_predicates_text.setText("");
		this.selected_rule = null;

		Control[] children = this.rules_grid.getChildren();
		for (int i = 3; i < children.length; i++) {
			children[i].dispose();
		}

		for (Rule rule : this.teacher.getAnalyzer().getRuleSet().getRules()) {
			this.addRule(rule);
		}

		this.rules_left_scroll.setContent(this.rules_grid);
		this.rules_left_scroll.setExpandHorizontal(true);
		this.rules_left_scroll.setExpandVertical(true);
		this.rules_left_scroll.setMinSize(this.rules_grid.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		this.parent.layout(true, true);
	}

	private void loadPredicates() {
		this.predicate_type_text.setText("");
		this.predicate_description_text.setText("");
		this.predicate_predicates_text.setText("");
		this.selected_predicate = null;

		Control[] children = this.predicates_grid.getChildren();
		for (int i = 3; i < children.length; i++) {
			children[i].dispose();
		}

		for (Predicate predicate : this.teacher.getAnalyzer().getPredicateSet().getPredicates()) {
			this.addPredicate(predicate);
		}

		this.predicates_left_scroll.setContent(predicates_grid);
		this.predicates_left_scroll.setExpandHorizontal(true);
		this.predicates_left_scroll.setExpandVertical(true);
		this.predicates_left_scroll.setMinSize(predicates_grid.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		this.parent.layout(true, true);
	}

	private void selectRule(Rule rule) {
		this.rule_type_text.setText(rule.getType());
		this.rule_description_text.setText(rule.getDescription());
		this.rule_query_text.setText(rule.getQuery());
		this.rule_predicates_text.setText(rule.getPredicates());
		this.selected_rule = rule;
	}

	private void selectPredicate(Predicate predicate) {
		this.predicate_type_text.setText(predicate.getTitle());
		this.predicate_description_text.setText(predicate.getDescription());
		this.predicate_predicates_text.setText(predicate.getPredicates());
		this.selected_predicate = predicate;
	}

}
