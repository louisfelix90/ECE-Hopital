package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.vue.components.inputs.*;

public class DocteurInputList extends ModelInputList {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Docteur.class;
	}

	@Override
	protected BaseInput getInputForField( String fieldName, String columnName ) {
		switch( fieldName ) {
			case "numero":
				return new LongIdInput( columnName, true );
			case "nom":
				return new StringInput( columnName, true );
			case "prenom":
				return new StringInput( columnName, true );
			case "adresse":
				return new StringInput( columnName, true );
			case "numeroTelephone":
				return new StringInput( columnName, true );
			case "specialite":
				return new SpecialiteInput( columnName, true );

		}

		return null;
	}

}