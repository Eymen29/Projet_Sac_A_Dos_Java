package SacADos;

import java.util.Comparator;

public class GloutonComparator implements Comparator<ObjetSac> {

	private boolean reverse;

	public GloutonComparator(boolean r) {
		reverse = r;
	}

	public int compare(ObjetSac o1, ObjetSac o2) {

		double valO1 = o1.getValeur() / o1.getPoids();
		double valO2 = o2.getValeur() / o2.getPoids();
		return (reverse) ? (int) (valO2 - valO1) : (int) (valO1 - valO2);
	}

}
