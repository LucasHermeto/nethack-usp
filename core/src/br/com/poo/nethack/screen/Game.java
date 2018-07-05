package br.com.poo.nethack.screen;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.github.czyzby.noise4j.map.Grid;
import com.github.czyzby.noise4j.map.generator.room.dungeon.DungeonGenerator;

import br.com.poo.nethack.items.Apple;
import br.com.poo.nethack.items.Axe;
import br.com.poo.nethack.items.BattleAxe;
import br.com.poo.nethack.items.Bow;
import br.com.poo.nethack.items.CloakDisplacement;
import br.com.poo.nethack.items.CloakMagicResistance;
import br.com.poo.nethack.items.Consumables;
import br.com.poo.nethack.items.Dagger;
import br.com.poo.nethack.items.FoodRation;
import br.com.poo.nethack.items.Gold;
import br.com.poo.nethack.items.Item;
import br.com.poo.nethack.items.LeatherArmor;
import br.com.poo.nethack.items.LongSword;
import br.com.poo.nethack.items.Mace;
import br.com.poo.nethack.items.PotionAbility;
import br.com.poo.nethack.items.PotionExtraHealing;
import br.com.poo.nethack.items.PotionHealing;
import br.com.poo.nethack.items.PotionLevel;
import br.com.poo.nethack.items.Quarterstaff;
import br.com.poo.nethack.items.RingMail;
import br.com.poo.nethack.items.Robe;
import br.com.poo.nethack.items.Scalpel;
import br.com.poo.nethack.items.ShortSword;
import br.com.poo.nethack.items.StaircaseDown;
import br.com.poo.nethack.items.WarHammer;
import br.com.poo.nethack.monster.Dingo;
import br.com.poo.nethack.monster.FloatingEye;
import br.com.poo.nethack.monster.Gremlin;
import br.com.poo.nethack.monster.Jackal;
import br.com.poo.nethack.monster.Kobold;
import br.com.poo.nethack.monster.KoboldLord;
import br.com.poo.nethack.monster.Monster;
import br.com.poo.nethack.monster.RabidRat;
import br.com.poo.nethack.monster.SewerRat;
import br.com.poo.nethack.monster.Warg;
import br.com.poo.nethack.player.Player;
import br.com.poo.nethack.util.Dices;
import br.com.poo.nethack.util.ScreenEnum;
import br.com.poo.nethack.util.ScreenManager;
import de.tomgrill.gdxdialogs.core.GDXDialogs;
import de.tomgrill.gdxdialogs.core.GDXDialogsSystem;
import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;
import de.tomgrill.gdxdialogs.core.listener.ButtonClickListener;

/** Classe principal do jogo
 * 
 * @author hermeto
 *
 */
public class Game extends AbstractScreen implements InputProcessor{
	// Mapa
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private List<Grid> grid;
    private int level = 0;
    
    // Camera
    private OrthographicCamera camera;
    
    // Jogador
    private SpriteBatch sb;
    private Texture texture;
    private Player player;

    private int playerX = 0;
    private int playerY = 0;
    private int time = 0;
    
    // Inventario
    private boolean isOpen = false;
//    private List<Sprite> items = new ArrayList<Sprite>();
    
    // Textos
    private GlyphLayout glyphLayout;
	private BitmapFont font;
	private String textDescription;
	private String textStatus;
	private GDXDialogs dialogs = GDXDialogsSystem.install();
	
	// Sprites
	private List<Sprite> gameobjects = new ArrayList<Sprite>();
	private List<Sprite> foods = new ArrayList<Sprite>();
	private List<Sprite> weapons = new ArrayList<Sprite>();
	private List<Sprite> armors = new ArrayList<Sprite>();
	private List<Sprite> potions = new ArrayList<Sprite>();
	
	
    public Game(Player player, int level, List<Grid> params) {
    	this.player = player;
    	
    	this.setLevel(level);
    	this.setGrid(params);
    	
    	foods.add(new Apple(5));
    	foods.add(new FoodRation(1));
    	
    	weapons.add(new Axe());
    	weapons.add(new BattleAxe());
    	weapons.add(new Bow());
    	weapons.add(new Dagger());
    	weapons.add(new LongSword());
    	weapons.add(new Mace());
    	weapons.add(new Quarterstaff());
    	weapons.add(new Scalpel());
    	weapons.add(new ShortSword());
    	weapons.add(new WarHammer());
    	
    	armors.add(new CloakDisplacement());
    	armors.add(new CloakMagicResistance());
    	armors.add(new LeatherArmor());
    	armors.add(new RingMail());
    	armors.add(new Robe());
    	
    	potions.add(new PotionAbility());
    	potions.add(new PotionExtraHealing(1));
    	potions.add(new PotionHealing(1));
    	potions.add(new PotionLevel());
    }
    
