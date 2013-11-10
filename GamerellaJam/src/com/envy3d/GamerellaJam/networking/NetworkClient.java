package com.envy3d.GamerellaJam.networking;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.envy3d.GamerellaJam.GameScreen;
import com.envy3d.shared.PacketRegister;
import com.esotericsoftware.kryonet.Client;

public class NetworkClient {
	private static final String urlString = "ec2-54-214-194-15.us-west-2.compute.amazonaws.com";
	private GameScreen gameScreen;
	public Client client;
	
	public NetworkClient(GameScreen gameScreen) {
		client = new Client();
		register();
		ClientNetworkListener nl = new ClientNetworkListener(gameScreen);
		nl.init(client);
		client.addListener(nl);
		client.start();
		
		while (!client.isConnected()) {
			try {
				//client.connect(15000, "", 54555);
				client.connect(15000, InetAddress.getByName(new URL(urlString).getHost()).getHostAddress(), 54555);
			} catch (IOException e) {
				e.printStackTrace();
				client.stop();
			}
		}
		Gdx.app.log("Network: ", "Client has connected to Server");
	}
	
	private void register() {
		PacketRegister.registerPackets(client.getKryo());
	}
}
