package br.com.poo.nethack.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.com.poo.nethack.player.Player;

/**
 * class Itens: Representacao de todos os itens presentes nesse jogo
 * @author braga
 *
 */
public abstract class Item extends Sprite {
	private String nome;
	private String descricao;
	private int weight;
	
	public Item(String n, String des, int w, int SpriteX, int SpriteY) {
		super(new TextureRegion(new Texture(Gdx.files.internal("sprite.png")), SpriteX, SpriteY, 32, 32));
		setNome(n);
		setDescricao(des);
		setWeight(w);
	}
	
	/**
	 * Quando o player anda para a mesma posicao que um item
	 * ele armazena no inventario, porem tem excessoes
	 */
	public void onInteract(Player p) {
		p.addInventory(this);
		System.out.println("You pick a " + this.getNome() + "!");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void use(Player p, int index) {
		
	}

	public void use(Player p) {
		// TODO Auto-generated method stub
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
