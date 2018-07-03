package br.com.poo.nethack.player.role;
import br.com.poo.nethack.items.LeatherArmor;
import br.com.poo.nethack.items.ShortSword;
import br.com.poo.nethack.util.Dices;

/**
 * class Rogue: fragil mas r�pida no ataque
 * A habilidade secreta dela � Stealth
 * @author braga
 *
 */
public class Rogue extends Role{

	public Rogue() {
		super("Rogue", 15, 14, 14, 18, 14, 8, 10, 1, 11, new Dices(1,8,0), 1,new ShortSword(), new LeatherArmor());
		// TODO Auto-generated constructor stub
	}

}
