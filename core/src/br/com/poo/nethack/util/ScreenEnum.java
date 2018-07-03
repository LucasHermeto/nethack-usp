package br.com.poo.nethack.util;

import java.util.List;

import com.github.czyzby.noise4j.map.Grid;

import br.com.poo.nethack.player.Player;
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
            return new Game((Player) params[0], (Integer) params[1], (List<Grid>) params[2]);
        }
    };
 
    public abstract AbstractScreen getScreen(Object... params);
	
}
