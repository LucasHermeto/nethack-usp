package br.com.poo.nethack.items;

import br.com.poo.nethack.util.Dices;

public class Dagger extends Weapon{

	public Dagger() {
		super("Dagger", "he dagger is a versatile weapon that can be used either in melee or as a thrown projectile", new Dices(1,4,0), 2, 10,  32*11, 32*10);
		// TODO Auto-generated constructor stub
	}
}
