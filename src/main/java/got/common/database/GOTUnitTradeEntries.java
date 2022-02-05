package got.common.database;

import got.common.entity.animal.*;
import got.common.entity.essos.asshai.*;
import got.common.entity.essos.braavos.*;
import got.common.entity.essos.dothraki.*;
import got.common.entity.essos.ghiscar.*;
import got.common.entity.essos.gold.*;
import got.common.entity.essos.ibben.*;
import got.common.entity.essos.jogos.*;
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
import got.common.entity.other.*;
import got.common.entity.sothoryos.sothoryos.*;
import got.common.entity.sothoryos.summer.*;
import got.common.entity.westeros.arryn.*;
import got.common.entity.westeros.crownlands.*;
import got.common.entity.westeros.dorne.*;
import got.common.entity.westeros.dragonstone.*;
import got.common.entity.westeros.gift.*;
import got.common.entity.westeros.hillmen.*;
import got.common.entity.westeros.ironborn.*;
import got.common.entity.westeros.north.*;
import got.common.entity.westeros.north.hillmen.*;
import got.common.entity.westeros.reach.*;
import got.common.entity.westeros.riverlands.*;
import got.common.entity.westeros.stormlands.*;
import got.common.entity.westeros.westerlands.*;
import got.common.entity.westeros.wildling.*;
import got.common.entity.westeros.wildling.thenn.*;

public class GOTUnitTradeEntries {
	public static int LEVYMAN = 5;
	public static float LEVYMAN_F;
	public static int LEVYMANA = 10;
	public static float LEVYMANA_F = 5.0f;
	public static int SOLDIER = 10;
	public static float SOLDIER_F = 5.0f;
	public static int SOLDIERA = 15;
	public static float SOLDIERA_F = 10.0f;
	public static int SOLDIERH = 15;
	public static float SOLDIERH_F = 10.0f;
	public static int SOLDIERHA = 20;
	public static float SOLDIERHA_F = 15.0f;
	public static int SLAVE = 10;
	public static float SLAVE_F;

