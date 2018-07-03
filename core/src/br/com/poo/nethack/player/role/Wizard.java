package br.com.poo.nethack.player.role;

import br.com.poo.nethack.items.CloakMagicResistance;
import br.com.poo.nethack.items.Quarterstaff;
import br.com.poo.nethack.util.Dices;

public class Wizard extends Role{

	public Wizard() {
		super("Wizard", 10, 18, 10, 13, 13, 10, 10, new Dices(1,3,4).Roll(), 12, new Dices(1,8,0), 1, new Quarterstaff(), new CloakMagicResistance());
		// TODO Auto-generated constructor stub
	}

}
