package winkhouse.widgets.data.editing.providers.label;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import winkhouse.widgets.data.IPropertiesFieldDescriptor;

public class ParametriLabelProvider implements ILabelProvider {

	@Override
	public void addListener(ILabelProviderListener arg0) {


	}

	@Override
	public void dispose() {


	}

	@Override
	public boolean isLabelProperty(Object arg0, String arg1) {

		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener arg0) {


	}

	@Override
	public Image getImage(Object arg0) {

		return null;
	}

	@Override
	public String getText(Object arg0) {
		return ((IPropertiesFieldDescriptor)arg0).getDescrizione();
		
	}

}
