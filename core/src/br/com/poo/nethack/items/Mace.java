package br.com.poo.nethack.items;

import br.com.poo.nethack.util.Dices;

/**
 * class Mace: Pouco dano mas extremamente leve
 * @author braga
 *
 */
public class Mace extends Weapon{
	public Mace() {
		super("Mace", "A Mace is a type of blunt weapon found in the Dungeon", new Dices(1, 6, 1), 0, 30, 32*10, 32*11);
		// TODO Auto-generated constructor stub
	}
}
