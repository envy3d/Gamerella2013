package com.envy3d.GamerellaJam.networking;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class ServerNetworkListener extends Listener {
	
	@Override
	public void connected(Connection connection) {
		super.connected(connection);
		Log.info("[SERVER] Someone has connected.");
	}
	
	@Override
	public void disconnected(Connection connection) {
		super.disconnected(connection);
		Log.info("[SERVER] Someone has disconnected.");
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
			Log.info(message);
		}
	}	
}
