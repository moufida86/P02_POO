package com.etudiant;

public class Main {

	public static void main(String[] args) 
	{
		// Une instance d'une classe peut être obtenue à l'aide de son constructeur.
		// Chaque classe possède un constructor par défaut, ce dernier n'a pas d'arguments.
		
		// Si un constructeur personnalisé a été défini, le constructeur par défaut n'existe plus.
		// Toutefois si on a besoin d'un constructeur sans argument on peut le créer. Pour l'instant, ce n'est pas le cas.
//		Personne p0 = new Personne();
//		System.out.println(p0.toString());
		
		// Depuis la nouvelle définition en private du constructeur, je ne peux plus créer une instance de Commun.
//		Commun c = new Commun();
//		System.out.println(c.toString());
		
		Personne maPersonne = new Personne("Chirac", "Jacques", Personne.MR);
		System.out.println(maPersonne);
		
		// =============================================================================================================//
		
		try
		{
			// Pour protéger ma saisie de civilite, on va gérer de l'encapsulation poiur éviter que le chiffre diffère de 0, 1 ou 2
			Personne personneProbleme1 = new Personne("Marie", "Durand", 100);
			System.out.println(personneProbleme1);
		}
		catch(IllegalArgumentException ex)
		{
			System.out.println("La création de la personneProbleme1 a échoué");
			System.out.println("Le message de l'execption est : " + ex.getMessage());
		}
		
		// =============================================================================================================//
		
//		Personne personneProbleme = new Personne("Marie", "Select * From Salaires Where Societe=M2I and salaire > 2000", Personne.MELLE); // Exception in thread "main" java.lang.IllegalArgumentException: Le nom Marie est erroné
		
		try
		{
			Personne personneProbleme2 = new Personne("Marie", "Martin", Personne.MME, 50); // Exception in thread "main" java.lang.IllegalArgumentException: La langue 50 est erronée
		} 
		catch(IllegalArgumentException ex)
		{
			System.out.println("La création de la personneProbleme2 a échoué");
			System.out.println("Le message de l'exception est : " + ex.getMessage());
		}
		
		// =============================================================================================================//
		
		Personne p1 = new Personne("Schmidt", "Helmut", Personne.MR, Commun.DE);
		System.out.println(p1);
		
		// Je veux désormais afficher le prénom de p1.
		// Il me faut donc donner des accesseurs à la classe Personne. Getters / Setters
		try
		{
			System.out.println(p1.getNom());
		}
		catch(RuntimeException ex)
		{
			System.out.println("Cette opération n'est pas autorisée");
			System.out.println("Le message de l'exception est : " + ex.getMessage());
		}
		
		// on teste la modification du nom :
		
		p1.setNom("Merkel");
		System.out.println(p1);
		
		try
		{
			// On teste un nom qui ne contient pas le nombre de caractères supportés
			p1.setNom("asdjkfhajksldhfljkasdhfkljasdhfasjdhfjlaksdfhasdlfjkha");
			System.out.println(p1);
		}
		catch(RuntimeException ex)
		{
			System.out.println("Cette opération n'est pas autorisée");
			System.out.println("Le message de l'exception est : " + ex.getMessage());
		}
		
		// =============================================================================================================//
		
		// Test de la méthode de modification d'une personne :
		
		p1.modifierPersonne("Angela", Personne.MME);
		System.out.println("Suite à la modification par la méthode modifierPersonne : " + p1);
		
		p1.modifierPersonne("Casta", "Laetitia", Personne.MELLE);
		System.out.println("Nouvelle modification de p1 : " + p1);
		
	} // Fin du Main

	
	
} // Fin de la classe
