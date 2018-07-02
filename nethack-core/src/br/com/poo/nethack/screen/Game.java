package br.com.poo.nethack.screen;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.github.czyzby.noise4j.map.Grid;
import com.github.czyzby.noise4j.map.generator.room.dungeon.DungeonGenerator;

import br.com.poo.nethack.util.ScreenEnum;
import br.com.poo.nethack.util.ScreenManager;

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
    private TextureRegion textureRegion;
    private Sprite player;
    private int playerX = 0;
    private int playerY = 0;
    private String name;
    private String classe;
    private String race;
	
    public Game(String name, String classe, String race, int level, List<Grid> params) {
    	this.name = name;
    	this.classe = classe;
    	this.race = race;
    	this.level = level;
    	this.grid = params;
    }
    
    @Override
	public void buildStage() { 	
    	System.out.println("Name: " + this.name + "\nClasse: "
    			+ this.classe + "\nRace: " + this.race);
    	texture = new Texture(Gdx.files.internal("sprite.png"));
    	
        // Mapa
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();
        
        try {
        	if (grid.get(this.level) != null) {
        		System.out.println("Você já esteve aqui");
        	}
        } catch (java.lang.IndexOutOfBoundsException e) {
        	grid.add(this.level, new Grid(64, 64));

        	final DungeonGenerator dungeonGenerator = new DungeonGenerator();
        	dungeonGenerator.setRoomGenerationAttempts(200);
        	dungeonGenerator.setMaxRoomSize(13);
        	dungeonGenerator.setMaxRoomsAmount(10);
        	dungeonGenerator.setTolerance(10); // Max difference between width and height.
        	dungeonGenerator.setMinRoomSize(9);
	        dungeonGenerator.setRandomConnectorChance(0f); // One way to solve the maze.
	        dungeonGenerator.generate(grid.get(this.level));
        }
	        
        tiledMap = new TiledMap();
        MapLayers layers = tiledMap.getLayers();
        
        TiledMapTileLayer layer1 = new TiledMapTileLayer(128, 128, 32, 32);
        Cell cell[][] = new Cell[grid.get(this.level).getWidth()][grid.get(this.level).getHeight()];
        
        // Adiciona elementos ao mapa
        final Color color = new Color();
        for (int x = 0; x < grid.get(this.level).getWidth(); x++) {
            for (int y = 0; y < grid.get(this.level).getHeight(); y++) {
            	cell[x][y] = new Cell();
            	
                final float cell1 = 1f - grid.get(this.level).get(x, y);
                color.set(cell1, cell1, cell1, 1f);
                
                // Verifica se eh fundo preto
                if (grid.get(this.level).get(x, y) == 1) 
                	cell[x][y].setTile(new StaticTiledMapTile(new TextureRegion(texture, 32*32, 32 * 32, 32, 32)));
                // Verifica se eh canto superior
                else if (grid.get(this.level).get(x, y) == 0.5 && ((grid.get(this.level).get(x, y+1) == 1 && grid.get(this.level).get(x + 1, y) == 1) || 
                		(grid.get(this.level).get(x, y + 1) == 1 && grid.get(this.level).get(x - 1, y) == 1))) {  
                	grid.get(this.level).set(x, y, 1.5f);
                	cell[x][y].setTile(new StaticTiledMapTile(new TextureRegion(texture, 32 * 32, 32 * 20, 32, 32)));
                // Verifica se eh canto inferior
                } else if (grid.get(this.level).get(x, y) == 0.5 && ((grid.get(this.level).get(x, y - 1) == 1 && grid.get(this.level).get(x + 1, y) == 1) || 
                		(grid.get(this.level).get(x, y - 1) == 1 && grid.get(this.level).get(x - 1, y) == 1))) {
                	grid.get(this.level).set(x, y, 1.5f);
                	cell[x][y].setTile(new StaticTiledMapTile(new TextureRegion(texture, 32 * 34, 32 * 20, 32, 32)));
                // Verifica se eh lado esquerdo ou direito
                } else if (grid.get(this.level).get(x, y) == 0.5 && ((grid.get(this.level).get(x + 1, y) == 1) || (grid.get(this.level).get(x - 1, y) == 1)) &&
                		(grid.get(this.level).get(x, y + 1) != 0 && grid.get(this.level).get(x, y - 1) != 0)) {
                	grid.get(this.level).set(x, y, 1.5f);
                	cell[x][y].setTile(new StaticTiledMapTile(new TextureRegion(texture, 32 * 30, 32 * 20, 32, 32)));
                // Verifica se eh lado superior ou inferior
                } else if (grid.get(this.level).get(x, y) == 0.5 && (grid.get(this.level).get(x, y + 1) == 1 || grid.get(this.level).get(x, y - 1) == 1) && 
                		(grid.get(this.level).get(x, y + 1) == 0.5 || grid.get(this.level).get(x, y - 1) == 0.5) && 
                		(grid.get(this.level).get(x + 1, y) == 0.5 || grid.get(this.level).get(x - 1, y) == 0.5)) {
                	grid.get(this.level).set(x, y, 1.5f);
                	cell[x][y].setTile(new StaticTiledMapTile(new TextureRegion(texture, 32 * 31, 32 * 20, 32, 32)));
                // Verifica se eh caminho
                } else if (grid.get(this.level).get(x, y) == 0)
                	cell[x][y].setTile(new StaticTiledMapTile(new TextureRegion(texture, 32 * 9, 32 * 21, 32, 32)));
                // Verifica se eh sala
                else if (grid.get(this.level).get(x, y) == 0.5) {
                	cell[x][y].setTile(new StaticTiledMapTile(new TextureRegion(texture, 32 * 8, 32 * 21, 32, 32)));
                	
                	if (playerX == 0 && playerY == 0) {
                		playerX = x * 32;
                		playerY = y * 32;
                	}
                }
                
                layer1.setCell(x, y, cell[x][y]);
            }
            layers.add(layer1);
        }
        
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        
        // Personagem
        sb = new SpriteBatch();
		textureRegion = new TextureRegion(texture, 0, 9 * 32, 32, 32);
		player = new Sprite(textureRegion);
		player.setPosition(playerX, playerY);
        
        camera.position.x = this.playerX;
        camera.position.y = this.playerY;
        
        Gdx.input.setInputProcessor(this);
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
        if(keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
        	if (grid.get(this.level).get(playerX/32 - 1, playerY/32) == 0 ||
        			grid.get(this.level).get(playerX/32 - 1, playerY/32) == 0.5) {
        		playerX -= 32;
	            camera.translate(-32,0);
	            player.translateX(-32f);
        	}
        }
        if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
        	if (grid.get(this.level).get(playerX/32 + 1, playerY/32) == 0 ||
        			grid.get(this.level).get(playerX/32 + 1, playerY/32) == 0.5) {
        		playerX += 32;
	            camera.translate(32,0);
	            player.translateX(32f);
        	}
        }
        if(keycode == Input.Keys.UP || keycode == Input.Keys.W) {
        	if (grid.get(this.level).get(playerX/32, playerY/32 + 1) == 0 ||
        			grid.get(this.level).get(playerX/32, playerY/32 + 1) == 0.5) {
        		playerY += 32;
	            camera.translate(0,32);
	            player.translateY(32f);
        	}
        }
        if(keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
        	if (grid.get(this.level).get(playerX/32, playerY/32 - 1) == 0 ||
        			grid.get(this.level).get(playerX/32, playerY/32 - 1) == 0.5) {
        		playerY -= 32;
	            camera.translate(0,-32);
	            player.translateY(-32f);
        	}
        }
        if (keycode == Input.Keys.Q) {
        	if (grid.get(this.level).get(playerX/32 - 1, playerY/32 + 1) == 0 ||
        			grid.get(this.level).get(playerX/32 - 1, playerY/32 + 1) == 0.5) {
        		playerX -= 32;
        		playerY += 32;
	            camera.translate(-32,32);
	            player.translate(-32f, 32f);
        	}
        }
        if (keycode == Input.Keys.E) {
        	if (grid.get(this.level).get(playerX/32 + 1, playerY/32 + 1) == 0 ||
        			grid.get(this.level).get(playerX/32 + 1, playerY/32 + 1) == 0.5) {
        		playerX += 32;
        		playerY += 32;
	            camera.translate(32,32);
	            player.translate(32f, 32f);
        	}
        }
        if (keycode == Input.Keys.Z) {
        	if (grid.get(this.level).get(playerX/32 - 1, playerY/32 - 1) == 0 ||
        			grid.get(this.level).get(playerX/32 - 1, playerY/32 - 1) == 0.5) {
        		playerX -= 32;
        		playerY -= 32;
	            camera.translate(-32,-32);
	            player.translate(-32f, -32f);
        	}
        }
        if (keycode == Input.Keys.C) {
        	if (grid.get(this.level).get(playerX/32 + 1, playerY/32 - 1) == 0 ||
        			grid.get(this.level).get(playerX/32 + 1, playerY/32 - 1) == 0.5) {
        		playerX += 32;
        		playerY -= 32;
	            camera.translate(32,-32);
	            player.translate(32f, -32f);
        	}
        }
        
        if(keycode == Input.Keys.NUM_1) 
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        
        if(keycode == Input.Keys.NUM_2) 
        	ScreenManager.getInstance().showScreen(ScreenEnum.GAME,
					this.name, this.classe, this.race, this.level + 1, this.grid);
        
        return false;
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
}
