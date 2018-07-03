package br.com.poo.nethack.items;

/**
 * class LeatherArmor: Armadura leve e com AC muito forte
 * Armadura inicial do Rogue
 * @author braga
 *
 */
public class LeatherArmor extends Armor{
	public LeatherArmor(int l, int c) {
		super("Leather Armor", "Leather Armor is a type of body armor", 2, 150, l, c);
		// TODO Auto-generated constructor stub
	}
	
	public LeatherArmor() {
		super("Leather Armor", "Leather Armor is a type of body armor", 2, 150, -1, -1);
	}
}
