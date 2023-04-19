package com.etudiant;

public class Commun 
{
	public static final int FR = 0;
	public static final int EN = 1;
	public static final int DE = 2;
	
	// Cette classe est une classe utilitaire qui ne devrait pas être instanciée.
	// Pour l'instant, elle peut être instanciée car elle possède le constructeur par défaut.
	// On va donc cacher ce constucteur par défaut, le cacher.
	
	// Je définis alors un (ou plusieurs) constructeur personnalisé.
	// Dès qu'un constructeur personnalisé a été créé, le constructeur par défaut n'est plus accessible.
	
	// Normalement les constructeurs sont en accès public, sauf celui là car son rôle n'est que de cacher son accès.
	private Commun()
	{
		
	}
	
	public static boolean isLangueValide(int langue)
	{
		return langue == FR || langue == EN || langue == DE; // C'est un true impliscite. Si ce n'est pas aucun de ces cas, ça returen false.
	}
	
}
