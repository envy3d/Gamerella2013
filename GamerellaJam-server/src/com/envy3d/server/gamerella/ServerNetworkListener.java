package com.envy3d.server.gamerella;

import com.envy3d.shared.Entity;
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
	}
	
	
	@Override
	public void connected(Connection connection) {
		super.connected(connection);
		System.out.println("[SERVER] Someone has connected.");
		for (int i = 0; i < gameLogic.map.HEIGHT; i++) {
			for (int j = 0; j < gameLogic.map.WIDTH; j++) {
				connection.sendTCP(new PacketTileState((byte)gameLogic.map.get(j, i).natureTex, gameLogic.map.get(j, i).gilded));
			}
		}
		int gildedCounter = 0;
		for (Entity e : gameLogic.entities) {
			connection.sendTCP(new PacketSpawn(e.id, e.isGilded(), e.posX, e.posY));
			if (e.isGilded())
				gildedCounter++;
			else
				gildedCounter--;
		}
		Entity entity = new Entity((short)gameLogic.entityIdAssignment, gildedCounter <= 0 ? true : false,
				  gameLogic.rand.nextInt(Map.WIDTH), gameLogic.rand.nextInt(Map.HEIGHT));
		gameLogic.entityIdAssignment++;
		gameLogic.entities.add(entity);
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
		
		if (object instanceof PacketLogInRequest) {
			PacketLogInAnswer logInAnswer = new PacketLogInAnswer();
			logInAnswer.accepted = true;
			connection.sendTCP(logInAnswer);
		}
		
		if (object instanceof PacketMessage) {
			String message = ((PacketMessage)object).message;
			System.out.println(message);
			((PacketMessage)object).message = ((PacketMessage)object).message + "0";
			connection.sendTCP(object);
		}
	}	
}
