package com.envy3d.shared;

public class FloatVec2 {
	public float x;
	public float y;
	
	public FloatVec2() {
		this(0,0);
	}
	
	public FloatVec2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float dot(FloatVec2 vec) {
		return (this.x * vec.x) + (this.y * vec.y);
	}
	
	public FloatVec2 normalize() {
		float mag = magnitude();
		x = x/mag;
		y = y/mag;
		return this;
	}
	
	public float magnitude() {
		return (float)Math.sqrt((x * x) + (y * y));
	}
}
