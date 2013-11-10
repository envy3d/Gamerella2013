package com.envy3d.server.gamerella;

import java.util.Random;

import com.badlogic.gdx.utils.Array;
import com.envy3d.shared.Entity;
import com.envy3d.shared.Map;

public class GameLogic {
	public Map map;
	public Array<Entity> entities;
	public int entityIdAssignment;
	public Random rand;
	
	public GameLogic() {
		rand = new Random();
		map = new Map();
		entities = new Array<Entity>(false, 10, Entity.class);
		entityIdAssignment = 0;
	}
	
	
}
