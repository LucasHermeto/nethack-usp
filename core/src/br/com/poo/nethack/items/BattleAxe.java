package br.com.poo.nethack.items;

import br.com.poo.nethack.util.Dices;

/**
 * class BattleAxe: A arma mais forte corpo-a-corpo presente at� agora
 * Ela tamb�m � arma inicial do Barbarian
 * @author braga
 *
 */
public class BattleAxe extends Weapon{
	public BattleAxe() {
		super("Battle Axe", "A very powerful two-handed version of the regular Axe", new Dices(1, 10, 2), 0, 120, 32*22, 32*10);
	}
}
