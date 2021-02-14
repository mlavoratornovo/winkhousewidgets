package winkhouse.widgets.data.factory;

import java.util.ArrayList;

import org.eclipse.jface.viewers.CellEditor;

import winkhouse.widgets.data.ICriteriaTableModel;
import winkhouse.widgets.data.editing.IDaACellEditor;

public class DaACellEditorFactory {

	private ArrayList<IDaACellEditor> cellEditors = new ArrayList<IDaACellEditor>();
	
	public DaACellEditorFactory() {
	}
	
	public IDaACellEditor provideData(Integer entityOwner, ICriteriaTableModel criteriaTableModel){
		
		for (IDaACellEditor cellEditor : cellEditors) {
			
			if (cellEditor.canProvide(entityOwner,criteriaTableModel)){
				
				return cellEditor;
				
			}
			
		}
		return null;
		
	}
	
	public void addCellEditor(IDaACellEditor cellEditor) throws ClassCastException{
		
		if (cellEditor instanceof CellEditor){
			cellEditors.add(cellEditor);
		}else{
			throw new ClassCastException("Il prametro passato non è di tipo org.eclipse.jface.viewers.CellEditor");
		}
		
	}

}
