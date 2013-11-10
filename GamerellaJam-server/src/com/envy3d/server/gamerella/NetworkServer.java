package com.envy3d.server.gamerella;

import java.io.IOException;

import com.envy3d.shared.PacketRegister;
import com.esotericsoftware.kryonet.Server;

public class NetworkServer {
	private GameLogic gameLogic;
	public Server server;
	
	public NetworkServer(GameLogic gameLogic) throws IOException {
		server = new Server();
		registerPackets();
		server.addListener(new ServerNetworkListener(gameLogic, server));
		server.bind(54555);
		server.start();
		System.out.println("Host has connected to client.");
	}
	
	private void registerPackets() {
		PacketRegister.registerPackets(server.getKryo());
	}
}
