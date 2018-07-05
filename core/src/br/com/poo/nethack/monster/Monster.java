package br.com.poo.nethack.monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.com.poo.nethack.util.Dices;
import br.com.poo.nethack.player.Player;
// Fazer os monstros perseguirem
/**
 * class Monster: Representa todos os monstros que tem no jogo
 * @author braga
 *
 */
public abstract class Monster extends Sprite{
	private Dices damage;
	private int level;
	private int AC;
	private int xp;
	private int hp;
	private String name;
	public Monster(String n, Dices d, int le, int a, int SpriteX, int SpriteY) {
		super(new TextureRegion(new Texture(Gdx.files.internal("sprite.png")), SpriteX, SpriteY, 32, 32));
		// TODO Auto-generated constructor stub
		setDamage(d);
		setLevel(le);
		setAC(a);
		setName(n);
		
		if(level != 0)
			setHp(new Dices(level,8,0).Roll());
		else
			setHp(new Dices(1,4,0).Roll());
		setXp((level*level)+1);
		if(AC == 2)
			setXp(getXp() + 5);
		else if(AC == 1)
			setXp(getXp() + 6);
		else if(AC == 0)
			setXp(getXp() + 7);
		else if(AC < 0)
			setXp(getXp() + (14+(-2*AC)));
		if(level >= 9)
			setXp(getXp() + 50);
	}
	public Dices getDamage() {
		return damage;
	}
	public void setDamage(Dices damage) {
		this.damage = damage;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getAC() {
		return AC;
	}
	public void setAC(int aC) {
		AC = aC;
	}
	public int getXp() {
		return xp;
	}
	public void setXp(int xp) {
		this.xp = xp;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public String onInteract(Player p) {
		String returnString = "";
		if(p.getState_cap().equals(new String("Strained")) || p.getState_cap().equals("Overloaded"))
			returnString += "You are carrying so many things that You can't move a muscle"; // Testa
		else {
			p.setExe_Str(p.getExe_Str()+1);
			int to_hit = new Dices(1,20,0).Roll();
			//System.out.println("to_hitDices: " + to_hit);
			int to_hit_bonus = 5;
			to_hit_bonus += p.getWield_w().getHitBonus();
			to_hit_bonus += p.getTo_hitStr();
			to_hit_bonus += p.getTo_hitDex();
			to_hit_bonus += p.getLevel();
			if(p.getLevel() == 1 || p.getLevel() == 2)
				to_hit_bonus++;
			if(p.getState_cap().equals(new String("Stressed")))
				to_hit_bonus -= 3;
			if(p.getState_cap().equals(new String("Strained")))
				to_hit_bonus -= 5;
			//to_hit_bonus -= (10 - AC)/2;
			//System.out.println("to_hit_bonus: " + to_hit_bonus);
			if(to_hit_bonus > to_hit) {
				p.setExe_Dex(p.getExe_Dex()+1); // Apenas quando o player causa dano no inimigo
				int damage = p.getWield_w().getDamage().Roll();
				int total_damage = damage;
				total_damage += p.getDamageStr();
				if(AC < 0) {
					int damage_reduction = new Dices(1,-AC,0).Roll();
					total_damage = damage - damage_reduction;
					if(total_damage <= 0)
						total_damage = 1;
					
				}
				hp -= total_damage;
				p.setReg_count(0);
				p.setPw_count(0);
				returnString += "You hitted " + getName() + "!\n";
				
				if(hp <= 0) {
					returnString += getName() + " was killed!";
					p.setXp(p.getXp() + xp);
					//
					// REMOVE DO JOGOO
					// E COLOCA UM ITEM
					//
					p.setScore(p.getScore() + xp*4);
				}
			}else
				returnString += "You missed the attack!\n";
		}
		if(hp > 0) {
			int to_hit = new Dices(1,20,0).Roll();
			int target_number = 10 + p.getAC() + level;
			if(to_hit < target_number) {
				returnString += name + " hitted you!";
				p.setLife(p.getLife() - damage.Roll());
			}else {
				returnString += name + " missed the attack!";
			}
		}
		
		return returnString;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
