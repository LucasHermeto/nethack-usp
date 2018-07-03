package br.com.poo.nethack.player.role;
import br.com.poo.nethack.items.Armor;
import br.com.poo.nethack.items.Weapon;
import br.com.poo.nethack.util.Dices;

/**
 * abstract classe Classe: Representa todas as roles que o player pode ser
 * @author braga
 *
 */
public abstract class Role {
	private String name;
	private int Str;
	private int Int;
	private int Wis;	// Atributos b�sicos que vem junto com a classe
	private int Dex;
	private int Con;
	private int Cha;
	private int s_hp;	// Vida e energia iniciais
	private int s_pw;
	private Weapon wea;
	private Armor arm;
	private int h_level;
	private Dices low;
	private int high;
	
	public Role (String n, int s, int i, int w, int d, int co, int cha, int hp, int pw, int h_l, Dices lo, int hig, Weapon we, Armor ar) {
		name = n;
		Str = s;
		s_hp = hp;
		setH_level(h_l);
		setLow(lo);
		setHigh(hig);
		setS_pw(pw);
		setInt(i);
		setWis(w);
		setDex(d);
		setCon(co);
		setCha(cha);
		setArm(ar);
		setWea(we);
	}
	
	public String getName() {
		return name;
	}
	
	public int getStr() {
		return Str;
	}
	
	public void setStr(int s) {
		Str = s;
	}

	public int getInt() {
		return Int;
	}

	public void setInt(int i) {
		Int = i;
	}

	public int getWis() {
		return Wis;
	}

	public void setWis(int wis) {
		Wis = wis;
	}

	public int getDex() {
		return Dex;
	}

	public void setDex(int dex) {
		Dex = dex;
	}

	public int getCon() {
		return Con;
	}

	public void setCon(int con) {
		Con = con;
	}

	public int getCha() {
		return Cha;
	}

	public void setCha(int cha) {
		Cha = cha;
	}

	public int getS_hp() {
		return s_hp;
	}

	public void setS_hp(int s_hp) {
		this.s_hp = s_hp;
	}

	public int getS_pw() {
		return s_pw;
	}

	public void setS_pw(int s_pw) {
		this.s_pw = s_pw;
	}
	
	public int getAC() {
		return getArm().getAC();
	}

	public Weapon getWea() {
		return wea;
	}

	public void setWea(Weapon wea) {
		this.wea = wea;
	}

	public Armor getArm() {
		return arm;
	}

	public void setArm(Armor arm) {
		this.arm = arm;
	}

	public int getH_level() {
		return h_level;
	}

	public void setH_level(int h_level) {
		this.h_level = h_level;
	}

	public Dices getLow() {
		return low;
	}

	public void setLow(Dices low) {
		this.low = low;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}
}
