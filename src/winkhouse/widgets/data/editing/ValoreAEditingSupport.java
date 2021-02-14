package winkhouse.widgets.data.editing;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;

import winkhouse.widgets.data.ICriteriaTableModel;
import winkhouse.widgets.data.IPropertiesFieldDescriptor;
import winkhouse.widgets.data.InnerTextDaAValueObject;
import winkhouse.widgets.data.editing.providers.IDaAValueObject;
import winkhouse.widgets.data.factory.DaACanEditCellHandlerFactory;
import winkhouse.widgets.data.factory.DaACellEditorFactory;
import winkhouse.widgets.data.factory.DaAValueProviderFactory;

public class ValoreAEditingSupport extends EditingSupport {

	private Integer criteriOwnerID = null;
	private DaACellEditorFactory daACellEditorFactory = null;
	private DaACanEditCellHandlerFactory daACanEditCellHandlerFactory = null;
	private DaAValueProviderFactory daAValueProviderFactory = null;
	
	public ValoreAEditingSupport(ColumnViewer viewer, 
								 Integer criteriOwnerID,
								 DaACellEditorFactory daACellEditorFactory,
								 DaACanEditCellHandlerFactory daACanEditCellHandlerFactory,
								 DaAValueProviderFactory daAValueProviderFactory) {
		super(viewer);
		this.criteriOwnerID = criteriOwnerID;
		this.daACellEditorFactory = daACellEditorFactory;
		this.daACanEditCellHandlerFactory = daACanEditCellHandlerFactory;
		this.daAValueProviderFactory = daAValueProviderFactory;
	}

	@Override
	protected boolean canEdit(Object arg0) {
		if (daACanEditCellHandlerFactory.getDaHandler() != null){
			return daACanEditCellHandlerFactory.getaHandler()
											   .canEdit((ICriteriaTableModel)arg0);
		}else{
			return false;
		}

	}

	@Override
	protected CellEditor getCellEditor(Object arg0) {
		
		CellEditor returnValue = null;	
		returnValue = (CellEditor)daACellEditorFactory.provideData(criteriOwnerID,(ICriteriaTableModel)arg0);
												
		return returnValue;
	}

	@Override
	protected Object getValue(Object arg0) {
		if (getCellEditor(arg0) instanceof ComboBoxViewerCellEditor){
			return this.daAValueProviderFactory.provideData(criteriOwnerID,
															(ICriteriaTableModel)arg0)
											   .getDaAValueObject((ICriteriaTableModel)arg0);
		}else{
			IDaAValueObject davo = this.daAValueProviderFactory.provideData(criteriOwnerID,(ICriteriaTableModel)arg0)
														  	   .getDaAValueObject((ICriteriaTableModel)arg0);
			if (davo != null){
				return davo.getDisplayValue();
			}else{
				return "";
			}
			
		}
	}

	@Override
	protected void setValue(Object arg0, Object arg1) {
		if (arg1 != null){
			
			if (arg1 instanceof IDaAValueObject){
				((ICriteriaTableModel)arg0).setAValue((IDaAValueObject)arg1);
				((TableViewer)this.getViewer()).refresh();
				
			}else if (arg1 instanceof String){
				InnerTextDaAValueObject itdavo = new InnerTextDaAValueObject((String)arg1);
				((ICriteriaTableModel)arg0).setAValue(itdavo);
				((TableViewer)this.getViewer()).refresh();
			}
		}
	}

	public Integer getCriteriOwnerID() {
		return criteriOwnerID;
	}

	public void setCriteriOwnerID(Integer criteriOwnerID) {
		this.criteriOwnerID = criteriOwnerID;
	}

	public DaACellEditorFactory getDaACellEditorFactory() {
		return daACellEditorFactory;
	}

	public void setDaACellEditorFactory(DaACellEditorFactory daACellEditorFactory) {
		this.daACellEditorFactory = daACellEditorFactory;
	}

	public DaACanEditCellHandlerFactory getDaACanEditCellHandlerFactory() {
		return daACanEditCellHandlerFactory;
	}

	public void setDaACanEditCellHandlerFactory(
			DaACanEditCellHandlerFactory daACanEditCellHandlerFactory) {
		this.daACanEditCellHandlerFactory = daACanEditCellHandlerFactory;
	}

}
