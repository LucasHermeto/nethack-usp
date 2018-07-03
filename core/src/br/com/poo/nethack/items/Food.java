package br.com.poo.nethack.items;

import br.com.poo.nethack.player.Player;

/**
 * class Food: Representa todas as comidas dentro do jogo
 * com elas o player nao morre de fome
 * @author braga
 *
 */
public abstract class Food extends Consumables{
	private int nutrition;
	
	public Food(String n, String des, int quant, int nu, int w, int l, int c) {
		super(n, des, quant, w, l, c);
		// TODO Auto-generated constructor stub
		nutrition = nu;
	}

	public int getNutrition() {
		return nutrition;
	}

	public void setNutrition(int nutrition) {
		this.nutrition = nutrition;
	}
	
	/**
	 * Quando Food � usada o player recebe a nutrition da Food
	 * e ela eh retirada do inventario se acabar os stacks
	 */
	@Override
	public void use(Player p, int index) {
		this.setQuant(this.getQuant() -1);
		p.setNutrition(p.getNutrition() + nutrition);
		p.setAtual_cap(p.getAtual_cap() - this.getWeight());
		System.out.println("You ate a " + this.getNome() + "! You feel less hungry.");
		if(this.getQuant() == 0)
			p.dropInventory(index-1);
	}
}
