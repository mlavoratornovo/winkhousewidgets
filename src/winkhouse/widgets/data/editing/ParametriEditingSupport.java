package winkhouse.widgets.data.editing;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;

import winkhouse.widgets.data.ICriteriaTableModel;
import winkhouse.widgets.data.IPropertiesFieldDescriptor;
import winkhouse.widgets.data.editing.providers.content.ParametriContentProvider;
import winkhouse.widgets.data.editing.providers.label.ParametriLabelProvider;
import winkhouse.widgets.data.factory.ObjectPropertiesFactory;

public class ParametriEditingSupport extends EditingSupport {
	
	private ComboBoxViewerCellEditor cbvceParametri = null;
	private Integer criteriOwnerID = null;
	private ObjectPropertiesFactory opf = null;
	
	public ParametriEditingSupport(ColumnViewer viewer, Integer criteriOwnerID, ObjectPropertiesFactory opf) {
		super(viewer);
		this.criteriOwnerID = criteriOwnerID;
		this.opf = opf;
	}

	@Override
	protected boolean canEdit(Object arg0) {
		return true;
	}

	@Override
	protected CellEditor getCellEditor(Object arg0) {
		if (cbvceParametri == null){
			cbvceParametri = new ComboBoxViewerCellEditor(((TableViewer)this.getViewer()).getTable(), 
														  SWT.READ_ONLY|SWT.DROP_DOWN);
			cbvceParametri.setContentProvider(new ParametriContentProvider(this.criteriOwnerID));
			cbvceParametri.setLabelProvider(new ParametriLabelProvider());
			cbvceParametri.setInput(opf.getObjectProperties(this.criteriOwnerID));
		}
		return cbvceParametri;
	}

	@Override
	protected Object getValue(Object arg0) {
		return ((ICriteriaTableModel)arg0).getPropertiesFieldDescriptior();
	}

	@Override
	protected void setValue(Object arg0, Object arg1) {
		if (arg1 != null){
			((ICriteriaTableModel)arg0).setPropertiesFieldDescriptior((IPropertiesFieldDescriptor)arg1);
			((TableViewer)this.getViewer()).refresh();
		}

	}

}
