package com.envy3d.shared;

public class Entity {
	public static final float MAX_SPEED = 25f;
	
	public int id;
	private boolean gilded;
	public int posX, posY;
	public IntVec2 dest;
	public FloatVec2 dirVec;
	
	public Entity(int id, boolean gilded, int posX, int posY) {
		this.id = id;
		this.gilded = gilded;
		this.posX = posX;
		this.posY = posY;
		dirVec = new FloatVec2();
		dest = new IntVec2(posX, posY);
	}
	
	public boolean isGilded() {
		return gilded;
	}
}
