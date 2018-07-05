package br.com.poo.nethack.items;

import com.badlogic.gdx.Gdx;

import br.com.poo.nethack.player.Player;
import de.tomgrill.gdxdialogs.core.GDXDialogs;
import de.tomgrill.gdxdialogs.core.GDXDialogsSystem;
import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;
import de.tomgrill.gdxdialogs.core.listener.ButtonClickListener;


public class AmuletYendor extends Armor{
	private GDXDialogs dialogs = GDXDialogsSystem.install();

	public AmuletYendor(String n, String des, int a, int w, int SpriteX, int SpriteY) {
		super("Amulet of Yendor", "The most powerful amulet", -100, 1000, 32*24, 32*17);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onInteract(Player player) {
		GDXButtonDialog bDialog = dialogs.newDialog(GDXButtonDialog.class);
		bDialog.setTitle("Congratulations!");
		bDialog.setMessage("Goodbye " + player.getName() + " the " + player.getRole().getName() + "...\n"
				+ "You died in The Dungeons of Doom on dungeon level " /*+ (this.getLevel() + 1)*/ + " with " + player.getScore() + " points,\n"
				+ "and " + player.getGold() + " pieces of gold, after " + /*time +*/ " moves.\n"
				+ "You were level " + player.getLevel() + " with a maximun of " + player.getMax_life() + " hit points when you died.\n"
				+ "R.I.P.");

		bDialog.setClickListener(new ButtonClickListener() {

			@Override
			public void click(int button) {
				Gdx.app.exit();
			}
		});

		bDialog.addButton("Ok");

		bDialog.build().show();
	}
}
