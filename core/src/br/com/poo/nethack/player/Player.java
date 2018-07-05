package br.com.poo.nethack.player;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.com.poo.nethack.items.Apple;
import br.com.poo.nethack.items.Armor;
import br.com.poo.nethack.items.Arrow;
import br.com.poo.nethack.items.Consumables;
import br.com.poo.nethack.items.FoodRation;
import br.com.poo.nethack.items.Item;
import br.com.poo.nethack.items.PotionAbility;
import br.com.poo.nethack.items.PotionExtraHealing;
import br.com.poo.nethack.items.PotionHealing;
import br.com.poo.nethack.items.PotionInvisibility;
import br.com.poo.nethack.items.PotionSleeping;
import br.com.poo.nethack.items.Weapon;
import br.com.poo.nethack.player.race.Dwarf;
import br.com.poo.nethack.player.race.Elf;
import br.com.poo.nethack.player.race.Gnome;
import br.com.poo.nethack.player.race.Human;
import br.com.poo.nethack.player.race.Orc;
import br.com.poo.nethack.player.race.Race;
import br.com.poo.nethack.player.role.Barbarian;
import br.com.poo.nethack.player.role.Healer;
import br.com.poo.nethack.player.role.Knight;
import br.com.poo.nethack.player.role.Priest;
import br.com.poo.nethack.player.role.Ranger;
import br.com.poo.nethack.player.role.Rogue;
import br.com.poo.nethack.player.role.Role;
import br.com.poo.nethack.player.role.Wizard;
import br.com.poo.nethack.util.Dices;
/**
 * class Player: Representa o player do jogo
 * @author braga
 * @author hermeto
 *
 */
public class Player extends Sprite {
	private String name;
	private Role role;
	private Race race;
	private String gender;
	private int level = 1;
	private int gold = 0;
	private List<Item> Inventory = new ArrayList<Item>();
	public static final char REPR = 'P';
	private int life = 0;
	private int max_life = 0;
	private int power = 0;
	private int max_power = 0;
	private int xp = 0;
	private int regeneration = 1;
	private int reg_count = 0; // Zera quando e atacado
	private int reg_turn = 15;
	private int pw_count;
	private int nutrition = 900;
	private int max_cap;
	private int atual_cap;
	private int count_cap = 0;
	private int to_hitStr = 0;
	private int to_hitDex = 0;
	private int damageStr = 0;
	private int exe_Str = 0;
	private int exe_Dex = 0;
	private int exe_Cos = 0;
	private int exe_Wis = 0;
	private int exe_Int = 0;
	private int exe_Cha = 0;
	private int cap_Str = 0;
	private int cap_Cons = 0;
	private int cons_hp = 0;
	private int missing_xp = 0;
	private int visible = 0;
	private int invi_count = 0;
	private int throw_range = 0;
	private int score = 0;
	private String state_cap = "";
	private String nu_state = "";
	private Weapon wield_w;
	private Armor wield_a;
	
	public Player(String name, int role, int race, int gender, int spriteX, int spriteY) {
		super(new TextureRegion(new Texture(Gdx.files.internal("sprite.png")), spriteX, spriteY, 32, 32));
		
		this.name = name;

		// Adiciona Classe
		if(role == 1) {
			setRole(new Barbarian());
		
			addInventory(new FoodRation(2));
		}else if (role == 2) {
			setRole(new Priest());
			
		}else if (role == 3) {
			setRole(new Knight());
			
			addInventory(new Apple(new Dices(1,11,9).Roll()));
		}else if (role == 4) {
			setRole(new Rogue());
			
			addInventory(new PotionInvisibility(3));
		}else if(role == 5){
			setRole(new Healer());
			
			setGold(new Dices(1,1000, 1000).Roll());
			addInventory(new Apple(new Dices(1,5,5).Roll()));
			addInventory(new PotionHealing(4));
			addInventory(new PotionExtraHealing(4));
		}else if(role == 6){
			setRole(new Ranger());
			
			addInventory(new FoodRation(2));
			addInventory(new Arrow(new Dices(1,100, 80).Roll()));
		}else if(role == 7){
			setRole(new Wizard());
			
			addInventory(new PotionAbility());
			addInventory(new PotionSleeping(1));
			addInventory(new PotionHealing(1));
		}
		
		// Adiciona Raca
		if(race == 1) {
			setRace(new Human());
		}else if (race == 2) {
			setRace(new Elf());
		}else if (race == 3) {
			setRace(new Orc());
		}else if (race == 4) {
			setRace(new Dwarf());
		}else if (race == 5) {
			setRace(new Gnome());
		}
		
		// Adiciona genero
		if(gender == 1) {
			setGender("Male");
		}else if (gender == 2) {
			setGender("Female");
		}else if (gender == 3) {
			setGender("Agender");
		}
	}
	
