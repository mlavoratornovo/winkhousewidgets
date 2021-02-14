package winkhouse.widgets.data;

import winkhouse.widgets.data.editing.providers.IDaAValueObject;

public interface ICriteriaTableModel {

	public void setPropertiesFieldDescriptior(IPropertiesFieldDescriptor pfd);
	
	public IPropertiesFieldDescriptor getPropertiesFieldDescriptior();
	
	public void setDaValue(IDaAValueObject value);
	
	public IDaAValueObject getDaValue();
	
	public void setAValue(IDaAValueObject value);
	
	public IDaAValueObject getAValue();
	
	public void setLogicalOperator(String value);
	
	public String getLogicalOperator();
	
	
	
}
