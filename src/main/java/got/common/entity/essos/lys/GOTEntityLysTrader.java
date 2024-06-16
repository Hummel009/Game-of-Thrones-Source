package got.common.entity.essos.lys;

import got.common.GOTLevelData;
import got.common.database.GOTItems;
import got.common.entity.other.iface.GOTTradeable;
import got.common.item.other.GOTItemRobes;
import got.common.item.other.GOTItemTurban;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.awt.*;
import java.util.Random;

public abstract class GOTEntityLysTrader extends GOTEntityLysMan implements GOTTradeable {
	protected GOTEntityLysTrader(World world) {
		super(world);
		addTargetTasks(false);
	}

	private static ItemStack createTraderTurban(Random random) {
		ItemStack turban = new ItemStack(GOTItems.robesHelmet);
		if (random.nextInt(3) == 0) {
			GOTItemTurban.setHasOrnament(turban, true);
		}
		float h = random.nextFloat() * 360.0f;
		float s = MathHelper.randomFloatClamp(random, 0.6f, 0.8f);
		float b = MathHelper.randomFloatClamp(random, 0.5f, 0.75f);
		int turbanColor = Color.HSBtoRGB(h, s, b) & 0xFFFFFF;
		GOTItemRobes.setRobesColor(turban, turbanColor);
		return turban;
	}

	@Override
	public boolean canTradeWith(EntityPlayer entityplayer) {
		return GOTLevelData.getData(entityplayer).getAlignment(getFaction()) >= 0.0f && isFriendly(entityplayer);
	}

	@Override
	public float getAlignmentBonus() {
		return 2.0f;
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		IEntityLivingData entityData = super.onSpawnWithEgg(data);
		setCurrentItemOrArmor(4, createTraderTurban(rand));
		return entityData;
	}
}