package got.common.database;

import got.common.entity.animal.GOTEntityHorse;
import got.common.entity.animal.GOTEntityWoolyRhino;
import got.common.entity.animal.GOTEntityZebra;
import got.common.entity.essos.asshai.GOTEntityAsshaiBannerBearer;
import got.common.entity.essos.asshai.GOTEntityAsshaiShadowbinder;
import got.common.entity.essos.asshai.GOTEntityAsshaiSpherebinder;
import got.common.entity.essos.asshai.GOTEntityAsshaiWarrior;
import got.common.entity.essos.braavos.*;
import got.common.entity.essos.dothraki.GOTEntityDothraki;
import got.common.entity.essos.dothraki.GOTEntityDothrakiArcher;
import got.common.entity.essos.ghiscar.*;
import got.common.entity.essos.gold.GOTEntityGoldenCompanyBannerBearer;
import got.common.entity.essos.gold.GOTEntityGoldenCompanySpearman;
import got.common.entity.essos.gold.GOTEntityGoldenCompanyWarrior;
import got.common.entity.essos.ibben.*;
import got.common.entity.essos.jogos.GOTEntityJogos;
import got.common.entity.essos.jogos.GOTEntityJogosArcher;
import got.common.entity.essos.lhazar.*;
import got.common.entity.essos.lorath.*;
import got.common.entity.essos.lys.*;
import got.common.entity.essos.myr.*;
import got.common.entity.essos.norvos.*;
import got.common.entity.essos.pentos.*;
import got.common.entity.essos.qarth.*;
import got.common.entity.essos.qohor.*;
import got.common.entity.essos.tyrosh.*;
import got.common.entity.essos.volantis.*;
import got.common.entity.essos.yiti.*;
import got.common.entity.other.GOTEntityProstitute;
import got.common.entity.other.info.GOTHireableInfo;
import got.common.entity.other.utils.GOTUnitTradeEntry;
import got.common.entity.sothoryos.sothoryos.GOTEntitySothoryosBannerBearer;
import got.common.entity.sothoryos.sothoryos.GOTEntitySothoryosBlowgunner;
import got.common.entity.sothoryos.sothoryos.GOTEntitySothoryosFarmhand;
import got.common.entity.sothoryos.sothoryos.GOTEntitySothoryosWarrior;
import got.common.entity.sothoryos.summer.*;
import got.common.entity.westeros.arryn.*;
import got.common.entity.westeros.crownlands.GOTEntityCrownlandsFarmhand;
import got.common.entity.westeros.crownlands.GOTEntityCrownlandsLevyman;
import got.common.entity.westeros.crownlands.GOTEntityCrownlandsLevymanArcher;
import got.common.entity.westeros.crownlands.GOTEntityKingsguard;
import got.common.entity.westeros.dorne.*;
import got.common.entity.westeros.dragonstone.*;
import got.common.entity.westeros.gift.GOTEntityGiftBannerBearer;
import got.common.entity.westeros.gift.GOTEntityGiftGuard;
import got.common.entity.westeros.hillmen.*;
import got.common.entity.westeros.ironborn.*;
import got.common.entity.westeros.north.*;
import got.common.entity.westeros.north.hillmen.GOTEntityNorthHillmanArcher;
import got.common.entity.westeros.north.hillmen.GOTEntityNorthHillmanAxeThrower;
import got.common.entity.westeros.north.hillmen.GOTEntityNorthHillmanBannerBearer;
import got.common.entity.westeros.north.hillmen.GOTEntityNorthHillmanWarrior;
import got.common.entity.westeros.reach.*;
import got.common.entity.westeros.riverlands.*;
import got.common.entity.westeros.stormlands.*;
import got.common.entity.westeros.westerlands.*;
import got.common.entity.westeros.wildling.*;
import got.common.entity.westeros.wildling.thenn.GOTEntityThennArcher;
import got.common.entity.westeros.wildling.thenn.GOTEntityThennAxeThrower;
import got.common.entity.westeros.wildling.thenn.GOTEntityThennBannerBearer;
import got.common.entity.westeros.wildling.thenn.GOTEntityThennWarrior;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressWarnings({"WeakerAccess", "unused"})
public class GOTUnitTradeEntries {
	public static final Collection<GOTUnitTradeEntries> CONTENT = new ArrayList<>();

	public static final int LEVYMAN = 5;
	public static final int LEVYMANA = 10;
	public static final int SOLDIER = 10;
	public static final int SOLDIERA = 15;
	public static final int SOLDIERH = 15;
	public static final int SOLDIERHA = 20;

	public static final float LEVYMAN_F = 5.0f;
	public static final float LEVYMANA_F = 5.0f;
	public static final float SOLDIER_F = 10.0f;
	public static final float SOLDIERA_F = 10.0f;
	public static final float SOLDIERH_F = 15.0f;
	public static final float SOLDIERHA_F = 15.0f;

	public static final int SLAVE = 10;
	public static final float SLAVE_F = 0.0f;

	public static final GOTUnitTradeEntries ARRYN;
	public static final GOTUnitTradeEntries ASSHAI;
	public static final GOTUnitTradeEntries BRAAVOS;
	public static final GOTUnitTradeEntries CROWNLANDS;
	public static final GOTUnitTradeEntries DORNE;
	public static final GOTUnitTradeEntries DOTHRAKI;
	public static final GOTUnitTradeEntries DRAGONSTONE;
	public static final GOTUnitTradeEntries GHISCAR;
	public static final GOTUnitTradeEntries GHISCAR_UNSULLIED;
	public static final GOTUnitTradeEntries GIFT;
	public static final GOTUnitTradeEntries GOLDENCOMPANY;
	public static final GOTUnitTradeEntries HILLMEN;
	public static final GOTUnitTradeEntries IBBEN;
	public static final GOTUnitTradeEntries IRONBORN;
	public static final GOTUnitTradeEntries JOGOS;
	public static final GOTUnitTradeEntries KINGSGUARD;
	public static final GOTUnitTradeEntries LHAZAR;
	public static final GOTUnitTradeEntries LORATH;
	public static final GOTUnitTradeEntries LYS;
	public static final GOTUnitTradeEntries MYR;
	public static final GOTUnitTradeEntries NORTH;
	public static final GOTUnitTradeEntries NORTH_HILLMEN;
	public static final GOTUnitTradeEntries NORVOS;
	public static final GOTUnitTradeEntries PENTOS;
	public static final GOTUnitTradeEntries QARTH;
	public static final GOTUnitTradeEntries QOHOR;
	public static final GOTUnitTradeEntries REACH;
	public static final GOTUnitTradeEntries RIVERLANDS;
	public static final GOTUnitTradeEntries SOTHORYOS;
	public static final GOTUnitTradeEntries STORMLANDS;
	public static final GOTUnitTradeEntries SUMMER;
	public static final GOTUnitTradeEntries THENN;
	public static final GOTUnitTradeEntries TYROSH;
	public static final GOTUnitTradeEntries VOLANTIS;
	public static final GOTUnitTradeEntries WESTERLANDS;
	public static final GOTUnitTradeEntries WILDLING;
	public static final GOTUnitTradeEntries YITI;

