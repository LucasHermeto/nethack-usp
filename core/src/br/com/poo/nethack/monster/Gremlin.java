package br.com.poo.nethack.monster;

import br.com.poo.nethack.util.Dices;
// Claw
public class Gremlin extends Monster {

	public Gremlin(int l, int c) {
		super("Gremlin", new Dices(1,6,0), 5, 2, l, c);
		// TODO Auto-generated constructor stub
	}

	// Quando recebe um ataque e nao morre
	// Tem uma pequena chance dele se dividir em dois
	// Os dois criados tem a metade da vida atual do seu antecessor
}
