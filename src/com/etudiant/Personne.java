/**
 * Étudier les pilliers de la POO:
 * - Classe, Objets (instance)
 * - Encapsulation
 * - Héritage
 * - Polymorphysme
 * 
 * =-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
 * 
 * - Classe => c'est un modèle, un blueprint.
 * - Instance => Un objet de cette classe.
 * 
 * (Voir UML pour savoir ce que l'on trouve dans une classe)
 * 
 * Dans une classe on trouve des champs statiques et des méthodes statiques (ou de classes).
 * 
 * Dans un object, on trouve des champs et des méthodes d'instance.
 * 
 * Les champs statiques contiennent des valeurs communes, donc statiques, à toutes les instances. La valeur ne varie pas d'une instance à l'autre.
 * Ex: la vitesse maximum sur autoroute est limitée à 130km/h, elle ne varie pas d'une voiture à l'autre.
 * 
 * Les méthodes static sont des actions qui travaillent avec les valeurs statiques. Les champs static existent (Fields), même si ausune instance n'a été créée.
 * Il faut donc utiliser une méthode de classe pour travailler avec ces champs (Fields).
 * 
 * - Encapsulation => C'est un ensemble de techniques qui assurent une séparation entre le contenu d'un objet et l'extérieur.
 * On filtre les entrées et les sorties de données.
 * 
 * Pour gérer les accès aux champs et aux méthodeson utilise les modificateurs d'accès : private, protected, public ou rien du tout
 * 
 * Un champ et une méthode private n'est accessible qu'à l'intérieur de la classe.
 * 
 * Un champ et une méthode protected n'est accessible qu'aux héritiers de la classe.
 * 
 * Un champ et méthode pubic est accessible partout et pour tous.
 * 
 * Si aucun modificateur d'accès (public, private ou protected) n'est précisé, alors les champs et les méthodes en question sont :
 * - Accessibles à l'intérieur de la classe
 * - Accessibles aux classes se trouvant à l'intérieur du même package
 * - Inaccessibles pour tout le reste
 * 
 * Une classe interne est une classe définie à l'intérieur d'une autre classe.
 * 
 * Une classe interne peut être private, protected ou public.
 * 
 * Une classe qui n'est pas interne ne peut être que public.
 * 
 * N'importe quelle classe dès qu'elle est créée a un constructeur, par défaut.
 * 
 */
package com.etudiant;

import javax.management.RuntimeErrorException;

public class Personne 
{
	//--------------------------------------- Attributs --------------------------------------------------------------//
	
	// définir trois champs de classe pour les trois civilités possibles. Final veut dire que le champs ne peut être modifié
	public static final int MR = 0; // MR pour Monsieur
	public static final int MME = 1; // MME pour Madame
	public static final int MELLE = 2; // MELLE pour Mademoiselle
	
	// Ici on définit des champs d'intsance dont la valeur diffère d'une instance à l'autre
	private String nom, prenom;
	private int civilite;
	
	private int langue = Commun.FR; // Langue va prendre la valeur 0
	
	//--------------------------------------- Constructeurs --------------------------------------------------------------//
	
	// Un constructeur est une méthode spéciale qui initialise l'espace mémoire allouée par new.
	// Il porte le nom de la classe et n'a pas de type de retour. Même pas void.
	
	public Personne(String nom, String prenom, int civilite)
	{
		// tester la validité de la civilité passée en paramètres
		if(!estCiviliteValide(civilite))
		{
			// la civilité ne correspond pas aux valeurs admises, je dois donc empêcher la création de l'instance.
			// Je dois donc lever une exception.
			throw new IllegalArgumentException("La civilité " + civilite + " est erronnée.");
		}
		
		// On va également vérifié le nom et le prénom grâce a une méthode de vérification
		if(!estNomOuPrenomValide(nom))
		{
			throw new IllegalArgumentException("Le nom " + nom + " est erroné");
		}
		
		if(!estNomOuPrenomValide(prenom))
		{
			throw new IllegalArgumentException("Le nom " + prenom + " est erroné");
		}
		
		this.nom = nom; // This représente l'adresse mémoire de l'instance.
		this.prenom = prenom;
		this.civilite = civilite;
	}
	
	
	// On va définir un nouveau constructeur qui accepte aussi la langue.
	// On parle donc d'une surcharge du constructeur.
	// Surcharger : donner le même nom à des consctructeurs ou des méthodes qui font des traitements semblables.
	// !ATTENTION! : Une méthode de constructeur surchargée doit avoir une signature 
	// (liste de paramètres : type, nombre ou ordre, différent de ce qui existe déjà) 
	// Le type de retour ne fait pas partie de la signature.
	public Personne(String nom, String prenom, int civilite, int langue)
	{
		// Pour hériter des précédentes valeurs du constructeur ci-dessus, je vais simplement
		// faire une ligne avec this qui va chercher les paramètres dans les parenthèses.
		this(nom, prenom, civilite);
		
		// Je vais d'abord vérifier la validité da langue
		
		if(!Commun.isLangueValide(langue))
		{
			throw new IllegalArgumentException("La langue " + langue + " est erronée");
		}
		// Si pas d'erreurs sur mon test, j'initialise la langue
		this.langue = langue;
	}
	
