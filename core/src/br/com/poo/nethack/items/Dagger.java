package br.com.poo.nethack.items;

import br.com.poo.nethack.util.Dices;

public class Dagger extends Weapon{

	public Dagger(int l, int c) {
		super("Dagger", "he dagger is a versatile weapon that can be used either in melee or as a thrown projectile", new Dices(1,4,0), 2, 10, l, c);
		// TODO Auto-generated constructor stub
	}

}
