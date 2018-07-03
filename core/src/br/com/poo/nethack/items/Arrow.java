package br.com.poo.nethack.items;

public class Arrow extends Throwables{

	public Arrow(int quant, int l, int c) {
		super("Arrow", "Projectile shot by Bows", quant, 1, l, c);
		// TODO Auto-generated constructor stub
	}

	public Arrow(int quant) {
		super("Arrow", "Projectile shot by Bows", quant, 1, -1, -1);
	}
}
