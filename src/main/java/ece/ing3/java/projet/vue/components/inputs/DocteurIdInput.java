package ece.ing3.java.projet.vue.components.inputs;

import java.awt.*;

public class DocteurIdInput extends DocteurInput {
	public DocteurIdInput( String columnName, boolean isSearch, Window parent ) {
		super( columnName, isSearch, parent );
	}

	@Override
	public void setValue( Long value ) throws IllegalArgumentException {
		super.setValue( value );
		textField.setEnabled( false );
		if( search != null ) {
			search.setEnabled( false );
		}
	}
}