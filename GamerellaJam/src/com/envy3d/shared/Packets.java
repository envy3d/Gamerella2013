package com.envy3d.shared;

public class Packets {
	
	public static class PacketLogInRequest { }
	
	public static class PacketLogInAnswer { public boolean accepted = false; }
	
	public static class PacketMessage { public String message; }
	
	public static class PacketMove { public short id; public int startX; public int startY; public int destX; public int destY; }
	
	public static class PacketTransmute { public short tileX; public short tileY; public boolean gilded; }
	
	public static class PacketSpawn {
		public short id; public boolean gilded; public int startX; public int startY;
		public PacketSpawn( short id, boolean gilded, int startX, int startY) {
			this.id = id;
			this.gilded = gilded;
			this.startX = startX;
			this.startY = startY;
		}
	}
	
	public static class PacketDespawn { public short id; }
	
	public static class PacketTileState { 
		public byte natureTex; public boolean gilded;
		public PacketTileState(byte natureTex, boolean gilded) {
			this.natureTex = natureTex;
			this.gilded = gilded;
		}
	}
	
	public static class PacketStart {
		public short playersId;
		public PacketStart(short playersId) {
			this.playersId = playersId;
		}
	}
	
}
