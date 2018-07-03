package br.com.poo.nethack.items;

/**
 * class Apple: Comida que tem peso e nutricao baixos
 * @author braga
 *
 */
public class Apple extends Food{

	public Apple(int q, int l, int c) {
		super("Apple", "HEY APPLE!", q, 50, 2, l, c);
		// TODO Auto-generated constructor stub
	}
	
	public Apple(int q) {
		super("Apple", "HEY APPLE!", q, 50, 2, -1, -1);
	}
	
}
