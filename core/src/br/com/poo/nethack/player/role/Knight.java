package br.com.poo.nethack.player.role;
import br.com.poo.nethack.items.LongSword;
import br.com.poo.nethack.items.RingMail;
import br.com.poo.nethack.util.Dices;

/**
 * class Knight: A classe mais padr�o dos rpgs
 * Tem um escudo que aumenta a AC do Knight
 * A habilidade secreta � que em alguns momentos o escudo anula totalmente o dano
 * @author braga
 *
 */
public class Knight extends Role{

	public Knight() {
		super("Knight", 15, 8, 15, 8, 11, 17, 14, 3, 10, new Dices(1,8,0), 2, new LongSword(), new RingMail());
		// TODO Auto-generated constructor stub
	}

}
