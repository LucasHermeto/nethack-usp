package br.com.poo.nethack.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import br.com.poo.nethack.util.ScreenEnum;
import br.com.poo.nethack.util.ScreenManager;

public class SelectPlayer extends AbstractScreen{
	private Skin skin;
	private Stage stage; 
	
	@Override
	public void buildStage() {
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		// Name
		final Label nameLabel = new Label("What's your name? ", skin);
		final TextField nameTextField = new TextField("", skin);
		nameTextField.setMessageText("Name");
		
		// Classes
		final Label classesLabel = new Label("Select your role: ", skin);
		final SelectBox<String> classes = new SelectBox<String>(skin);
		classes.setAlignment(Align.right);
		classes.getList().setAlignment(Align.right);
		classes.getStyle().listStyle.selection.setRightWidth(10);
		classes.getStyle().listStyle.selection.setLeftWidth(20);
		classes.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				System.out.println(classes.getSelected());
			}
		});
		classes.setItems("Barbarian", "Priest", "Knight", "Rogue", "Healer", "Ranger", "Wizard");
		classes.setSelected("Barbarian");
		
		// Race
		final Label raceLabel = new Label("Select your race: ", skin);
		final SelectBox<String> race = new SelectBox<String>(skin);
		race.setAlignment(Align.right);
		race.getList().setAlignment(Align.right);
		race.getStyle().listStyle.selection.setRightWidth(10);
		race.getStyle().listStyle.selection.setLeftWidth(20);
		race.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				System.out.println(race.getSelected());
			}
		});
		race.setItems("Human", "Elf", "Ork", "Dwarf", "Gnome");
		race.setSelected("Human");
		
		// Button
		final TextButton okButton = new TextButton("Start", skin);
        okButton.setWidth(20f);
        okButton.setHeight(20f);
        okButton.addListener(
        		new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						ScreenManager.getInstance()
						.showScreen(ScreenEnum.GAME,
								nameTextField.getText(),
								classes.getSelected(),
								race.getSelected());
					}
				});   

		Window window = new Window("Select Player", skin);
		window.setPosition(0, 0);
		window.row().fill().expandX();
		window.add(nameLabel).colspan(2);
		window.add(nameTextField).minWidth(100).expandX().fillX().colspan(2);
		window.row();
		window.add(classesLabel).colspan(2);
		window.add(classes).maxWidth(100);
		window.row();
		window.add(raceLabel).colspan(2);
		window.add(race).maxWidth(100);
		window.row().fill().colspan(2);
		window.add(okButton).width(100);
		window.pack();
		
		window.setWidth(Gdx.graphics.getWidth());
		window.setHeight(Gdx.graphics.getHeight());
		
		stage.addActor(window);
		addActor(window);
	}

}
