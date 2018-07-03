package br.com.poo.nethack.items;

import br.com.poo.nethack.util.Dices;

/**
 * class Bow: Arma extremamente fraca se utilizada corpo-a-corpo
 * Tem habilidade de jogar flechas nas 8 direcoes
 * Arma inicial do Ranger
 * @author braga
 *
 */
public class Bow extends Weapon{
	public Bow(int l, int c) {
		super("Bow", "Always aim in the head", new Dices(1, 2, 0), 0, 30, l, c);
		// TODO Auto-generated constructor stub
	}
	
	public Bow() {
		super("Bow", "Always aim in the head", new Dices(1, 2, 0), 0, 30, -1, -1);
	}
}
