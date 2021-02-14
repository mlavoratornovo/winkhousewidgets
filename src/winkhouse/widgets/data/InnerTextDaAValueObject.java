package winkhouse.widgets.data;

import winkhouse.widgets.data.editing.providers.IDaAValueObject;

public class InnerTextDaAValueObject implements IDaAValueObject {

		private String strValue = null;
		
		public InnerTextDaAValueObject(String strValue){
			this.strValue = strValue; 
		}

		@Override
		public String getDisplayValue() {
			return this.strValue;
		}

		@Override
		public String getCodValue() {
			return this.strValue;
		}

		@Override
		public Object getBindValue() {
			return null;
		}

}