    @Override
	public void buildStage() {
    	// Textos
    	FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial_narrow_7.ttf"));
    	FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    	parameter.size = 16;
    	
    	font = generator.generateFont(parameter);
    	
    	generator.dispose();
    	textDescription = "Hello " + player.getName() + ", Welcome to NetHack! You are a " +
    			player.getGender() + " " + player.getRacename() + " " + player.getClasse() + ".";
    	
    	textStatus = player.getName() + " the "+ player.getClasse() + "    "+ "St:" +
    			player.getSt() + " Dx:" + player.getDx() + " Co:" + player.getCo() +
    			" In:" + player.getIn() + " Wi:" + player.getWi() +" Ch:" + player.getCh() + "\n" +
    			"DLvl:" + this.getLevel() + "    " + "$:" + player.getGold() + " HP:" +
    			player.getLife() + "(" + player.getMax_life() + ")" + " PW:" + player.getPower() +
    			"(" + player.getMax_power() + ")" + " AC:" + player.getAC() + " Xp:"+ player.getXp() + " T:" + time;
    	
        // Mapa
    	texture = new Texture(Gdx.files.internal("sprite.png"));
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();
        
        try {
        	if (getGrid().get(this.getLevel()) != null) {
        		System.out.println("Você já esteve aqui");
        	}
        } catch (java.lang.IndexOutOfBoundsException e) {
        	getGrid().add(this.getLevel(), new Grid(64, 64));

        	final DungeonGenerator dungeonGenerator = new DungeonGenerator();
        	dungeonGenerator.setRoomGenerationAttempts(200);
        	dungeonGenerator.setMaxRoomSize(13);
        	dungeonGenerator.setMaxRoomsAmount(10);
        	dungeonGenerator.setTolerance(10); // Max difference between width and height.
        	dungeonGenerator.setMinRoomSize(9);
	        dungeonGenerator.setRandomConnectorChance(0f); // One way to solve the maze.
	        dungeonGenerator.generate(getGrid().get(this.getLevel()));
        }
	    
	    StaircaseDown down = new StaircaseDown();
	    gameobjects.add(down);
        
        tiledMap = new TiledMap();
        MapLayers layers = tiledMap.getLayers();
        
        TiledMapTileLayer layer1 = new TiledMapTileLayer(128, 128, 32, 32);
        Cell cell[][] = new Cell[getGrid().get(this.getLevel()).getWidth()][getGrid().get(this.getLevel()).getHeight()];
        
        // Adiciona elementos ao mapa
        final Color color = new Color();
        for (int x = 0; x < getGrid().get(this.getLevel()).getWidth(); x++) {
            for (int y = 0; y < getGrid().get(this.getLevel()).getHeight(); y++) {
            	cell[x][y] = new Cell();
            	
                final float cell1 = 1f - getGrid().get(this.getLevel()).get(x, y);
                color.set(cell1, cell1, cell1, 1f);
                
                // Verifica se eh fundo preto
                if (getGrid().get(this.getLevel()).get(x, y) == 1) 
                	cell[x][y].setTile(new StaticTiledMapTile(new TextureRegion(texture, 32*32, 32 * 32, 32, 32)));
                // Verifica se eh canto superior
                else if (grid.get(this.level).get(x, y) == 0.5 &&
                		((grid.get(this.level).get(x, y+1) == 1 && grid.get(this.level).get(x + 1, y) == 1) || 
                		(grid.get(this.level).get(x, y + 1) == 1 && grid.get(this.level).get(x - 1, y) == 1))) {  
                	grid.get(this.level).set(x, y, 1.5f);
                	cell[x][y].setTile(new StaticTiledMapTile(new TextureRegion(texture, 32 * 32, 32 * 20, 32, 32)));
                // Verifica se eh canto inferior
                } else if (grid.get(this.level).get(x, y) == 0.5 &&
                		((grid.get(this.level).get(x, y - 1) == 1 && grid.get(this.level).get(x + 1, y) == 1) || 
                		(grid.get(this.level).get(x, y - 1) == 1 && grid.get(this.level).get(x - 1, y) == 1))) {
                	grid.get(this.level).set(x, y, 1.5f);
                	cell[x][y].setTile(new StaticTiledMapTile(new TextureRegion(texture, 32 * 34, 32 * 20, 32, 32)));
                // Verifica se eh lado esquerdo ou direito
                } else if (grid.get(this.level).get(x, y) == 0.5 &&
                		((grid.get(this.level).get(x + 1, y) == 1) || (grid.get(this.level).get(x - 1, y) == 1)) &&
                		(grid.get(this.level).get(x, y + 1) != 0 && grid.get(this.level).get(x, y - 1) != 0)) {
                	grid.get(this.level).set(x, y, 1.5f);
                	cell[x][y].setTile(new StaticTiledMapTile(new TextureRegion(texture, 32 * 30, 32 * 20, 32, 32)));
                // Verifica se eh lado superior ou inferior
                } else if (grid.get(this.level).get(x, y) == 0.5 &&
                		(grid.get(this.level).get(x, y + 1) == 1 || grid.get(this.level).get(x, y - 1) == 1) && 
                		(grid.get(this.level).get(x, y + 1) == 0.5 || grid.get(this.level).get(x, y - 1) == 0.5) && 
                		(grid.get(this.level).get(x + 1, y) == 0.5 || grid.get(this.level).get(x - 1, y) == 0.5)) {
                	grid.get(this.level).set(x, y, 1.5f);
                	cell[x][y].setTile(new StaticTiledMapTile(new TextureRegion(texture, 32 * 31, 32 * 20, 32, 32)));
                // Verifica se eh caminho
                } else if (getGrid().get(this.getLevel()).get(x, y) == 0)
                	cell[x][y].setTile(new StaticTiledMapTile(new TextureRegion(texture, 32 * 9, 32 * 21, 32, 32)));
                // Verifica se eh sala
                else if (getGrid().get(this.getLevel()).get(x, y) == 0.5) {
                	cell[x][y].setTile(new StaticTiledMapTile(new TextureRegion(texture, 32 * 8, 32 * 21, 32, 32)));
                	
                	if (playerX == 0 && playerY == 0) {
                		playerX = x * 32;
                		playerY = y * 32;
                	}
            		down.setPosition(x * 32, y * 32);
                }
                
                layer1.setCell(x, y, cell[x][y]);
            }
            layers.add(layer1);
        }
        
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        
        sb = new SpriteBatch();
        glyphLayout = new GlyphLayout();
        
		player.setPosition(playerX, playerY);

	    Gold gold = new Gold(135);
	    gameobjects.add(gold);
		gold.setPosition(playerX + 32, playerY + 32);
		
		generateObjects();
        
        camera.position.x = this.playerX;
        camera.position.y = this.playerY;
        
        Gdx.input.setInputProcessor(this);
    }
    
