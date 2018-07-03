package br.com.poo.nethack.items;

import br.com.poo.nethack.player.Player;
import br.com.poo.nethack.util.Dices;

/**
 * class PotionHealing: Potion que ao ser usada restaura a vida do player
 * @author braga
 *
 */
public class PotionHealing extends Potions{

	public PotionHealing(int quant, int l, int c) {
		super("Potion of Healing", "This will heal you.", quant, 20, l, c);
		// TODO Auto-generated constructor stub
	}

	public PotionHealing(int quant) {
		super("Potion of Healing", "This will heal you.", quant, 20, -1, -1);
	}
	
	@Override
	public void use(Player p, int index) {
		p.setScore(p.getScore() + 10);
		System.out.println("You drinked " + this.getNome() +"! You feel healthy.");
		p.setLife(p.getLife() + new Dices(7,4,0).Roll());
		if(p.getLife() > p.getMax_life())
			p.setLife(p.getMax_life());
		this.setQuant(this.getQuant() -1);
		p.setAtual_cap(p.getAtual_cap() - this.getWeight());
		if(this.getQuant() == 0)
			p.dropInventory(index-1);
	}
}
