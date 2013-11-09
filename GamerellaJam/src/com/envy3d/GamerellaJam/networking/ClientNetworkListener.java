package com.envy3d.GamerellaJam.networking;

import com.envy3d.NetworkStuff.Packet.Packet0LogInRequest;
import com.envy3d.NetworkStuff.Packet.Packet1LogInAnswer;
import com.envy3d.NetworkStuff.Packet.Packet2Message;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class ClientNetworkListener extends Listener {
private Client client;
	
	public void init(Client client) {
		this.client = client;
	}
	
	@Override
	public void connected(Connection connection) {
		super.connected(connection);
		Log.info("[CLIENT] You have connected.");
		client.sendTCP(new Packet0LogInRequest());
	}
	
	@Override
	public void disconnected(Connection connection) {
		super.disconnected(connection);
		Log.info("[CLIENT] You have disconnected.");
	}
	
	@Override
	public void received(Connection connection, Object object) {
		super.received(connection, object);
		
		if (object instanceof Packet1LogInAnswer) {
			boolean answer = ((Packet1LogInAnswer)object).accepted;
			
			if (answer) {
				Log.info("Enter your first message for the server.");
				while (true) {
					//if (MPClient.scanner.hasNext()) {
						Packet2Message mpacket = new Packet2Message();
						//mpacket.message = MPClient.scanner.nextLine();
						mpacket.message = "Apples";
						Packet2Message mpacket2 = new Packet2Message();
						mpacket2.message = "Oranges";
						client.sendTCP(mpacket);
						client.sendTCP(mpacket2);
						//Log.info("Enter another message.");
						Log.info(mpacket.message);
						Log.info(mpacket2.message);
					//}
				}
			}
			else {
				connection.close();
			}
		}
	}
}
