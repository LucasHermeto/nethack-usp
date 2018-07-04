package br.com.poo.nethack.items;

import br.com.poo.nethack.util.Dices;

/**
 * class Axe: Machado b�sico para cortar madeira
 * Encontrado na Caverna
 * @author braga
 *
 */
public class Axe extends Weapon {

	public Axe() {
		super("Axe", "CHOP THE WOOD", new Dices(1,5, 0), 0, 60, 32*21, 32*10);
		// TODO Auto-generated constructor stub
	}
}
