package br.com.poo.nethack.items;

import br.com.poo.nethack.player.Player;
import br.com.poo.nethack.util.Dices;

/**
 * abstract class Weapon: Representa todas as armas presentes no jogo
 * Damage: � o dano corpo-a-corpo que essa arma da no alvo
 * HitBonus: Aumenta a sua chance de acertar um ataque
 * Weight: Peso que o player carrega ao equipa-la
 * @author braga
 *
 */
public abstract class Weapon extends Item{
	private Dices Damage;
	private int hitBonus;
	
	public Weapon(String n, String des, Dices d, int h, int w, int SpriteX, int SpriteY) {
		super(n,des, w, SpriteX, SpriteY);
		setDamage(d);
		setHitBonus(h);
	}
	
	/**
	 * Quando usada no inventario, a arma escolhida eh equipada
	 * e a que estava equipada vai para o inventario
	 */
	@Override
	public void use(Player p, int index) {
		System.out.println("Wield Weapon now is: " + this.getNome());
		Weapon wea = p.getWield_w();
		p.setWield_w(this);
		p.setInventory(index-1, wea);
	}

	public int getHitBonus() {
		return hitBonus;
	}

	public void setHitBonus(int hitBonus) {
		this.hitBonus = hitBonus;
	}

	public Dices getDamage() {
		return Damage;
	}

	public void setDamage(Dices damage) {
		Damage = damage;
	}
}
