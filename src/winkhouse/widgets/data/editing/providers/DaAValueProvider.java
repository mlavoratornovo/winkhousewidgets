package winkhouse.widgets.data.editing.providers;

import java.util.ArrayList;

import winkhouse.widgets.data.ICriteriaTableModel;

public abstract class DaAValueProvider {
		
	public abstract IDaAValueObject getDaAValueObject(ICriteriaTableModel rowObj);
	
	public abstract ArrayList<IDaAValueObject> getDaAValueObjects();
	
	public abstract boolean canProvide(Integer entityOwner,ICriteriaTableModel criteriaTableModel);
	
}
