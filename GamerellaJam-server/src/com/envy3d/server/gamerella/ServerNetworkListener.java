package com.envy3d.server.gamerella;

import com.badlogic.gdx.utils.IntMap.Entry;
import com.envy3d.shared.Entity;
import com.envy3d.shared.IntVec2;
import com.envy3d.shared.Map;
import com.envy3d.shared.Packets.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class ServerNetworkListener extends Listener {
	private GameLogic gameLogic;
	private Server server;
	
	public ServerNetworkListener(GameLogic gameLogic, Server server) {
		this.gameLogic = gameLogic;
		this.server = server;
	}
	
	
	@Override
	public void connected(Connection connection) {
		super.connected(connection);
		System.out.println("[SERVER] Someone has connected.");
		/*for (int i = 0; i < Map.HEIGHT; i++) {
			for (int j = 0; j < Map.WIDTH; j++) {
				connection.sendTCP(new PacketTileState((short)j, (short)i, (byte)gameLogic.map.get(j, i).natureTex,
														gameLogic.map.get(j, i).gilded));
			}
		}*/
		int gildedCounter = 0;
		for (Entry<Entity> e : gameLogic.entities.entries()) {
			connection.sendTCP(new PacketSpawn(e.value.id, e.value.isGilded(), e.value.posX, e.value.posY));
			if (e.value.isGilded())
				gildedCounter++;
			else
				gildedCounter--;
		}
		Entity entity = new Entity((short)gameLogic.entityIdAssignment, gildedCounter <= 0 ? true : false,
				  gameLogic.rand.nextInt(Map.WIDTH), gameLogic.rand.nextInt(Map.HEIGHT));
		gameLogic.entityIdAssignment++;
		gameLogic.entities.put(entity.id, entity);
		server.sendToAllTCP(new PacketSpawn(entity.id, entity.isGilded(), entity.posX, entity.posY));
		connection.sendTCP(new PacketStart(entity.id));
	}
	
	@Override
	public void disconnected(Connection connection) {
		super.disconnected(connection);
		System.out.println("[SERVER] Someone has disconnected.");
	}
	
	@Override
	public void received(Connection connection, Object object) {
		super.received(connection, object);
		
		if (object instanceof PacketMove) {
			server.sendToAllTCP(object);
			Entity tempEntity = gameLogic.entities.get(((PacketMove)object).id);
			if (tempEntity != null) {
				tempEntity.posX = ((PacketMove)object).startX;
				tempEntity.posY = ((PacketMove)object).startY;
				tempEntity.dest = new IntVec2(((PacketMove)object).destX, ((PacketMove)object).destX);
			}
		}
	}	
}
