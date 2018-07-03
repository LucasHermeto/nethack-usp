package br.com.poo.nethack.player.role;

import br.com.poo.nethack.items.BattleAxe;
import br.com.poo.nethack.items.RingMail;
import br.com.poo.nethack.util.Dices;

/**
 * class Barbarian: Eh a classe com maior dano bruto corpo-a-corpo
 * A habilidade secreta dele � ter resistencia contra veneno
 * @author braga
 *
 */
public class Barbarian extends Role{	
	public Barbarian() {
		super("Barbarian", 18, 7, 7, 17, 18, 6, 14+3, 1, 10, new Dices(1,10,0), 2,new BattleAxe(), new RingMail());
	}
	
}
