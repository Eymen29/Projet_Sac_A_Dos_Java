package SacADos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

import Arbre.Arbre;

public class SacADos {

	ArrayList<ObjetSac> listeDesObjets;
	LinkedList<ObjetSac> contenu;
	private float poidsMaximal;
	public static final float echel = 1;

	public SacADos() {
		listeDesObjets = new ArrayList<ObjetSac>();
		contenu = new LinkedList<ObjetSac>();
	}

	public SacADos(String path, float poidsMaximal) {
		this();
		this.poidsMaximal = poidsMaximal;

		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream("resources\\itemsEval-float.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] data = line.split(";");
			listeDesObjets.add(new ObjetSac(data[0], Float.parseFloat(data[1].trim()), Float.parseFloat(data[2].trim())));
		}
		sc.close();
	}

	public SacADos(SacADos sacADos) {
		this();
		for (ObjetSac o : sacADos.listeDesObjets) {
			listeDesObjets.add(o);
		}
		for (ObjetSac o : sacADos.contenu) {
			contenu.add(o);
		}
		poidsMaximal = sacADos.poidsMaximal;

	}

	public void GloutonAlgo() {
		Collections.sort(listeDesObjets, new GloutonComparator(false));
		contenu.clear();
		int sommeTotal = 0;
		for (int i = 0; i < listeDesObjets.size() && sommeTotal <= poidsMaximal; i++) {
			ObjetSac sacADos = listeDesObjets.get(i);
			if (sommeTotal + sacADos.getPoids() <= poidsMaximal) {
				contenu.add(sacADos);
				sommeTotal += sacADos.getPoids();
			}
		}
	}

	public void DynamiqueAlgo() {
		int n = listeDesObjets.size();
		Collections.sort(listeDesObjets, new GloutonComparator(false));
		float[][] matrice = new float[n][(int) ((poidsMaximal + 1) * echel)];
		ObjetSac b0 = listeDesObjets.get(0);
		for (int j = 0; j < matrice[0].length; j++) {
			float limite = (float) j / echel;
			if (b0.getPoids() > limite) {
				matrice[0][j] = 0;
			} else {
				matrice[0][j] = (float) b0.getValeur();
			}
		}
		for (int i = 1; i < listeDesObjets.size(); i++) {
			ObjetSac sacADos = listeDesObjets.get(i);
			for (int j = 0; j < matrice[i].length; j++) {
				float limite = (float) j / echel;
				if (sacADos.getPoids() > limite) {
					matrice[i][j] = matrice[i - 1][j];
				} else {
					
					matrice[i][j] = (float) Math.round(Math.max(matrice[i - 1][j],
							(float) matrice[i - 1][j - (int) (sacADos.getPoids() * echel)] + sacADos.getValeur()) * 100) / 100;
				}
			}
		}

		
		int j = matrice[0].length - 1;

		while (j > 0 && matrice[matrice.length - 1][j] == matrice[matrice.length - 1][j - 1]) {
			j--;
		}
		
		contenu.clear();

		int i = matrice.length - 1;

		while (j > 0) {
			while (i > 0 && matrice[i][j] == matrice[i - 1][j]) {
				i--;
			}
			ObjetSac sacADos = listeDesObjets.get(i);
			j -= sacADos.getPoids() * echel;
			if (j >= 0) {
				contenu.add(sacADos);
			}
			i--;
		}
	}

	public void PSEAlgo() {

		Arbre arbre = new Arbre(this);
		arbre.construction();
		contenu = arbre.getBestContent();

	}

	public LinkedList<ObjetSac> getObjectSelected() {

		LinkedList<ObjetSac> liste = new LinkedList<ObjetSac>();
		for (ObjetSac ObjetSac : contenu) {
			liste.add(ObjetSac);
		}
		return liste;
	}

	public ArrayList<ObjetSac> getlisteDesObjets() {

		ArrayList<ObjetSac> liste = new ArrayList<ObjetSac>();
		for (ObjetSac ObjetSac : listeDesObjets) {
			liste.add(ObjetSac);
		}
		return liste;
	}

	public float getValeurPorte() {
		float valeur = 0;
		for (ObjetSac ObjetSac : contenu) {
			valeur += ObjetSac.getValeur();
		}
		return valeur;
	}

	public float getValeurPotentiel() {
		float valeur = 0;
		for (ObjetSac ObjetSac : listeDesObjets) {
			valeur += ObjetSac.getValeur();
		}

		for (ObjetSac ObjetSac : contenu) {
			valeur += ObjetSac.getValeur();
		}

		return valeur;
	}

	public float getPoidsPorte() {
		float poids = 0;
		for (ObjetSac ObjetSac : contenu) {
			poids += ObjetSac.getPoids();
		}
		return poids;
	}

	public ObjetSac enleve(int i) {
		return listeDesObjets.remove(i);
	}

	public void ajouter(ObjetSac o) {
		contenu.add(o);
	}

	public boolean vide() {
		return listeDesObjets.isEmpty();
	}

	public float getPoidsMaximal() {
		return poidsMaximal;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		for (ObjetSac o : contenu) {
			s.append(o.toString() + "\n");
		}
		return s.toString();
	}

}
