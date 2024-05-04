package got.common.network;

import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import got.GOT;
import got.common.GOTLevelData;
import got.common.GOTPlayerData;
import got.common.database.GOTTitle;
import got.common.fellowship.GOTFellowship;
import got.common.fellowship.GOTFellowshipClient;
import got.common.util.GOTLog;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

import java.io.IOException;
import java.util.UUID;

public abstract class GOTPacketFellowshipPartialUpdate implements IMessage {
	private UUID fellowshipID;

	protected GOTPacketFellowshipPartialUpdate() {
	}

	protected GOTPacketFellowshipPartialUpdate(GOTFellowship fs) {
		fellowshipID = fs.getFellowshipID();
	}

	@Override
	public void fromBytes(ByteBuf data) {
		fellowshipID = new UUID(data.readLong(), data.readLong());
	}

	@Override
	public void toBytes(ByteBuf data) {
		data.writeLong(fellowshipID.getMostSignificantBits());
		data.writeLong(fellowshipID.getLeastSignificantBits());
	}

	protected abstract void updateClient(GOTFellowshipClient var1);

	protected UUID getFellowshipID() {
		return fellowshipID;
	}

	public static class AddMember extends OnePlayerUpdate {
		private GOTTitle.PlayerTitle playerTitle;

		@SuppressWarnings({"WeakerAccess", "unused"})
		public AddMember() {
		}

		public AddMember(GOTFellowship fs, UUID member) {
			super(fs, member);
			playerTitle = GOTLevelData.getData(member).getPlayerTitle();
		}

		@Override
		public void fromBytes(ByteBuf data) {
			super.fromBytes(data);
			playerTitle = GOTTitle.PlayerTitle.readNullableTitle(data);
		}

		@Override
		public void toBytes(ByteBuf data) {
			super.toBytes(data);
			GOTTitle.PlayerTitle.writeNullableTitle(data, playerTitle);
		}

		@Override
		public void updateClient(GOTFellowshipClient fellowship) {
			fellowship.addMember(playerProfile, playerTitle);
		}

		public static class Handler extends GOTPacketFellowshipPartialUpdate.Handler<AddMember> {
		}
	}

	public static class ChangeIcon extends GOTPacketFellowshipPartialUpdate {
		private ItemStack fellowshipIcon;

		@SuppressWarnings({"WeakerAccess", "unused"})
		public ChangeIcon() {
		}

		public ChangeIcon(GOTFellowship fs) {
			super(fs);
			fellowshipIcon = fs.getIcon();
		}

		@Override
		public void fromBytes(ByteBuf data) {
			super.fromBytes(data);
			NBTTagCompound iconData = new NBTTagCompound();
			try {
				iconData = new PacketBuffer(data).readNBTTagCompoundFromBuffer();
			} catch (IOException e) {
				FMLLog.severe("Hummel009: Error reading fellowship icon data");
				e.printStackTrace();
			}
			fellowshipIcon = ItemStack.loadItemStackFromNBT(iconData);
		}

		@Override
		public void toBytes(ByteBuf data) {
			super.toBytes(data);
			NBTTagCompound iconData = new NBTTagCompound();
			if (fellowshipIcon != null) {
				fellowshipIcon.writeToNBT(iconData);
			}
			try {
				new PacketBuffer(data).writeNBTTagCompoundToBuffer(iconData);
			} catch (IOException e) {
				FMLLog.severe("Hummel009: Error writing fellowship icon data");
				e.printStackTrace();
			}
		}

		@Override
		public void updateClient(GOTFellowshipClient fellowship) {
			fellowship.setIcon(fellowshipIcon);
		}

		public static class Handler extends GOTPacketFellowshipPartialUpdate.Handler<ChangeIcon> {
		}
	}

	private abstract static class Handler<P extends GOTPacketFellowshipPartialUpdate> implements IMessageHandler<P, IMessage> {
		@Override
		public IMessage onMessage(P packet, MessageContext context) {
			EntityPlayer entityplayer = GOT.proxy.getClientPlayer();
			GOTPlayerData pd = GOTLevelData.getData(entityplayer);
			GOTFellowshipClient fellowship = pd.getClientFellowshipByID(packet.getFellowshipID());
			if (fellowship != null) {
				packet.updateClient(fellowship);
			} else {
				GOTLog.getLogger().warn("Client couldn't find fellowship for ID {}", packet.getFellowshipID());
			}
			return null;
		}
	}

