package com.envy3d.server.gamerella;

import java.io.IOException;

import com.envy3d.networking.shared.Packets.*;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;

public class NetworkServer {
	public Server server;
	
	public NetworkServer() throws IOException {
		server = new Server();
		registerPackets();
		server.addListener(new ServerNetworkListener());
		server.bind(54554);
		server.start();
		
		System.out.println("Host has connected to client.");
	}
	
	private void registerPackets() {
		Kryo kryo = server.getKryo();
		kryo.register(Packet0LogInRequest.class);
		kryo.register(Packet1LogInAnswer.class);
		kryo.register(Packet2Message.class);
	}
}
