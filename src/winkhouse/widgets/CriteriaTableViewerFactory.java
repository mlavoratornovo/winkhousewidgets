package winkhouse.widgets;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

import winkhouse.widgets.data.ICriteriaTableModel;
import winkhouse.widgets.data.editing.ParametriEditingSupport;
import winkhouse.widgets.data.editing.ValoreAEditingSupport;
import winkhouse.widgets.data.editing.ValoreDaEditingSupport;
import winkhouse.widgets.data.factory.DaACanEditCellHandlerFactory;
import winkhouse.widgets.data.factory.DaACellEditorFactory;
import winkhouse.widgets.data.factory.DaAValueProviderFactory;
import winkhouse.widgets.data.factory.ObjectPropertiesFactory;

public class CriteriaTableViewerFactory{
	
	private int typeCriteria = 0;
	private TableViewer tableCriteri = null;
	private DaACellEditorFactory daCellEditorFactory = null;
	private DaACellEditorFactory aCellEditorFactory = null;
	private DaACanEditCellHandlerFactory daACanEditCellHandlerFactory = null;
	private ObjectPropertiesFactory objectPropertiesFactory = null;
	private DaAValueProviderFactory daValueProviderFactory = null;
	private DaAValueProviderFactory aValueProviderFactory = null;
	/*
	private CellLabelProvider parametroLabelProvider = null;
	private CellLabelProvider daValueLabelProvider = null;
	private CellLabelProvider aValueLabelProvider = null;
	*/
	public CriteriaTableViewerFactory(int typeCriteria){
		this.typeCriteria = typeCriteria;
	}
	
	public TableViewer getTableViewer(Composite parent,int style){
		if (tableCriteri == null){
			tableCriteri = new TableViewer(parent,style);	
		}
		return tableCriteri;
	}
	
