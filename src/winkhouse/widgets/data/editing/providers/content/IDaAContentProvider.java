package winkhouse.widgets.data.editing.providers.content;

import org.eclipse.jface.viewers.IStructuredContentProvider;


public interface IDaAContentProvider extends IStructuredContentProvider {	
	
	public boolean canProvide(Integer entityOwner, String methodName);	
	
}
