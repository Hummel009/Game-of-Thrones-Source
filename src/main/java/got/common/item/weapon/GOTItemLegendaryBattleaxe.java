package got.common.item.weapon;

import got.common.database.GOTCreativeTabs;
import net.minecraft.item.Item;

public class GOTItemLegendaryBattleaxe extends GOTItemBattleaxe {
	public GOTItemLegendaryBattleaxe(Item.ToolMaterial material) {
		super(material);
		setMaxDamage((int) Math.ceil(getMaxDamage() * 1.1f));
		gotWeaponDamage = (int) Math.ceil(gotWeaponDamage * 1.1f);
		setCreativeTab(GOTCreativeTabs.TAB_STORY);
	}
}