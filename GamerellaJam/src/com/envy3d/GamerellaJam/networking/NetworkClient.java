package com.envy3d.GamerellaJam.networking;

import java.io.IOException;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.envy3d.networking.shared.Packets.*;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.minlog.Log;

public class NetworkClient {
	public Client client;
	public static Scanner scanner;
	
	public NetworkClient() {
		scanner = new Scanner(System.in);
		client = new Client();
		register();
		
		ClientNetworkListener nl = new ClientNetworkListener();
		nl.init(client);
		client.addListener(nl);
		
		while (!client.isConnected()) {
			try {
				client.start();
				//client.connect(15000, "132.205.167.195", 54555);  // comp
				client.connect(15000, "54.214.194.15", 54555);     // phone
			} catch (IOException e) {
				e.printStackTrace();
				client.stop();
			}
		}
		Gdx.app.log("Network: ", "Client has connected to Server");
	}
	
	private void register() {
		Kryo kryo = client.getKryo();
		kryo.register(Packet0LogInRequest.class);
		kryo.register(Packet1LogInAnswer.class);
		kryo.register(Packet2Message.class);
	}
}
