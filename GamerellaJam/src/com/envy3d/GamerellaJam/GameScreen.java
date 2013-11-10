package com.envy3d.GamerellaJam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.IntMap.Entry;
import com.envy3d.shared.Entity;
import com.envy3d.shared.FloatVec2;
import com.envy3d.shared.Map;

public class GameScreen implements Screen, GestureListener {
	public IntMap<Entity> entities;
	public Map map;
	public SpriteBatch spriteBatch;
	public OrthographicCamera orthoCam;
	
	
	public int playerId;
	
	public GameScreen() {
		spriteBatch = new SpriteBatch();
		orthoCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		entities = new IntMap<Entity>(10);
		map = new Map();
		playerId = -1;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (playerId >= 0) {
			for (Entry<Entity> e : entities.entries()) {
				float xDiff = e.value.dest.x - e.value.posX;
				float yDiff = e.value.dest.y - e.value.posY;
				if (Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)) <= Entity.MAX_SPEED * delta) {
					e.value.posX = e.value.dest.x;
					e.value.posY = e.value.dest.y;
				}
				else {
					e.value.dirVec = (new FloatVec2(xDiff, yDiff)).normalize();
					e.value.posX = (int)(e.value.dirVec.x * Entity.MAX_SPEED * delta);
					e.value.posY = (int)(e.value.dirVec.y * Entity.MAX_SPEED * delta);
				}
			}
			Entity entity = entities.get(playerId);
			orthoCam.position.set(entity.posX, entity.posY, 0);
			//orthoCam.translate(entity.posX - orthoCam.position.x, entity.posY - orthoCam.position.y);
			orthoCam.update();
			spriteBatch.begin();
			
			spriteBatch.end();
		}
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		
		
		return true;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
