package winkhouse.widgets.data.factory;

import java.util.ArrayList;

import org.eclipse.jface.viewers.IStructuredContentProvider;

import winkhouse.widgets.data.editing.providers.IDaAValueObject;
import winkhouse.widgets.data.editing.providers.content.IDaAContentProvider;

public class DaAContentProviderFactory {
	
	private ArrayList<IDaAContentProvider> contentProviders = new ArrayList<IDaAContentProvider>();
	private Integer entityOwner = null;
	private String methodName = null;
	private static DaAContentProviderFactory instance = null;

	public static DaAContentProviderFactory getInstance(){
		if (instance == null){
			instance = new DaAContentProviderFactory();
		}
		return instance;
	}
	
	private DaAContentProviderFactory() {
	}
	
	public IStructuredContentProvider provideData(){
		
		for (IDaAContentProvider contentProvider : contentProviders) {
			
			if (contentProvider.canProvide(this.entityOwner, this.methodName)){
				
				return contentProvider;
				
			}
			
		}
		return null;
		
	}
	
	public void addContentProvider(IDaAContentProvider contentProvider){
		
		contentProviders.add(contentProvider);		
		
	}

	public Integer getEntityOwner() {
		return entityOwner;
	}

	public void setEntityOwner(Integer entityOwner) {
		this.entityOwner = entityOwner;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
}
