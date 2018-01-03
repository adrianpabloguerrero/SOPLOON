package teachingassistant.views;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import teachingassistant.analyzer.bugs.Bug;
import teachingassistant.analyzer.bugs.BuggedCode;

public class CorrectionsView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "TeachingAssistant.corrections";

	private TreeViewer viewer;
	private List<TreeViewerColumn> columns;

	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		createColumns(viewer);

		final Tree tree = viewer.getTree();
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "plugin.viewer");
		getSite().setSelectionProvider(viewer);

		viewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection thisSelection = (IStructuredSelection) event.getSelection();
				Object selectedNode = thisSelection.getFirstElement();
				if (selectedNode == null)
					return;
				if (selectedNode instanceof Bug)
					((Bug) selectedNode).open();
				else if (selectedNode instanceof BuggedCode)
					((BuggedCode) selectedNode).open();
			}
		});

		viewer.setContentProvider(new ITreeContentProvider() {

			@SuppressWarnings("unchecked")
			@Override
			public Object[] getElements(Object inputElement) {
				return ((List<Bug>) inputElement).toArray();
			}

			@Override
			public Object[] getChildren(Object parentElement) {
				if (parentElement instanceof Bug) {
					Bug bug = (Bug) parentElement;
					if (!bug.isSimple())
						return bug.getBuggedCodes().toArray();
				}
				return null;
			}

			@Override
			public Object getParent(Object element) {
				if (element instanceof BuggedCode)
					return ((BuggedCode) element).getBug();
				return null;
			}

			@Override
			public boolean hasChildren(Object element) {
				return (this.getChildren(element) != null);
			}

			@Override
			public void dispose() {

			}

			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

			}

		});

	}

	private void createColumns(TreeViewer viewer) {

		this.columns = new Vector<TreeViewerColumn>();

		TreeViewerColumn col = new TreeViewerColumn(viewer, SWT.NONE);
		col.getColumn().setText("Detection");
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof Bug)
					return ((Bug) element).getType();
				return "";
			}
		});
		this.columns.add(col);

		col = new TreeViewerColumn(viewer, SWT.NONE);
		col.getColumn().setText("File");
		col.getColumn().setAlignment(SWT.CENTER);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof Bug) {
					Bug bug = (Bug) element;
					if (bug.isSimple())
						return bug.getFile();
				} else if (element instanceof BuggedCode) {
					return ((BuggedCode) element).getFile();
				}
				return "";
			}
		});
		this.columns.add(col);

		col = new TreeViewerColumn(viewer, SWT.NONE);
		col.getColumn().setText("Line");
		col.getColumn().setAlignment(SWT.CENTER);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof Bug) {
					Bug bug = (Bug) element;
					if (bug.isSimple())
						return bug.getLineNumber();
				} else if (element instanceof BuggedCode) {
					return ((BuggedCode) element).getLineNumber();
				}
				return "";
			}
		});
		this.columns.add(col);

		col = new TreeViewerColumn(viewer, SWT.NONE);
		col.getColumn().setText("Code");
		col.getColumn().setAlignment(SWT.CENTER);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof Bug) {
					Bug bug = (Bug) element;
					if (bug.isSimple())
						return bug.getCode();
				} else if (element instanceof BuggedCode) {
					return ((BuggedCode) element).getCode();
				}
				return "";
			}
		});
		this.columns.add(col);

		col = new TreeViewerColumn(viewer, SWT.NONE);
		col.getColumn().setWidth(800);
		col.getColumn().setText("Description");
		col.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if (element instanceof Bug)
					return ((Bug) element).getDescription();
				return "";
			}
		});
		this.columns.add(col);

	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		this.viewer.getControl().setFocus();
	}

	public void setBugs(List<Bug> bugs) {
		Collections.sort(bugs);
		this.viewer.setInput(bugs);
		this.viewer.refresh();
		for (TreeViewerColumn column : this.columns) {
			column.getColumn().pack();
		}
		TreeViewerColumn codecol = this.columns.get(3);
		if (codecol.getColumn().getWidth() > 350) {
			codecol.getColumn().setWidth(350);
		}
	}

}