	private abstract static class OnePlayerUpdate extends GOTPacketFellowshipPartialUpdate {
		protected GameProfile playerProfile;

		protected OnePlayerUpdate() {
		}

		protected OnePlayerUpdate(GOTFellowship fs, UUID player) {
			super(fs);
			playerProfile = GOTPacketFellowship.getPlayerProfileWithUsername(player);
		}

		@Override
		public void fromBytes(ByteBuf data) {
			super.fromBytes(data);
			playerProfile = GOTPacketFellowship.readPlayerUuidAndUsername(data);
		}

		@Override
		public void toBytes(ByteBuf data) {
			super.toBytes(data);
			GOTPacketFellowship.writePlayerUuidAndUsername(data, playerProfile);
		}
	}

	public static class RemoveAdmin extends OnePlayerUpdate {
		private boolean isAdminned;

		@SuppressWarnings({"WeakerAccess", "unused"})
		public RemoveAdmin() {
		}

		public RemoveAdmin(GOTFellowship fs, UUID admin, boolean adminned) {
			super(fs, admin);
			isAdminned = adminned;
		}

		@Override
		public void fromBytes(ByteBuf data) {
			super.fromBytes(data);
			isAdminned = data.readBoolean();
		}

		@Override
		public void toBytes(ByteBuf data) {
			super.toBytes(data);
			data.writeBoolean(isAdminned);
		}

		@Override
		public void updateClient(GOTFellowshipClient fellowship) {
			fellowship.removeAdmin(playerProfile.getId(), isAdminned);
		}

		public static class Handler extends GOTPacketFellowshipPartialUpdate.Handler<RemoveAdmin> {
		}
	}

	public static class RemoveMember extends OnePlayerUpdate {
		@SuppressWarnings({"WeakerAccess", "unused"})
		public RemoveMember() {
		}

		public RemoveMember(GOTFellowship fs, UUID member) {
			super(fs, member);
		}

		@Override
		public void updateClient(GOTFellowshipClient fellowship) {
			fellowship.removeMember(playerProfile);
		}

		public static class Handler extends GOTPacketFellowshipPartialUpdate.Handler<RemoveMember> {
		}
	}

	public static class Rename extends GOTPacketFellowshipPartialUpdate {
		private String fellowshipName;

		public Rename() {
		}

		public Rename(GOTFellowship fs) {
			super(fs);
			fellowshipName = fs.getName();
		}

		@Override
		public void fromBytes(ByteBuf data) {
			super.fromBytes(data);
			byte fsNameLength = data.readByte();
			ByteBuf fsNameBytes = data.readBytes(fsNameLength);
			fellowshipName = fsNameBytes.toString(Charsets.UTF_8);
		}

		@Override
		public void toBytes(ByteBuf data) {
			super.toBytes(data);
			byte[] fsNameBytes = fellowshipName.getBytes(Charsets.UTF_8);
			data.writeByte(fsNameBytes.length);
			data.writeBytes(fsNameBytes);
		}

		@Override
		public void updateClient(GOTFellowshipClient fellowship) {
			fellowship.setName(fellowshipName);
		}

		public static class Handler extends GOTPacketFellowshipPartialUpdate.Handler<Rename> {
		}
	}

	public static class SetAdmin extends OnePlayerUpdate {
		private boolean isAdminned;

		@SuppressWarnings({"WeakerAccess", "unused"})
		public SetAdmin() {
		}

		public SetAdmin(GOTFellowship fs, UUID admin, boolean adminned) {
			super(fs, admin);
			isAdminned = adminned;
		}

		@Override
		public void fromBytes(ByteBuf data) {
			super.fromBytes(data);
			isAdminned = data.readBoolean();
		}

		@Override
		public void toBytes(ByteBuf data) {
			super.toBytes(data);
			data.writeBoolean(isAdminned);
		}

		@Override
		public void updateClient(GOTFellowshipClient fellowship) {
			fellowship.setAdmin(playerProfile.getId(), isAdminned);
		}

		public static class Handler extends GOTPacketFellowshipPartialUpdate.Handler<SetAdmin> {
		}
	}

	public static class SetOwner extends OnePlayerUpdate {
		private boolean isOwned;

		@SuppressWarnings({"WeakerAccess", "unused"})
		public SetOwner() {
		}

		public SetOwner(GOTFellowship fs, UUID owner, boolean owned) {
			super(fs, owner);
			isOwned = owned;
		}

