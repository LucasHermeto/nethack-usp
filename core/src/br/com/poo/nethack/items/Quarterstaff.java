package br.com.poo.nethack.items;

import br.com.poo.nethack.util.Dices;

public class Quarterstaff extends Weapon{

	public Quarterstaff(int l, int c) {
		super("Quarterstaff", "A wood staff for your tricks", new Dices(1,6,0), 0, 40, l, c);
		// TODO Auto-generated constructor stub
	}

	public Quarterstaff() {
		super("Quarterstaff", "A wood staff for your tricks", new Dices(1,6,0), 0, 40, -1, -1);
	}
}
