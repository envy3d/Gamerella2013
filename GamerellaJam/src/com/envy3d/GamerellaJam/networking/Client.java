package com.envy3d.GamerellaJam.networking;

import java.io.IOException;
import java.util.Scanner;

import com.envy3d.NetworkStuff.ClientNetworkListener;
import com.envy3d.NetworkStuff.Packet.Packet0LogInRequest;
import com.envy3d.NetworkStuff.Packet.Packet1LogInAnswer;
import com.envy3d.NetworkStuff.Packet.Packet2Message;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.minlog.Log;

public class Client {
	public Client client;
	public static Scanner scanner;
	
	public Client() {
		scanner = new Scanner(System.in);
		client = new Client();
		register();
		
		ClientNetworkListener nl = new ClientNetworkListener();
		nl.init(client);
		client.addListener(nl);
		
		client.start();
		try {
			Log.info("Enter the specified IP.");
			client.connect(15000, "localhost", 54555);
			//client.connect(15000, scanner.nextLine(), 54555);
		} catch (IOException e) {
			e.printStackTrace();
			client.stop();
		}
	}
	
	private void register() {
		Kryo kryo = client.getKryo();
		kryo.register(Packet0LogInRequest.class);
		kryo.register(Packet1LogInAnswer.class);
		kryo.register(Packet2Message.class);
	}
}
