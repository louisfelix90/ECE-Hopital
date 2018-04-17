package ece.ing3.java.projet.controleur.dialogs;

import ece.ing3.java.projet.utils.DialogListener;
import ece.ing3.java.projet.vue.dialogs.ChambreSearchDialog;
import ece.ing3.java.projet.vue.dialogs.ModelSearchDialog;

public class ChambreSearchDialogController extends ModelSearchDialogController {
	private ChambreSearchDialogController( ModelSearchDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	public static ModelSearchDialog createDialog( DialogListener listener ) {
		return createDialog( new ChambreSearchDialog(), listener );
	}
}
