package com.envy3d.shared;

public class Entity {
	public int id;
	private boolean gilded;
	public int posX, posY;
	public IntVec2 dest;
	
	public Entity(int id, boolean gilded, int posX, int posY) {
		this.id = id;
		this.gilded = gilded;
		this.posX = posX;
		this.posY = posY;
	}
	
	public boolean isGilded() {
		return gilded;
	}
}
