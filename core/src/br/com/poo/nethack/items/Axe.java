package br.com.poo.nethack.items;

import br.com.poo.nethack.util.Dices;

/**
 * class Axe: Machado b�sico para cortar madeira
 * Encontrado na Caverna
 * @author braga
 *
 */
public class Axe extends Weapon {

	public Axe(int l, int c) {
		super("Axe", "CHOP THE WOOD", new Dices(1,5, 0), 0, 60, l, c);
		// TODO Auto-generated constructor stub
	}

	public Axe() {
		super("Axe", "CHOP THE WOOD", new Dices(1,5, 0), 0, 60, -1, -1);
	}
}