    /** Gera aleatoriamente o enemigo e a posicao dele
     * 
     */
    public void generateObjects() {
    	Dices d = new Dices(1, 9, -1);
    	
    	if (this.level >= 3) {
    		for (int i = 0; i < d.Roll(); i++)
    			generateMonster(d);
    	} else 
    		generateMonster(d);
    }
    
    public void generateMonster(Dices d) {
    	int face = d.Roll();
    	
    	Monster m = null;
    	if (face == 0) m = new Dingo();
    	else if (face == 1) m = new FloatingEye();
    	else if (face == 2) m = new Gremlin();
    	else if (face == 3) m = new Jackal();
    	else if (face == 4) m = new Kobold();
    	else if (face == 5) m = new KoboldLord();
    	else if (face == 6) m = new RabidRat();
    	else if (face == 7) m = new SewerRat();
    	else if (face == 8) m = new Warg();
    	else  m = new Dingo();
    	
    	// Escolhe direcao
    	d = new Dices(1, 4, -1);
    	int dir[][] = {{0, 32}, {32, 32}, {32, 0}, {32, -32}, {0, -32}, {-32, -32}, {-32, 0}, {-32, 32}};
    	for (int i = 0; i < 8; i++) {
    		int x = ((int)player.getX() + dir[i][0] * d.Roll()) , 
    			y = ((int)player.getY() + dir[i][1] * d.Roll());
    		if (x > 0 && y > 0) {
	    		float grid_aux = grid.get(this.level).get(x/32, y/32); 
	    		if (grid_aux == 0f || grid_aux == 0.5f) {
	    			m.setPosition(x, y);
	    			gameobjects.add(m);
	    			break;
	    		}
    		}
    	}
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        
        
		font.draw(sb, textDescription, player.getX() - 318f, player.getY() + 234f);
		font.draw(sb, textStatus, player.getX() - 318f, player.getY() - 192f);
		
		if (isOpen) {
			font.draw(sb, "Inventory:", player.getX() + 200f, player.getY() + 200f);
			for (int i = 0; i < player.getInventory().size(); i++) {
				String textItem = player.getInventory().get(i).getNome();
				if(player.getInventory().get(i) instanceof Consumables) 
					textItem += "  " + "x" + ((Consumables)player.getInventory().get(i)).getQuant();
				glyphLayout.setText(font, textItem);
				font.draw(sb, textItem, player.getX() + 318f - glyphLayout.width, player.getY() + (168f - i * 32));	
			}
		}
		
		for(int i = 0; i < gameobjects.size(); i++) {
			gameobjects.get(i).draw(sb);
			getGrid().get(this.getLevel()).set((int)gameobjects.get(i).getX()/32, (int)gameobjects.get(i).getY()/32, (float)i+2);
		}
        player.draw(sb);
        
        sb.end();
    }
    
