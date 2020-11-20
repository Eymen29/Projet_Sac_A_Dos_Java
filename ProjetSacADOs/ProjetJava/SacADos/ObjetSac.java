package SacADos;

public class ObjetSac {

	private String name;
	private float poids;
	private float valeur;

	public ObjetSac(String n, float p, float v) {
		name = n;
		poids = p;
		valeur = v;
	}

	public String getName() {
		return name;
	}

	public float getPoids() {
		return poids;
	}

	public float getValeur() {
		return valeur;
	}

	public String toString() {
		return name + " ; valeur :  " + valeur + " ; poids " + poids;
	}

	public ObjetSac clone() {
		return new ObjetSac(name, poids, valeur);
	}
}
