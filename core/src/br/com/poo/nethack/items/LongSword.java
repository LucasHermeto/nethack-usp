package br.com.poo.nethack.items;

import br.com.poo.nethack.util.Dices;

/**
 * class LongSword: Arma com dano moderado
 * Arma inicial do Knight
 * @author braga
 *
 */
public class LongSword extends Weapon{
	public LongSword(int l, int c) {
		super("Long Sword", "One of the more popular weapons.", new Dices(1, 10, 0), 0, 40, l, c);
		// TODO Auto-generated constructor stub
	}
	
	public LongSword() {
		super("Long Sword", "One of the more popular weapons.", new Dices(1, 10, 0), 0, 40, -1, -1);
	}
}
