package com.envy3d.server.gamerella;

import java.util.Random;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.IntMap.Entry;
import com.envy3d.shared.Entity;
import com.envy3d.shared.FloatVec2;
import com.envy3d.shared.Map;
import com.envy3d.shared.Packets.*;

public class GameLogic {
	public Map map;
	public IntMap<Entity> entities;
	public int entityIdAssignment;
	public Random rand;
	
	public GameLogic() {
		rand = new Random();
		map = new Map();
		entities = new IntMap<Entity>(10);
		entityIdAssignment = 0;
	}
	
	public void update(float delta) {
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
			if (e.value.isGilded() && map.get(e.value.posX / Map.TILE_WIDTH, e.value.posY / Map.TILE_HEIGHT).gilded == false) {
				map.set(e.value.posX / Map.TILE_WIDTH, e.value.posY / Map.TILE_HEIGHT, true);
				Main.netServer.server.sendToAllTCP(new PacketTransmute((short)(e.value.posX / Map.TILE_WIDTH),
																	   (short)(e.value.posY / Map.TILE_HEIGHT), true));
			} else if (e.value.isGilded() == false && map.get(e.value.posX / Map.TILE_WIDTH, e.value.posY / Map.TILE_HEIGHT).gilded == true){
				map.set(e.value.posX / Map.TILE_WIDTH, e.value.posY / Map.TILE_HEIGHT, false);
				Main.netServer.server.sendToAllTCP(new PacketTransmute((short)(e.value.posX / Map.TILE_WIDTH),
						   											   (short)(e.value.posY / Map.TILE_HEIGHT), false));
			}
		}
		
		
	}
}
