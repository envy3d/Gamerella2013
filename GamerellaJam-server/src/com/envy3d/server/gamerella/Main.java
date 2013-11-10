package com.envy3d.server.gamerella;

import java.io.IOException;

import com.envy3d.networking.shared.Packets.*;

public class Main {
	public static NetworkServer netServer;
	
	public static void main(String[] args) {
		try {
			netServer = new NetworkServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true) {
			if(netServer.server != null) {
				if (netServer.server.getConnections() != null) {
					//stuff
					Packet2Message p2m = new Packet2Message();
					p2m.message = "connected!";
					netServer.server.sendToAllTCP(p2m);
				}
			}
		}
	}

}
