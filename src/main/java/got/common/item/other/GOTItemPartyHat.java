package got.common.item.other;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import got.common.database.GOTCreativeTabs;
import got.common.database.GOTMaterial;
import got.common.database.GOTRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

import java.util.List;

public class GOTItemPartyHat extends GOTItemArmor {
	public static int HAT_WHITE = 16777215;

	public GOTItemPartyHat() {
		super(GOTMaterial.ROBES, 0);
		setCreativeTab(GOTCreativeTabs.tabMisc);
	}

	public static ItemStack createDyedHat(int i) {
		return GOTItemPartyHat.setHatColor(new ItemStack(GOTRegistry.partyHat), i);
	}

	public static int getHatColor(ItemStack itemstack) {
		int dye = GOTItemPartyHat.getSavedDyeColor(itemstack);
		if (dye != -1) {
			return dye;
		}
		return 16777215;
	}

	public static int getSavedDyeColor(ItemStack itemstack) {
		if (itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("HatColor")) {
			return itemstack.getTagCompound().getInteger("HatColor");
		}
		return -1;
	}

	public static boolean isHatDyed(ItemStack itemstack) {
		return GOTItemPartyHat.getSavedDyeColor(itemstack) != -1;
	}

	public static void removeHatDye(ItemStack itemstack) {
		if (itemstack.getTagCompound() != null) {
			itemstack.getTagCompound().removeTag("HatColor");
		}
	}

	public static ItemStack setHatColor(ItemStack itemstack, int i) {
		if (itemstack.getTagCompound() == null) {
			itemstack.setTagCompound(new NBTTagCompound());
		}
		itemstack.getTagCompound().setInteger("HatColor", i);
		return itemstack;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
		if (GOTItemPartyHat.isHatDyed(itemstack) && GOTItemPartyHat.getHatColor(itemstack) != 16777215) {
			list.add(StatCollector.translateToLocal("item.got.hat.dyed"));
		}
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return "got:textures/armor/partyhat.png";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getColorFromItemStack(ItemStack itemstack, int pass) {
		return GOTItemPartyHat.getHatColor(itemstack);
	}
}
