package br.com.poo.nethack.items;

import br.com.poo.nethack.util.Dices;

/**
 * class Mace: Pouco dano mas extremamente leve
 * @author braga
 *
 */
public class Mace extends Weapon{
	public Mace(int l, int c) {
		super("Mace", "A Mace is a type of blunt weapon found in the Dungeon", new Dices(1, 6, 1), 0, 30, l, c);
		// TODO Auto-generated constructor stub
	}
	
	public Mace() {
		super("Mace", "A Mace is a type of blunt weapon found in the Dungeon", new Dices(1, 6, 1), 0, 30, -1, -1);
	}
}
