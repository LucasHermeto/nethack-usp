package br.com.poo.nethack.items;

import br.com.poo.nethack.util.Dices;

/**
 * class WarHammer: Martelo de combate achado na caverna em pessimo estado
 * @author braga
 *
 */
public class WarHammer extends Weapon{
	public WarHammer(int l, int c) {
		super("War Hammer", "Martela o martelao", new Dices(1, 4, 1), 0, 50, l, c);
		// TODO Auto-generated constructor stub
	}

	public WarHammer() {
		super("War Hammer", "Martela o martelao", new Dices(1, 4, 1), 0, 50, -1, -1);
	}
}
