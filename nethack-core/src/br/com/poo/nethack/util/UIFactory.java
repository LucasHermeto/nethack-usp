package br.com.poo.nethack.util;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class UIFactory {
	public static InputListener createListener(final ScreenEnum dstScreen, final Object... params) {
		return  new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x,
							float y, int pointer, int button) {
						ScreenManager.getInstance().showScreen(dstScreen, params);
						return false;
					}
				};
	}
}