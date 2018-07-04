package br.com.poo.nethack.items;

import br.com.poo.nethack.player.Player;
// Faz o inimigo dormir
public class PotionSleeping extends Potions{

	public PotionSleeping(int quant) {
		super("Potion of Sleeping", "Good Night Cinderella", quant, 20, 32*27, 32*16);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void use(Player p, int index) {
//		int direction = 0, distance = 1;
//		System.out.println("Throw in which direction?(Other number to cancel)");
//		try {
////			direction = EntradaTeclado.leInt();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (direction > 0 && direction < 10 && direction != 5) {
//			while(distance <= p.getThrow_range()) {
//				if(direction == 1) {
//					
//				}
//			}
//		}
//		
//		this.setQuant(this.getQuant() -1);
//		p.setAtual_cap(p.getAtual_cap() - this.getWeight());
//		if(this.getQuant() == 0)
//			p.dropInventory(index-1);
	}
}