    @Override
    public void dispose() {
    	super.dispose();
    	tiledMap.dispose();
    	sb.dispose();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {		
		textDescription = "\"Siga em frente, olhe para o lado...\"";
		
		// Movimento
        if(keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
        	if(!(getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32) >= 2 && ((gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32)-2) instanceof Monster) || (gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32)-2) instanceof StaircaseDown )))) {
	        	if (getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32) == 0 ||
	        			getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32) == 0.5 ||
	        			getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32) >= 2) {
	        		if(getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32) >= 2) {
		        		textDescription = ((Item)gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32) -2))).onInteract(player);
		        		gameobjects.remove((int) (getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32) -2));
		        		getGrid().get(this.getLevel()).set(playerX/32 - 1, playerY/32, 0.5f);
	        		}
	        		
	        		if (getGrid().get(this.getLevel()).get(playerX/32, playerY/32) == 0f &&
	        			getGrid().get(this.getLevel()).get((playerX-32)/32, playerY/32) == 0.5f)
	        			generateObjects();
	        		
	        		playerX -= 32;
		            camera.translate(-32,0);
		            player.translateX(-32f);
		            this.time++;
	        	}
        	}else {
        		if((gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32)-2) instanceof StaircaseDown )) {
        			ScreenManager.getInstance().showScreen(ScreenEnum.GAME,
        					this.player, this.getLevel() + 1, this.getGrid());
        		}else {
        			textDescription = ((Monster) gameobjects.get((int)(getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32) -2))).onInteract(player);
	        		if(((Monster) gameobjects.get((int)(getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32) -2))).getHp() <= 0) {
	        			gameobjects.remove((int) (getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32) -2));
	        			
	        			Sprite item = GenerateItem();
		            	item.setPosition(playerX -32, playerY);
		            	gameobjects.add(item);
		            	getGrid().get(this.getLevel()).set((int)item.getX()/32, (int)item.getY()/32, gameobjects.indexOf(item)+2);
	    
	        		}	
        		}
        	}
        }
        if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
        	if(!(getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32) >= 2 && ((gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32)-2) instanceof Monster) || (gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32)-2) instanceof StaircaseDown)))) {
	        	if (getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32) == 0 ||
	        			getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32) == 0.5 ||
	        			getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32) >= 2) {
	        		if(getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32) >= 2) {
	        			textDescription = ((Item) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32)-2))).onInteract(player);
	        			gameobjects.remove((int) (getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32) -2));
	        			getGrid().get(this.getLevel()).set(playerX/32 + 1, playerY/32, 0.5f);
	        		}
	        		
	        		if (getGrid().get(this.getLevel()).get(playerX/32, playerY/32) == 0f &&
		        			getGrid().get(this.getLevel()).get((playerX+32)/32, playerY/32) == 0.5f)
		        			generateObjects();
	        		
	        		playerX += 32;
		            camera.translate(32,0);
		            player.translateX(32f);
		            this.time++;
		          
	        	}
        	}else {
        		if((gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32)-2) instanceof StaircaseDown)) {
        			ScreenManager.getInstance().showScreen(ScreenEnum.GAME,
        					this.player, this.getLevel() + 1, this.getGrid());
        		}else {
        			textDescription = ((Monster) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32)-2))).onInteract(player);
	        		if(((Monster) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32)-2))).getHp() <= 0) {
		        		gameobjects.remove((int) (getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32) -2));
		    			getGrid().get(this.getLevel()).set(playerX/32 + 1, playerY/32, 0.5f);
		    			
		    			Sprite item = GenerateItem();
		            	item.setPosition(playerX +32, playerY);
		            	gameobjects.add(item);
		            	getGrid().get(this.getLevel()).set((int)item.getX()/32, (int)item.getY()/32, gameobjects.indexOf(item)+2);
	        		}
        		}
        	}
        }
        if(keycode == Input.Keys.UP || keycode == Input.Keys.W) {
        	if(!( getGrid().get(this.getLevel()).get(playerX/32, playerY/32 + 1) >= 2 && ((gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32, playerY/32 + 1)-2) instanceof Monster) || (gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32, playerY/32 + 1)-2) instanceof StaircaseDown)))) {
	        	if (getGrid().get(this.getLevel()).get(playerX/32, playerY/32 + 1) == 0 ||
	        			getGrid().get(this.getLevel()).get(playerX/32, playerY/32 + 1) == 0.5 ||
	        			getGrid().get(this.getLevel()).get(playerX/32, playerY/32 + 1) >= 2) {
	        		if(getGrid().get(this.getLevel()).get(playerX/32, playerY/32 + 1) >= 2) {
	        			textDescription = ((Item) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32, playerY/32 + 1) -2))).onInteract(player);
		            	gameobjects.remove((int) (getGrid().get(this.getLevel()).get(playerX/32, playerY/32 + 1) -2));
		            	getGrid().get(this.getLevel()).set(playerX/32, playerY/32 + 1, 0.5f);
	        		}
	        		
	        		if (getGrid().get(this.getLevel()).get(playerX/32, playerY/32) == 0f &&
		        			getGrid().get(this.getLevel()).get(playerX/32, (playerY+32)/32) == 0.5f)
		        			generateObjects();
	        		
	        		playerY += 32;
		            camera.translate(0,32);
		            player.translateY(32f);
		            this.time++;
	        	}
        	}else {
        		if((gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32, playerY/32 + 1)-2) instanceof StaircaseDown)) {
        			ScreenManager.getInstance().showScreen(ScreenEnum.GAME,
        					this.player, this.getLevel() + 1, this.getGrid());
        		}else {
        			textDescription = ((Monster) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32, playerY/32 + 1) -2))).onInteract(player);
	        		if(((Monster) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32, playerY/32 + 1) -2))).getHp() <= 0) {
	        			gameobjects.remove((int) (getGrid().get(this.getLevel()).get(playerX/32, playerY/32 + 1) -2));
		            	getGrid().get(this.getLevel()).set(playerX/32, playerY/32 + 1, 0.5f);
		            	
		            	Sprite item = GenerateItem();
		            	item.setPosition(playerX, playerY +32);
		            	gameobjects.add(item);
		            	getGrid().get(this.getLevel()).set((int)item.getX()/32, (int)item.getY()/32, gameobjects.indexOf(item)+2);
	        		}
        		}
        	}
        }
        if(keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
        	if(!(getGrid().get(this.getLevel()).get(playerX/32, playerY/32 - 1) >= 2 && ((gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32, playerY/32 - 1)-2) instanceof Monster || gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32, playerY/32 - 1)-2) instanceof StaircaseDown )))) {
	        	if (getGrid().get(this.getLevel()).get(playerX/32, playerY/32 - 1) == 0 ||
	        			getGrid().get(this.getLevel()).get(playerX/32, playerY/32 - 1) == 0.5 ||
	        			getGrid().get(this.getLevel()).get(playerX/32, playerY/32 - 1) >= 2) {
	        		if(getGrid().get(this.getLevel()).get(playerX/32, playerY/32 - 1) >= 2) {
	        			textDescription = ((Item) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32, playerY/32 - 1) -2))).onInteract(player);
		            	gameobjects.remove((int) (getGrid().get(this.getLevel()).get(playerX/32, playerY/32 - 1) -2));
		            	getGrid().get(this.getLevel()).set(playerX/32, playerY/32 - 1, 0.5f);
		            }
	        		
	        		if (getGrid().get(this.getLevel()).get(playerX/32, playerY/32) == 0f &&
		        			getGrid().get(this.getLevel()).get(playerX/32, (playerY-32)/32) == 0.5f)
		        			generateObjects();
	        		
	        		playerY -= 32;
		            camera.translate(0,-32);
		            player.translateY(-32f);
		            this.time++;
	        	}
        	}else {
        		if(gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32, playerY/32 - 1)-2) instanceof StaircaseDown) {
        			ScreenManager.getInstance().showScreen(ScreenEnum.GAME,
        					this.player, this.getLevel() + 1, this.getGrid());
        		}else {
        			textDescription = ((Monster) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32, playerY/32 - 1) -2))).onInteract(player);
	        		if(((Monster) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32, playerY/32 - 1) -2))).getHp() <= 0) {
	        			gameobjects.remove((int) (getGrid().get(this.getLevel()).get(playerX/32, playerY/32 - 1) -2));
		            	getGrid().get(this.getLevel()).set(playerX/32, playerY/32 - 1, 0.5f);
		            	
		            	Sprite item = GenerateItem();
		            	item.setPosition(playerX, playerY -32);
		            	gameobjects.add(item);
		            	getGrid().get(this.getLevel()).set((int)item.getX()/32, (int)item.getY()/32, gameobjects.indexOf(item)+2);
	        		}
        		}
        	}
        }
        if (keycode == Input.Keys.Q) {
        	if(!(getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 +1) >= 2 && ((gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 +1)-2) instanceof Monster || gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 +1)-2) instanceof StaircaseDown)))){
	        	if (getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 + 1) == 0 ||
	        			getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 + 1) == 0.5 ||
	        			getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 + 1) >= 2) {
	        		if(getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 + 1) >= 2) {
	        			textDescription = ((Item) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 + 1) -2))).onInteract(player);
		            	gameobjects.remove((int) (getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 + 1) -2));
		            	getGrid().get(this.getLevel()).set(playerX/32 - 1, playerY/32 + 1, 0.5f);
	        		}
	        		
	        		if (getGrid().get(this.getLevel()).get(playerX/32, playerY/32) == 0f &&
		        			getGrid().get(this.getLevel()).get((playerX-32)/32, (playerY+32)/32) == 0.5f)
		        			generateObjects();
	        		
	        		playerX -= 32;
	        		playerY += 32;
		            camera.translate(-32,32);
		            player.translate(-32f, 32f);
		            this.time++;
	        	}
        	}else {
        		if(gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 +1)-2) instanceof StaircaseDown) {
        			ScreenManager.getInstance().showScreen(ScreenEnum.GAME,
        					this.player, this.getLevel() + 1, this.getGrid());
        		}else {
        			textDescription = ((Monster) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 + 1) -2))).onInteract(player);
	        		if(((Monster) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 + 1) -2))).getHp() <= 0) {
	        			gameobjects.remove((int) (getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 + 1) -2));
		            	getGrid().get(this.getLevel()).set(playerX/32 - 1, playerY/32 + 1, 0.5f);
		            	
		            	Sprite item = GenerateItem();
		            	item.setPosition(playerX -32, playerY +32);
		            	gameobjects.add(item);
		            	getGrid().get(this.getLevel()).set((int)item.getX()/32, (int)item.getY()/32, gameobjects.indexOf(item)+2);
	        		}
        		}
        	}
        }
        if (keycode == Input.Keys.E) {
        	if(!(getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 + 1) >= 2 && ((gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 + 1)-2) instanceof Monster || gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 + 1)-2) instanceof StaircaseDown)))){
	        	if (getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 + 1) == 0 ||
	        			getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 + 1) == 0.5 ||
	        			getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 + 1) >= 2) {
	        		if(getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 + 1) >= 2) {
	        			textDescription = ((Item) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 + 1) -2))).onInteract(player);
		            	gameobjects.remove((int) (getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 + 1) -2));
		            	getGrid().get(this.getLevel()).set(playerX/32 + 1, playerY/32 + 1, 0.5f);
	        		}
	        		
	        		if (getGrid().get(this.getLevel()).get(playerX/32, playerY/32) == 0f &&
		        			getGrid().get(this.getLevel()).get((playerX+32)/32, (playerY+32)/32) == 0.5f)
		        			generateObjects();
	        		
	        		playerX += 32;
	        		playerY += 32;
		            camera.translate(32,32);
		            player.translate(32f, 32f);
		            this.time++;
	        	}
        	}else {
        		if(gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 + 1)-2) instanceof StaircaseDown) {
        			ScreenManager.getInstance().showScreen(ScreenEnum.GAME,
        					this.player, this.getLevel() + 1, this.getGrid());
        		}else {
        			textDescription = ((Monster) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 + 1) -2))).onInteract(player);
	        		if(((Monster) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 + 1) -2))).getHp() <= 0){
	        			gameobjects.remove((int) (getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 + 1) -2));
		            	getGrid().get(this.getLevel()).set(playerX/32 + 1, playerY/32 + 1, 0.5f);
		            	
		            	Sprite item = GenerateItem();
		            	item.setPosition(playerX +32, playerY +32);
		            	gameobjects.add(item);
		            	getGrid().get(this.getLevel()).set((int)item.getX()/32, (int)item.getY()/32, gameobjects.indexOf(item)+2);
	        		}
        		}
        	}
        }
        if (keycode == Input.Keys.Z) {
        	if(!(getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 - 1) >= 2 && ((gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 - 1)-2) instanceof Monster || gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 - 1)-2) instanceof StaircaseDown)))){
	        	if (getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 - 1) == 0 ||
	        			getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 - 1) == 0.5 ||
	        			getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 - 1) >= 2) {
	        		if(getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 - 1) >= 2) {
	        			textDescription = ((Item) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 - 1) -2))).onInteract(player);
		            	gameobjects.remove((int) (getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 - 1) -2));
		            	getGrid().get(this.getLevel()).set(playerX/32 - 1, playerY/32 - 1, 0.5f);
	        		}
	        		
	        		if (getGrid().get(this.getLevel()).get(playerX/32, playerY/32) == 0f &&
		        			getGrid().get(this.getLevel()).get((playerX-32)/32, (playerY-32)/32) == 0.5f)
		        			generateObjects();
	        		
	        		playerX -= 32;
	        		playerY -= 32;
		            camera.translate(-32,-32);
		            player.translate(-32f, -32f);
		            this.time++;
	        	}
        	}else {
        		if(gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 - 1)-2) instanceof StaircaseDown) {
        			ScreenManager.getInstance().showScreen(ScreenEnum.GAME,
        					this.player, this.getLevel() + 1, this.getGrid());
        		}else {
        			textDescription = ((Monster) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 - 1) -2))).onInteract(player);
	        		if(((Monster) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 - 1) -2))).getHp() <= 0){
	        			Sprite item = GenerateItem();	
	        			gameobjects.remove((int) (getGrid().get(this.getLevel()).get(playerX/32 - 1, playerY/32 - 1) -2));
		            	getGrid().get(this.getLevel()).set(playerX/32 - 1, playerY/32 - 1, 0.5f);
		            	item.setPosition(playerX -32, playerY -32);
		            	gameobjects.add(item);
		            	getGrid().get(this.getLevel()).set((int)item.getX()/32, (int)item.getY()/32, gameobjects.indexOf(item)+2);
	        		}
        		}
        	}
        }
        if (keycode == Input.Keys.C) {
        	if(!(getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 - 1) >= 2 && ((gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 - 1)-2) instanceof Monster || gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 - 1)-2) instanceof StaircaseDown)))){
	        	if (getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 - 1) == 0 ||
	        			getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 - 1) == 0.5 ||
	        			getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 - 1) >= 2) {
	        		if(getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 - 1) >= 2) {
	        			textDescription = ((Item) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 - 1) -2))).onInteract(player);
		            	gameobjects.remove((int) (getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 - 1) -2));
		            	getGrid().get(this.getLevel()).set(playerX/32 + 1, playerY/32 - 1, 0.5f);
	        		}
	        		
	        		if (getGrid().get(this.getLevel()).get(playerX/32, playerY/32) == 0f &&
		        			getGrid().get(this.getLevel()).get((playerX+32)/32, (playerY-32)/32) == 0.5f)
		        			generateObjects();
	        		
	        		playerX += 32;
	        		playerY -= 32;
		            camera.translate(32,-32);
		            player.translate(32f, -32f);
		            this.time++;
	        	}
        	}else {
        		if(gameobjects.get((int) getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 - 1)-2) instanceof StaircaseDown) {
        			ScreenManager.getInstance().showScreen(ScreenEnum.GAME,
        					this.player, this.getLevel() + 1, this.getGrid());
        		}else{
	        		((Monster) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 - 1) -2))).onInteract(player);
	        		if(((Monster) gameobjects.get((int) (getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 - 1) -2))).getHp() <= 0) {
	        			Sprite item = GenerateItem();	
	        			gameobjects.remove((int) (getGrid().get(this.getLevel()).get(playerX/32 + 1, playerY/32 - 1) -2));
	        			getGrid().get(this.getLevel()).set(playerX/32 + 1, playerY/32 - 1, 0.5f);
	        			item.setPosition(playerX + 32, playerY - 32);
	        			gameobjects.add(item);
	        			getGrid().get(this.getLevel()).set((int)item.getX()/32, (int)item.getY()/32, gameobjects.indexOf(item)+2);
	        		}
        		}
        	}
        }
        
        // Inventario
        if (keycode == Input.Keys.I) {
        	isOpen = !isOpen;
        
//        	// Get item texture
//        	if (isOpen) {
//	        	for (Item i : player.getInventory()) {
//	        		if (i.getNome() == "Apple" && !items.contains(i))
//	        			items.add(new Sprite(new TextureRegion(texture, 32 * 4, 32 * 16, 32, 32)));
//	        	}
//        	}
        }
        
        // Extra
        if(keycode == Input.Keys.NUM_1) 
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        
        if(keycode == Input.Keys.NUM_2) 
        	ScreenManager.getInstance().showScreen(ScreenEnum.GAME,
					this.player, this.getLevel() + 1, this.getGrid());
        
        if(keycode == Input.Keys.NUM_3) 
        	player.setLife(player.getLife() - 1);
        
    	this.player.Alive();
    	
    	textStatus = player.getName() + " the "+ player.getClasse() + "    "+ "St:" +
    			player.getSt() + " Dx:" + player.getDx() + " Co:" + player.getCo() +
    			" In:" + player.getIn() + " Wi:" + player.getWi() +" Ch:" + player.getCh() + " " + player.getState_cap()+ "\n" +
    			"DLvl:" + this.getLevel() + "    " + "$:" + player.getGold() + " HP:" +
    			player.getLife() + "(" + player.getMax_life() + ")" + " PW:" + player.getPower() +
    			"(" + player.getMax_power() + ")" + " AC:" + player.getAC() + " Xp:"+ player.getXp() + " T:" + this.time + " " + player.getNu_state();
        
		if (player.getLife() <= 0) {
			player.setScore(player.getScore() + player.getGold());
			
			GDXButtonDialog bDialog = dialogs.newDialog(GDXButtonDialog.class);
			bDialog.setTitle("Here Lies...");
			bDialog.setMessage("Goodbye " + player.getName() + " the " + player.getRole().getName() + "...\n"
					+ "You died in The Dungeons of Doom on dungeon level " + (this.getLevel() + 1) + " with " + player.getScore() + " points,\n"
					+ "and " + player.getGold() + " pieces of gold, after " + time + " moves.\n"
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
    	
        return false;
	}

	public Sprite GenerateItem() {
		int aux = new Dices(1,6,0).Roll();
		Sprite item;
		
		if(aux == 1 || aux == 2) {
			item = new Gold(new Dices(1,1000,0).Roll());
		}else if(aux == 3) {
			int which_food = new Dices(1,2,0).Roll();
			item = foods.get(which_food-1);
		}else if(aux == 4) {
			int which_weapon = new Dices(1,10,0).Roll();
			item = weapons.get(which_weapon-1);
		}else if(aux == 5) {
			int which_armor = new Dices(1,5,0).Roll();
			item = armors.get(which_armor-1);
		}else {
			int which_potion = new Dices(1,4,0).Roll();
			item = potions.get(which_potion-1);
		}
		
		return item;
	}
	
	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {	    
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<Grid> getGrid() {
		return grid;
	}

	public void setGrid(List<Grid> grid) {
		this.grid = grid;
	}
}
