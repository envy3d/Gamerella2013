package com.envy3d.shared;

public class Packets {
	
	public static class PacketLogInRequest { }
	
	public static class PacketLogInAnswer { public boolean accepted = false; }
	
	public static class PacketMessage { public String message; }
	
	public static class PacketMove { public int id; public int startX; public int startY; public int destX; public int destY; }
	
	public static class PacketTransmute { public short tileX; public short tileY; public boolean gilded; }
	
	public static class PacketSpawn {
		public int id; public boolean gilded; public int startX; public int startY;
		public PacketSpawn( int id, boolean gilded, int startX, int startY) {
			this.id = id;
			this.gilded = gilded;
			this.startX = startX;
			this.startY = startY;
		}
	}
	
	public static class PacketDespawn { public int id; }
	
	public static class PacketTileState { 
		public short tileX; public short tileY; public byte natureTex; public boolean gilded;
		public PacketTileState(short tileX, short tileY, byte natureTex, boolean gilded) {
			this.tileX = tileX;
			this.tileY = tileY;
			this.natureTex = natureTex;
			this.gilded = gilded;
		}
	}
	
	public static class PacketStart {
		public int playersId;
		public PacketStart(int playersId) {
			this.playersId = playersId;
		}
	}
	
}
