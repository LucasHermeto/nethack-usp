package br.com.poo.nethack.player.race;

import br.com.poo.nethack.util.Dices;

/**
 * class Dwarf: A raca com maior dano bruto do jogo
 * @author braga
 *
 */
public class Dwarf extends Race{

	public Dwarf() {
		super("Dwarf", 25, 16, 16, 20, 20, 16, 4, 0, new Dices(1,3,0), 2);
		// TODO Auto-generated constructor stub
	}

}
