package br.com.poo.nethack.items;

import br.com.poo.nethack.player.Player;
import br.com.poo.nethack.util.Dices;

public class PotionInvisibility extends Potions{

	public PotionInvisibility(int quant) {
		super("Potion of invisibility", "Now you see and now you don't", quant, 20, 32*27, 32*16);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void use(Player p) {
		p.setScore(p.getScore() + 10);
		p.setVisible(0);
		p.setInvi_count(30 + new Dices(1,10,0).Roll());
		System.out.println("Far out, man! You can't see yourself.");
	}
}