	public static GOTUnitTradeEntries ARRYN = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityArrynLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityArrynLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityArrynSoldier.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityArrynSoldierArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityArrynSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.westerosHorseArmor), new GOTUnitTradeEntry(GOTEntityArrynBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityArrynBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.westerosHorseArmor), new GOTUnitTradeEntry(GOTEntityArrynGuard.class, SOLDIER, SOLDIER_F));
	public static GOTUnitTradeEntries BRAAVOS = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityBraavosLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityBraavosLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityBraavosSoldier.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityBraavosSoldierArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityBraavosSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.essosHorseArmor), new GOTUnitTradeEntry(GOTEntityBraavosBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityBraavosBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.essosHorseArmor));
	public static GOTUnitTradeEntries DORNE = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityDorneLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityDorneLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityDorneSoldier.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityDorneSoldierArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityDorneSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.westerosHorseArmor), new GOTUnitTradeEntry(GOTEntityDorneBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityDorneBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.westerosHorseArmor));
	public static GOTUnitTradeEntries DRAGONSTONE = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityDragonstoneLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityDragonstoneLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityDragonstoneSoldier.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityDragonstoneSoldierArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityDragonstoneSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.westerosHorseArmor), new GOTUnitTradeEntry(GOTEntityDragonstoneBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityDragonstoneBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.westerosHorseArmor));
	public static GOTUnitTradeEntries GHISCAR = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityGhiscarLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityGhiscarLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityGhiscarCorsair.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityGhiscarCorsairArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityGhiscarBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityGhiscarGuard.class, SOLDIER, SOLDIER_F));
	public static GOTUnitTradeEntries IRONBORN = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityIronbornLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityIronbornLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityIronbornSoldier.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityIronbornSoldierArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityIronbornBannerBearer.class, SOLDIER, SOLDIER_F));
	public static GOTUnitTradeEntries LORATH = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityLorathLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityLorathLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityLorathSoldier.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityLorathSoldierArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityLorathSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.essosHorseArmor), new GOTUnitTradeEntry(GOTEntityLorathBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityLorathBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.essosHorseArmor));
	public static GOTUnitTradeEntries LYS = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityLysLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityLysLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityLysSoldier.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityLysSoldierArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityLysSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.essosHorseArmor), new GOTUnitTradeEntry(GOTEntityLysBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityLysBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.essosHorseArmor));
	public static GOTUnitTradeEntries MYR = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityMyrLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityMyrLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityMyrSoldier.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityMyrSoldierArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityMyrSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.essosHorseArmor), new GOTUnitTradeEntry(GOTEntityMyrBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityMyrBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.essosHorseArmor));
	public static GOTUnitTradeEntries NORTH = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityNorthLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityNorthLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityNorthSoldier.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityNorthSoldierArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityNorthSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.westerosHorseArmor), new GOTUnitTradeEntry(GOTEntityNorthBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityNorthBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.westerosHorseArmor), new GOTUnitTradeEntry(GOTEntityNorthGuard.class, SOLDIER, SOLDIER_F));
	public static GOTUnitTradeEntries NORVOS = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityNorvosLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityNorvosLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityNorvosGuard.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityNorvosBannerBearer.class, SOLDIER, SOLDIER_F));
	public static GOTUnitTradeEntries PENTOS = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityPentosLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityPentosLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityPentosGuard.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityPentosBannerBearer.class, SOLDIER, SOLDIER_F));
	public static GOTUnitTradeEntries QARTH = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityQarthLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityQarthLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityQarthGuard.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityQarthBannerBearer.class, SOLDIER, SOLDIER_F));
	public static GOTUnitTradeEntries QOHOR = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityQohorLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityQohorLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityQohorUnsullied.class, SOLDIER + 30, SOLDIER_F + 30.0f), new GOTUnitTradeEntry(GOTEntityQohorGuard.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityQohorBannerBearer.class, SOLDIER, SOLDIER_F));
	public static GOTUnitTradeEntries REACH = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityReachLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityReachLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityReachSoldier.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityReachSoldierArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityReachSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.westerosHorseArmor), new GOTUnitTradeEntry(GOTEntityReachBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityReachBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.westerosHorseArmor), new GOTUnitTradeEntry(GOTEntityReachGuard.class, SOLDIER, SOLDIER_F));
	public static GOTUnitTradeEntries RIVERLANDS = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityRiverlandsLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityRiverlandsLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityRiverlandsSoldier.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityRiverlandsSoldierArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityRiverlandsSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.westerosHorseArmor), new GOTUnitTradeEntry(GOTEntityRiverlandsBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityRiverlandsBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.westerosHorseArmor));
	public static GOTUnitTradeEntries STORMLANDS = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityStormlandsLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityStormlandsLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityStormlandsSoldier.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityStormlandsSoldierArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityStormlandsSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.westerosHorseArmor), new GOTUnitTradeEntry(GOTEntityStormlandsBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityStormlandsBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.westerosHorseArmor));
	public static GOTUnitTradeEntries TYROSH = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityTyroshLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityTyroshLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityTyroshSoldier.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityTyroshSoldierArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityTyroshSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.essosHorseArmor), new GOTUnitTradeEntry(GOTEntityTyroshBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityTyroshBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.essosHorseArmor));
	public static GOTUnitTradeEntries VOLANTIS = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityVolantisLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityVolantisLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityVolantisSoldier.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityVolantisSoldierArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityVolantisSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.essosHorseArmor), new GOTUnitTradeEntry(GOTEntityVolantisBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityVolantisBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.essosHorseArmor));
	public static GOTUnitTradeEntries WESTERLANDS = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityWesterlandsLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityWesterlandsLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityWesterlandsSoldier.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityWesterlandsSoldierArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityWesterlandsSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.westerosHorseArmor), new GOTUnitTradeEntry(GOTEntityWesterlandsBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityWesterlandsBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.westerosHorseArmor), new GOTUnitTradeEntry(GOTEntityWesterlandsGuard.class, SOLDIER, SOLDIER_F));
	public static GOTUnitTradeEntries LHAZAR = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityLhazarWarrior.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityLhazarArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityLhazarBannerBearer.class, LEVYMAN, LEVYMAN_F));
	public static GOTUnitTradeEntries YITI = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityYiTiLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityYiTiLevymanCrossbower.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityYiTiSoldier.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityYiTiSoldierCrossbower.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityYiTiFrontier.class, SOLDIER + 5, SOLDIER_F + 5.0f).setPledgeExclusive(), new GOTUnitTradeEntry(GOTEntityYiTiFrontierCrossbower.class, SOLDIERA + 5, SOLDIERA_F + 5.0f).setPledgeExclusive(), new GOTUnitTradeEntry(GOTEntityYiTiSamurai.class, SOLDIER + 10, SOLDIER_F + 10.0f).setPledgeExclusive(), new GOTUnitTradeEntry(GOTEntityYiTiFireThrower.class, SOLDIER + 30, SOLDIER_F + 30.0f).setPledgeExclusive(), new GOTUnitTradeEntry(GOTEntityYiTiBombardier.class, SOLDIER + 50, SOLDIER_F + 50.0f).setPledgeExclusive(), new GOTUnitTradeEntry(GOTEntityYiTiSoldier.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F), new GOTUnitTradeEntry(GOTEntityYiTiFrontier.class, GOTEntityHorse.class, "Rider", SOLDIERH + 5, SOLDIERH_F + 5.0f).setPledgeExclusive(), new GOTUnitTradeEntry(GOTEntityYiTiSamurai.class, GOTEntityHorse.class, "Rider", SOLDIERH + 10, SOLDIERH_F + 10.0f).setMountArmor(GOTRegistry.yitiHorseArmor).setPledgeExclusive(), new GOTUnitTradeEntry(GOTEntityYiTiBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityYiTiBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.yitiHorseArmor));
	public static GOTUnitTradeEntries KINGSGUARD = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityKingsguard.class, SOLDIER + 5, SOLDIER_F + 5.0f).setPledgeExclusive());
	public static GOTUnitTradeEntries ASSHAI = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityAsshaiWarrior.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityAsshaiSpherebinder.class, SOLDIER + 50, SOLDIER_F + 50.0f).setPledgeExclusive(), new GOTUnitTradeEntry(GOTEntityAsshaiShadowbinder.class, SOLDIER + 100, SOLDIER_F + 100.0f).setPledgeExclusive(), new GOTUnitTradeEntry(GOTEntityAsshaiBannerBearer.class, SOLDIER, SOLDIER_F));
	public static GOTUnitTradeEntries CROWNLANDS = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityCrownlandsLevyman.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityCrownlandsLevymanArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityCrownlandsBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityCrownlandsBannerBearer.class, GOTEntityHorse.class, "Rider", SOLDIERH, SOLDIERH_F).setMountArmor(GOTRegistry.westerosHorseArmor), new GOTUnitTradeEntry(GOTEntityCrownlandsGuard.class, SOLDIER, SOLDIER_F));
	public static GOTUnitTradeEntries CROWNLANDS_GUARDIAN = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityCrownlandsGuard.class, SOLDIER, SOLDIER_F));
	public static GOTUnitTradeEntries GHISCAR_UNSULLIED = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityGhiscarUnsullied.class, SOLDIER + 30, SOLDIER_F + 30.0f));
	public static GOTUnitTradeEntries GHISCAR_CORSAIR = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityGhiscarCorsair.class, SOLDIER, SOLDIER_F));
	public static GOTUnitTradeEntries WILDLING = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityWildling.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityWildlingArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityWildlingAxeThrower.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityWildlingBannerBearer.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityGiant.class, SOLDIER + 100, SOLDIER_F + 100.0f).setPledgeExclusive());
	public static GOTUnitTradeEntries SUMMER = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntitySummerWarrior.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntitySummerArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntitySummerBannerBearer.class, SOLDIER, SOLDIER_F));
	public static GOTUnitTradeEntries THENN = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityThenn.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityThennArcher.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityThennAxeThrower.class, LEVYMANA, LEVYMANA_F), new GOTUnitTradeEntry(GOTEntityThennBannerBearer.class, LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityThennBerserker.class, SOLDIER + 30, SOLDIER_F + 30.0f).setPledgeExclusive(), new GOTUnitTradeEntry(GOTEntityGiant.class, SOLDIER + 100, SOLDIER_F + 100.0f).setPledgeExclusive());
	public static GOTUnitTradeEntries SOTHORYOS = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntitySothoryosWarrior.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntitySothoryosBlowgunner.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntitySothoryosBannerBearer.class, SOLDIER, SOLDIER_F));
	public static GOTUnitTradeEntries NORTH_HILLMEN = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityNorthHillmanWarrior.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityNorthHillmanArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityNorthHillmanAxeThrower.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityNorthHillmanBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityNorthHillmanWarrior.class, GOTEntityWoolyRhino.class, "Rider", SOLDIERH + 30, SOLDIERH_F + 30.0f).setPledgeExclusive());
	public static GOTUnitTradeEntries GIFT = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityGiftGuard.class, SOLDIER, SOLDIER_F).setPledgeExclusive(), new GOTUnitTradeEntry(GOTEntityGiftBannerBearer.class, SOLDIER, SOLDIER_F).setPledgeExclusive());
	public static GOTUnitTradeEntries GOLDENCOMPANY = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntityGoldenWarrior.class, SOLDIER, 0.0f), new GOTUnitTradeEntry(GOTEntityGoldenSpearman.class, SOLDIER, 0.0f), new GOTUnitTradeEntry(GOTEntityGoldenBannerBearer.class, SOLDIER, 0.0f));
	public static GOTUnitTradeEntries IBBEN = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityIbbenWarrior.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityIbbenArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityIbbenAxeThrower.class, SOLDIERA, SOLDIERA_F));
	public static GOTUnitTradeEntries JOGOS = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityJogos.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityJogosArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityJogosBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityJogos.class, GOTEntityZebra.class, "Rider", SOLDIERH, SOLDIERH_F), new GOTUnitTradeEntry(GOTEntityJogosArcher.class, GOTEntityZebra.class, "Rider", SOLDIERHA, SOLDIERHA_F), new GOTUnitTradeEntry(GOTEntityJogosBannerBearer.class, GOTEntityZebra.class, "Rider", SOLDIERH, SOLDIERH_F));
	public static GOTUnitTradeEntries HILLMEN = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityHillmanWarrior.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityHillmanArcher.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityHillmanAxeThrower.class, SOLDIERA, SOLDIERA_F), new GOTUnitTradeEntry(GOTEntityHillmanBannerBearer.class, SOLDIER, SOLDIER_F), new GOTUnitTradeEntry(GOTEntityHillmanBerserker.class, SOLDIER + 30, SOLDIER_F + 30.0f).setPledgeExclusive());
	public static GOTUnitTradeEntries DOTHRAKI = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(GOTEntityDothraki.class, GOTEntityHorse.class, "Rider", LEVYMAN, LEVYMAN_F), new GOTUnitTradeEntry(GOTEntityDothrakiArcher.class, GOTEntityHorse.class, "Rider", LEVYMANA, LEVYMANA_F));

	public static GOTUnitTradeEntries ARRYN_FARMER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntityArrynFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.FARMER));
	public static GOTUnitTradeEntries CROWNLANDS_FARMER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntityCrownlandsFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.FARMER));
	public static GOTUnitTradeEntries DORNE_FARMER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntityDorneFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.FARMER));
	public static GOTUnitTradeEntries DRAGONSTONE_FARMER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntityDragonstoneFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.FARMER));
	public static GOTUnitTradeEntries IBBEN_FARMER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntityIbbenFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.FARMER));
	public static GOTUnitTradeEntries IRONBORN_FARMER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntityIronbornFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.FARMER));
	public static GOTUnitTradeEntries NORTH_FARMER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntityNorthFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.FARMER));
	public static GOTUnitTradeEntries REACH_FARMER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntityReachFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.FARMER));
	public static GOTUnitTradeEntries RIVERLANDS_FARMER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntityRiverlandsFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.FARMER));
	public static GOTUnitTradeEntries SOTHORYOS_FARMER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntitySothoryosFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.FARMER));
	public static GOTUnitTradeEntries STORMLANDS_FARMER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntityStormlandsFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.FARMER));
	public static GOTUnitTradeEntries SUMMER_FARMER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntitySummerFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.FARMER));
	public static GOTUnitTradeEntries WESTERLANDS_FARMER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntityWesterlandsFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.FARMER));
	public static GOTUnitTradeEntries YITI_FARMER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntityYiTiFarmhand.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.FARMER));
	public static GOTUnitTradeEntries GHISCAR_SLAVER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntityGhiscarSlave.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.FARMER));
	public static GOTUnitTradeEntries LYS_SLAVER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntityLysSlave.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.FARMER));
	public static GOTUnitTradeEntries MYR_SLAVER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntityMyrSlave.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.FARMER));
	public static GOTUnitTradeEntries TYROSH_SLAVER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntityTyroshSlave.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.FARMER));
	public static GOTUnitTradeEntries VOLANTIS_SLAVER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntityVolantisSlave.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.FARMER));

	public static GOTUnitTradeEntries PROSTITUTE_KEEPER = new GOTUnitTradeEntries(0.0f, new GOTUnitTradeEntry(GOTEntityProstitute.class, SLAVE, SLAVE_F).setTask(GOTHiredNPCInfo.Task.PROSTITUTE));

	public static int lhazar_MERCENARY_COST = 20;
	public GOTUnitTradeEntry[] tradeEntries;

	public GOTUnitTradeEntries(float baseAlignment, GOTUnitTradeEntry... trades) {
		for (GOTUnitTradeEntry trade : tradeEntries = trades) {
			trade.alignmentRequired += baseAlignment;
			if ((trade.alignmentRequired >= 0.0f)) {
				continue;
			}
			throw new IllegalArgumentException("Units cannot require negative alignment!");
		}
	}
}
