package winkhouse.widgets.data.editing;

import winkhouse.widgets.data.ICriteriaTableModel;

public interface IDaACellEditor {

	public boolean canProvide(Integer entityOwner, ICriteriaTableModel criteriaTableModel);
	
}
