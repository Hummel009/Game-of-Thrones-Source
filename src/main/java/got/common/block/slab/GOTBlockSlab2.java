package got.common.block.slab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import got.common.database.GOTRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class GOTBlockSlab2 extends GOTBlockSlabBase {
	public GOTBlockSlab2(boolean flag) {
		super(flag, Material.rock, 8);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int i, int j) {
		j &= 7;
		switch (j) {
			case 0:
				return GOTRegistry.smoothStone.getIcon(i, 6);
			case 1:
				return GOTRegistry.smoothStone.getIcon(i, 2);
			case 2:
				return GOTRegistry.brick1.getIcon(i, 7);
			case 3:
				return GOTRegistry.pillar2.getIcon(i, 0);
			case 4:
				return GOTRegistry.rock.getIcon(i, 6);
			case 5:
				return GOTRegistry.brick6.getIcon(i, 4);
			case 6:
				return GOTRegistry.brick6.getIcon(i, 6);
			case 7:
				return GOTRegistry.brick6.getIcon(i, 7);
			default:
				break;
		}
		return super.getIcon(i, j);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
		list.add(new ItemStack(item, 1, 2));
		list.add(new ItemStack(item, 1, 3));
		list.add(new ItemStack(item, 1, 4));
		list.add(new ItemStack(item, 1, 5));
		list.add(new ItemStack(item, 1, 6));
		list.add(new ItemStack(item, 1, 7));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconregister) {
	}
}
