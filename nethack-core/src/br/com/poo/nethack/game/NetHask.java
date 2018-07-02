package br.com.poo.nethack.game;

import br.com.poo.nethack.util.ScreenEnum;
import br.com.poo.nethack.util.ScreenManager;

public class NetHask extends com.badlogic.gdx.Game {

	@Override
	public void create() {
        ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().showScreen( ScreenEnum.MAIN_MENU); 
	}

}
