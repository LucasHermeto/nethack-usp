package br.com.poo.nethack.items;

/**
 * class RingMail: Armadura com AC forte
 * Armadura inicial do Knight e Barbarian
 * @author braga
 *
 */
public class RingMail extends Armor{
	public RingMail(int l, int c) {
		super("Ring Mail", "Light iron armor", 3, 250, l, c);
		// TODO Auto-generated constructor stub
	}

	public RingMail() {
		super("Ring Mail", "Light iron armor", 3, 250, -1, -1);
	}
}
