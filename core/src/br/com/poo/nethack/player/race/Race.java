package br.com.poo.nethack.player.race;

import br.com.poo.nethack.util.Dices;

/**
 * class Race: Representa todas as racas presentes no jogo
 * @author braga
 *
 */
public abstract class Race {
	private String name;
	private int mStr;
	private int mInt;
	private int mWis;
	private int mDex;
	private int mCon;
	private int mCha;
	private int s_hp;
	private int s_pw;
	private Dices l_l;
	private int h_l;
	
	public Race(String n, int s, int i, int w, int d, int co, int ch, int hp, int pw, Dices l, int h) {
		name = n;
		setmStr(s);
		setmInt(i);
		setmWis(w);
		setmDex(d);
		setmCon(co);
		setmCha(ch);
		setL_l(l);
		setH_l(h);
		setS_hp(hp);
		setS_pw(pw);
	}
	
	public String getName() {
		return name;
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

	public Dices getL_l() {
		return l_l;
	}

	public void setL_l(Dices l_l) {
		this.l_l = l_l;
	}

	public int getH_l() {
		return h_l;
	}

	public void setH_l(int h_l) {
		this.h_l = h_l;
	}

	public int getmStr() {
		return mStr;
	}

	public void setmStr(int mStr) {
		this.mStr = mStr;
	}

	public int getmInt() {
		return mInt;
	}

	public void setmInt(int mInt) {
		this.mInt = mInt;
	}

	public int getmWis() {
		return mWis;
	}

	public void setmWis(int mWis) {
		this.mWis = mWis;
	}

	public int getmDex() {
		return mDex;
	}

	public void setmDex(int mDex) {
		this.mDex = mDex;
	}

	public int getmCon() {
		return mCon;
	}

	public void setmCon(int mCon) {
		this.mCon = mCon;
	}

	public int getmCha() {
		return mCha;
	}

	public void setmCha(int mCha) {
		this.mCha = mCha;
	}
}
