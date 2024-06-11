package got.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import got.GOT;
import got.common.GOTLevelData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class GOTPacketDragonFireballTimer implements IMessage {
	private int timer;

	@SuppressWarnings("unused")
	public GOTPacketDragonFireballTimer() {
	}

	public GOTPacketDragonFireballTimer(int i) {
		timer = i;
	}

	@Override
	public void fromBytes(ByteBuf data) {
		timer = data.readInt();
	}

	@Override
	public void toBytes(ByteBuf data) {
		data.writeInt(timer);
	}

	public static class Handler implements IMessageHandler<GOTPacketDragonFireballTimer, IMessage> {
		@Override
		public IMessage onMessage(GOTPacketDragonFireballTimer packet, MessageContext context) {
			EntityPlayer entityplayer = GOT.proxy.getClientPlayer();
			GOTLevelData.getData(entityplayer).setTimeSinceDragonFireball(packet.timer);
			return null;
		}
	}
}
