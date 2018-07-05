package br.com.poo.nethack.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import br.com.poo.nethack.util.ScreenEnum;
import br.com.poo.nethack.util.ScreenManager;

/** Tela do Menu Principal
 * 
 * @author hermeto
 *
 */
public class MainMenuScreen extends AbstractScreen {
	private Texture logo;
    private Skin skin;
	
	public MainMenuScreen() {
		super();
	}

	@Override
	public void buildStage() {
		logo = new Texture(Gdx.files.internal("nethack.png"));
		Image logoImage = new Image(logo);
		logoImage.setScale(0.1f);
		logoImage.setPosition((Gdx.graphics.getWidth()) / 2 - 130f,
				(Gdx.graphics.getHeight())/ 2 + 20f);
		addActor(logoImage);
		
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        final TextButton button = new TextButton("Play", skin);

        button.setWidth(200f);
        button.setHeight(20f);
        button.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - 10f);
		
        final TextButton buttonExit = new TextButton("Exit", skin);
        buttonExit.setWidth(200f);
        buttonExit.setHeight(20f);
        buttonExit.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - 40f);
        
        // Button Listeners
        button.addListener(
        		new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						ScreenManager.getInstance().showScreen(ScreenEnum.SELECT_PLAYER);
					}
				});       
		buttonExit.addListener(
				new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						Gdx.app.exit();
					}
				});
		
        // Add to Stage
		addActor(button);
		addActor(buttonExit);
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}