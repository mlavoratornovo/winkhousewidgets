package winkhouse.widgets.data.factory;

import java.util.ArrayList;
import java.util.HashMap;

import winkhouse.widgets.data.IPropertiesFieldDescriptor;

public class ObjectPropertiesFactory {
	
	private HashMap<Integer,GridDataFactory> propertyFactoryMap = null;
	
	public ObjectPropertiesFactory(){}
	
	public boolean addFactory(Integer iCriteriaOwnersID,GridDataFactory factory){
		boolean return_value = true;
		if (propertyFactoryMap == null){
			propertyFactoryMap = new HashMap<Integer, GridDataFactory>();
		}
		try {
			if (propertyFactoryMap.put(iCriteriaOwnersID,factory) != null){
				return_value = false;
			}
		} catch (Exception e) {
			return_value = false;
		}			
		return return_value;
	}
	
	public boolean removeFactory(Integer iCriteriaOwnersID,GridDataFactory factory){
		boolean return_value = true;
		if (propertyFactoryMap == null){
			propertyFactoryMap = new HashMap<Integer, GridDataFactory>();
		}
		try {
			if (propertyFactoryMap.remove(iCriteriaOwnersID) == null){
				return_value = false; 
			}
		} catch (Exception e) {
			return_value = false;
		}			
		return return_value;
	}
	
	protected GridDataFactory getFactory(Integer iCriteriaOwnersID){
		
		if (propertyFactoryMap != null){
			return propertyFactoryMap.get(iCriteriaOwnersID);
		}
		return null;
		
	}
	
	public ArrayList<IPropertiesFieldDescriptor> getObjectProperties(Integer iCriteriaOwnersID){

		GridDataFactory factory = getFactory(iCriteriaOwnersID);
		if (factory != null){
			return factory.getOwnerProperties();
		}
		return null;
		
	}

}
