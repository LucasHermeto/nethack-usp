package br.com.poo.nethack.items;

import br.com.poo.nethack.util.Dices;

/**
 * class ShortSword: Arma com dano pequeno, por�m extremamente leve
 * Arma inicial do Rogue
 * @author braga
 *
 */
public class ShortSword extends Weapon{
	public ShortSword(int l, int c) {
		super("Short Sword", "A Popular weapon in the Dungeon of Doom", new Dices(1, 7, 0), 0, 30, l, c);
		// TODO Auto-generated constructor stub
	}

	public ShortSword() {
		super("Short Sword", "A Popular weapon in the Dungeon of Doom", new Dices(1, 7, 0), 0, 30, -1, -1);
	}
}
