package CompteUser;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/*
 * Test Junit - pour la création de compte d'utilisateur d'un employé 
 */
class testLoginUser {
	@Test
	void test() {		
	CompteAdmin test = new  CompteAdmin();//instantiation de l'objet de la classe CompteAdmin
		//saisir les valeurs des attributs de l'objet pour la création des comptes
		test.login = "leox@gmail.com";
	    test.setMdp("FreD1995");
		test.getMdp() ;
		test.noEmp = "28";
		test.addCompte();
		/**
		 *  un utilisateur a un seul compte, l'affichage d'une erreur est attendue
		 *  informant que cet utilisateur a déjà une compte
		 */
		assertEquals(test,test);
	}
}
