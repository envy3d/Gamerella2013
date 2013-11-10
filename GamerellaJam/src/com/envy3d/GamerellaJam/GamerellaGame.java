package com.envy3d.GamerellaJam;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.envy3d.GamerellaJam.networking.NetworkClient;

public class GamerellaGame extends Game {
	private GameScreen gameScreen;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private NetworkClient client;
	public Sprite natSprite;
	public Sprite golSprite;
	public Sprite natTiles;
	public Sprite golTiles;
	
	public GamerellaGame() {
		
	}
	
	@Override
	public void create() {		
		gameScreen = new GameScreen();
		Gdx.input.setInputProcessor(new GestureDetector(gameScreen));
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
		
		sprite = new Sprite(region);
		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		
		natSprite = new Sprite(new Texture(Gdx.files.internal("NatureGirl.png")));
		golSprite = new Sprite(new Texture(Gdx.files.internal("GoldenBoy.png")));
		natTiles = new Sprite(new Texture(Gdx.files.internal("Grass.png")));
		golTiles = new Sprite(new Texture(Gdx.files.internal("Gold.png")));
		
		client = new NetworkClient(gameScreen);
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
		
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite.draw(batch);
		batch.end();
		setScreen(gameScreen);
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
}
