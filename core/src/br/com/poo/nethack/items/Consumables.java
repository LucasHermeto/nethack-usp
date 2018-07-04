package br.com.poo.nethack.items;

/**
 * class Consumables: Representa todos aqueles itens
 * que possuem stacks e pode ser usados ate acabarem os stacks
 * @author braga
 *
 */
public class Consumables extends Item{
	private int quant;
	
	public Consumables(String n, String des, int quant, int w, int SpriteX, int SpriteY) {
		super(n, des, w, SpriteX, SpriteY);
		this.setQuant(quant);
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}
	
}
