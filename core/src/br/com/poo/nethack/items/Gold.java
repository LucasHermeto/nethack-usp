package br.com.poo.nethack.items;

/**
 * class Gold: Golds podem ser encontrados dentro da dungeon
 * @author braga
 *
 */
public class Gold extends Consumables{

	public Gold(int quant, int l, int c) {
		super("Gold", "", quant, 1, l, c);
	}

	/**
	 * Quando o player pega Gold, aumenta a quantidade de gold guardada pelo player
	 */
//	public void onInteract(Player p) {
//		System.out.println("You gained " + this.getQuant() + " golds!");
//		p.setGold(p.getGold() + this.getQuant());
//	}
}
