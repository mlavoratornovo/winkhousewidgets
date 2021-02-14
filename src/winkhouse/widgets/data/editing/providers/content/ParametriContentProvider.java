package winkhouse.widgets.data.editing.providers.content;

import java.util.ArrayList;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import winkhouse.widgets.data.IPropertiesFieldDescriptor;

public class ParametriContentProvider implements IStructuredContentProvider {

	private Integer entityOwner = null;
	
	public ParametriContentProvider(Integer entityOwner) {
		this.entityOwner = entityOwner;
	}

	@Override
	public void dispose() {


	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {


	}

	@Override
	public Object[] getElements(Object arg0) {
		if (arg0 instanceof ArrayList<?>){
			try {
				return ((ArrayList<IPropertiesFieldDescriptor>)arg0).toArray();
			} catch (Exception e) {
				return  null;
			}			
		}else{
			return null;
		}
		
	}

}
