package com.envy3d.networking.shared;

public class Packets {
	
	public static class Packet0LogInRequest { }
	
	public static class Packet1LogInAnswer { public boolean accepted = false; }
	
	public static class Packet2Message { public String message; }
	
}
