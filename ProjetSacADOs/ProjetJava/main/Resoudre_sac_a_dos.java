package main;

import java.util.LinkedList;

import SacADos.ObjetSac;
import SacADos.SacADos;

public class Resoudre_sac_a_dos {

	public static void main(String[] args) {
		if (args.length != 3) {
			System.err.println("veuillez entrer trois parametres");
			System.exit(1);
		}
		SacADos sacADos = new SacADos(args[0], Float.parseFloat(args[1]));
		long debut = System.currentTimeMillis();
		switch (args[2].toLowerCase()) {
		case "pse":
			sacADos.PSEAlgo();
			break;
		case "dynamique":
			sacADos.DynamiqueAlgo();
			break;
		case "glouton":
			sacADos.GloutonAlgo();
		}

		long fin = System.currentTimeMillis();
		long tempsPasse = fin - debut;

		System.out.println("temps final : " + (float) tempsPasse / 1000 + " sec");

		LinkedList<ObjetSac> liste = sacADos.getObjectSelected();
		for (ObjetSac ObjetSac : liste) {
			System.out.println(ObjetSac);
		}
		System.out.println(
				"\nValeur totale : " + sacADos.getValeurPorte() + " \nPoids total : " + sacADos.getPoidsPorte());

	}

}
