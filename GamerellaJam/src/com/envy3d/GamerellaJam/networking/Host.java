package com.envy3d.GamerellaJam.networking;

import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;

public class Host {
	private Server server;
	
	public Host() throws IOException {
		server = new Server();
		registerPackets();
		server.addListener(new ServerNetworkListener());
		server.bind(54555);
		server.start();
	}
	
	private void registerPackets() {
		Kryo kryo = server.getKryo();
		kryo.register(Packet0LogInRequest.class);
		kryo.register(Packet1LogInAnswer.class);
		kryo.register(Packet2Message.class);
	}
}
