package br.com.poo.nethack.items;

import br.com.poo.nethack.player.Player;

/**
 * abstract class Armadura: Representa todas as armaduras presentes no jogo
 * AC: Se voc� recebe um ataque esse valor indica se voc� sofre dano ou se recebe dano reduzido
 * 	- Quanto menor o valor de AC menor o dano que o jogador recebe
 *  - Sem Armadura a AC do jogador fica 10    
 * Weight: Peso que o player tem que carregar ao vestir ela
 * @author braga
 *
 */
public abstract class Armor extends Item{
	private int AC;
	public Armor(String n, String des, int a, int w, int l, int c) {
		super(n, des, w,'a', l, c);
		AC = a;
	}
	
	public int getAC() {
		return AC;
	}
	/**
	 * Quando a armadura eh usada no inventario ela eh trocada com a armadura
	 * que o player esta usando no momento
	 */
	@Override
	public void use(Player p, int index) {
		System.out.println("Wield Armor now is: " + this.getNome());
		Armor ar = p.getWield_a();
		p.setWield_a(this);
		p.setInventory(index-1, ar);
	}
}
