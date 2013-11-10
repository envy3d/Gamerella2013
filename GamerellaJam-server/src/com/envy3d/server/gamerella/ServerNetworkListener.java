package com.envy3d.server.gamerella;

import com.envy3d.networking.shared.Packets.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ServerNetworkListener extends Listener {
	
	@Override
	public void connected(Connection connection) {
		super.connected(connection);
		System.out.println("[SERVER] Someone has connected.");
	}
	
	@Override
	public void disconnected(Connection connection) {
		super.disconnected(connection);
		System.out.println("[SERVER] Someone has disconnected.");
	}
	
	@Override
	public void received(Connection connection, Object object) {
		super.received(connection, object);
		
		if (object instanceof Packet0LogInRequest) {
			Packet1LogInAnswer logInAnswer = new Packet1LogInAnswer();
			logInAnswer.accepted = true;
			connection.sendTCP(logInAnswer);
		}
		
		if (object instanceof Packet2Message) {
			String message = ((Packet2Message)object).message;
			System.out.println(message);
			((Packet2Message)object).message = ((Packet2Message)object).message + "0";
			connection.sendTCP(object);
		}
	}	
}
