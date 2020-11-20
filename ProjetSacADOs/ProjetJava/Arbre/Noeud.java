package Arbre;

import java.util.LinkedList;

import SacADos.ObjetSac;

public class Noeud {

	private Noeud leftNode, rightNode;

	private LinkedList<ObjetSac> listeObjetPort;

	

	private Arbre master;
	private int currentIndex;

	public Noeud(LinkedList<ObjetSac> l, Arbre m, int currentIdx) {

		listeObjetPort = l;

		leftNode = null;
		rightNode = null;
		master = m;
		currentIndex = currentIdx;

	}

	public void construction() {

		
		if (currentIndex > master.getListAllObject().size() - 1) {
			master.setBestContent(listeObjetPort);
			return;
		}

		float valeurPort = 0;
		float poidsPort = 0;
		float potentiel = 0;

		for (ObjetSac ObjetSac : listeObjetPort) {
			valeurPort += ObjetSac.getValeur();
			potentiel += ObjetSac.getValeur();

			poidsPort += ObjetSac.getPoids();
		}

		for (int i = currentIndex; i < master.getListAllObject().size(); i++) {
			ObjetSac b = master.getListAllObject().get(i);
			potentiel += b.getValeur();
		}

		
		if (valeurPort > master.getMeilleurValeur()) {
			master.setBestContent(listeObjetPort);
		}

		if (potentiel > master.getMeilleurValeur()) {

			

			ObjetSac currentObj = master.getListAllObject().get(currentIndex);

			
			LinkedList<ObjetSac> leftBag = listeObjetPort;
			LinkedList<ObjetSac> rightBag = new LinkedList<ObjetSac>();

			for (ObjetSac ObjetSac : listeObjetPort) {
				rightBag.addFirst(ObjetSac);
			}

			
			if (poidsPort + currentObj.getPoids() <= master.getPoidsMax()) {
				rightBag.addFirst(currentObj);
				rightNode = new Noeud(rightBag, master, currentIndex + 1);
				rightNode.construction();
			}

			leftNode = new Noeud(leftBag, master, currentIndex + 1);
			leftNode.construction();

		}
	}

}
