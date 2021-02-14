package winkhouse.widgets.data.factory;

import java.util.ArrayList;

import winkhouse.widgets.data.ICriteriaTableModel;
import winkhouse.widgets.data.editing.providers.DaAValueProvider;

public class DaAValueProviderFactory {

	private ArrayList<DaAValueProvider> daAValueFactories = new ArrayList<DaAValueProvider>();
		
	public DaAValueProviderFactory() {
	}

	public DaAValueProvider provideData(Integer entityOwner,ICriteriaTableModel criteriaTableModel){
		
		for (DaAValueProvider daAValueProvider : daAValueFactories) {
			
			if (daAValueProvider.canProvide(entityOwner,criteriaTableModel)){
				
				return daAValueProvider;
				
			}
			
		}
		return null;
		
	}
	
	public void addContentProvider(DaAValueProvider daAValueProvider) throws ClassCastException{
		daAValueFactories.add(daAValueProvider);		
	}

}
