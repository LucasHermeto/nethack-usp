package br.com.poo.nethack.items;

import br.com.poo.nethack.player.Player;
import br.com.poo.nethack.util.Dices;

public class PotionAbility extends Potions{

	public PotionAbility() {
		super("Potion of gain ability", "It can increase your atributes", 1, 20, 32*24, 32*16);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void use(Player p, int index) {
		p.setScore(p.getScore() + 10);
		int what_atri = new Dices(1,6,0).Roll();
		if(what_atri == 1)
			p.setExe_Str(50);
		else if(what_atri == 2)
			p.setExe_Dex(50);
		else if(what_atri == 3)
			p.setExe_Cos(50);
		else if(what_atri == 4)
			p.setExe_Wis(50);
		else if(what_atri == 5)
			p.setExe_Int(1);
		else
			p.setExe_Cha(1);
		
		this.setQuant(this.getQuant() -1);
		p.setAtual_cap(p.getAtual_cap() - this.getWeight());
		if(this.getQuant() == 0)
			p.dropInventory(index-1);
	}
}
