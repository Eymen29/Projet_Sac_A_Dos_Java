package Arbre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import SacADos.ObjetSac;
import SacADos.GloutonComparator;
import SacADos.SacADos;

public class Arbre {

	private Noeud racine;
	private float meilleurValeur;
	private LinkedList<ObjetSac> bestContent;
	private ArrayList<ObjetSac> listeAllObject;
	private float poidsMax;

	public Arbre(SacADos b) {
		racine = new Noeud(new LinkedList<ObjetSac>(), this, 0);
		meilleurValeur = 0;
		listeAllObject = b.getlisteDesObjets();
		Collections.sort(listeAllObject, new GloutonComparator(true));
		bestContent = null;
		poidsMax = b.getPoidsMaximal();
	}

	public void construction() {
		racine.construction();
	}

	public float getMeilleurValeur() {
		return meilleurValeur;
	}

	public LinkedList<ObjetSac> getBestContent() {
		return bestContent;
	}

	public ArrayList<ObjetSac> getListAllObject() {
		return listeAllObject;
	}

	public void setBestContent(LinkedList<ObjetSac> listeObjetPort) {

		float valeur = 0;
		for (ObjetSac ObjetSac : listeObjetPort) {
			valeur += ObjetSac.getValeur();
		}

		if (bestContent == null || valeur > meilleurValeur) {
			bestContent = listeObjetPort;
			meilleurValeur = valeur;
		}

	}

	public float getPoidsMax() {
		return poidsMax;
	}

}
