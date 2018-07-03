package br.com.poo.nethack.items;

import br.com.poo.nethack.player.Player;

public class PotionLevel extends Potions{

	public PotionLevel(int l, int c) {
		super("Potion of gain level", "Powerful Potion", 1, 20, l, c);
		// TODO Auto-generated constructor stub
	}

	public PotionLevel() {
		super("Potion of gain level", "Powerful Potion", 1, 20, -11, -1);
	}
	
	@Override
	public void use(Player p, int index) {
		p.setScore(p.getScore() + 10);
		System.out.println("You drinked " + this.getNome() +"! You feel very powerful.");
		p.setXp(p.getXp() + p.getMissing_xp());
		
		this.setQuant(this.getQuant() -1);
		p.setAtual_cap(p.getAtual_cap() - this.getWeight());
		if(this.getQuant() == 0)
			p.dropInventory(index-1);
	}
}
