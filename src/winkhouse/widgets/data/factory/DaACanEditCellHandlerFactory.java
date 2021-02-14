package winkhouse.widgets.data.factory;

import java.util.ArrayList;

import winkhouse.widgets.data.ICanEditCellHandler;

public class DaACanEditCellHandlerFactory {

	private static DaACanEditCellHandlerFactory instance = null;
	private ICanEditCellHandler daHandler = null;
	private ICanEditCellHandler aHandler = null;
	
	public DaACanEditCellHandlerFactory(){};
	
	public ICanEditCellHandler getDaHandler() {
		return daHandler;
	}

	public void setDaHandler(ICanEditCellHandler daHandler) {
		this.daHandler = daHandler;
	}

	public ICanEditCellHandler getaHandler() {
		return aHandler;
	}

	public void setaHandler(ICanEditCellHandler aHandler) {
		this.aHandler = aHandler;
	}
	
	
}