	public static final GOTUnitTradeEntries ARRYN_FARMER;
	public static final GOTUnitTradeEntries BRAAVOS_FARMER;
	public static final GOTUnitTradeEntries CROWNLANDS_FARMER;
	public static final GOTUnitTradeEntries DORNE_FARMER;
	public static final GOTUnitTradeEntries DRAGONSTONE_FARMER;
	public static final GOTUnitTradeEntries GHISCAR_SLAVER;
	public static final GOTUnitTradeEntries IBBEN_FARMER;
	public static final GOTUnitTradeEntries IRONBORN_FARMER;
	public static final GOTUnitTradeEntries LHAZAR_FARMER;
	public static final GOTUnitTradeEntries LORATH_FARMER;
	public static final GOTUnitTradeEntries LYS_SLAVER;
	public static final GOTUnitTradeEntries MYR_SLAVER;
	public static final GOTUnitTradeEntries NORTH_FARMER;
	public static final GOTUnitTradeEntries NORVOS_FARMER;
	public static final GOTUnitTradeEntries PENTOS_FARMER;
	public static final GOTUnitTradeEntries QARTH_FARMER;
	public static final GOTUnitTradeEntries QOHOR_FARMER;
	public static final GOTUnitTradeEntries REACH_FARMER;
	public static final GOTUnitTradeEntries RIVERLANDS_FARMER;
	public static final GOTUnitTradeEntries SOTHORYOS_FARMER;
	public static final GOTUnitTradeEntries STORMLANDS_FARMER;
	public static final GOTUnitTradeEntries SUMMER_FARMER;
	public static final GOTUnitTradeEntries TYROSH_SLAVER;
	public static final GOTUnitTradeEntries VOLANTIS_SLAVER;
	public static final GOTUnitTradeEntries WESTERLANDS_FARMER;
	public static final GOTUnitTradeEntries YITI_FARMER;

	public static final GOTUnitTradeEntries PROSTITUTE_KEEPER;

	private static final List<GOTUnitTradeEntry> ARRYN_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> ASSHAI_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> BRAAVOS_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> CROWNLANDS_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> DORNE_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> DOTHRAKI_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> DRAGONSTONE_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> GHISCAR_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> GHISCAR_UNSULLIED_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> GIFT_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> GOLDENCOMPANY_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> HILLMEN_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> IBBEN_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> IRONBORN_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> JOGOS_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> KINGSGUARD_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> LHAZAR_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> LORATH_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> LYS_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> MYR_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> NORTH_HILLMEN_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> NORTH_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> NORVOS_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> PENTOS_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> QARTH_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> QOHOR_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> REACH_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> RIVERLANDS_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> SOTHORYOS_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> STORMLANDS_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> SUMMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> THENN_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> TYROSH_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> VOLANTIS_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> WESTERLANDS_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> WILDLING_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> YITI_L = new ArrayList<>();

	private static final List<GOTUnitTradeEntry> ARRYN_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> BRAAVOS_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> CROWNLANDS_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> DORNE_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> DRAGONSTONE_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> GHISCAR_SLAVER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> IBBEN_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> IRONBORN_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> LHAZAR_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> LORATH_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> LYS_SLAVER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> MYR_SLAVER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> NORTH_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> NORVOS_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> PENTOS_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> QARTH_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> QOHOR_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> REACH_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> RIVERLANDS_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> SOTHORYOS_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> STORMLANDS_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> SUMMER_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> TYROSH_SLAVER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> VOLANTIS_SLAVER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> WESTERLANDS_FARMER_L = new ArrayList<>();
	private static final List<GOTUnitTradeEntry> YITI_FARMER_L = new ArrayList<>();

	private static final List<GOTUnitTradeEntry> PROSTITUTE_KEEPER_L = new ArrayList<>();

