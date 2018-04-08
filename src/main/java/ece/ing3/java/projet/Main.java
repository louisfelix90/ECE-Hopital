package ece.ing3.java.projet;

import ece.ing3.java.projet.configuration.Configuration;
import ece.ing3.java.projet.database.Database;
import ece.ing3.java.projet.utils.REPL;

public class Main {
	public static void main( String[] args ) throws Exception {
		Configuration.init();
		Database.init();
		REPL.main( args );
		Database.close();
	}
}