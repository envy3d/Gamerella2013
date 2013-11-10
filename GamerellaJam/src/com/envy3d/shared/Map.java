package com.envy3d.shared;

import java.util.Random;

public class Map {
	public static final int WIDTH = 200;
	public static final int HEIGHT = 200;
	private MapTile[][] map;
	
	
	public Map() {
		Random r = new Random();
		map = new MapTile[HEIGHT][];
		for (int i = 0; i < HEIGHT; i++) {
			map[i] = new MapTile[WIDTH];
			for (int j = 0; j < WIDTH; j++) {
				map[i][j] = new MapTile(r.nextInt(2));
			}
		}
	}
	
	public synchronized void set(int x, int y, boolean gilded) {
		if (y >= 0 && y < HEIGHT) {
			if (x >= 0 && x < WIDTH) {
				map[y][x].gilded = gilded;
			}
		}
	}
	
	public synchronized void set(int x, int y, int natureTex, boolean gilded) {
		if (y >= 0 && y < HEIGHT) {
			if (x >= 0 && x < WIDTH) {
				map[y][x].natureTex = natureTex;
				map[y][x].gilded = gilded;
			}
		}
	}
	
	public synchronized MapTile get(int x, int y) { 
		return map[y][x];
	}
	
}
