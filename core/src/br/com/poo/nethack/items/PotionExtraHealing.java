package br.com.poo.nethack.items;

import br.com.poo.nethack.player.Player;
import br.com.poo.nethack.util.Dices;

/**
 * class PotionExtraHealing: Potion que ao ser usado restaura muitos pontos de vida do player
 * @author braga
 *
 */
public class PotionExtraHealing extends Potions{

	public PotionExtraHealing(int quant, int l, int c) {
		super("Potion of Extra Healing", "This will heal you a lot.", quant, 20, l, c);
		// TODO Auto-generated constructor stub
	}

	public PotionExtraHealing(int quant) {
		super("Potion of Extra Healing", "This will heal you a lot.", quant, 20, -1, -1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void use(Player p, int index) {
		p.setScore(p.getScore() + 10);
		System.out.println("You drinked " + this.getNome() +"! You feel very healthy.");
		p.setLife(p.getLife() + new Dices(7,8,0).Roll());
		if(p.getLife() > p.getMax_life())
			p.setLife(p.getMax_life());
		this.setQuant(this.getQuant() -1);
		p.setAtual_cap(p.getAtual_cap() - this.getWeight());
		if(this.getQuant() == 0)
			p.dropInventory(index-1);
		p.setExe_Str(p.getExe_Str()+1);
	}

}
