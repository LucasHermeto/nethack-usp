package br.com.poo.nethack.items;

import br.com.poo.nethack.util.Dices;

/**
 * class WarHammer: Martelo de combate achado na caverna em pessimo estado
 * @author braga
 *
 */
public class WarHammer extends Weapon{
	public WarHammer() {
		super("War Hammer", "Martela o martelao", new Dices(1, 4, 1), 0, 50, 32*1, 32*11);
		// TODO Auto-generated constructor stub
	}
}
