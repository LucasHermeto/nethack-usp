package br.com.poo.nethack.items;

import br.com.poo.nethack.util.Dices;

/**
 * class ShortSword: Arma com dano pequeno, por�m extremamente leve
 * Arma inicial do Rogue
 * @author braga
 *
 */
public class ShortSword extends Weapon{
	public ShortSword() {
		super("Short Sword", "A Popular weapon in the Dungeon of Doom", new Dices(1, 7, 0), 0, 30, 32*23, 32*10);
		// TODO Auto-generated constructor stub
	}
}
