package com.envy3d.GamerellaJam.networking;

import com.envy3d.GamerellaJam.GameScreen;
import com.envy3d.shared.Entity;
import com.envy3d.shared.IntVec2;
import com.envy3d.shared.Packets.*;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientNetworkListener extends Listener {
	private GameScreen gameScreen;
	private Client client;
	
	public ClientNetworkListener(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}
	
	public void init(Client client) {
		this.client = client;
	}
	
	@Override
	public void connected(Connection connection) {
		super.connected(connection);
		System.out.println("[CLIENT] You have connected.");
		client.sendTCP(new PacketLogInRequest());
	}
	
	@Override
	public void disconnected(Connection connection) {
		super.disconnected(connection);
		System.out.println("[CLIENT] You have disconnected.");
	}
	
	@Override
	public void received(Connection connection, Object object) {
		super.received(connection, object);

		if (object instanceof PacketMove) {
			Entity tempEntity = gameScreen.entities.get(((PacketMove)object).id);
			if (tempEntity != null) {
				tempEntity.posX = ((PacketMove)object).startX;
				tempEntity.posY = ((PacketMove)object).startY;
				tempEntity.dest = new IntVec2(((PacketMove)object).destX, ((PacketMove)object).destX);
			}
		}
		else if (object instanceof PacketTransmute) {
			gameScreen.map.set(((PacketTransmute)object).tileX, ((PacketTransmute)object).tileY, ((PacketTransmute)object).gilded);
		}
		else if (object instanceof PacketSpawn) {
			gameScreen.entities.put(((PacketSpawn)object).id, new Entity(((PacketSpawn)object).id, ((PacketSpawn)object).gilded,
																		((PacketSpawn)object).startX, ((PacketSpawn)object).startY));
		}
		else if (object instanceof PacketDespawn) {
			
		}
		else if (object instanceof PacketTileState) {
			gameScreen.map.set(((PacketTileState)object).tileX, ((PacketTileState)object).tileY,
								((PacketTileState)object).natureTex, ((PacketTileState)object).gilded);
		}
		else if (object instanceof PacketStart) {
			gameScreen.playerId = ((PacketStart)object).playersId;
		}
	}
}