	public TableViewer getCriteriaTable(Composite parent, int style){
		
		getTableViewer(parent, style);
		
		TableColumn tcParametro = new TableColumn(tableCriteri.getTable(),SWT.CENTER,0);
		tcParametro.setWidth(100);
		tcParametro.setText("Parametro");
		
		TableViewerColumn tvcParametro = new TableViewerColumn(tableCriteri,tcParametro);
		tvcParametro.setEditingSupport(new ParametriEditingSupport(tableCriteri,typeCriteria,objectPropertiesFactory));
		tvcParametro.setLabelProvider(new CellLabelProvider() {
			
			@Override
			public void update(ViewerCell cell) {
				if (((ICriteriaTableModel)cell.getElement()).getPropertiesFieldDescriptior() != null){
					cell.setText(
							String.valueOf(((ICriteriaTableModel)cell.getElement()).getPropertiesFieldDescriptior()
																				   .getDescrizione())
								);
				}else{
					cell.setText("");
				}
			}
		});

		TableColumn tcValoreDA = new TableColumn(tableCriteri.getTable(),SWT.CENTER,1);
		tcValoreDA.setWidth(100);
		tcValoreDA.setText("Da");
		
		TableViewerColumn tvcValoreDA = new TableViewerColumn(tableCriteri,tcValoreDA);
		tvcValoreDA.setEditingSupport(new ValoreDaEditingSupport(tableCriteri,
																 typeCriteria,
																 daCellEditorFactory,
																 daACanEditCellHandlerFactory,
																 daValueProviderFactory));
		tvcValoreDA.setLabelProvider(new CellLabelProvider() {
			
			@Override
			public void update(ViewerCell cell) {
				if (((ICriteriaTableModel)cell.getElement()).getDaValue() != null){
					cell.setText(
							String.valueOf(((ICriteriaTableModel)cell.getElement()).getDaValue()
																				   .getDisplayValue())
								);
				}else{
					cell.setText("");
				}
			}
		});


		TableColumn tcValoreA = new TableColumn(tableCriteri.getTable(),SWT.CENTER,2);
		tcValoreA.setWidth(100);
		tcValoreA.setText("A");
		
		TableViewerColumn tvcValoreA = new TableViewerColumn(tableCriteri,tcValoreA);
		tvcValoreA.setEditingSupport(new ValoreAEditingSupport(tableCriteri,
				 											   typeCriteria,
				 											   aCellEditorFactory,
				 											   daACanEditCellHandlerFactory,
				 											   aValueProviderFactory));
		tvcValoreA.setLabelProvider(new CellLabelProvider() {
			
			@Override
			public void update(ViewerCell cell) {
				if (((ICriteriaTableModel)cell.getElement()).getAValue() != null){
					cell.setText(
							String.valueOf(((ICriteriaTableModel)cell.getElement()).getAValue()
																				   .getDisplayValue())
								);
				}else{
					cell.setText("");
				}
			}
		});


		TableColumn tcOpLogico = new TableColumn(tableCriteri.getTable(),SWT.CENTER,3);
		tcOpLogico.setWidth(80);
		tcOpLogico.setText("Op. Logico");
		
		TableViewerColumn tvcOpLogico = new TableViewerColumn(tableCriteri,tcOpLogico);
		tvcOpLogico.setLabelProvider(new CellLabelProvider(){

			@Override
			public void update(ViewerCell cell) {
				
				if (((ICriteriaTableModel)cell.getElement()).getLogicalOperator() != null){
					cell.setText(
							String.valueOf(((ICriteriaTableModel)cell.getElement()).getLogicalOperator())
								);
				}else{
					cell.setText("");
				}
			}
			
		});
		tvcOpLogico.setEditingSupport(new EditingSupport(tableCriteri){

			@Override
			protected boolean canEdit(Object element) {
				return true;
			}

			@Override
			protected CellEditor getCellEditor(Object element) {
				CellEditor returnValue = null;
				returnValue = new ComboBoxCellEditor(tableCriteri.getTable(),
													 new String[]{"","AND","OR"},
													 SWT.READ_ONLY|SWT.DROP_DOWN); 

				return returnValue;
			}

			@Override
			protected Object getValue(Object element) {				
				if ((((ICriteriaTableModel)element).getLogicalOperator() != null) && 
					(!((ICriteriaTableModel)element).getLogicalOperator().equalsIgnoreCase(""))){
					return (((ICriteriaTableModel)element).getLogicalOperator().equalsIgnoreCase("AND"))?1:2;
				}else{
					return 0;
				}							
			}

			@Override
			protected void setValue(Object element, Object value) {
				if (!((ICriteriaTableModel)element).getPropertiesFieldDescriptior().getMethodName().equalsIgnoreCase("(")){
					((ICriteriaTableModel)element).setLogicalOperator(
							(((Integer)value).intValue() == 0)
							 ? ""
							 :(((Integer)value).intValue() == 1)
							  ? "AND" 
							  : "OR" 
																	);					
				}
				tableCriteri.refresh();
			}
			
		});
		
		return tableCriteri;

		
	}

	public DaACellEditorFactory getDaCellEditorFactory() {
		return daCellEditorFactory;
	}

	public DaACellEditorFactory getACellEditorFactory() {
		return aCellEditorFactory;
	}
	
	public void setDaCellEditorFactory(DaACellEditorFactory daCellEditorFactory) {
		this.daCellEditorFactory = daCellEditorFactory;
	}
	
	public void setACellEditorFactory(DaACellEditorFactory aCellEditorFactory) {
		this.aCellEditorFactory = aCellEditorFactory;
	}
	

	public DaACanEditCellHandlerFactory getDaACanEditCellHandlerFactory() {
		return daACanEditCellHandlerFactory;
	}

	public void setDaACanEditCellHandlerFactory(
			DaACanEditCellHandlerFactory daACanEditCellHandlerFactory) {
		this.daACanEditCellHandlerFactory = daACanEditCellHandlerFactory;
	}

	public ObjectPropertiesFactory getObjectPropertiesFactory() {
		return objectPropertiesFactory;
	}

	public void setObjectPropertiesFactory(ObjectPropertiesFactory objectPropertiesFactory) {
		this.objectPropertiesFactory = objectPropertiesFactory;
	}

	public int getTypeCriteria() {
		return typeCriteria;
	}

	public TableViewer getTableCriteri() {
		return tableCriteri;
	}

	public void setDaValueProviderFactory(DaAValueProviderFactory daValueProviderFactory) {
		this.daValueProviderFactory = daValueProviderFactory;
	}	

	public void setAValueProviderFactory(DaAValueProviderFactory aValueProviderFactory) {
		this.aValueProviderFactory = aValueProviderFactory;
	}	

}
