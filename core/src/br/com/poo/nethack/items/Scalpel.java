package br.com.poo.nethack.items;

import br.com.poo.nethack.util.Dices;

/**
 * class Scalpel: Arma muito leve que consegue dar pouco dano
 * @author braga
 *
 */
public class Scalpel extends Weapon{

	public Scalpel() {
		super("Scalple", "It's like a knife", new Dices(1,3,0), 2, 5, 32*16, 32*10);
		// TODO Auto-generated constructor stub
	}
}