	//--------------------------------------- Méthodes --------------------------------------------------------------//
	
	// On va ré écrire la méthode héritée toString(). Cette méthode est hérité de la superclasse Object
	// et ne fournit que le nom complet de la classe à laquelle appartient l'instance et l'addresse mémoire de cette dernière.
	// Avec @Override, on précise que cette méthode prendra le dessus sur la méthode par défaut.
	
	@Override
	public String toString()
	{
		return getClass().getName() + " : " + getDetails();
	}
	
	protected String getDetails()
	{
//		return civilite + " " + prenom + " " + nom;
		return getCiviliteTexte() + " " + prenom + " " + nom;
	}
	
	// Tableau statique qui contient les civilités en clair, correspondant aux civilités numériques et à la langue de la personne.
	private static String[][] tbCivilites =
		{
				// en français
				{"Monsieur", "Madame", "Mademoiselle"},
				// en anglais
				{"Mister", "Misses", "Miss"},
				// en allemand
				{"Herr", "Frau", "Fraulein"}
		};
	
	// On va créer une méthode utilitaire pour exploiter le tableau ci-dessus.
	protected String getCiviliteTexte()
	{
		return tbCivilites[langue][civilite];
	}
	
	private static boolean estCiviliteValide(int civilite)
	{
//		if(civilite < 0 || civilite > 2)
//		{
//			return false;
//		} else {
//			return true;
//		}
		
		// On peut aussi écrire le même code précédent de cette façon :
		
		return civilite == Personne.MR || civilite == Personne.MME || civilite == Personne.MELLE;
	}
	
	private static boolean estNomOuPrenomValide(String nom_ou_prenom)
	{
		// On commence déjà par vérifier que le chaine de caractère existe et que ce soit compris entre 2 et 15 caractères:
		return nom_ou_prenom != null && nom_ou_prenom.length() >= 2 && nom_ou_prenom.length() <= 15;
	}
	
	
	//--------------------------------------- Accesseurs --------------------------------------------------------------//
	
	// Ce sont des méthodes qui permettent de lire et d'ecrire le contenu des différents champs.
	
	// On a l'habitude de commencer le nom des accesseurs qui assurent la lecture par GET.
	// On parle d'un getter.
	
	public String getNom()
	{
		if(nom.equals("Schmidt")) // En Java, on ne peut pas mettre if(nom == "Schmidt")
		{
			throw new RuntimeException("Vous n'avez pas le droit de connaître le nom de cette personne");
		}
		return nom;
	}
	
	// Le nom des accesseurs en écriture commencent d'habitude par SET.
	// On parle de setter.
	
	public void setNom(String nom)
	{
		if(!estNomOuPrenomValide(nom))
		{
			throw new IllegalArgumentException("Le nom " + nom + " est erroné");
		}
		this.nom = nom;
	}
	
	public String getPreom()
	{
		return prenom;
	}
	
	// Quelques méthodes surchargées de modification =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- //
	
	public void modifierPersonne(String prenom, int civilite)
	{
		// On commence par tester les deux valeurs que nous avons en paramètres : prenom et civilite
		
		if(!estNomOuPrenomValide(prenom))
		{
			throw new IllegalArgumentException("Le nom " + prenom + " est erroné");
		}
		
		if(!estCiviliteValide(civilite))
		{
			throw new IllegalArgumentException("La civilité " + civilite + " est erronnée.");
		}
		
		// Si tout va bien, on accepte les modifications 
		this.prenom = prenom;
		this.civilite = civilite;
		
	}
	
	// La surcharge suivante n'en est pas une car elle a la même signature que la précédente : String + int => public void modifierPersonne(String nom, int civilite)
	// Pour que cela fonctionne, je peux inverser les deux paramètre : int + String => public void modifierPersonne(int civilite, String nom)
	public void modifierPersonne(String nom, String prenom, int civilite)
	{
		// Comme plus haut, je teste le nom
		
		if(!estNomOuPrenomValide(nom))
		{
			throw new IllegalArgumentException("Le nom " + nom + " est erroné");
		}
		
		modifierPersonne(prenom, civilite); // Je copie les valeurs de la méthode précédente
		this.nom = nom; // Et je définis la nouvelel valeur
	}
	
	// TODO: Créer les getters et setters pour la civilite et la langue
	
} // Fin de la classe Personne
