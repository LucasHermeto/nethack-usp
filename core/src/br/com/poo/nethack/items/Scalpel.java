package br.com.poo.nethack.items;

import br.com.poo.nethack.util.Dices;

/**
 * class Scalpel: Arma muito leve que consegue dar pouco dano
 * @author braga
 *
 */
public class Scalpel extends Weapon{

	public Scalpel(int l, int c) {
		super("Scalple", "It's like a knife", new Dices(1,3,0), 2, 5, l, c);
		// TODO Auto-generated constructor stub
	}

	public Scalpel() {
		super("Scalple", "It's like a knife", new Dices(1,3,0), 2, 5, -1, -1);
	}
}
