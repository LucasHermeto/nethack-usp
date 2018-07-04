package br.com.poo.nethack.monster;

import br.com.poo.nethack.util.Dices;

public class FloatingEye extends Monster{

	public FloatingEye() {
		super("Floating Eye", new Dices(1,0,0), 2, 9, 32*29, 32*0);
		// TODO Auto-generated constructor stub
	}

	// PARALISA O PLAYER
	// QUando recebe um ataque e nao morre
	// Every time you attack a floating eye with a melee attack,
	// there is a 2/3 chance that it will use its gaze attack against you if your attack did not kill it.
	// You are paralysed for (LEV + 1)d70 turns if your wisdom is greater than 12.
}
