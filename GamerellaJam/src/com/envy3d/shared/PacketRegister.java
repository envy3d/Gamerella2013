package com.envy3d.shared;

import com.envy3d.shared.Packets.*;
import com.esotericsoftware.kryo.Kryo;

public class PacketRegister {

	private PacketRegister() {
		
	}
	
	public static void registerPackets(Kryo kryo) {
		kryo.register(PacketLogInRequest.class);
		kryo.register(PacketLogInAnswer.class);
		kryo.register(PacketMessage.class);
		kryo.register(PacketMove.class);
		kryo.register(PacketTransmute.class);
		kryo.register(PacketSpawn.class);
		kryo.register(PacketDespawn.class);
		kryo.register(PacketTileState.class);
		kryo.register(PacketStart.class);
	}
}