		@Override
		public void fromBytes(ByteBuf data) {
			super.fromBytes(data);
			isOwned = data.readBoolean();
		}

		@Override
		public void toBytes(ByteBuf data) {
			super.toBytes(data);
			data.writeBoolean(isOwned);
		}

		@Override
		public void updateClient(GOTFellowshipClient fellowship) {
			fellowship.setOwner(playerProfile, isOwned);
		}

		public static class Handler extends GOTPacketFellowshipPartialUpdate.Handler<SetOwner> {
		}
	}

	public static class ToggleHiredFriendlyFire extends GOTPacketFellowshipPartialUpdate {
		private boolean preventHiredFF;

		@SuppressWarnings({"WeakerAccess", "unused"})
		public ToggleHiredFriendlyFire() {
		}

		public ToggleHiredFriendlyFire(GOTFellowship fs) {
			super(fs);
			preventHiredFF = fs.getPreventHiredFriendlyFire();
		}

		@Override
		public void fromBytes(ByteBuf data) {
			super.fromBytes(data);
			preventHiredFF = data.readBoolean();
		}

		@Override
		public void toBytes(ByteBuf data) {
			super.toBytes(data);
			data.writeBoolean(preventHiredFF);
		}

		@Override
		public void updateClient(GOTFellowshipClient fellowship) {
			fellowship.setPreventHiredFriendlyFire(preventHiredFF);
		}

		public static class Handler extends GOTPacketFellowshipPartialUpdate.Handler<ToggleHiredFriendlyFire> {
		}
	}

	public static class TogglePvp extends GOTPacketFellowshipPartialUpdate {
		private boolean preventPVP;

		@SuppressWarnings({"WeakerAccess", "unused"})
		public TogglePvp() {
		}

		public TogglePvp(GOTFellowship fs) {
			super(fs);
			preventPVP = fs.getPreventPVP();
		}

		@Override
		public void fromBytes(ByteBuf data) {
			super.fromBytes(data);
			preventPVP = data.readBoolean();
		}

		@Override
		public void toBytes(ByteBuf data) {
			super.toBytes(data);
			data.writeBoolean(preventPVP);
		}

		@Override
		public void updateClient(GOTFellowshipClient fellowship) {
			fellowship.setPreventPVP(preventPVP);
		}

		public static class Handler extends GOTPacketFellowshipPartialUpdate.Handler<TogglePvp> {
		}
	}

	public static class ToggleShowMap extends GOTPacketFellowshipPartialUpdate {
		private boolean showMapLocations;

		@SuppressWarnings({"WeakerAccess", "unused"})
		public ToggleShowMap() {
		}

		public ToggleShowMap(GOTFellowship fs) {
			super(fs);
			showMapLocations = fs.getShowMapLocations();
		}

		@Override
		public void fromBytes(ByteBuf data) {
			super.fromBytes(data);
			showMapLocations = data.readBoolean();
		}

		@Override
		public void toBytes(ByteBuf data) {
			super.toBytes(data);
			data.writeBoolean(showMapLocations);
		}

		@Override
		public void updateClient(GOTFellowshipClient fellowship) {
			fellowship.setShowMapLocations(showMapLocations);
		}

		public static class Handler extends GOTPacketFellowshipPartialUpdate.Handler<ToggleShowMap> {
		}
	}

	public static class UpdatePlayerTitle extends OnePlayerUpdate {
		private GOTTitle.PlayerTitle playerTitle;

		@SuppressWarnings({"WeakerAccess", "unused"})
		public UpdatePlayerTitle() {
		}

		public UpdatePlayerTitle(GOTFellowship fs, UUID player, GOTTitle.PlayerTitle title) {
			super(fs, player);
			playerTitle = title;
		}

		@Override
		public void fromBytes(ByteBuf data) {
			super.fromBytes(data);
			playerTitle = GOTTitle.PlayerTitle.readNullableTitle(data);
		}

		@Override
		public void toBytes(ByteBuf data) {
			super.toBytes(data);
			GOTTitle.PlayerTitle.writeNullableTitle(data, playerTitle);
		}

		@Override
		public void updateClient(GOTFellowshipClient fellowship) {
			fellowship.updatePlayerTitle(playerProfile.getId(), playerTitle);
		}

		public static class Handler extends GOTPacketFellowshipPartialUpdate.Handler<UpdatePlayerTitle> {
		}
	}
}