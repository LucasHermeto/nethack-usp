package br.com.poo.nethack.items;

/**
 * class FoodRation: O melhor tipo de comida
 * Consegue manter o seu personagem satisfeito por um bom tempo
 * @author braga
 *
 */
public class FoodRation extends Food{

	public FoodRation(int l, int c) {
		super("Food Ration", "A complete meal for your stomach.", 1, 800, 20, l, c);
		// TODO Auto-generated constructor stub
	}

	public FoodRation() {
		super("Food Ration", "A complete meal for your stomach.", 1, 800, 20, -1, -1);
	}
}