	static {
		ARRYN_L.add(new GOTUnitTradeEntry(GOTEntityArrynLevyman.class, LEVYMAN, LEVYMAN_F));
		ARRYN_L.add(new GOTUnitTradeEntry(GOTEntityArrynLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		ARRYN_L.add(new GOTUnitTradeEntry(GOTEntityArrynSoldier.class, SOLDIER, SOLDIER_F));
		ARRYN_L.add(new GOTUnitTradeEntry(GOTEntityArrynSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		ARRYN_L.add(new GOTUnitTradeEntry(GOTEntityArrynBannerBearer.class, SOLDIER, SOLDIER_F));
		ARRYN_L.add(new GOTUnitTradeEntry(GOTEntityArrynSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.westerosHorseArmor));
		ARRYN_L.add(new GOTUnitTradeEntry(GOTEntityArrynBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.westerosHorseArmor));

		BRAAVOS_L.add(new GOTUnitTradeEntry(GOTEntityBraavosLevyman.class, LEVYMAN, LEVYMAN_F));
		BRAAVOS_L.add(new GOTUnitTradeEntry(GOTEntityBraavosLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		BRAAVOS_L.add(new GOTUnitTradeEntry(GOTEntityBraavosSoldier.class, SOLDIER, SOLDIER_F));
		BRAAVOS_L.add(new GOTUnitTradeEntry(GOTEntityBraavosSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		BRAAVOS_L.add(new GOTUnitTradeEntry(GOTEntityBraavosBannerBearer.class, SOLDIER, SOLDIER_F));
		BRAAVOS_L.add(new GOTUnitTradeEntry(GOTEntityBraavosSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));
		BRAAVOS_L.add(new GOTUnitTradeEntry(GOTEntityBraavosBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));

		DORNE_L.add(new GOTUnitTradeEntry(GOTEntityDorneLevyman.class, LEVYMAN, LEVYMAN_F));
		DORNE_L.add(new GOTUnitTradeEntry(GOTEntityDorneLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		DORNE_L.add(new GOTUnitTradeEntry(GOTEntityDorneSoldier.class, SOLDIER, SOLDIER_F));
		DORNE_L.add(new GOTUnitTradeEntry(GOTEntityDorneSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		DORNE_L.add(new GOTUnitTradeEntry(GOTEntityDorneBannerBearer.class, SOLDIER, SOLDIER_F));
		DORNE_L.add(new GOTUnitTradeEntry(GOTEntityDorneSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.westerosHorseArmor));
		DORNE_L.add(new GOTUnitTradeEntry(GOTEntityDorneBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.westerosHorseArmor));

		DRAGONSTONE_L.add(new GOTUnitTradeEntry(GOTEntityDragonstoneLevyman.class, LEVYMAN, LEVYMAN_F));
		DRAGONSTONE_L.add(new GOTUnitTradeEntry(GOTEntityDragonstoneLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		DRAGONSTONE_L.add(new GOTUnitTradeEntry(GOTEntityDragonstoneSoldier.class, SOLDIER, SOLDIER_F));
		DRAGONSTONE_L.add(new GOTUnitTradeEntry(GOTEntityDragonstoneSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		DRAGONSTONE_L.add(new GOTUnitTradeEntry(GOTEntityDragonstoneBannerBearer.class, SOLDIER, SOLDIER_F));
		DRAGONSTONE_L.add(new GOTUnitTradeEntry(GOTEntityDragonstoneSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.westerosHorseArmor));
		DRAGONSTONE_L.add(new GOTUnitTradeEntry(GOTEntityDragonstoneBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.westerosHorseArmor));

		GHISCAR_L.add(new GOTUnitTradeEntry(GOTEntityGhiscarLevyman.class, LEVYMAN, LEVYMAN_F));
		GHISCAR_L.add(new GOTUnitTradeEntry(GOTEntityGhiscarLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		GHISCAR_L.add(new GOTUnitTradeEntry(GOTEntityGhiscarSoldier.class, SOLDIER, SOLDIER_F));
		GHISCAR_L.add(new GOTUnitTradeEntry(GOTEntityGhiscarSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		GHISCAR_L.add(new GOTUnitTradeEntry(GOTEntityGhiscarBannerBearer.class, SOLDIER, SOLDIER_F));
		GHISCAR_L.add(new GOTUnitTradeEntry(GOTEntityGhiscarSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));
		GHISCAR_L.add(new GOTUnitTradeEntry(GOTEntityGhiscarBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));

		IRONBORN_L.add(new GOTUnitTradeEntry(GOTEntityIronbornLevyman.class, LEVYMAN, LEVYMAN_F));
		IRONBORN_L.add(new GOTUnitTradeEntry(GOTEntityIronbornLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		IRONBORN_L.add(new GOTUnitTradeEntry(GOTEntityIronbornSoldier.class, SOLDIER, SOLDIER_F));
		IRONBORN_L.add(new GOTUnitTradeEntry(GOTEntityIronbornSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		IRONBORN_L.add(new GOTUnitTradeEntry(GOTEntityIronbornBannerBearer.class, SOLDIER, SOLDIER_F));
		IRONBORN_L.add(new GOTUnitTradeEntry(GOTEntityIronbornSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));
		IRONBORN_L.add(new GOTUnitTradeEntry(GOTEntityIronbornBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));

		LORATH_L.add(new GOTUnitTradeEntry(GOTEntityLorathLevyman.class, LEVYMAN, LEVYMAN_F));
		LORATH_L.add(new GOTUnitTradeEntry(GOTEntityLorathLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		LORATH_L.add(new GOTUnitTradeEntry(GOTEntityLorathSoldier.class, SOLDIER, SOLDIER_F));
		LORATH_L.add(new GOTUnitTradeEntry(GOTEntityLorathSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		LORATH_L.add(new GOTUnitTradeEntry(GOTEntityLorathBannerBearer.class, SOLDIER, SOLDIER_F));
		LORATH_L.add(new GOTUnitTradeEntry(GOTEntityLorathSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));
		LORATH_L.add(new GOTUnitTradeEntry(GOTEntityLorathBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));

		LYS_L.add(new GOTUnitTradeEntry(GOTEntityLysLevyman.class, LEVYMAN, LEVYMAN_F));
		LYS_L.add(new GOTUnitTradeEntry(GOTEntityLysLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		LYS_L.add(new GOTUnitTradeEntry(GOTEntityLysSoldier.class, SOLDIER, SOLDIER_F));
		LYS_L.add(new GOTUnitTradeEntry(GOTEntityLysSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		LYS_L.add(new GOTUnitTradeEntry(GOTEntityLysBannerBearer.class, SOLDIER, SOLDIER_F));
		LYS_L.add(new GOTUnitTradeEntry(GOTEntityLysSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));
		LYS_L.add(new GOTUnitTradeEntry(GOTEntityLysBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));

		MYR_L.add(new GOTUnitTradeEntry(GOTEntityMyrLevyman.class, LEVYMAN, LEVYMAN_F));
		MYR_L.add(new GOTUnitTradeEntry(GOTEntityMyrLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		MYR_L.add(new GOTUnitTradeEntry(GOTEntityMyrSoldier.class, SOLDIER, SOLDIER_F));
		MYR_L.add(new GOTUnitTradeEntry(GOTEntityMyrSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		MYR_L.add(new GOTUnitTradeEntry(GOTEntityMyrBannerBearer.class, SOLDIER, SOLDIER_F));
		MYR_L.add(new GOTUnitTradeEntry(GOTEntityMyrSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));
		MYR_L.add(new GOTUnitTradeEntry(GOTEntityMyrBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));

		NORTH_L.add(new GOTUnitTradeEntry(GOTEntityNorthLevyman.class, LEVYMAN, LEVYMAN_F));
		NORTH_L.add(new GOTUnitTradeEntry(GOTEntityNorthLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		NORTH_L.add(new GOTUnitTradeEntry(GOTEntityNorthSoldier.class, SOLDIER, SOLDIER_F));
		NORTH_L.add(new GOTUnitTradeEntry(GOTEntityNorthSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		NORTH_L.add(new GOTUnitTradeEntry(GOTEntityNorthBannerBearer.class, SOLDIER, SOLDIER_F));
		NORTH_L.add(new GOTUnitTradeEntry(GOTEntityNorthSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.westerosHorseArmor));
		NORTH_L.add(new GOTUnitTradeEntry(GOTEntityNorthBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.westerosHorseArmor));

		NORVOS_L.add(new GOTUnitTradeEntry(GOTEntityNorvosLevyman.class, LEVYMAN, LEVYMAN_F));
		NORVOS_L.add(new GOTUnitTradeEntry(GOTEntityNorvosLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		NORVOS_L.add(new GOTUnitTradeEntry(GOTEntityNorvosSoldier.class, SOLDIER, SOLDIER_F));
		NORVOS_L.add(new GOTUnitTradeEntry(GOTEntityNorvosSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		NORVOS_L.add(new GOTUnitTradeEntry(GOTEntityNorvosBannerBearer.class, SOLDIER, SOLDIER_F));
		NORVOS_L.add(new GOTUnitTradeEntry(GOTEntityNorvosSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));
		NORVOS_L.add(new GOTUnitTradeEntry(GOTEntityNorvosBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));

		PENTOS_L.add(new GOTUnitTradeEntry(GOTEntityPentosLevyman.class, LEVYMAN, LEVYMAN_F));
		PENTOS_L.add(new GOTUnitTradeEntry(GOTEntityPentosLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		PENTOS_L.add(new GOTUnitTradeEntry(GOTEntityPentosSoldier.class, SOLDIER, SOLDIER_F));
		PENTOS_L.add(new GOTUnitTradeEntry(GOTEntityPentosSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		PENTOS_L.add(new GOTUnitTradeEntry(GOTEntityPentosBannerBearer.class, SOLDIER, SOLDIER_F));
		PENTOS_L.add(new GOTUnitTradeEntry(GOTEntityPentosSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));
		PENTOS_L.add(new GOTUnitTradeEntry(GOTEntityPentosBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));

		QARTH_L.add(new GOTUnitTradeEntry(GOTEntityQarthLevyman.class, LEVYMAN, LEVYMAN_F));
		QARTH_L.add(new GOTUnitTradeEntry(GOTEntityQarthLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		QARTH_L.add(new GOTUnitTradeEntry(GOTEntityQarthSoldier.class, SOLDIER, SOLDIER_F));
		QARTH_L.add(new GOTUnitTradeEntry(GOTEntityQarthSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		QARTH_L.add(new GOTUnitTradeEntry(GOTEntityQarthBannerBearer.class, SOLDIER, SOLDIER_F));
		QARTH_L.add(new GOTUnitTradeEntry(GOTEntityQarthSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));
		QARTH_L.add(new GOTUnitTradeEntry(GOTEntityQarthBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));

		QOHOR_L.add(new GOTUnitTradeEntry(GOTEntityQohorLevyman.class, LEVYMAN, LEVYMAN_F));
		QOHOR_L.add(new GOTUnitTradeEntry(GOTEntityQohorLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		QOHOR_L.add(new GOTUnitTradeEntry(GOTEntityQohorSoldier.class, SOLDIER, SOLDIER_F));
		QOHOR_L.add(new GOTUnitTradeEntry(GOTEntityQohorSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		QOHOR_L.add(new GOTUnitTradeEntry(GOTEntityQohorBannerBearer.class, SOLDIER, SOLDIER_F));
		QOHOR_L.add(new GOTUnitTradeEntry(GOTEntityQohorUnsullied.class, SOLDIER * 3, SOLDIER_F * 3));
		QOHOR_L.add(new GOTUnitTradeEntry(GOTEntityQohorSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));
		QOHOR_L.add(new GOTUnitTradeEntry(GOTEntityQohorBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));

		REACH_L.add(new GOTUnitTradeEntry(GOTEntityReachLevyman.class, LEVYMAN, LEVYMAN_F));
		REACH_L.add(new GOTUnitTradeEntry(GOTEntityReachLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		REACH_L.add(new GOTUnitTradeEntry(GOTEntityReachSoldier.class, SOLDIER, SOLDIER_F));
		REACH_L.add(new GOTUnitTradeEntry(GOTEntityReachSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		REACH_L.add(new GOTUnitTradeEntry(GOTEntityReachBannerBearer.class, SOLDIER, SOLDIER_F));
		REACH_L.add(new GOTUnitTradeEntry(GOTEntityReachSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.westerosHorseArmor));
		REACH_L.add(new GOTUnitTradeEntry(GOTEntityReachBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.westerosHorseArmor));

		RIVERLANDS_L.add(new GOTUnitTradeEntry(GOTEntityRiverlandsLevyman.class, LEVYMAN, LEVYMAN_F));
		RIVERLANDS_L.add(new GOTUnitTradeEntry(GOTEntityRiverlandsLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		RIVERLANDS_L.add(new GOTUnitTradeEntry(GOTEntityRiverlandsSoldier.class, SOLDIER, SOLDIER_F));
		RIVERLANDS_L.add(new GOTUnitTradeEntry(GOTEntityRiverlandsSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		RIVERLANDS_L.add(new GOTUnitTradeEntry(GOTEntityRiverlandsBannerBearer.class, SOLDIER, SOLDIER_F));
		RIVERLANDS_L.add(new GOTUnitTradeEntry(GOTEntityRiverlandsSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.westerosHorseArmor));
		RIVERLANDS_L.add(new GOTUnitTradeEntry(GOTEntityRiverlandsBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.westerosHorseArmor));

		STORMLANDS_L.add(new GOTUnitTradeEntry(GOTEntityStormlandsLevyman.class, LEVYMAN, LEVYMAN_F));
		STORMLANDS_L.add(new GOTUnitTradeEntry(GOTEntityStormlandsLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		STORMLANDS_L.add(new GOTUnitTradeEntry(GOTEntityStormlandsSoldier.class, SOLDIER, SOLDIER_F));
		STORMLANDS_L.add(new GOTUnitTradeEntry(GOTEntityStormlandsSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		STORMLANDS_L.add(new GOTUnitTradeEntry(GOTEntityStormlandsBannerBearer.class, SOLDIER, SOLDIER_F));
		STORMLANDS_L.add(new GOTUnitTradeEntry(GOTEntityStormlandsSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.westerosHorseArmor));
		STORMLANDS_L.add(new GOTUnitTradeEntry(GOTEntityStormlandsBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.westerosHorseArmor));

		TYROSH_L.add(new GOTUnitTradeEntry(GOTEntityTyroshLevyman.class, LEVYMAN, LEVYMAN_F));
		TYROSH_L.add(new GOTUnitTradeEntry(GOTEntityTyroshLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		TYROSH_L.add(new GOTUnitTradeEntry(GOTEntityTyroshSoldier.class, SOLDIER, SOLDIER_F));
		TYROSH_L.add(new GOTUnitTradeEntry(GOTEntityTyroshSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		TYROSH_L.add(new GOTUnitTradeEntry(GOTEntityTyroshBannerBearer.class, SOLDIER, SOLDIER_F));
		TYROSH_L.add(new GOTUnitTradeEntry(GOTEntityTyroshSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));
		TYROSH_L.add(new GOTUnitTradeEntry(GOTEntityTyroshBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));

		VOLANTIS_L.add(new GOTUnitTradeEntry(GOTEntityVolantisLevyman.class, LEVYMAN, LEVYMAN_F));
		VOLANTIS_L.add(new GOTUnitTradeEntry(GOTEntityVolantisLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		VOLANTIS_L.add(new GOTUnitTradeEntry(GOTEntityVolantisSoldier.class, SOLDIER, SOLDIER_F));
		VOLANTIS_L.add(new GOTUnitTradeEntry(GOTEntityVolantisSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		VOLANTIS_L.add(new GOTUnitTradeEntry(GOTEntityVolantisBannerBearer.class, SOLDIER, SOLDIER_F));
		VOLANTIS_L.add(new GOTUnitTradeEntry(GOTEntityVolantisSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));
		VOLANTIS_L.add(new GOTUnitTradeEntry(GOTEntityVolantisBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));

		WESTERLANDS_L.add(new GOTUnitTradeEntry(GOTEntityWesterlandsLevyman.class, LEVYMAN, LEVYMAN_F));
		WESTERLANDS_L.add(new GOTUnitTradeEntry(GOTEntityWesterlandsLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		WESTERLANDS_L.add(new GOTUnitTradeEntry(GOTEntityWesterlandsSoldier.class, SOLDIER, SOLDIER_F));
		WESTERLANDS_L.add(new GOTUnitTradeEntry(GOTEntityWesterlandsSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		WESTERLANDS_L.add(new GOTUnitTradeEntry(GOTEntityWesterlandsBannerBearer.class, SOLDIER, SOLDIER_F));
		WESTERLANDS_L.add(new GOTUnitTradeEntry(GOTEntityWesterlandsSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.westerosHorseArmor));
		WESTERLANDS_L.add(new GOTUnitTradeEntry(GOTEntityWesterlandsBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.westerosHorseArmor));

		LHAZAR_L.add(new GOTUnitTradeEntry(GOTEntityLhazarLevyman.class, LEVYMAN, LEVYMAN_F));
		LHAZAR_L.add(new GOTUnitTradeEntry(GOTEntityLhazarLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		LHAZAR_L.add(new GOTUnitTradeEntry(GOTEntityLhazarSoldier.class, SOLDIER, SOLDIER_F));
		LHAZAR_L.add(new GOTUnitTradeEntry(GOTEntityLhazarSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		LHAZAR_L.add(new GOTUnitTradeEntry(GOTEntityLhazarBannerBearer.class, SOLDIER, SOLDIER_F));
		LHAZAR_L.add(new GOTUnitTradeEntry(GOTEntityLhazarSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));
		LHAZAR_L.add(new GOTUnitTradeEntry(GOTEntityLhazarBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.essosHorseArmor));

		SUMMER_L.add(new GOTUnitTradeEntry(GOTEntitySummerLevyman.class, LEVYMAN, LEVYMAN_F));
		SUMMER_L.add(new GOTUnitTradeEntry(GOTEntitySummerLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		SUMMER_L.add(new GOTUnitTradeEntry(GOTEntitySummerSoldier.class, SOLDIER, SOLDIER_F));
		SUMMER_L.add(new GOTUnitTradeEntry(GOTEntitySummerSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		SUMMER_L.add(new GOTUnitTradeEntry(GOTEntitySummerBannerBearer.class, SOLDIER, SOLDIER_F));
		SUMMER_L.add(new GOTUnitTradeEntry(GOTEntitySummerSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F));
		SUMMER_L.add(new GOTUnitTradeEntry(GOTEntitySummerBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F));

		GHISCAR_UNSULLIED_L.add(new GOTUnitTradeEntry(GOTEntityGhiscarUnsullied.class, SOLDIER * 3, SOLDIER_F * 3));

		GIFT_L.add(new GOTUnitTradeEntry(GOTEntityGiftGuard.class, SOLDIER, SOLDIER_F).setPledgeExclusive());
		GIFT_L.add(new GOTUnitTradeEntry(GOTEntityGiftBannerBearer.class, SOLDIER, SOLDIER_F).setPledgeExclusive());

		GOLDENCOMPANY_L.add(new GOTUnitTradeEntry(GOTEntityGoldenCompanyWarrior.class, SOLDIER, 0.0f));
		GOLDENCOMPANY_L.add(new GOTUnitTradeEntry(GOTEntityGoldenCompanySpearman.class, SOLDIER, 0.0f));
		GOLDENCOMPANY_L.add(new GOTUnitTradeEntry(GOTEntityGoldenCompanyBannerBearer.class, SOLDIER, 0.0f));

		CROWNLANDS_L.add(new GOTUnitTradeEntry(GOTEntityCrownlandsLevyman.class, LEVYMAN, LEVYMAN_F));
		CROWNLANDS_L.add(new GOTUnitTradeEntry(GOTEntityCrownlandsLevymanArcher.class, LEVYMANA, LEVYMANA_F));

		KINGSGUARD_L.add(new GOTUnitTradeEntry(GOTEntityKingsguard.class, SOLDIER * 2, SOLDIER_F * 2).setPledgeExclusive());

		ASSHAI_L.add(new GOTUnitTradeEntry(GOTEntityAsshaiWarrior.class, SOLDIER, SOLDIER_F));
		ASSHAI_L.add(new GOTUnitTradeEntry(GOTEntityAsshaiSpherebinder.class, SOLDIER * 5, SOLDIER_F * 5).setPledgeExclusive());
		ASSHAI_L.add(new GOTUnitTradeEntry(GOTEntityAsshaiShadowbinder.class, SOLDIER * 5, SOLDIER_F * 5).setPledgeExclusive());
		ASSHAI_L.add(new GOTUnitTradeEntry(GOTEntityAsshaiBannerBearer.class, SOLDIER, SOLDIER_F));

		WILDLING_L.add(new GOTUnitTradeEntry(GOTEntityWildlingWarrior.class, SOLDIER, SOLDIER_F));
		WILDLING_L.add(new GOTUnitTradeEntry(GOTEntityWildlingArcher.class, SOLDIERA, SOLDIERA_F));
		WILDLING_L.add(new GOTUnitTradeEntry(GOTEntityWildlingAxeThrower.class, SOLDIERA, SOLDIERA_F));
		WILDLING_L.add(new GOTUnitTradeEntry(GOTEntityWildlingBannerBearer.class, SOLDIER, SOLDIER_F));
		WILDLING_L.add(new GOTUnitTradeEntry(GOTEntityGiant.class, SOLDIER * 10, SOLDIER_F * 10).setPledgeExclusive());

		THENN_L.add(new GOTUnitTradeEntry(GOTEntityThennWarrior.class, SOLDIER, SOLDIER_F));
		THENN_L.add(new GOTUnitTradeEntry(GOTEntityThennArcher.class, SOLDIERA, SOLDIERA_F));
		THENN_L.add(new GOTUnitTradeEntry(GOTEntityThennAxeThrower.class, SOLDIERA, SOLDIERA_F));
		THENN_L.add(new GOTUnitTradeEntry(GOTEntityThennBannerBearer.class, SOLDIER, SOLDIER_F));
		THENN_L.add(new GOTUnitTradeEntry(GOTEntityGiant.class, SOLDIER * 10, SOLDIER_F * 10).setPledgeExclusive());

		HILLMEN_L.add(new GOTUnitTradeEntry(GOTEntityHillmanWarrior.class, SOLDIER, SOLDIER_F));
		HILLMEN_L.add(new GOTUnitTradeEntry(GOTEntityHillmanArcher.class, SOLDIERA, SOLDIERA_F));
		HILLMEN_L.add(new GOTUnitTradeEntry(GOTEntityHillmanAxeThrower.class, SOLDIERA, SOLDIERA_F));
		HILLMEN_L.add(new GOTUnitTradeEntry(GOTEntityHillmanBannerBearer.class, SOLDIER, SOLDIER_F));
		HILLMEN_L.add(new GOTUnitTradeEntry(GOTEntityHillmanBerserker.class, SOLDIER * 2, SOLDIER_F * 2).setPledgeExclusive());

		NORTH_HILLMEN_L.add(new GOTUnitTradeEntry(GOTEntityNorthHillmanWarrior.class, SOLDIER, SOLDIER_F));
		NORTH_HILLMEN_L.add(new GOTUnitTradeEntry(GOTEntityNorthHillmanArcher.class, SOLDIERA, SOLDIERA_F));
		NORTH_HILLMEN_L.add(new GOTUnitTradeEntry(GOTEntityNorthHillmanAxeThrower.class, SOLDIERA, SOLDIERA_F));
		NORTH_HILLMEN_L.add(new GOTUnitTradeEntry(GOTEntityNorthHillmanBannerBearer.class, SOLDIER, SOLDIER_F));
		NORTH_HILLMEN_L.add(new GOTUnitTradeEntry(GOTEntityNorthHillmanWarrior.class, GOTEntityWoolyRhino.class, "Rider", SOLDIERH * 2, SOLDIERH_F * 2).setPledgeExclusive());

		JOGOS_L.add(new GOTUnitTradeEntry(GOTEntityJogos.class, SOLDIER, SOLDIER_F));
		JOGOS_L.add(new GOTUnitTradeEntry(GOTEntityJogosArcher.class, SOLDIERA, SOLDIERA_F));
		JOGOS_L.add(new GOTUnitTradeEntry(GOTEntityJogos.class, GOTEntityZebra.class, "Rider", SOLDIERH, SOLDIERH_F));
		JOGOS_L.add(new GOTUnitTradeEntry(GOTEntityJogosArcher.class, GOTEntityZebra.class, "Rider", SOLDIERHA, SOLDIERHA_F));

		DOTHRAKI_L.add(new GOTUnitTradeEntry(GOTEntityDothraki.class, LEVYMAN, LEVYMAN_F));
		DOTHRAKI_L.add(new GOTUnitTradeEntry(GOTEntityDothrakiArcher.class, LEVYMANA, LEVYMANA_F));
		DOTHRAKI_L.add(new GOTUnitTradeEntry(GOTEntityDothraki.class, GOTEntityHorse.class, "Rider", SOLDIER, SOLDIER_F));
		DOTHRAKI_L.add(new GOTUnitTradeEntry(GOTEntityDothrakiArcher.class, GOTEntityHorse.class, "Rider", SOLDIERA, SOLDIERA_F));

		IBBEN_L.add(new GOTUnitTradeEntry(GOTEntityIbbenLevyman.class, LEVYMAN, LEVYMAN_F));
		IBBEN_L.add(new GOTUnitTradeEntry(GOTEntityIbbenLevymanArcher.class, LEVYMANA, LEVYMANA_F));
		IBBEN_L.add(new GOTUnitTradeEntry(GOTEntityIbbenSoldier.class, SOLDIER, SOLDIER_F));
		IBBEN_L.add(new GOTUnitTradeEntry(GOTEntityIbbenSoldierArcher.class, SOLDIERA, SOLDIERA_F));
		IBBEN_L.add(new GOTUnitTradeEntry(GOTEntityIbbenBannerBearer.class, SOLDIER, SOLDIER_F));

		SOTHORYOS_L.add(new GOTUnitTradeEntry(GOTEntitySothoryosWarrior.class, SOLDIER, SOLDIER_F));
		SOTHORYOS_L.add(new GOTUnitTradeEntry(GOTEntitySothoryosBlowgunner.class, SOLDIERA, SOLDIERA_F));
		SOTHORYOS_L.add(new GOTUnitTradeEntry(GOTEntitySothoryosBannerBearer.class, SOLDIER, SOLDIER_F));

		YITI_L.add(new GOTUnitTradeEntry(GOTEntityYiTiLevyman.class, LEVYMAN, LEVYMAN_F));
		YITI_L.add(new GOTUnitTradeEntry(GOTEntityYiTiLevymanCrossbower.class, LEVYMANA, LEVYMANA_F));
		YITI_L.add(new GOTUnitTradeEntry(GOTEntityYiTiSoldier.class, SOLDIER, SOLDIER_F));
		YITI_L.add(new GOTUnitTradeEntry(GOTEntityYiTiSoldierCrossbower.class, SOLDIERA, SOLDIERA_F));
		YITI_L.add(new GOTUnitTradeEntry(GOTEntityYiTiFrontier.class, SOLDIER, SOLDIER_F).setPledgeExclusive());
		YITI_L.add(new GOTUnitTradeEntry(GOTEntityYiTiSamurai.class, SOLDIER * 2, SOLDIER_F * 2).setPledgeExclusive());
		YITI_L.add(new GOTUnitTradeEntry(GOTEntityYiTiFireThrower.class, SOLDIER * 3, SOLDIER_F * 3).setPledgeExclusive());
		YITI_L.add(new GOTUnitTradeEntry(GOTEntityYiTiBombardier.class, SOLDIER * 5, SOLDIER_F * 5).setPledgeExclusive());
		YITI_L.add(new GOTUnitTradeEntry(GOTEntityYiTiSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F));
		YITI_L.add(new GOTUnitTradeEntry(GOTEntityYiTiFrontier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setPledgeExclusive());
		YITI_L.add(new GOTUnitTradeEntry(GOTEntityYiTiSamurai.class, GOTEntityHorse.class, "Rider", SOLDIERH * 2, SOLDIERH_F * 2).setMountArmor(GOTItems.yitiHorseArmor).setPledgeExclusive());
		YITI_L.add(new GOTUnitTradeEntry(GOTEntityYiTiSoldierBannerBearer.class, SOLDIER, SOLDIER_F));
		YITI_L.add(new GOTUnitTradeEntry(GOTEntityYiTiSamuraiBannerBearer.class, SOLDIER * 2, SOLDIER_F * 2));
		YITI_L.add(new GOTUnitTradeEntry(GOTEntityYiTiSoldierBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTItems.yitiHorseArmor));
		YITI_L.add(new GOTUnitTradeEntry(GOTEntityYiTiSamuraiBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH * 2, SOLDIERH_F * 2).setMountArmor(GOTItems.yitiHorseArmor));

		ARRYN_FARMER_L.add(new GOTUnitTradeEntry(GOTEntityArrynFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		CROWNLANDS_FARMER_L.add(new GOTUnitTradeEntry(GOTEntityCrownlandsFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		DORNE_FARMER_L.add(new GOTUnitTradeEntry(GOTEntityDorneFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		DRAGONSTONE_FARMER_L.add(new GOTUnitTradeEntry(GOTEntityDragonstoneFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		IBBEN_FARMER_L.add(new GOTUnitTradeEntry(GOTEntityIbbenFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		IRONBORN_FARMER_L.add(new GOTUnitTradeEntry(GOTEntityIronbornFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		NORTH_FARMER_L.add(new GOTUnitTradeEntry(GOTEntityNorthFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		REACH_FARMER_L.add(new GOTUnitTradeEntry(GOTEntityReachFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		RIVERLANDS_FARMER_L.add(new GOTUnitTradeEntry(GOTEntityRiverlandsFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		SOTHORYOS_FARMER_L.add(new GOTUnitTradeEntry(GOTEntitySothoryosFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		STORMLANDS_FARMER_L.add(new GOTUnitTradeEntry(GOTEntityStormlandsFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		SUMMER_FARMER_L.add(new GOTUnitTradeEntry(GOTEntitySummerFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		WESTERLANDS_FARMER_L.add(new GOTUnitTradeEntry(GOTEntityWesterlandsFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		LORATH_FARMER_L.add(new GOTUnitTradeEntry(GOTEntityLorathFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		BRAAVOS_FARMER_L.add(new GOTUnitTradeEntry(GOTEntityBraavosFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		NORVOS_FARMER_L.add(new GOTUnitTradeEntry(GOTEntityNorvosFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		PENTOS_FARMER_L.add(new GOTUnitTradeEntry(GOTEntityPentosFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		QOHOR_FARMER_L.add(new GOTUnitTradeEntry(GOTEntityQohorFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		QARTH_FARMER_L.add(new GOTUnitTradeEntry(GOTEntityQarthFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		YITI_FARMER_L.add(new GOTUnitTradeEntry(GOTEntityYiTiFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		LHAZAR_FARMER_L.add(new GOTUnitTradeEntry(GOTEntityLhazarFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		GHISCAR_SLAVER_L.add(new GOTUnitTradeEntry(GOTEntityGhiscarSlave.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		LYS_SLAVER_L.add(new GOTUnitTradeEntry(GOTEntityLysSlave.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		MYR_SLAVER_L.add(new GOTUnitTradeEntry(GOTEntityMyrSlave.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		TYROSH_SLAVER_L.add(new GOTUnitTradeEntry(GOTEntityTyroshSlave.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));
		VOLANTIS_SLAVER_L.add(new GOTUnitTradeEntry(GOTEntityVolantisSlave.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.FARMER));

		PROSTITUTE_KEEPER_L.add(new GOTUnitTradeEntry(GOTEntityProstitute.class, SLAVE, SLAVE_F).setTask(GOTHireableInfo.Task.PROSTITUTE));

		ARRYN = new GOTUnitTradeEntries(50.0f, ARRYN_L);
		BRAAVOS = new GOTUnitTradeEntries(50.0f, BRAAVOS_L);
		DORNE = new GOTUnitTradeEntries(50.0f, DORNE_L);
		DRAGONSTONE = new GOTUnitTradeEntries(50.0f, DRAGONSTONE_L);
		GHISCAR = new GOTUnitTradeEntries(50.0f, GHISCAR_L);
		IRONBORN = new GOTUnitTradeEntries(50.0f, IRONBORN_L);
		LORATH = new GOTUnitTradeEntries(50.0f, LORATH_L);
		LYS = new GOTUnitTradeEntries(50.0f, LYS_L);
		MYR = new GOTUnitTradeEntries(50.0f, MYR_L);
		NORTH = new GOTUnitTradeEntries(50.0f, NORTH_L);
		NORVOS = new GOTUnitTradeEntries(50.0f, NORVOS_L);
		PENTOS = new GOTUnitTradeEntries(50.0f, PENTOS_L);
		QARTH = new GOTUnitTradeEntries(50.0f, QARTH_L);
		QOHOR = new GOTUnitTradeEntries(50.0f, QOHOR_L);
		REACH = new GOTUnitTradeEntries(50.0f, REACH_L);
		RIVERLANDS = new GOTUnitTradeEntries(50.0f, RIVERLANDS_L);
		STORMLANDS = new GOTUnitTradeEntries(50.0f, STORMLANDS_L);
		TYROSH = new GOTUnitTradeEntries(50.0f, TYROSH_L);
		VOLANTIS = new GOTUnitTradeEntries(50.0f, VOLANTIS_L);
		WESTERLANDS = new GOTUnitTradeEntries(50.0f, WESTERLANDS_L);
		LHAZAR = new GOTUnitTradeEntries(50.0f, LHAZAR_L);
		YITI = new GOTUnitTradeEntries(50.0f, YITI_L);
		KINGSGUARD = new GOTUnitTradeEntries(50.0f, KINGSGUARD_L);
		ASSHAI = new GOTUnitTradeEntries(50.0f, ASSHAI_L);
		CROWNLANDS = new GOTUnitTradeEntries(50.0f, CROWNLANDS_L);
		GHISCAR_UNSULLIED = new GOTUnitTradeEntries(50.0f, GHISCAR_UNSULLIED_L);
		WILDLING = new GOTUnitTradeEntries(50.0f, WILDLING_L);
		SUMMER = new GOTUnitTradeEntries(50.0f, SUMMER_L);
		THENN = new GOTUnitTradeEntries(50.0f, THENN_L);
		SOTHORYOS = new GOTUnitTradeEntries(50.0f, SOTHORYOS_L);
		NORTH_HILLMEN = new GOTUnitTradeEntries(50.0f, NORTH_HILLMEN_L);
		GIFT = new GOTUnitTradeEntries(50.0f, GIFT_L);
		IBBEN = new GOTUnitTradeEntries(50.0f, IBBEN_L);
		JOGOS = new GOTUnitTradeEntries(50.0f, JOGOS_L);
		HILLMEN = new GOTUnitTradeEntries(50.0f, HILLMEN_L);
		DOTHRAKI = new GOTUnitTradeEntries(50.0f, DOTHRAKI_L);

		GOLDENCOMPANY = new GOTUnitTradeEntries(0.0f, GOLDENCOMPANY_L);

		ARRYN_FARMER = new GOTUnitTradeEntries(0.0f, ARRYN_FARMER_L);
		CROWNLANDS_FARMER = new GOTUnitTradeEntries(0.0f, CROWNLANDS_FARMER_L);
		DORNE_FARMER = new GOTUnitTradeEntries(0.0f, DORNE_FARMER_L);
		DRAGONSTONE_FARMER = new GOTUnitTradeEntries(0.0f, DRAGONSTONE_FARMER_L);
		IBBEN_FARMER = new GOTUnitTradeEntries(0.0f, IBBEN_FARMER_L);
		IRONBORN_FARMER = new GOTUnitTradeEntries(0.0f, IRONBORN_FARMER_L);
		NORTH_FARMER = new GOTUnitTradeEntries(0.0f, NORTH_FARMER_L);
		REACH_FARMER = new GOTUnitTradeEntries(0.0f, REACH_FARMER_L);
		RIVERLANDS_FARMER = new GOTUnitTradeEntries(0.0f, RIVERLANDS_FARMER_L);
		SOTHORYOS_FARMER = new GOTUnitTradeEntries(0.0f, SOTHORYOS_FARMER_L);
		STORMLANDS_FARMER = new GOTUnitTradeEntries(0.0f, STORMLANDS_FARMER_L);
		SUMMER_FARMER = new GOTUnitTradeEntries(0.0f, SUMMER_FARMER_L);
		WESTERLANDS_FARMER = new GOTUnitTradeEntries(0.0f, WESTERLANDS_FARMER_L);
		LORATH_FARMER = new GOTUnitTradeEntries(0.0f, LORATH_FARMER_L);
		BRAAVOS_FARMER = new GOTUnitTradeEntries(0.0f, BRAAVOS_FARMER_L);
		NORVOS_FARMER = new GOTUnitTradeEntries(0.0f, NORVOS_FARMER_L);
		PENTOS_FARMER = new GOTUnitTradeEntries(0.0f, PENTOS_FARMER_L);
		QOHOR_FARMER = new GOTUnitTradeEntries(0.0f, QOHOR_FARMER_L);
		QARTH_FARMER = new GOTUnitTradeEntries(0.0f, QARTH_FARMER_L);
		YITI_FARMER = new GOTUnitTradeEntries(0.0f, YITI_FARMER_L);
		LHAZAR_FARMER = new GOTUnitTradeEntries(0.0f, LHAZAR_FARMER_L);
		GHISCAR_SLAVER = new GOTUnitTradeEntries(0.0f, GHISCAR_SLAVER_L);
		LYS_SLAVER = new GOTUnitTradeEntries(0.0f, LYS_SLAVER_L);
		MYR_SLAVER = new GOTUnitTradeEntries(0.0f, MYR_SLAVER_L);
		TYROSH_SLAVER = new GOTUnitTradeEntries(0.0f, TYROSH_SLAVER_L);
		VOLANTIS_SLAVER = new GOTUnitTradeEntries(0.0f, VOLANTIS_SLAVER_L);

		PROSTITUTE_KEEPER = new GOTUnitTradeEntries(0.0f, PROSTITUTE_KEEPER_L);
	}

	private final GOTUnitTradeEntry[] tradeEntries;

	@SuppressWarnings("WeakerAccess")
	public GOTUnitTradeEntries(float baseAlignment, List<GOTUnitTradeEntry> list) {
		GOTUnitTradeEntry[] arr = new GOTUnitTradeEntry[list.size()];
		arr = list.toArray(arr);
		for (GOTUnitTradeEntry trade : tradeEntries = arr) {
			trade.setAlignmentRequired(trade.getAlignmentRequired() + baseAlignment);
			if (trade.getAlignmentRequired() >= 0.0f) {
				continue;
			}
			throw new IllegalArgumentException("Units cannot require negative alignment!");
		}
		CONTENT.add(this);
	}

	public GOTUnitTradeEntry[] getTradeEntries() {
		return tradeEntries;
	}
}