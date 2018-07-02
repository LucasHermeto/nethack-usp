package br.com.poo.nethack.util;

import br.com.poo.nethack.screen.AbstractScreen;
import br.com.poo.nethack.screen.Game;
import br.com.poo.nethack.screen.MainMenuScreen;
import br.com.poo.nethack.screen.SelectPlayer;

public enum ScreenEnum {

    MAIN_MENU {
        public AbstractScreen getScreen(Object... params) {
            return new MainMenuScreen();
        }
    },
    SELECT_PLAYER {
    	public AbstractScreen getScreen(Object... params) {
    		return new SelectPlayer();
    	}
    },
    GAME {
        public AbstractScreen getScreen(Object... params) {
            return new Game((String) params[0], (String) params[1], (String) params[2]);
        }
    };
 
    public abstract AbstractScreen getScreen(Object... params);
	
}
