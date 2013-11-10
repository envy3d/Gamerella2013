package com.envy3d.server.gamerella;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.envy3d.shared.Map;
import com.envy3d.shared.Packets.*;

public class Main {
	public static NetworkServer netServer;
	public static GameLogic gameLogic;
	public static long currentTime;
	public static float deltaTime;
	
	public static void main(String[] args) {
		gameLogic = new GameLogic();
		try {
			netServer = new NetworkServer(gameLogic);
		} catch (IOException e) {
			e.printStackTrace();
		}
		currentTime = System.nanoTime();
		deltaTime = 0.01f;
		
		while(true) {
			deltaTime = TimeUnit.SECONDS.convert(System.nanoTime() - currentTime, TimeUnit.NANOSECONDS);
			currentTime = System.nanoTime();
			update(deltaTime);
			
		}
	}

	public static void update(float delta) {
		
	}
}