	public int getLife() {
		return this.life;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String s) {
		name = s;
	}
	
	public void setRole(Role s) {
		role = s;
		setLife(getLife() + s.getS_hp());
		setMax_life(getMax_life() + s.getS_hp());
		power += s.getS_pw();
		max_power += s.getS_pw();
		setWield_w(s.getWea());
		setWield_a(s.getArm());
	}
	
	public void setGender(String g) {
		gender = g;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setRace(Race r) {
		race = r;
		setLife(getLife() + r.getS_hp());
		setMax_life(getMax_life() + r.getS_hp());
		power += r.getS_pw();
		max_power += r.getS_pw();
	}
	
	public String getRacename() {
		return race.getName();
	}
	
	public Role getRole() {
		return role;
	}
	
	public Race getRace() {
		return race;
	}
	
	public String getClasse() {
		return role.getName();
	}
	
	public int getSt() {
		if(nu_state.equals(new String("Fainting")))
			return role.getStr() - 2;
		return role.getStr();
	}
	public int getDx() {
		return role.getDex();
	}
	public int getCo() {
		return role.getCon();
	}
	public int getIn() {
		return role.getInt();
	}
	public int getWi() {
		return role.getWis();
	}
	public int getCh() {
		return role.getCha();
	}

	public int getNivel() {
		return getLevel();
	}

	public void setNivel(int nivel) {
		setLevel(nivel);
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getMax_life() {
		return max_life;
	}

	public void setMax_life(int max_life) {
		this.max_life = max_life;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getMax_power() {
		return max_power;
	}

	public void setMax_power(int max_power) {
		this.max_power = max_power;
	}
	
	public int getAC() {
		return wield_a.getAC();
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}
	
	/**
	 * Funcao que adiciona um item no inventario do player
	 * @param i
	 */
	public void addInventory(Item i) {
		getInventory().add(i);
		if(!(i instanceof Consumables))
			setAtual_cap(getAtual_cap() + i.getWeight());
		else
			setAtual_cap(getAtual_cap() + ((Consumables) i).getWeight() * ((Consumables) i).getQuant());
	}

	public Weapon getWield_w() {
		return wield_w;
	}

	public void setWield_w(Weapon wield_w) {
		this.wield_w = wield_w;
		setAtual_cap(getAtual_cap() + wield_w.getWeight());
	}

	public Armor getWield_a() {
		return wield_a;
	}

	public void setWield_a(Armor wield_a) {
		this.wield_a = wield_a;
		setAtual_cap(getAtual_cap() + wield_a.getWeight());
	}
	
	public void setInventory(int index, Item i) {
		Inventory.set(index, i);
	}
	
	/**
	 * Funcao que dropa um item do inventario
	 * @param index
	 */
	public void dropInventory(int index) {
		if(!(getInventory().get(index) instanceof Consumables)) {
			setAtual_cap(getAtual_cap() - getInventory().get(index).getWeight());
		}else { 
			setAtual_cap(getAtual_cap() - ((Consumables)getInventory().get(index)).getWeight() * ((Consumables)getInventory().get(index)).getQuant());
		}
		getInventory().remove(index);
	}
	
	/**
	 * Funcao responsavel pelo sistema de level do personagem
	 * para upar para o proximo level, e cuidar dos status que aumentam por causa disso
	 */
	public void levelUp() {
		boolean levelupped = false;
		if(getLevel() < 10) {
			if(xp >= Math.pow(2, getLevel()+1)*10) {
				setLevel(getLevel() + 1);
				levelupped = true;
			}
			missing_xp = (int) ((Math.pow(2, getLevel()+1)*10) - xp); 
			if(getLevel() == 1)
				reg_turn = 15;
			else if(getLevel() == 2)
				reg_turn = 11;
			else if(getLevel() == 3)
				reg_turn = 9;
			else if(getLevel() == 4)
				reg_turn = 8;
			else if(getLevel() == 5)
				reg_turn = 7;
			else if(getLevel() == 6)
				reg_turn = 6;
			else if(getLevel() == 7)
				reg_turn = 5;
			else if(getLevel() == 8)
				reg_turn = 5;
			else if(getLevel() == 9)
				reg_turn = 4;
			else if(getLevel() == 10)
				reg_turn = 3;
		}else if (getLevel() >= 10 && getLevel() < 20) {
			if(xp >= Math.pow(2, getLevel()-9)*10000) {
				setLevel(getLevel() + 1);
				levelupped = true;
			}
			missing_xp = (int) ((Math.pow(2, getLevel()-9)*10000) - xp);
		}else if(getLevel() >= 20) {
			if(xp >= (getLevel()-19)*10000000) {
				setLevel(getLevel() + 1);
				levelupped = true;
			}
			missing_xp = ((getLevel()-19)*10000000) - xp;
		}
		if(levelupped) {
			System.out.println("You feel more powerful now!");
			if(this.getLevel() < role.getH_level()) {
				int lowRoleDice = role.getLow().Roll();
				int lowRaceDice = race.getL_l().Roll();
				this.setLife(life + lowRoleDice + lowRaceDice);
				this.setMax_life(max_life + lowRoleDice + lowRaceDice);
			}else {
				this.setLife(life + role.getHigh() + race.getH_l());
				this.setMax_life(max_life + role.getHigh() + race.getH_l());
			}
			this.setLife(life + cons_hp);
			this.setMax_life(max_life + cons_hp);
			power += ((role.getWis()/2)+1);
			max_power += ((role.getWis()/2)+1);
		}
		if(getLevel() > 10)
			if(role.getCon() > 12)
				regeneration = new Dices(1,role.getCon(),0).Roll();
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getReg_count() {
		return reg_count;
	}

	public void setReg_count(int reg_count) {
		this.reg_count = reg_count;
	}
	
	/**
	 * Funcao que cuida do sistema de Regeneracao de vida e energia do player
	 */
	public void Regeneration() {
		reg_count++;
		setPw_count(getPw_count() + 1);
		if(getState_cap().equals(new String("Stressed")) || getState_cap().equals(new String("Strained")) || getState_cap().equals(new String("Overloaded"))) {
			reg_count = 0;
			setPw_count(0);
		}
		if(reg_count == reg_turn) {
			reg_count = 0;
			life += regeneration;
			if(life > max_life) {
				life = max_life;
			}
		}
		if(getPw_count() > (38 - getLevel())*(2/3)) {
			setPw_count(0);
			power += new Dices(1,((role.getWis() + role.getInt())/15)+1,0).Roll();
			if(power > max_power)
				power = max_power;
		}
	}
	
	/**
	 * Funcao que cuida do sistema de Alimentacao do player
	 */
	public void Nutrition() {
		setNutrition(getNutrition() - 1);
		if(getState_cap().equals(new String("Stressed")) || getState_cap().equals(new String("Strained")) || getState_cap().equals(new String("Overloaded")))
			setNutrition(getNutrition() - 1);
		if(getNutrition() >= 1000)
			setNu_state("Satiated");
		else if(getNutrition() >= 150 && getNutrition() < 1000)
			setNu_state("Not Hungry");
		else if(getNutrition() >= 50 && getNutrition() < 150)
			setNu_state("Hungry");
		else if(getNutrition() > 0 && getNutrition() < 50) {
			setNu_state("Fainting");
			System.out.println("You feel weak!");
		}else if(getNutrition() <= 0)
			life = 0;
	}

	public int getNutrition() {
		return nutrition;
	}

	public void setNutrition(int nutrition) {
		this.nutrition = nutrition;
	}

	public String getNu_state() {
		return nu_state;
	}

	public void setNu_state(String nu_state) {
		this.nu_state = nu_state;
	}
	
	/**
	 * Funcao que cuida do Sistema de cansaco baseado nos itens que o player carrega
	 */
	public void Capacity() {
		max_cap = (25*(this.getSt() + this.getCo()) + 25);
		if(getAtual_cap() < (1.5*max_cap))
			setState_cap("Unencumbered");
		else if (getAtual_cap() >= 1.5*max_cap && getAtual_cap() < 2*max_cap)
			setState_cap("Stressed");
		else if(getAtual_cap() >= 2*max_cap && getAtual_cap() < 3*max_cap)
			setState_cap("Strained");
		else if(getAtual_cap() > 3*max_cap)
			setState_cap("Overloaded");
		
		if(getState_cap().equals(new String("Strained")) || getState_cap().equals(new String("Overloaded"))) {
			count_cap++;
			if(count_cap == 10) {
				count_cap = 0;
				life -= 1;
			}
		}
	}

	public String getState_cap() {
		return state_cap;
	}

	public void setState_cap(String state_cap) {
		this.state_cap = state_cap;
	}
	
	/**
	 * Essa funcao contem todas as funcoes que precisam ser utilizadas todo os turnos em que o player estiver vivo
	 */
	public void Alive() {
		Nutrition();
		levelUp();
		Capacity();
		Regeneration();
		Strength();
		Dexterity();
		Constitution();
		Exercise();
		
		if(visible == 0) {
			invi_count --;
			if(invi_count == 0) {
				visible = 1;
				System.out.println("You can see yourself again.");
			}
		}
	}

	public int getAtual_cap() {
		return atual_cap;
	}

	public void setAtual_cap(int atual_cap) {
		this.atual_cap = atual_cap;
	}

	public int getTo_hitStr() {
		return to_hitStr;
	}

	public void setTo_hitStr(int to_hitStr) {
		this.to_hitStr = to_hitStr;
	}

	public int getDamageStr() {
		return damageStr;
	}

	public void setDamageStr(int damageStr) {
		this.damageStr = damageStr;
	}
	
	/**
	 * Funcao que calcula a influencia da forca na vida do jogador
	 */
	public void Strength() { 
		if(role.getStr() >= 3 && role.getStr() <6) {
			to_hitStr = -2;
			damageStr = -1;
			throw_range = 3;
		}else if (role.getStr() >= 6 && role.getStr() < 8) {
			to_hitStr = -1;
			damageStr = 0;
			throw_range = 4;
		}else if (role.getStr() >= 8 && role.getStr() < 16) {
			to_hitStr = 0;
			damageStr = 0;
			if(role.getStr() == 8 || role.getStr() == 9)
				throw_range = 5;
			else if(role.getStr() == 10 || role.getStr() == 11)
				throw_range = 6;
			else if(role.getStr() == 12 || role.getStr() == 13)
				throw_range = 7;
			else if(role.getStr() == 14 || role.getStr() == 14)
				throw_range = 8;
		}else if (role.getStr() == 16) {
			to_hitStr = 0;
			damageStr = 1;
			throw_range = 9;
		}else if (role.getStr() == 17) {
			to_hitStr = 1;
			damageStr = 1;
			throw_range = 9;
		}else if (role.getStr() == 18) {
			to_hitStr = 1;
			damageStr = 2;
			throw_range = 10;
		}else if (role.getStr() == 19 || role.getStr() == 20) {
			to_hitStr = 1;
			damageStr = 3;
			throw_range = 11;
		}else if (role.getStr() == 21 || role.getStr() == 22) {
			to_hitStr = 2;
			damageStr = 3;
			throw_range = 11;
		}else if (role.getStr() == 23) {
			to_hitStr = 2;
			damageStr = 4;
			throw_range = 12;
		}else if (role.getStr() == 24) {
			to_hitStr = 2;
			damageStr = 5;
			throw_range = 13;
		}else if (role.getStr() == 25) {
			to_hitStr = 3;
			damageStr = 6;
			throw_range = 13;
		}
		
		//System.out.println("To_hitStr: " + to_hitStr + ", damageStr: " + damageStr);
	}
	
	/**
	 * Funcao que calcula a influencia da Destreza na vida do jogador
	 */
	public void Dexterity() { 
		if(role.getDex() == 3)
			to_hitDex = -3;
		else if(role.getDex() == 4 || role.getDex() == 5)
			to_hitDex = -2;
		else if(role.getDex() == 6 || role.getDex() == 7)
			to_hitDex = -1;
		else if(role.getDex() >= 8 && role.getDex() <=14)
			to_hitDex = 0;
		else if(role.getDex() == 15)
			to_hitDex = 1;
		else if(role.getDex() == 16)
			to_hitDex = 2;
		else if(role.getDex() == 17)
			to_hitDex = 3;
		else if(role.getDex() == 18)
			to_hitDex = 4;
		else if(role.getDex() == 19)
			to_hitDex = 5;
		else if(role.getDex() == 20)
			to_hitDex = 6;
		else if(role.getDex() == 21)
			to_hitDex = 7;
		else if(role.getDex() == 22)
			to_hitDex = 8;
		else if(role.getDex() == 23)
			to_hitDex = 9;
		else if(role.getDex() == 24)
			to_hitDex = 10;
		else if(role.getDex() == 25)
			to_hitDex = 11;
		
		//System.out.println("to_hitDex: " + to_hitDex);
	}
	
	public void Constitution() { 
		if(role.getCon() == 3)
			cons_hp = -2;
		else if(role.getCon() >= 4 && role.getCon() <= 6)
			cons_hp = -1;
		else if(role.getCon() >= 7 && role.getCon() <= 14)
			cons_hp = 0;
		else if(role.getCon() >= 15 && role.getCon() <= 16)
			cons_hp = 1;
		else if(role.getCon() == 17)
			cons_hp = 2;
		else if(role.getCon() == 18)
			cons_hp = 3;
		else
			cons_hp = 4;
	}
	
	/**
	 * Quando o player realiza atividades ele pode aumentar o status de sua Forca,
	 * Destreza, Constituicao e Sabedoria
	 */
	public void Exercise() { 
		if(getState_cap().equals(new String("Stressed")) || getState_cap().equals(new String("Strained"))) {
			cap_Str++;
			if(cap_Str == 10) {
				setExe_Str(getExe_Str() + 1);
				cap_Str = 0;
			}
		}else
			cap_Str = 0;
		
		if(getNu_state().equals(new String("Not Hungry")))
			cap_Cons++;
		else
			cap_Cons = 0;
		if(cap_Cons == 10) {
			cap_Cons = 0;
			setExe_Cos(getExe_Cos() + 1);
		}
		
		if(getExe_Str() == 50) {
			setExe_Str(0);
			if(role.getStr() < race.getmStr()) {
				role.setStr(role.getStr()+1);
				System.out.println("You feel strong!");
			}else
				System.out.println("You're already as strong as you can get.");
		}
		if(getExe_Dex() == 50) {
			setExe_Dex(0);
			if(role.getDex() < race.getmDex()) {
				role.setDex(role.getDex()+1);
				System.out.println("You feel agile! You must have been working on your reflexes.");
			}else
				System.out.println("You're already as agile as you can get.");
		}
		if(getExe_Cos() == 50) {
			setExe_Cos(0);
			if(role.getCon() < race.getmCon()) {
				role.setCon(role.getCon()+1);
				System.out.println("You feel tough! You must be leading a healthy life-style.");
			}else
				System.out.println("You're already as tough as you can get.");
		}
		if(getExe_Wis() == 50) {
			setExe_Wis(0);
			if(role.getWis() < race.getmWis()) {
				role.setWis(role.getWis()+1);
				System.out.println("You feel wise!  You must have been very observant.");
				// Se conseguir lan�ar uma magia Exercita o Wis
			}else
				System.out.println("You're already as wise as you can get.");
		}
		if(getExe_Int() == 1) {
			setExe_Int(0);
			if(role.getInt() < race.getmInt()) {
				role.setInt(role.getInt()+1);
				System.out.println("You feel smart! You must have been 200% USP Estudent");
			}else
				System.out.println("You're already as smart as you can get.");
		}
		if(getExe_Cha() == 1) {
			setExe_Cha(0);
			if(role.getCha() < race.getmCha()) {
				role.setCha(role.getCha()+1);
				System.out.println("You feel charismatic!");
			}else
				System.out.println("You're already as charismact as you can get.");
		}
	}

	public int getExe_Str() {
		return exe_Str;
	}

	public void setExe_Str(int exe_Str) {
		this.exe_Str = exe_Str;
	}

	public int getTo_hitDex() {
		return to_hitDex;
	}

	public void setTo_hitDex(int to_hitDex) {
		this.to_hitDex = to_hitDex;
	}

	public int getExe_Dex() {
		return exe_Dex;
	}

	public void setExe_Dex(int exe_Dex) {
		this.exe_Dex = exe_Dex;
	}

	public int getCons_hp() {
		return cons_hp;
	}

	public void setCons_hp(int cons_hp) {
		this.cons_hp = cons_hp;
	}

	public int getExe_Cos() {
		return exe_Cos;
	}

	public void setExe_Cos(int exe_Cos) {
		this.exe_Cos = exe_Cos;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMissing_xp() {
		return missing_xp;
	}

	public void setMissing_xp(int missing_xp) {
		this.missing_xp = missing_xp;
	}

	public int getExe_Wis() {
		return exe_Wis;
	}

	public void setExe_Wis(int exe_Wis) {
		this.exe_Wis = exe_Wis;
	}

	public int getExe_Int() {
		return exe_Int;
	}

	public void setExe_Int(int exe_Int) {
		this.exe_Int = exe_Int;
	}

	public int getExe_Cha() {
		return exe_Cha;
	}

	public void setExe_Cha(int exe_Cha) {
		this.exe_Cha = exe_Cha;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public int getInvi_count() {
		return invi_count;
	}

	public void setInvi_count(int invi_count) {
		this.invi_count = invi_count;
	}

	public int getThrow_range() {
		return throw_range;
	}

	public void setThrow_range(int throw_range) {
		this.throw_range = throw_range;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<Item> getInventory() {
		return Inventory;
	}

	public void setInventory(ArrayList<Item> inventory) {
		Inventory = inventory;
	}

	public int getPw_count() {
		return pw_count;
	}

	public void setPw_count(int pw_count) {
		this.pw_count = pw_count;
	}
}
