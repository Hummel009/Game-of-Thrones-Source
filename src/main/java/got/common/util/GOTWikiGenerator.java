package got.common.util;

import got.common.GOTAchievementRank;
import got.common.GOTDate;
import got.common.block.other.GOTBlockOreGem;
import got.common.block.other.GOTBlockRock;
import got.common.database.*;
import got.common.entity.GOTEntityRegistry;
import got.common.entity.other.GOTEntityNPC;
import got.common.entity.other.GOTEntitySpiderBase;
import got.common.entity.other.iface.*;
import got.common.entity.other.utils.GOTEntityUtils;
import got.common.entity.other.utils.GOTTradeEntry;
import got.common.entity.other.utils.GOTUnitTradeEntry;
import got.common.entity.westeros.legendary.captain.*;
import got.common.entity.westeros.legendary.deco.GOTEntityMyrcellaBaratheon;
import got.common.entity.westeros.legendary.deco.GOTEntityTommenBaratheon;
import got.common.entity.westeros.legendary.quest.GOTEntityCerseiLannister;
import got.common.entity.westeros.legendary.quest.GOTEntitySamwellTarly;
import got.common.entity.westeros.legendary.quest.GOTEntityTyrionLannister;
import got.common.entity.westeros.legendary.quest.GOTEntityVarys;
import got.common.entity.westeros.legendary.reborn.GOTEntityJonSnow;
import got.common.entity.westeros.legendary.reborn.GOTEntityLancelLannister;
import got.common.entity.westeros.legendary.trader.*;
import got.common.entity.westeros.legendary.warrior.*;
import got.common.faction.GOTFaction;
import got.common.faction.GOTFactionRank;
import got.common.item.other.GOTItemBanner;
import got.common.world.biome.GOTBiome;
import got.common.world.biome.GOTBiomeDecorator;
import got.common.world.biome.GOTClimateType;
import got.common.world.biome.variant.GOTBiomeVariant;
import got.common.world.biome.variant.GOTBiomeVariantList;
import got.common.world.feature.GOTTreeType;
import got.common.world.map.GOTAbstractWaypoint;
import got.common.world.map.GOTFixer;
import got.common.world.map.GOTWaypoint;
import got.common.world.spawning.GOTFactionContainer;
import got.common.world.spawning.GOTSpawnEntry;
import got.common.world.spawning.GOTSpawnListContainer;
import got.common.world.structure.GOTStructureRegistry;
import got.common.world.structure.other.GOTStructureBase;
import got.common.world.structure.other.GOTStructureBaseSettlement;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GOTWikiGenerator {
	private static final Map<Class<? extends Entity>, Entity> ENTITY_CLASS_TO_ENTITY = new HashMap<>();
	private static final Map<Class<? extends Entity>, GOTWaypoint> ENTITY_CLASS_TO_WP = new HashMap<>();
	private static final Map<Class<? extends WorldGenerator>, GOTStructureBase> STRUCTURE_CLASS_TO_STRUCTURE = new HashMap<>();

	private static final Map<String, String> FACTION_TO_PAGENAME = new HashMap<>();
	private static final Map<String, String> ENTITY_TO_PAGENAME = new HashMap<>();
	private static final Map<String, String> BIOME_TO_PAGENAME = new HashMap<>();

	private static final Iterable<Item> ITEMS = new ArrayList<>(GOTItems.CONTENT);
	private static final Iterable<GOTUnitTradeEntries> UNIT_TRADE_ENTRIES = new ArrayList<>(GOTUnitTradeEntries.CONTENT);
	private static final Iterable<Class<? extends Entity>> ENTITY_CLASSES = new HashSet<>(GOTEntityRegistry.CONTENT);
	private static final Iterable<Class<? extends WorldGenerator>> STRUCTURE_CLASSES = new HashSet<>(GOTStructureRegistry.CONTENT);
	private static final Iterable<GOTAchievement> ACHIEVEMENTS = new HashSet<>(GOTAchievement.CONTENT);

	private static final Collection<GOTBiome> BIOMES = new HashSet<>(GOTBiome.CONTENT);

	private static final Iterable<GOTFaction> FACTIONS = EnumSet.allOf(GOTFaction.class);
	private static final Iterable<GOTTreeType> TREES = EnumSet.allOf(GOTTreeType.class);
	private static final Iterable<GOTWaypoint> WAYPOINTS = EnumSet.allOf(GOTWaypoint.class);
	private static final Iterable<GOTCapes> CAPES = EnumSet.allOf(GOTCapes.class);
	private static final Iterable<GOTShields> SHIELDS = EnumSet.allOf(GOTShields.class);

	private static final Collection<String> MINERALS = new HashSet<>();

	private static final String BEGIN = "</title>\n<ns>10</ns>\n<revision>\n<text>&lt;includeonly&gt;{{#switch: {{{1}}}";
	private static final String END = "\n}}&lt;/includeonly&gt;&lt;noinclude&gt;[[" + Lang.CATEGORY + "]]&lt;/noinclude&gt;</text>\n</revision>\n</page>\n";
	private static final String TITLE = "<page>\n<title>";
	private static final String TITLE_SINGLE = "<page><title>";
	private static final String PAGE_LEFT = "</title><revision><text>";
	private static final String PAGE_RIGHT = "</text></revision></page>\n";
	private static final String UTF_8 = "UTF-8";
	private static final String TEMPLATE = "Template:";
	private static final String NL = "\n";
	private static final String TRUE = "True";
	private static final String FALSE = "False";
	private static final String N_A = "N/A";

	static {
		BIOMES.remove(GOTBiome.ocean1);
		BIOMES.remove(GOTBiome.ocean2);
		BIOMES.remove(GOTBiome.ocean3);
		BIOMES.remove(GOTBiome.beach);
		BIOMES.remove(GOTBiome.beachGravel);
		BIOMES.remove(GOTBiome.beachWhite);
		BIOMES.remove(GOTBiome.lake);
		BIOMES.remove(GOTBiome.river);
		BIOMES.remove(GOTBiome.island);
		BIOMES.remove(GOTBiome.kingSpears);
		BIOMES.remove(GOTBiome.bleedingBeach);
		BIOMES.remove(GOTBiome.valyriaSea1);
		BIOMES.remove(GOTBiome.valyriaSea2);

		ENTITY_CLASS_TO_WP.put(GOTEntityYgritte.class, GOTWaypoint.HARDHOME);
		ENTITY_CLASS_TO_WP.put(GOTEntityTormund.class, GOTWaypoint.HARDHOME);
		ENTITY_CLASS_TO_WP.put(GOTEntityManceRayder.class, GOTWaypoint.HARDHOME);
		ENTITY_CLASS_TO_WP.put(GOTEntityCraster.class, GOTWaypoint.CRASTERS_KEEP);
		ENTITY_CLASS_TO_WP.put(GOTEntityVictarionGreyjoy.class, GOTWaypoint.VICTARION_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityLancelLannister.LancelLannisterNormal.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityTobhoMott.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityTyrionLannister.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityGendryBaratheon.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityPetyrBaelish.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityBronn.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityPodrickPayne.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityHotPie.class, GOTWaypoint.CROSSROADS_INN);
		ENTITY_CLASS_TO_WP.put(GOTEntityVargoHoat.class, GOTWaypoint.CROSSROADS_INN);
		ENTITY_CLASS_TO_WP.put(GOTEntitySandorClegane.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityJoffreyBaratheon.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityCerseiLannister.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityJaimeLannister.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityPycelle.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityJanosSlynt.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityVarys.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityIlynPayne.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityHighSepton.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityTommenBaratheon.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityMyrcellaBaratheon.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityMerynTrant.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityBarristanSelmy.class, GOTWaypoint.KINGS_LANDING);
		ENTITY_CLASS_TO_WP.put(GOTEntityJeorMormont.class, GOTWaypoint.CASTLE_BLACK);
		ENTITY_CLASS_TO_WP.put(GOTEntityJonSnow.JonSnowLife1.class, GOTWaypoint.CASTLE_BLACK);
		ENTITY_CLASS_TO_WP.put(GOTEntityAemonTargaryen.class, GOTWaypoint.CASTLE_BLACK);
		ENTITY_CLASS_TO_WP.put(GOTEntityAlliserThorne.class, GOTWaypoint.CASTLE_BLACK);
		ENTITY_CLASS_TO_WP.put(GOTEntityEdd.class, GOTWaypoint.CASTLE_BLACK);
		ENTITY_CLASS_TO_WP.put(GOTEntitySamwellTarly.class, GOTWaypoint.CASTLE_BLACK);
		ENTITY_CLASS_TO_WP.put(GOTEntityCotterPyke.class, GOTWaypoint.EASTWATCH);
		ENTITY_CLASS_TO_WP.put(GOTEntityHarmune.class, GOTWaypoint.EASTWATCH);
		ENTITY_CLASS_TO_WP.put(GOTEntityDenysMallister.class, GOTWaypoint.SHADOW_TOWER);
		ENTITY_CLASS_TO_WP.put(GOTEntityMullin.class, GOTWaypoint.SHADOW_TOWER);
	}

	private GOTWikiGenerator() {
	}

	public static void generate(Type type, World world, EntityPlayer entityPlayer) {
		long time = System.nanoTime();

		try {
			Files.createDirectories(Paths.get("hummel"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		switch (type) {
			case TABLES:
				Collection<Runnable> tableGens = new HashSet<>();

				tableGens.add(GOTWikiGenerator::genTableShields);
				tableGens.add(GOTWikiGenerator::genTableCapes);
				tableGens.add(GOTWikiGenerator::genTableUnits);
				tableGens.add(GOTWikiGenerator::genTableArmor);
				tableGens.add(GOTWikiGenerator::genTableWeapons);
				tableGens.add(GOTWikiGenerator::genTableFood);
				tableGens.add(() -> genTableAchievements(entityPlayer));
				tableGens.add(() -> genTableWaypoints(entityPlayer));

				tableGens.parallelStream().forEach(Runnable::run);

				break;
			case XML:
				try (PrintWriter printWriter = new PrintWriter("hummel/import.xml", UTF_8)) {
					StringBuilder sb = new StringBuilder();

					GOTDate.Season season = GOTDate.AegonCalendar.getSeason();

					sb.append("<mediawiki xmlns=\"http://www.mediawiki.org/xml/export-0.11/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.mediawiki.org/xml/export-0.11/ http://www.mediawiki.org/xml/export-0.11.xsd\" version=\"0.11\" xml:lang=\"ru\">");

					Collection<Runnable> runnables = new HashSet<>();

					runnables.add(() -> searchForEntities(world));
					runnables.add(() -> searchForStructures(world));
					runnables.add(GOTWikiGenerator::searchForMinerals);
					runnables.add(GOTWikiGenerator::searchForPagenamesEntity);
					runnables.add(GOTWikiGenerator::searchForPagenamesBiome);
					runnables.add(GOTWikiGenerator::searchForPagenamesFaction);

					runnables.parallelStream().forEach(Runnable::run);

					Collection<Supplier<StringBuilder>> suppliers = new HashSet<>();

					Set<String> existingPages = getExistingPages();
					Collection<String> neededPages = new HashSet<>();

					suppliers.add(() -> addPagesMinerals(neededPages, existingPages));
					suppliers.add(() -> addPagesEntities(neededPages, existingPages));
					suppliers.add(() -> addPagesBiomes(neededPages, existingPages));
					suppliers.add(() -> addPagesFactions(neededPages, existingPages));
					suppliers.add(() -> addPagesTrees(neededPages, existingPages));
					suppliers.add(() -> addPagesStructures(neededPages, existingPages));

					suppliers.parallelStream().map(Supplier::get).forEach(sb::append);
					suppliers.clear();

					markPagesForRemoval(neededPages, existingPages);

					suppliers.add(GOTWikiGenerator::genTemplateMineralBiomes);
					suppliers.add(GOTWikiGenerator::genTemplateTreeBiomes);
					suppliers.add(GOTWikiGenerator::genTemplateStructureBiomes);
					suppliers.add(GOTWikiGenerator::genTemplateStructureEntities);

					suppliers.add(GOTWikiGenerator::genTemplateBiomeBandits);
					suppliers.add(GOTWikiGenerator::genTemplateBiomeClimate);
					suppliers.add(GOTWikiGenerator::genTemplateBiomeConquestFactions);
					suppliers.add(GOTWikiGenerator::genTemplateBiomeInvasionFactions);
					suppliers.add(GOTWikiGenerator::genTemplateBiomeMinerals);
					suppliers.add(GOTWikiGenerator::genTemplateBiomeAnimals);
					suppliers.add(GOTWikiGenerator::genTemplateBiomeMusic);
					suppliers.add(GOTWikiGenerator::genTemplateBiomeName);
					suppliers.add(GOTWikiGenerator::genTemplateBiomeNPCs);
					suppliers.add(GOTWikiGenerator::genTemplateBiomeRainfall);
					suppliers.add(GOTWikiGenerator::genTemplateBiomeStructures);
					suppliers.add(GOTWikiGenerator::genTemplateBiomeTemperature);
					suppliers.add(GOTWikiGenerator::genTemplateBiomeTrees);
					suppliers.add(GOTWikiGenerator::genTemplateBiomeVariants);
					suppliers.add(GOTWikiGenerator::genTemplateBiomeVisitAchievement);
					suppliers.add(GOTWikiGenerator::genTemplateBiomeWaypoints);

					suppliers.add(GOTWikiGenerator::genTemplateFactionBanners);
					suppliers.add(GOTWikiGenerator::genTemplateFactionCharacters);
					suppliers.add(GOTWikiGenerator::genTemplateFactionCodename);
					suppliers.add(GOTWikiGenerator::genTemplateFactionConquestBiomes);
					suppliers.add(GOTWikiGenerator::genTemplateFactionEnemies);
					suppliers.add(GOTWikiGenerator::genTemplateFactionFriends);
					suppliers.add(GOTWikiGenerator::genTemplateFactionInvasionBiomes);
					suppliers.add(GOTWikiGenerator::genTemplateFactionNPCs);
					suppliers.add(GOTWikiGenerator::genTemplateFactionName);
					suppliers.add(GOTWikiGenerator::genTemplateFactionPledgeRank);
					suppliers.add(GOTWikiGenerator::genTemplateFactionRanks);
					suppliers.add(GOTWikiGenerator::genTemplateFactionRegion);
					suppliers.add(GOTWikiGenerator::genTemplateFactionShieldsCapes);
					suppliers.add(GOTWikiGenerator::genTemplateFactionSpawnBiomes);
					suppliers.add(GOTWikiGenerator::genTemplateFactionStructures);
					suppliers.add(GOTWikiGenerator::genTemplateFactionWarCrimes);
					suppliers.add(GOTWikiGenerator::genTemplateFactionWaypoints);

					suppliers.add(GOTWikiGenerator::genTemplateEntityBannerBearer);
					suppliers.add(GOTWikiGenerator::genTemplateEntityBuyPools);
					suppliers.add(GOTWikiGenerator::genTemplateEntityCharacter);
					suppliers.add(GOTWikiGenerator::genTemplateEntityLegendaryDrop);
					suppliers.add(GOTWikiGenerator::genTemplateEntityFaction);
					suppliers.add(GOTWikiGenerator::genTemplateEntityFarmhand);
					suppliers.add(GOTWikiGenerator::genTemplateEntityHealth);
					suppliers.add(GOTWikiGenerator::genTemplateEntityHireable);
					suppliers.add(GOTWikiGenerator::genTemplateEntityHireAlignment);
					suppliers.add(GOTWikiGenerator::genTemplateEntityHirePrice);
					suppliers.add(GOTWikiGenerator::genTemplateEntityHirePricePledge);
					suppliers.add(GOTWikiGenerator::genTemplateEntityImmuneToFire);
					suppliers.add(GOTWikiGenerator::genTemplateEntityImmuneToFrost);
					suppliers.add(GOTWikiGenerator::genTemplateEntityImmuneToHeat);
					suppliers.add(GOTWikiGenerator::genTemplateEntityKillAchievement);
					suppliers.add(GOTWikiGenerator::genTemplateEntityKillAlignment);
					suppliers.add(GOTWikiGenerator::genTemplateEntityMarriage);
					suppliers.add(GOTWikiGenerator::genTemplateEntityMercenary);
					suppliers.add(GOTWikiGenerator::genTemplateEntityOwners);
					suppliers.add(GOTWikiGenerator::genTemplateEntityRideableAnimal);
					suppliers.add(GOTWikiGenerator::genTemplateEntityRideableNPC);
					suppliers.add(GOTWikiGenerator::genTemplateEntitySellPools);
					suppliers.add(GOTWikiGenerator::genTemplateEntitySellUnitPools);
					suppliers.add(GOTWikiGenerator::genTemplateEntitySmith);
					suppliers.add(GOTWikiGenerator::genTemplateEntityBiomes);
					suppliers.add(GOTWikiGenerator::genTemplateEntitySpawnsInDarkness);
					suppliers.add(GOTWikiGenerator::genTemplateEntityTargetSeeker);
					suppliers.add(GOTWikiGenerator::genTemplateEntityTradeable);
					suppliers.add(GOTWikiGenerator::genTemplateEntityUnitTradeable);
					suppliers.add(GOTWikiGenerator::genTemplateEntityStructures);
					suppliers.add(() -> genTemplateEntityWaypoint(world));

					suppliers.parallelStream().map(Supplier::get).forEach(sb::append);
					suppliers.clear();

					sb.append("</mediawiki>");

					GOTDate.AegonCalendar.getDate().getMonth().setSeason(season);

					printWriter.write(sb.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}

				break;
		}

		long newTime = System.nanoTime();

		IChatComponent component = new ChatComponentText("Generated in " + (newTime - time) / 1.0E6 + " milliseconds");
		entityPlayer.addChatMessage(component);
	}

	@SuppressWarnings("StringBufferReplaceableByString")
	private static void genTableAchievements(EntityPlayer entityPlayer) {
		Collection<String> data = new TreeSet<>();

		for (GOTAchievement achievement : ACHIEVEMENTS) {
			if (!(achievement instanceof GOTAchievementRank)) {
				StringBuilder sb = new StringBuilder();

				sb.append(NL).append("| ");
				sb.append(achievement.getTitle(entityPlayer));
				sb.append(" || ").append(achievement.getDescription());
				sb.append(NL).append("|-");

				data.add(sb.toString());
			}
		}

		StringBuilder sb = new StringBuilder();

		for (String datum : data) {
			sb.append(datum);
		}

		try (PrintWriter printWriter = new PrintWriter("hummel/achievements.txt", UTF_8)) {
			printWriter.write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void genTableArmor() {
		Collection<String> data = new TreeSet<>();

		for (Item item : ITEMS) {
			if (item instanceof ItemArmor) {
				StringBuilder sb = new StringBuilder();

				float damage = ((ItemArmor) item).damageReduceAmount;
				ItemArmor.ArmorMaterial material = ((ItemArmor) item).getArmorMaterial();

				sb.append(NL).append("| ");
				sb.append(getItemName(item));
				sb.append(" || ").append(getItemFilename(item));
				sb.append(" || ").append(item.getMaxDamage());
				sb.append(" || ").append(damage);

				sb.append(" || ");
				if (material == null || material.customCraftingMaterial == null) {
					sb.append(N_A);
				} else {
					sb.append(getItemName(material.customCraftingMaterial));
				}

				sb.append(NL).append("|-");

				data.add(sb.toString());
			}
		}

		StringBuilder sb = new StringBuilder();

		for (String datum : data) {
			sb.append(datum);
		}

		try (PrintWriter printWriter = new PrintWriter("hummel/armor.txt", UTF_8)) {
			printWriter.write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("StringBufferReplaceableByString")
	private static void genTableCapes() {
		Collection<String> data = new TreeSet<>();

		for (GOTCapes cape : CAPES) {
			StringBuilder sb = new StringBuilder();

			sb.append(NL).append("| ");
			sb.append(cape.getCapeName());
			sb.append(" || ").append(cape.getCapeDesc());
			sb.append(" || ").append(getCapeFilename(cape));
			sb.append(NL).append("|-");

			data.add(sb.toString());
		}

		StringBuilder sb = new StringBuilder();

		for (String datum : data) {
			sb.append(datum);
		}

		try (PrintWriter printWriter = new PrintWriter("hummel/capes.txt", UTF_8)) {
			printWriter.write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	private static void genTableFood() {
		Collection<String> data = new TreeSet<>();

		DecimalFormat decimalFormat = new DecimalFormat("#.##");

		for (Item item : ITEMS) {
			if (item instanceof ItemFood) {
				StringBuilder sb = new StringBuilder();

				int heal = ((ItemFood) item).func_150905_g(null);
				float saturation = ((ItemFood) item).func_150906_h(null);

				sb.append(NL).append("| ");
				sb.append(getItemName(item));
				sb.append(" || ").append(getItemFilename(item));
				sb.append(" || ").append("{{Bar|bread|").append(decimalFormat.format(saturation * heal * 2)).append("}}");
				sb.append(" || ").append("{{Bar|food|").append(heal).append("}}");
				sb.append(" || ").append(item.getItemStackLimit());
				sb.append(NL).append("|-");

				data.add(sb.toString());
			}
		}

		StringBuilder sb = new StringBuilder();

		for (String datum : data) {
			sb.append(datum);
		}

		try (PrintWriter printWriter = new PrintWriter("hummel/food.txt", UTF_8)) {
			printWriter.write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("StringBufferReplaceableByString")
	private static void genTableShields() {
		Collection<String> data = new TreeSet<>();

		for (GOTShields shield : SHIELDS) {
			StringBuilder sb = new StringBuilder();

			sb.append(NL).append("| ");
			sb.append(shield.getShieldName());
			sb.append(" || ").append(shield.getShieldDesc());
			sb.append(" || ").append(getShieldFilename(shield));
			sb.append(NL).append("|-");

			data.add(sb.toString());
		}

		StringBuilder sb = new StringBuilder();

		for (String datum : data) {
			sb.append(datum);
		}

		try (PrintWriter printWriter = new PrintWriter("hummel/shields.txt", UTF_8)) {
			printWriter.write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void genTableUnits() {
		Collection<String> data = new TreeSet<>();

		for (GOTUnitTradeEntries unitTradeEntries : UNIT_TRADE_ENTRIES) {
			for (GOTUnitTradeEntry entry : unitTradeEntries.getTradeEntries()) {
				StringBuilder sb = new StringBuilder();

				sb.append(NL).append("| ");
				sb.append(getEntityLink(entry.getEntityClass()));

				if (entry.getMountClass() != null) {
					sb.append(Lang.RIDER);
				}

				int cost = entry.getInitialCost();
				int alignment = (int) entry.getAlignmentRequired();

				if (entry.getPledgeType() == GOTUnitTradeEntry.PledgeType.NONE) {
					sb.append(" || ").append("{{Coins|").append(cost * 2).append("}}");
					sb.append(" || ").append("{{Coins|").append(cost).append("}}");
					sb.append(" || ").append('+').append(alignment);
					sb.append(" || ").append('-');
				} else {
					sb.append(" || ").append(N_A);
					sb.append(" || ").append("{{Coins|").append(cost).append("}}");
					sb.append(" || ").append('+').append(Math.max(alignment, 100));
					sb.append(" || ").append('+');
				}

				sb.append(NL).append("|-");

				data.add(sb.toString());
			}
		}

		StringBuilder sb = new StringBuilder();

		for (String datum : data) {
			sb.append(datum);
		}

		try (PrintWriter printWriter = new PrintWriter("hummel/units.txt", UTF_8)) {
			printWriter.write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("StringBufferReplaceableByString")
	private static void genTableWaypoints(EntityPlayer entityPlayer) {
		Collection<String> data = new TreeSet<>();

		for (GOTWaypoint wp : WAYPOINTS) {
			StringBuilder sb = new StringBuilder();

			sb.append(NL).append("| ");
			sb.append(wp.getDisplayName());
			sb.append(" || ").append(wp.getLoreText(entityPlayer));
			sb.append(NL).append("|-");

			data.add(sb.toString());
		}

		StringBuilder sb = new StringBuilder();

		for (String datum : data) {
			sb.append(datum);
		}

		try (PrintWriter printWriter = new PrintWriter("hummel/waypoints.txt", UTF_8)) {
			printWriter.write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void genTableWeapons() {
		Collection<String> data = new TreeSet<>();

		for (Item item : ITEMS) {
			if (item instanceof ItemSword) {
				StringBuilder sb = new StringBuilder();

				float damage = GOTReflection.getDamageAmount(item);
				Item.ToolMaterial material = GOTReflection.getToolMaterial(item);

				sb.append(NL).append("| ");
				sb.append(getItemName(item));
				sb.append(" || ").append(getItemFilename(item));
				sb.append(" || ").append(item.getMaxDamage());
				sb.append(" || ").append(damage);

				sb.append(" || ");
				if (material == null || material.getRepairItemStack() == null) {
					sb.append(N_A);
				} else {
					sb.append(getItemName(material.getRepairItemStack().getItem()));
				}

				sb.append(NL).append("|-");

				data.add(sb.toString());
			}
		}

		StringBuilder sb = new StringBuilder();

		for (String datum : data) {
			sb.append(datum);
		}

		try (PrintWriter printWriter = new PrintWriter("hummel/weapon.txt", UTF_8)) {
			printWriter.write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static StringBuilder genTemplateBiomeAnimals() {
		Map<GOTBiome, Set<String>> data = new HashMap<>();

		for (GOTBiome biome : BIOMES) {
			data.put(biome, new TreeSet<>());

			Collection<BiomeGenBase.SpawnListEntry> spawnListEntries = new HashSet<>(biome.getSpawnableList(EnumCreatureType.ambient));
			spawnListEntries.addAll(biome.getSpawnableList(EnumCreatureType.waterCreature));
			spawnListEntries.addAll(biome.getSpawnableList(EnumCreatureType.creature));
			spawnListEntries.addAll(biome.getSpawnableList(EnumCreatureType.monster));
			spawnListEntries.addAll(biome.getSpawnableList(GOTBiome.CREATURE_TYPE_GOT_AMBIENT));

			for (BiomeGenBase.SpawnListEntry spawnListEntry : spawnListEntries) {
				data.get(biome).add(NL + "* " + getEntityLink(spawnListEntry.entityClass) + ';');
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Biome-Animals");
		sb.append(BEGIN);

		for (Map.Entry<GOTBiome, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getBiomePagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.BIOME_HAS_ANIMALS, Lang.BIOME_NO_ANIMALS);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateBiomeBandits() {
		Map<GOTBiome, String> data = new HashMap<>();

		for (GOTBiome biome : BIOMES) {
			data.put(biome, biome.getBanditChance().toString());
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Biome-Bandits");
		sb.append(BEGIN);

		for (Map.Entry<GOTBiome, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getBiomePagename(entry.getKey())).append(" = ");

			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateBiomeClimate() {
		Map<GOTBiome, String> data = new HashMap<>();

		for (GOTBiome biome : BIOMES) {
			if (biome.getClimateType() != null) {
				switch (biome.getClimateType()) {
					case COLD:
						data.put(biome, Lang.CLIMATE_COLD.toString());
						break;
					case COLD_AZ:
						data.put(biome, Lang.CLIMATE_COLD_AZ.toString());
						break;
					case NORMAL:
						data.put(biome, Lang.CLIMATE_NORMAL.toString());
						break;
					case NORMAL_AZ:
						data.put(biome, Lang.CLIMATE_NORMAL_AZ.toString());
						break;
					case SUMMER:
						data.put(biome, Lang.CLIMATE_SUMMER.toString());
						break;
					case SUMMER_AZ:
						data.put(biome, Lang.CLIMATE_SUMMER_AZ.toString());
						break;
					case WINTER:
						data.put(biome, Lang.CLIMATE_WINTER.toString());
						break;
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Biome-Climate");
		sb.append(BEGIN);

		for (Map.Entry<GOTBiome, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getBiomePagename(entry.getKey())).append(" = ");

			appendDefault(sb, Lang.CLIMATE_NULL.toString());
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateBiomeConquestFactions() {
		Map<GOTBiome, Set<String>> data = new HashMap<>();

		for (GOTBiome biome : BIOMES) {
			data.put(biome, new TreeSet<>());

			for (GOTFactionContainer factionContainer : biome.getNPCSpawnList().getFactionContainers()) {
				if (factionContainer.getBaseWeight() <= 0) {
					for (GOTSpawnListContainer spawnListContainer : factionContainer.getSpawnListContainers()) {
						for (GOTSpawnEntry spawnEntry : spawnListContainer.getSpawnList().getSpawnEntries()) {
							Entity entity = ENTITY_CLASS_TO_ENTITY.get(spawnEntry.entityClass);
							if (entity instanceof GOTEntityNPC) {
								GOTFaction faction = ((GOTEntityNPC) entity).getFaction();
								data.get(biome).add(NL + "* " + getFactionLink(faction) + ';');
								break;
							}
						}
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Biome-ConquestFactions");
		sb.append(BEGIN);

		for (Map.Entry<GOTBiome, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getBiomePagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.BIOME_HAS_CONQUEST, Lang.BIOME_NO_CONQUEST);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateBiomeInvasionFactions() {
		Map<GOTBiome, Set<String>> data = new HashMap<>();

		for (GOTBiome biome : BIOMES) {
			data.put(biome, new TreeSet<>());

			for (GOTInvasions invasion : biome.getInvasionSpawns().getRegisteredInvasions()) {
				for (GOTInvasions.InvasionSpawnEntry invasionSpawnEntry : invasion.getInvasionMobs()) {
					Entity entity = ENTITY_CLASS_TO_ENTITY.get(invasionSpawnEntry.getEntityClass());
					if (entity instanceof GOTEntityNPC) {
						GOTFaction faction = ((GOTEntityNPC) entity).getFaction();
						data.get(biome).add(NL + "* " + getFactionLink(faction) + ';');
						break;
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Biome-InvasionFactions");
		sb.append(BEGIN);

		for (Map.Entry<GOTBiome, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getBiomePagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.BIOME_HAS_INVASION, Lang.BIOME_NO_INVASION);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateBiomeMinerals() {
		Map<GOTBiome, Set<String>> data = new HashMap<>();

		for (GOTBiome biome : BIOMES) {
			data.put(biome, new TreeSet<>());

			Collection<GOTBiomeDecorator.OreGenerant> oreGenerants = new HashSet<>(biome.getDecorator().getBiomeSoils());
			oreGenerants.addAll(biome.getDecorator().getBiomeOres());
			oreGenerants.addAll(biome.getDecorator().getBiomeGems());

			for (GOTBiomeDecorator.OreGenerant oreGenerant : oreGenerants) {
				Block block = GOTReflection.getOreGenBlock(oreGenerant.getOreGen());
				int meta = GOTReflection.getOreGenMeta(oreGenerant.getOreGen());

				String blockName;
				if (block instanceof GOTBlockOreGem || block instanceof BlockDirt || block instanceof GOTBlockRock) {
					blockName = getBlockMetaName(block, meta);
				} else {
					blockName = getBlockName(block);
				}

				String stats = "(" + oreGenerant.getOreChance() + "%; Y: " + oreGenerant.getMinHeight() + '-' + oreGenerant.getMaxHeight() + ')';

				data.get(biome).add(NL + "* " + getBlockLink(blockName) + ' ' + stats + ';');
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Biome-Minerals");
		sb.append(BEGIN);

		for (Map.Entry<GOTBiome, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getBiomePagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.BIOME_HAS_MINERALS, Lang.BIOME_NO_MINERALS);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateBiomeMusic() {
		Map<GOTBiome, String> data = new HashMap<>();

		for (GOTBiome biome : BIOMES) {
			if (biome.getBiomeMusic() != null) {
				data.put(biome, biome.getBiomeMusic().getSubregion());
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Biome-Music");
		sb.append(BEGIN);

		for (Map.Entry<GOTBiome, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getBiomePagename(entry.getKey())).append(" = ");

			appendDefault(sb, N_A);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateBiomeName() {
		Map<GOTBiome, String> data = new HashMap<>();

		for (GOTBiome biome : BIOMES) {
			data.put(biome, getBiomeName(biome));
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Biome-Name");
		sb.append(BEGIN);

		for (Map.Entry<GOTBiome, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getBiomePagename(entry.getKey())).append(" = ");

			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateBiomeNPCs() {
		Map<GOTBiome, Set<String>> data = new HashMap<>();

		for (GOTBiome biome : BIOMES) {
			data.put(biome, new TreeSet<>());

			for (GOTFactionContainer factionContainer : biome.getNPCSpawnList().getFactionContainers()) {
				if (factionContainer.getBaseWeight() > 0) {
					for (GOTSpawnListContainer spawnListContainer : factionContainer.getSpawnListContainers()) {
						for (GOTSpawnEntry spawnEntry : spawnListContainer.getSpawnList().getSpawnEntries()) {
							data.get(biome).add(NL + "* " + getEntityLink(spawnEntry.entityClass) + ';');
						}
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Biome-NPCs");
		sb.append(BEGIN);

		for (Map.Entry<GOTBiome, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getBiomePagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.BIOME_HAS_NPCS, Lang.BIOME_NO_NPCS);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateBiomeRainfall() {
		Map<GOTBiome, String> data = new HashMap<>();

		for (GOTBiome biome : BIOMES) {
			StringBuilder sb = new StringBuilder();

			GOTDate.AegonCalendar.getDate().getMonth().setSeason(GOTDate.Season.WINTER);
			GOTClimateType.performSeasonalChangesServerSide();
			sb.append(Lang.SEASON_WINTER).append(": ").append(biome.rainfall);

			GOTDate.AegonCalendar.getDate().getMonth().setSeason(GOTDate.Season.SPRING);
			GOTClimateType.performSeasonalChangesServerSide();
			sb.append("&lt;br&gt;");
			sb.append(Lang.SEASON_SPRING).append(": ").append(biome.rainfall);

			GOTDate.AegonCalendar.getDate().getMonth().setSeason(GOTDate.Season.SUMMER);
			GOTClimateType.performSeasonalChangesServerSide();
			sb.append("&lt;br&gt;");
			sb.append(Lang.SEASON_SUMMER).append(": ").append(biome.rainfall);

			GOTDate.AegonCalendar.getDate().getMonth().setSeason(GOTDate.Season.AUTUMN);
			GOTClimateType.performSeasonalChangesServerSide();
			sb.append("&lt;br&gt;");
			sb.append(Lang.SEASON_AUTUMN).append(": ").append(biome.rainfall);

			data.put(biome, sb.toString());
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Biome-Rainfall");
		sb.append(BEGIN);

		for (Map.Entry<GOTBiome, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getBiomePagename(entry.getKey())).append(" = ");

			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateBiomeStructures() {
		Map<GOTBiome, Set<String>> data = new HashMap<>();

		for (GOTBiome biome : BIOMES) {
			data.put(biome, new TreeSet<>());

			for (GOTBiomeDecorator.Structure structure : biome.getDecorator().getStructures()) {
				data.get(biome).add(NL + "* " + getStructureLink(structure.getStructureGen().getClass()) + ';');
			}

			for (GOTStructureBaseSettlement settlement : biome.getDecorator().getSettlements()) {
				if (settlement.getSpawnChance() != 0.0f) {
					Set<String> names = getSettlementNames(settlement.getClass());
					for (String name : names) {
						data.get(biome).add(NL + "* " + getSettlementName(name) + ';');
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Biome-Structures");
		sb.append(BEGIN);

		for (Map.Entry<GOTBiome, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getBiomePagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.BIOME_HAS_STRUCTURES, Lang.BIOME_NO_STRUCTURES);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateBiomeTemperature() {
		Map<GOTBiome, String> data = new HashMap<>();

		for (GOTBiome biome : BIOMES) {
			StringBuilder sb = new StringBuilder();

			GOTDate.AegonCalendar.getDate().getMonth().setSeason(GOTDate.Season.WINTER);
			GOTClimateType.performSeasonalChangesServerSide();
			sb.append(Lang.SEASON_WINTER).append(": ").append(biome.temperature);

			GOTDate.AegonCalendar.getDate().getMonth().setSeason(GOTDate.Season.SPRING);
			GOTClimateType.performSeasonalChangesServerSide();
			sb.append("&lt;br&gt;");
			sb.append(Lang.SEASON_SPRING).append(": ").append(biome.temperature);

			GOTDate.AegonCalendar.getDate().getMonth().setSeason(GOTDate.Season.SUMMER);
			GOTClimateType.performSeasonalChangesServerSide();
			sb.append("&lt;br&gt;");
			sb.append(Lang.SEASON_SUMMER).append(": ").append(biome.temperature);

			GOTDate.AegonCalendar.getDate().getMonth().setSeason(GOTDate.Season.AUTUMN);
			GOTClimateType.performSeasonalChangesServerSide();
			sb.append("&lt;br&gt;");
			sb.append(Lang.SEASON_AUTUMN).append(": ").append(biome.temperature);

			data.put(biome, sb.toString());
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Biome-Temperature");
		sb.append(BEGIN);

		for (Map.Entry<GOTBiome, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getBiomePagename(entry.getKey())).append(" = ");

			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateBiomeTrees() {
		Map<GOTBiome, Set<String>> data = new HashMap<>();

		for (GOTBiome biome : BIOMES) {
			data.put(biome, new TreeSet<>());

			Collection<GOTTreeType.WeightedTreeType> weightedTreeTypes = biome.getDecorator().getTreeTypes();

			Collection<GOTTreeType> excludedTreeTypes = EnumSet.noneOf(GOTTreeType.class);

			for (GOTTreeType.WeightedTreeType weightedTreeType : weightedTreeTypes) {
				GOTTreeType treeType = weightedTreeType.getTreeType();

				data.get(biome).add(NL + "* " + getTreeLink(treeType) + ';');

				excludedTreeTypes.add(treeType);
			}

			for (GOTBiomeVariantList.VariantBucket variantBucket : biome.getBiomeVariants().getVariantList()) {
				for (GOTTreeType.WeightedTreeType weightedTreeType : variantBucket.getVariant().getTreeTypes()) {
					GOTTreeType treeType = weightedTreeType.getTreeType();

					if (!excludedTreeTypes.contains(treeType)) {
						data.get(biome).add(NL + "* " + getTreeLink(treeType) + " (" + getBiomeVariantName(variantBucket.getVariant()).toLowerCase(Locale.ROOT) + ");");
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Biome-Trees");
		sb.append(BEGIN);

		for (Map.Entry<GOTBiome, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getBiomePagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.BIOME_HAS_TREES, Lang.BIOME_NO_TREES);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateBiomeVariants() {
		Map<GOTBiome, Set<String>> data = new HashMap<>();

		for (GOTBiome biome : BIOMES) {
			data.put(biome, new TreeSet<>());

			for (GOTBiomeVariantList.VariantBucket variantBucket : biome.getBiomeVariants().getVariantList()) {
				data.get(biome).add(NL + "* " + getBiomeVariantName(variantBucket.getVariant()) + ';');
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Biome-Variants");
		sb.append(BEGIN);

		for (Map.Entry<GOTBiome, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getBiomePagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.BIOME_HAS_VARIANTS, Lang.BIOME_NO_VARIANTS);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateBiomeVisitAchievement() {
		Map<GOTBiome, String> data = new HashMap<>();

		for (GOTBiome biome : BIOMES) {
			GOTAchievement achievement = biome.getBiomeAchievement();

			if (achievement != null) {
				data.put(biome, '«' + achievement.getTitle() + '»');
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Biome-VisitAchievement");
		sb.append(BEGIN);

		for (Map.Entry<GOTBiome, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getBiomePagename(entry.getKey())).append(" = ");

			appendDefault(sb, N_A);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateBiomeWaypoints() {
		Map<GOTBiome, Set<String>> data = new HashMap<>();

		for (GOTBiome biome : BIOMES) {
			data.put(biome, new TreeSet<>());

			for (GOTWaypoint wp : biome.getBiomeWaypoints().getWaypoints()) {
				data.get(biome).add(NL + "* " + wp.getDisplayName() + " (" + getFactionLink(wp.getFaction()) + ");");
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Biome-Waypoints");
		sb.append(BEGIN);

		for (Map.Entry<GOTBiome, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getBiomePagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.BIOME_HAS_WAYPOINTS, Lang.BIOME_NO_WAYPOINTS);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityBannerBearer() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTBannerBearer) {
				data.put(entityEntry.getKey(), TRUE);
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-BannerBearer");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendDefault(sb, FALSE);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityBiomes() {
		Map<Class<? extends Entity>, Set<String>> data = new HashMap<>();

		for (GOTBiome biome : BIOMES) {
			Collection<BiomeGenBase.SpawnListEntry> spawnListEntries = new HashSet<>();
			Collection<Class<? extends Entity>> conquestEntityClasses = new HashSet<>();
			Collection<Class<? extends Entity>> invasionEntityClasses = new HashSet<>();

			spawnListEntries.addAll(biome.getSpawnableList(EnumCreatureType.ambient));
			spawnListEntries.addAll(biome.getSpawnableList(EnumCreatureType.waterCreature));
			spawnListEntries.addAll(biome.getSpawnableList(EnumCreatureType.creature));
			spawnListEntries.addAll(biome.getSpawnableList(EnumCreatureType.monster));
			spawnListEntries.addAll(biome.getSpawnableGOTAmbientList());

			for (GOTFactionContainer factionContainer : biome.getNPCSpawnList().getFactionContainers()) {
				if (factionContainer.getBaseWeight() > 0) {
					for (GOTSpawnListContainer spawnListContainer : factionContainer.getSpawnListContainers()) {
						spawnListEntries.addAll(spawnListContainer.getSpawnList().getSpawnEntries());
					}
				} else {
					for (GOTSpawnListContainer spawnListContainer : factionContainer.getSpawnListContainers()) {
						for (BiomeGenBase.SpawnListEntry spawnListEntry : spawnListContainer.getSpawnList().getSpawnEntries()) {
							conquestEntityClasses.add(spawnListEntry.entityClass);
						}
					}
				}
			}

			for (GOTInvasions invasion : biome.getInvasionSpawns().getRegisteredInvasions()) {
				for (GOTInvasions.InvasionSpawnEntry invasionSpawnEntry : invasion.getInvasionMobs()) {
					invasionEntityClasses.add(invasionSpawnEntry.getEntityClass());
				}
			}

			Collection<Class<? extends Entity>> bothConquestInvasion = new HashSet<>(conquestEntityClasses);
			bothConquestInvasion.retainAll(invasionEntityClasses);

			conquestEntityClasses.removeAll(bothConquestInvasion);
			invasionEntityClasses.removeAll(bothConquestInvasion);

			for (BiomeGenBase.SpawnListEntry entry : spawnListEntries) {
				data.computeIfAbsent(entry.entityClass, s -> new TreeSet<>());
				data.get(entry.entityClass).add(NL + "* " + getBiomeLink(biome) + ';');
			}

			for (Class<? extends Entity> entityClass : conquestEntityClasses) {
				data.computeIfAbsent(entityClass, s -> new TreeSet<>());
				data.get(entityClass).add(NL + "* " + getBiomeLink(biome) + ' ' + Lang.ENTITY_CONQUEST + ';');
			}

			for (Class<? extends Entity> entityClass : invasionEntityClasses) {
				data.computeIfAbsent(entityClass, s -> new TreeSet<>());
				data.get(entityClass).add(NL + "* " + getBiomeLink(biome) + ' ' + Lang.ENTITY_INVASION + ';');
			}

			for (Class<? extends Entity> entityClass : bothConquestInvasion) {
				data.computeIfAbsent(entityClass, s -> new TreeSet<>());
				data.get(entityClass).add(NL + "* " + getBiomeLink(biome) + ' ' + Lang.ENTITY_CONQUEST_INVASION + ';');
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-Biomes");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.ENTITY_HAS_BIOMES, Lang.ENTITY_NO_BIOMES);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityBuyPools() {
		Map<Class<? extends Entity>, Set<String>> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTTradeable) {
				data.put(entityEntry.getKey(), new TreeSet<>());

				GOTTradeable tradeable = (GOTTradeable) entityEntry.getValue();

				for (GOTTradeEntry entry : tradeable.getSellPool().getTradeEntries()) {
					data.get(entityEntry.getKey()).add(NL + "* " + entry.getTradeItem().getDisplayName() + ": {{Coins|" + entry.getCost() + "}};");
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-BuyPools");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.ENTITY_HAS_BUY_POOLS, Lang.ENTITY_NO_BUY_POOLS);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityCharacter() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTEntityNPC && ((GOTEntityNPC) entityEntry.getValue()).isLegendaryNPC()) {
				data.put(entityEntry.getKey(), TRUE);
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-Character");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendDefault(sb, FALSE);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityFaction() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTEntityNPC) {
				GOTEntityNPC npc = (GOTEntityNPC) entityEntry.getValue();
				data.put(entityEntry.getKey(), getFactionLink(npc.getFaction()));
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-Faction");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityFarmhand() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTFarmhand) {
				data.put(entityEntry.getKey(), TRUE);
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-Farmhand");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendDefault(sb, FALSE);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityHealth() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			EntityLivingBase entity = (EntityLivingBase) entityEntry.getValue();
			data.put(entityEntry.getKey(), String.valueOf((int) entity.getMaxHealth()));
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-Health");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityHireable() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (GOTUnitTradeEntries entries : UNIT_TRADE_ENTRIES) {
			for (GOTUnitTradeEntry entry : entries.getTradeEntries()) {
				data.put(entry.getEntityClass(), TRUE);
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-Hireable");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendDefault(sb, FALSE);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityHireAlignment() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (GOTUnitTradeEntries entries : UNIT_TRADE_ENTRIES) {
			for (GOTUnitTradeEntry entry : entries.getTradeEntries()) {
				int alignment = (int) entry.getAlignmentRequired();

				if (entry.getPledgeType() == GOTUnitTradeEntry.PledgeType.NONE) {
					data.put(entry.getEntityClass(), "+" + alignment);
				} else {
					data.put(entry.getEntityClass(), "+" + Math.max(alignment, 100));
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-HireAlignment");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityHirePrice() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (GOTUnitTradeEntries entries : UNIT_TRADE_ENTRIES) {
			for (GOTUnitTradeEntry entry : entries.getTradeEntries()) {
				int cost = entry.getInitialCost();

				if (entry.getPledgeType() == GOTUnitTradeEntry.PledgeType.NONE) {
					data.put(entry.getEntityClass(), "{{Coins|" + cost * 2 + "}}");
				} else {
					data.put(entry.getEntityClass(), N_A);
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-HirePrice");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityHirePricePledge() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (GOTUnitTradeEntries entries : UNIT_TRADE_ENTRIES) {
			for (GOTUnitTradeEntry entry : entries.getTradeEntries()) {
				int cost = entry.getInitialCost();

				if (entry.getPledgeType() == GOTUnitTradeEntry.PledgeType.NONE) {
					data.put(entry.getEntityClass(), "{{Coins|" + cost + "}}");
				} else {
					data.put(entry.getEntityClass(), N_A);
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-HirePricePledge");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityImmuneToFire() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue().isImmuneToFire()) {
				data.put(entityEntry.getKey(), TRUE);
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-ImmuneToFire");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendDefault(sb, FALSE);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityImmuneToFrost() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTEntityNPC && entityEntry.getValue() instanceof GOTBiome.ImmuneToFrost) {
				data.put(entityEntry.getKey(), TRUE);
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-ImmuneToFrost");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendDefault(sb, FALSE);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityImmuneToHeat() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTBiome.ImmuneToHeat) {
				data.put(entityEntry.getKey(), TRUE);
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-ImmuneToHeat");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendDefault(sb, FALSE);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityKillAchievement() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTEntityNPC) {
				GOTEntityNPC npc = (GOTEntityNPC) entityEntry.getValue();
				if (npc.getKillAchievement() != null) {
					data.put(entityEntry.getKey(), '«' + npc.getKillAchievement().getTitle() + '»');
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-KillAchievement");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendDefault(sb, N_A);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityKillAlignment() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTEntityNPC) {
				GOTEntityNPC npc = (GOTEntityNPC) entityEntry.getValue();
				data.put(entityEntry.getKey(), "+" + (int) npc.getAlignmentBonus());
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-KillAlignment");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityLegendaryDrop() {
		Map<Class<? extends Entity>, Set<String>> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTEntityNPC && ((GOTEntityNPC) entityEntry.getValue()).isLegendaryNPC()) {
				data.put(entityEntry.getKey(), new TreeSet<>());

				GOTEntityNPC npc = (GOTEntityNPC) entityEntry.getValue();

				npc.dropFewItems(true, 999);

				for (Object obj : npc.getDrops()) {
					if (obj instanceof Item) {
						ItemStack itemStack = new ItemStack((Item) obj);
						data.get(entityEntry.getKey()).add(NL + "* " + itemStack.getDisplayName() + ';');
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-LegendaryDrop");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.ENTITY_HAS_LEGENDARY_DROP, Lang.ENTITY_NO_LEGENDARY_DROP);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityMarriage() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTEntityNPC && GOTEntityUtils.canBeMarried((GOTEntityNPC) entityEntry.getValue())) {
				data.put(entityEntry.getKey(), TRUE);
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-Marriage");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendDefault(sb, FALSE);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityMercenary() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTMercenary) {
				data.put(entityEntry.getKey(), TRUE);
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-Mercenary");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendDefault(sb, FALSE);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityOwners() {
		Map<Class<? extends Entity>, Set<String>> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTUnitTradeable) {
				GOTUnitTradeable tradeable = (GOTUnitTradeable) entityEntry.getValue();
				for (GOTUnitTradeEntry entry : tradeable.getUnits().getTradeEntries()) {
					data.computeIfAbsent(entry.getEntityClass(), s -> new TreeSet<>());
					data.get(entry.getEntityClass()).add(NL + "* " + getEntityLink(entityEntry.getKey()) + ';');
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append("DB Entity-Owners");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.ENTITY_HAS_OWNERS, Lang.ENTITY_NO_OWNERS);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityRideableAnimal() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTNPCMount) {
				data.put(entityEntry.getKey(), TRUE);
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-RideableAnimal");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendDefault(sb, FALSE);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityRideableNPC() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTEntitySpiderBase) {
				data.put(entityEntry.getKey(), TRUE);
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append(" DB Entity-RideableNPC");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendDefault(sb, FALSE);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntitySellPools() {
		Map<Class<? extends Entity>, Set<String>> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTTradeable) {
				data.put(entityEntry.getKey(), new TreeSet<>());

				GOTTradeable tradeable = (GOTTradeable) entityEntry.getValue();

				for (GOTTradeEntry entry : tradeable.getBuyPool().getTradeEntries()) {
					data.get(entityEntry.getKey()).add(NL + "* " + entry.getTradeItem().getDisplayName() + ": {{Coins|" + entry.getCost() + "}};");
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-SellPools");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.ENTITY_HAS_SELL_UNIT_POOLS, Lang.ENTITY_NO_SELL_UNIT_POOLS);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntitySellUnitPools() {
		Map<Class<? extends Entity>, List<String>> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTUnitTradeable) {
				data.put(entityEntry.getKey(), new ArrayList<>());

				GOTUnitTradeable tradeable = (GOTUnitTradeable) entityEntry.getValue();

				for (GOTUnitTradeEntry entry : tradeable.getUnits().getTradeEntries()) {
					StringBuilder sb = new StringBuilder();

					sb.append(NL).append("* ").append(getEntityLink(entry.getEntityClass()));
					if (entry.getMountClass() != null) {
						sb.append(Lang.RIDER);
					}
					sb.append(": ");

					int cost = entry.getInitialCost();
					int alignment = (int) entry.getAlignmentRequired();

					if (entry.getPledgeType() == GOTUnitTradeEntry.PledgeType.NONE) {
						sb.append("{{Coins|").append(cost * 2).append("}} ").append(Lang.NO_PLEDGE).append(", ");
						sb.append("{{Coins|").append(cost).append("}} ").append(Lang.NEED_PLEDGE).append("; ");
						sb.append('+').append(alignment).append(' ').append(Lang.REPUTATION).append(';');
					} else {
						sb.append("N/A ").append(Lang.NO_PLEDGE).append(", ");
						sb.append("{{Coins|").append(cost).append("}} ").append(Lang.NEED_PLEDGE).append("; ");
						sb.append('+').append(Math.max(alignment, 100)).append(' ').append(Lang.REPUTATION).append(';');
					}

					data.get(entityEntry.getKey()).add(sb.toString());
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-SellUnitPools");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, List<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.ENTITY_HAS_SELL_UNIT_POOLS, Lang.ENTITY_NO_SELL_UNIT_POOLS);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntitySmith() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTTradeable.Smith) {
				data.put(entityEntry.getKey(), TRUE);
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-Smith");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendDefault(sb, FALSE);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntitySpawnsInDarkness() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTEntityNPC && ((GOTEntityNPC) entityEntry.getValue()).isSpawnsInDarkness()) {
				data.put(entityEntry.getKey(), TRUE);
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-SpawnsInDarkness");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendDefault(sb, FALSE);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityStructures() {
		StringBuilder sb = new StringBuilder();

		Map<Class<? extends Entity>, Set<String>> data = new HashMap<>();

		for (Map.Entry<Class<? extends WorldGenerator>, GOTStructureBase> structureEntry : STRUCTURE_CLASS_TO_STRUCTURE.entrySet()) {
			Class<? extends WorldGenerator> structureClass = structureEntry.getKey();
			GOTStructureBase structure = structureEntry.getValue();

			if (structure != null) {
				Set<Class<? extends Entity>> entityClasses = structure.getEntityClasses();
				for (Class<? extends Entity> entityClass : entityClasses) {
					data.computeIfAbsent(entityClass, s -> new TreeSet<>());
					data.get(entityClass).add(NL + "* " + getStructureLink(structureClass) + ';');
				}
			}
		}

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-Structures");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.ENTITY_HAS_STRUCTURES, Lang.ENTITY_NO_STRUCTURES);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityTargetSeeker() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTEntityNPC && ((GOTEntityNPC) entityEntry.getValue()).isTargetSeeker()) {
				data.put(entityEntry.getKey(), TRUE);
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-TargetSeeker");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendDefault(sb, FALSE);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityTradeable() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTTradeable) {
				data.put(entityEntry.getKey(), TRUE);
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-Tradeable");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendDefault(sb, FALSE);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityUnitTradeable() {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			if (entityEntry.getValue() instanceof GOTUnitTradeable) {
				data.put(entityEntry.getKey(), TRUE);
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-UnitTradeable");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendDefault(sb, FALSE);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateEntityWaypoint(World world) {
		Map<Class<? extends Entity>, String> data = new HashMap<>();

		for (Map.Entry<Class<? extends Entity>, GOTWaypoint> entityEntry : ENTITY_CLASS_TO_WP.entrySet()) {
			data.put(entityEntry.getKey(), entityEntry.getValue().getDisplayName());
		}

		for (Map.Entry<GOTAbstractWaypoint, GOTStructureBaseSettlement> spawnerEntry : GOTFixer.SPAWNERS.entrySet()) {
			GOTStructureBaseSettlement spawner = spawnerEntry.getValue();
			spawner.getLegendaryNPCs(world);
			for (GOTFixer.SpawnInfo info : spawner.getLegendaryNPCs(world)) {
				data.put(info.getNPC().getClass(), spawnerEntry.getKey().getDisplayName());
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Entity-Waypoint");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends Entity>, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getEntityPagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.ENTITY_HAS_WAYPOINT, Lang.ENTITY_NO_WAYPOINT);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateFactionBanners() {
		Map<GOTFaction, Set<String>> data = new EnumMap<>(GOTFaction.class);

		for (GOTFaction faction : FACTIONS) {
			data.put(faction, new TreeSet<>());

			for (GOTItemBanner.BannerType banner : faction.getFactionBanners()) {
				data.get(faction).add(NL + "* " + getBannerName(banner) + ';');
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Faction-Banners");
		sb.append(BEGIN);

		for (Map.Entry<GOTFaction, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getFactionPagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.FACTION_HAS_BANNERS, Lang.FACTION_NO_BANNERS);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateFactionCharacters() {
		Map<GOTFaction, Set<String>> data = new EnumMap<>(GOTFaction.class);

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			Entity entity = entityEntry.getValue();
			if (entity instanceof GOTEntityNPC) {
				GOTEntityNPC npc = (GOTEntityNPC) entity;
				if (npc.isLegendaryNPC()) {
					data.computeIfAbsent(npc.getFaction(), s -> new TreeSet<>());
					data.get(npc.getFaction()).add(NL + "* " + getEntityLink(entityEntry.getKey()) + ';');
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Faction-Characters");
		sb.append(BEGIN);

		for (Map.Entry<GOTFaction, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getFactionPagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.FACTION_HAS_CHARACTERS, Lang.FACTION_NO_CHARACTERS);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateFactionCodename() {
		Map<GOTFaction, String> data = new EnumMap<>(GOTFaction.class);

		for (GOTFaction faction : FACTIONS) {
			data.put(faction, faction.codeName());
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Faction-Codename");
		sb.append(BEGIN);

		for (Map.Entry<GOTFaction, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getFactionPagename(entry.getKey())).append(" = ");

			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateFactionConquestBiomes() {
		Map<GOTFaction, Set<String>> data = new EnumMap<>(GOTFaction.class);

		for (GOTBiome biome : BIOMES) {
			for (GOTFactionContainer factionContainer : biome.getNPCSpawnList().getFactionContainers()) {
				if (factionContainer.getBaseWeight() <= 0) {
					for (GOTSpawnListContainer spawnListContainer : factionContainer.getSpawnListContainers()) {
						for (GOTSpawnEntry spawnEntry : spawnListContainer.getSpawnList().getSpawnEntries()) {
							Entity entity = ENTITY_CLASS_TO_ENTITY.get(spawnEntry.entityClass);
							if (entity instanceof GOTEntityNPC) {
								GOTFaction faction = ((GOTEntityNPC) entity).getFaction();
								data.computeIfAbsent(faction, s -> new TreeSet<>());
								data.get(faction).add(NL + "* " + getBiomeLink(biome) + ';');
								break;
							}
						}
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Faction-ConquestBiomes");
		sb.append(BEGIN);

		for (Map.Entry<GOTFaction, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getFactionPagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.FACTION_HAS_CONQUEST_BIOMES, Lang.FACTION_NO_CONQUEST_BIOMES);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateFactionEnemies() {
		Map<GOTFaction, String> data = new EnumMap<>(GOTFaction.class);

		for (GOTFaction faction : FACTIONS) {
			StringJoiner sj = new StringJoiner(" • ");

			for (GOTFaction otherFaction : FACTIONS) {
				if (faction.isBadRelation(otherFaction) && faction != otherFaction) {
					sj.add(getFactionLink(otherFaction));
				}
			}

			data.put(faction, sj.toString());
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Faction-Enemies");
		sb.append(BEGIN);

		for (Map.Entry<GOTFaction, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getFactionPagename(entry.getKey())).append(" = ");

			appendSection(sb, entry.getValue());
		}
		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateFactionFriends() {
		Map<GOTFaction, String> data = new EnumMap<>(GOTFaction.class);

		for (GOTFaction faction : FACTIONS) {
			StringJoiner sj = new StringJoiner(" • ");

			for (GOTFaction otherFaction : FACTIONS) {
				if (faction.isGoodRelation(otherFaction) && faction != otherFaction) {
					sj.add(getFactionLink(otherFaction));
				}
			}

			data.put(faction, sj.toString());
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Faction-Friends");
		sb.append(BEGIN);

		for (Map.Entry<GOTFaction, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getFactionPagename(entry.getKey())).append(" = ");

			appendSection(sb, entry.getValue());
		}
		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateFactionInvasionBiomes() {
		Map<GOTFaction, Set<String>> data = new EnumMap<>(GOTFaction.class);

		for (GOTBiome biome : BIOMES) {
			for (GOTInvasions invasion : biome.getInvasionSpawns().getRegisteredInvasions()) {
				for (GOTInvasions.InvasionSpawnEntry invasionSpawnEntry : invasion.getInvasionMobs()) {
					Entity entity = ENTITY_CLASS_TO_ENTITY.get(invasionSpawnEntry.getEntityClass());
					if (entity instanceof GOTEntityNPC) {
						GOTFaction faction = ((GOTEntityNPC) entity).getFaction();
						data.computeIfAbsent(faction, s -> new TreeSet<>());
						data.get(faction).add(NL + "* " + getBiomeLink(biome) + ';');
						break;
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Faction-InvasionBiomes");
		sb.append(BEGIN);

		for (Map.Entry<GOTFaction, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getFactionPagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.FACTION_HAS_INVASION_BIOMES, Lang.FACTION_NO_INVASION_BIOMES);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateFactionName() {
		Map<GOTFaction, String> data = new EnumMap<>(GOTFaction.class);

		for (GOTFaction faction : FACTIONS) {
			data.put(faction, getFactionName(faction));
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Faction-Name");
		sb.append(BEGIN);

		for (Map.Entry<GOTFaction, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getFactionPagename(entry.getKey())).append(" = ");

			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateFactionNPCs() {
		Map<GOTFaction, Set<String>> data = new EnumMap<>(GOTFaction.class);

		for (Map.Entry<Class<? extends Entity>, Entity> entityEntry : ENTITY_CLASS_TO_ENTITY.entrySet()) {
			Entity entity = entityEntry.getValue();
			if (entity instanceof GOTEntityNPC) {
				GOTEntityNPC npc = (GOTEntityNPC) entity;
				if (!npc.isLegendaryNPC()) {
					data.computeIfAbsent(npc.getFaction(), s -> new TreeSet<>());
					data.get(npc.getFaction()).add(NL + "* " + getEntityLink(entityEntry.getKey()) + ';');
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Faction-NPCs");
		sb.append(BEGIN);

		for (Map.Entry<GOTFaction, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getFactionPagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.FACTION_HAS_NPCS, Lang.FACTION_NO_NPCS);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateFactionPledgeRank() {
		Map<GOTFaction, String> data = new EnumMap<>(GOTFaction.class);

		for (GOTFaction faction : FACTIONS) {
			GOTFactionRank rank = faction.getPledgeRank();

			if (rank != null) {
				StringBuilder sb = new StringBuilder();

				sb.append(rank.getDisplayName());

				String femRank = rank.getDisplayNameFem();
				if (!femRank.contains("got")) {
					sb.append('/').append(femRank);
				}

				sb.append(" (+").append((int) faction.getPledgeAlignment()).append(')');

				data.put(faction, sb.toString());
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Faction-PledgeRank");
		sb.append(BEGIN);

		for (Map.Entry<GOTFaction, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getFactionPagename(entry.getKey())).append(" = ");

			appendDefault(sb, N_A);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateFactionRanks() {
		Map<GOTFaction, List<String>> data = new EnumMap<>(GOTFaction.class);

		for (GOTFaction faction : FACTIONS) {
			data.put(faction, new ArrayList<>());

			for (GOTFactionRank rank : faction.getRanksSortedDescending()) {
				StringBuilder sb = new StringBuilder();

				sb.append(NL).append("* ").append(rank.getDisplayFullName());

				String femRank = rank.getDisplayFullNameFem();
				if (!femRank.contains("got")) {
					sb.append('/').append(femRank);
				}

				sb.append(" (+").append((int) rank.getAlignment()).append(");");

				data.get(faction).add(sb.toString());
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Faction-Ranks");
		sb.append(BEGIN);

		for (Map.Entry<GOTFaction, List<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getFactionPagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.FACTION_HAS_RANKS, Lang.FACTION_NO_RANKS);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateFactionRegion() {
		Map<GOTFaction, String> data = new EnumMap<>(GOTFaction.class);

		for (GOTFaction faction : FACTIONS) {
			if (faction.getFactionRegion() != null) {
				data.put(faction, faction.getFactionRegion().getRegionName());
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Faction-Region");
		sb.append(BEGIN);

		for (Map.Entry<GOTFaction, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getFactionPagename(entry.getKey())).append(" = ");

			appendDefault(sb, N_A);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateFactionShieldsCapes() {
		Map<GOTFaction, String> data = new EnumMap<>(GOTFaction.class);

		for (GOTFaction faction : FACTIONS) {
			StringBuilder sb = new StringBuilder();

			sb.append(NL).append("&lt;table class=\"wikitable shields-capes\"&gt;");

			for (GOTShields shield : SHIELDS) {
				if (shield.getAlignmentFaction() == faction) {
					sb.append(NL + "&lt;tr&gt;&lt;td&gt;").append(shield.getShieldName()).append("&lt;/td&gt;&lt;td&gt;").append(shield.getShieldDesc()).append("&lt;/td&gt;&lt;td&gt;").append(getShieldFilename(shield)).append("&lt;/td&gt;&lt;/tr&gt;");
				}
			}

			for (GOTCapes cape : CAPES) {
				if (cape.getAlignmentFaction() == faction) {
					sb.append(NL + "&lt;tr&gt;&lt;td&gt;").append(cape.getCapeName()).append("&lt;/td&gt;&lt;td&gt;").append(cape.getCapeDesc()).append("&lt;/td&gt;&lt;td&gt;").append(getCapeFilename(cape)).append("&lt;/td&gt;&lt;/tr&gt;");
				}
			}

			sb.append(NL).append("&lt;table&gt;");

			data.put(faction, sb.toString());
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Faction-ShieldsCapes");
		sb.append(BEGIN);

		for (Map.Entry<GOTFaction, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getFactionPagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.FACTION_HAS_ATTRIBUTES, Lang.FACTION_NO_ATTRIBUTES);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateFactionSpawnBiomes() {
		Map<GOTFaction, Set<String>> data = new EnumMap<>(GOTFaction.class);

		for (GOTBiome biome : BIOMES) {
			for (GOTFactionContainer factionContainer : biome.getNPCSpawnList().getFactionContainers()) {
				if (factionContainer.getBaseWeight() > 0) {
					for (GOTSpawnListContainer spawnListContainer : factionContainer.getSpawnListContainers()) {
						for (GOTSpawnEntry spawnEntry : spawnListContainer.getSpawnList().getSpawnEntries()) {
							Entity entity = ENTITY_CLASS_TO_ENTITY.get(spawnEntry.entityClass);
							if (entity instanceof GOTEntityNPC) {
								GOTFaction faction = ((GOTEntityNPC) entity).getFaction();
								data.computeIfAbsent(faction, s -> new TreeSet<>());
								data.get(faction).add(NL + "* " + getBiomeLink(biome) + ';');
								break;
							}
						}
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Faction-Spawn");
		sb.append(BEGIN);

		for (Map.Entry<GOTFaction, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getFactionPagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.FACTION_HAS_SPAWN_BIOMES, Lang.FACTION_NO_SPAWN_BIOMES);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateFactionStructures() {
		Map<GOTFaction, Set<String>> data = new EnumMap<>(GOTFaction.class);

		for (Map.Entry<Class<? extends WorldGenerator>, GOTFaction> entry : GOTStructureRegistry.CLASS_TO_FACTION_MAPPING.entrySet()) {
			data.computeIfAbsent(entry.getValue(), s -> new TreeSet<>());
			data.get(entry.getValue()).add(NL + "* " + getStructureLink(entry.getKey()) + ';');
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Faction-Structures");
		sb.append(BEGIN);

		for (Map.Entry<GOTFaction, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getFactionPagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.FACTION_HAS_STRUCTURES, Lang.FACTION_NO_STRUCTURES);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateFactionWarCrimes() {
		Map<GOTFaction, String> data = new EnumMap<>(GOTFaction.class);

		for (GOTFaction faction : FACTIONS) {
			if (!faction.isApprovesWarCrimes()) {
				data.put(faction, Lang.FACTION_NO_WAR_CRIMES.toString());
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Faction-WarCrimes");
		sb.append(BEGIN);

		for (Map.Entry<GOTFaction, String> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getFactionPagename(entry.getKey())).append(" = ");

			appendDefault(sb, Lang.FACTION_HAS_WAR_CRIMES.toString());
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateFactionWaypoints() {
		Map<GOTFaction, Set<String>> data = new EnumMap<>(GOTFaction.class);

		for (GOTWaypoint wp : WAYPOINTS) {
			data.computeIfAbsent(wp.getFaction(), s -> new TreeSet<>());
			data.get(wp.getFaction()).add(NL + "* " + wp.getDisplayName() + ';');
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Faction-Waypoints");
		sb.append(BEGIN);

		for (Map.Entry<GOTFaction, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getFactionPagename(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.FACTION_HAS_WAYPOINTS, Lang.FACTION_NO_WAYPOINTS);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateMineralBiomes() {
		Map<String, Set<String>> data = new HashMap<>();

		for (GOTBiome biome : BIOMES) {
			Collection<GOTBiomeDecorator.OreGenerant> oreGenerants = new HashSet<>(biome.getDecorator().getBiomeSoils());
			oreGenerants.addAll(biome.getDecorator().getBiomeOres());
			oreGenerants.addAll(biome.getDecorator().getBiomeGems());

			for (GOTBiomeDecorator.OreGenerant oreGenerant : oreGenerants) {
				Block block = GOTReflection.getOreGenBlock(oreGenerant.getOreGen());
				int meta = GOTReflection.getOreGenMeta(oreGenerant.getOreGen());

				String blockName;
				if (block instanceof GOTBlockOreGem || block instanceof BlockDirt || block instanceof GOTBlockRock) {
					blockName = getBlockMetaName(block, meta);
				} else {
					blockName = getBlockName(block);
				}

				String stats = " (" + oreGenerant.getOreChance() + "%; Y: " + oreGenerant.getMinHeight() + '-' + oreGenerant.getMaxHeight() + ");";

				data.computeIfAbsent(blockName, s -> new TreeSet<>());
				data.get(blockName).add(NL + "* " + getBiomeLink(biome) + stats);
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Mineral-Biomes");
		sb.append(BEGIN);

		for (Map.Entry<String, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(entry.getKey()).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.MINERAL_HAS_BIOMES, Lang.MINERAL_NO_BIOMES);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateStructureBiomes() {
		Map<Class<? extends WorldGenerator>, Set<String>> data = new HashMap<>();

		for (GOTBiome biome : BIOMES) {
			for (GOTBiomeDecorator.Structure structure : biome.getDecorator().getStructures()) {
				data.computeIfAbsent(structure.getStructureGen().getClass(), s -> new TreeSet<>());
				data.get(structure.getStructureGen().getClass()).add(NL + "* " + getBiomeLink(biome) + ';');
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Structure-Biomes");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends WorldGenerator>, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getStructureName(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.STRUCTURE_HAS_BIOMES, Lang.STRUCTURE_NO_BIOMES);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateStructureEntities() {
		StringBuilder sb = new StringBuilder();

		Map<Class<? extends WorldGenerator>, Set<String>> data = new HashMap<>();

		for (Map.Entry<Class<? extends WorldGenerator>, GOTStructureBase> structureEntry : STRUCTURE_CLASS_TO_STRUCTURE.entrySet()) {
			Class<? extends WorldGenerator> structureClass = structureEntry.getKey();
			GOTStructureBase structure = structureEntry.getValue();

			if (structure != null) {
				data.put(structureClass, new TreeSet<>());

				Set<Class<? extends Entity>> entityClasses = structure.getEntityClasses();
				for (Class<? extends Entity> entityClass : entityClasses) {
					data.get(structureClass).add(NL + "* " + getEntityLink(entityClass) + ';');
				}
			}
		}

		sb.append(TITLE).append(TEMPLATE).append("DB Structure-Entities");
		sb.append(BEGIN);

		for (Map.Entry<Class<? extends WorldGenerator>, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getStructureName(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.STRUCTURE_HAS_ENTITIES, Lang.STRUCTURE_NO_ENTITIES);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder genTemplateTreeBiomes() {
		Map<GOTTreeType, Set<String>> data = new EnumMap<>(GOTTreeType.class);

		for (GOTBiome biome : BIOMES) {
			Collection<GOTTreeType.WeightedTreeType> weightedTreeTypes = biome.getDecorator().getTreeTypes();

			Collection<GOTTreeType> excludedTreeTypes = EnumSet.noneOf(GOTTreeType.class);

			for (GOTTreeType.WeightedTreeType weightedTreeType : weightedTreeTypes) {
				GOTTreeType treeType = weightedTreeType.getTreeType();

				data.computeIfAbsent(treeType, s -> new TreeSet<>());
				data.get(treeType).add(NL + "* " + getBiomeLink(biome) + ';');

				excludedTreeTypes.add(treeType);
			}

			for (GOTBiomeVariantList.VariantBucket variantBucket : biome.getBiomeVariants().getVariantList()) {
				for (GOTTreeType.WeightedTreeType weightedTreeType : variantBucket.getVariant().getTreeTypes()) {
					GOTTreeType treeType = weightedTreeType.getTreeType();

					if (!excludedTreeTypes.contains(treeType)) {
						data.computeIfAbsent(treeType, s -> new TreeSet<>());
						data.get(treeType).add(NL + "* " + getBiomeLink(biome) + " (" + getBiomeVariantName(variantBucket.getVariant()) + ");");
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(TITLE).append(TEMPLATE).append("DB Tree-Biomes");
		sb.append(BEGIN);

		for (Map.Entry<GOTTreeType, Set<String>> entry : data.entrySet()) {
			sb.append(NL).append("| ");
			sb.append(getTreeName(entry.getKey())).append(" = ");

			appendPreamble(sb, entry.getValue(), Lang.TREE_HAS_BIOMES, Lang.TREE_NO_BIOMES);
			appendSection(sb, entry.getValue());
		}

		sb.append(END);

		return sb;
	}

	private static StringBuilder addPagesBiomes(Collection<String> neededPages, Collection<String> existingPages) {
		StringBuilder sb = new StringBuilder();

		for (GOTBiome biome : BIOMES) {
			String pageName = getBiomePagename(biome);
			neededPages.add(pageName);
			if (!existingPages.contains(pageName)) {
				sb.append(TITLE_SINGLE).append(pageName);
				sb.append(PAGE_LEFT).append("{{Статья Биом}}").append(PAGE_RIGHT);
			}
		}

		return sb;
	}

	private static StringBuilder addPagesEntities(Collection<String> neededPages, Collection<String> existingPages) {
		StringBuilder sb = new StringBuilder();

		for (Class<? extends Entity> entityClass : ENTITY_CLASSES) {
			String pageName = getEntityPagename(entityClass);
			neededPages.add(pageName);
			if (!existingPages.contains(pageName)) {
				sb.append(TITLE_SINGLE).append(pageName);
				sb.append(PAGE_LEFT).append("{{Статья Моб}}").append(PAGE_RIGHT);
			}
		}

		return sb;
	}

	private static StringBuilder addPagesFactions(Collection<String> neededPages, Collection<String> existingPages) {
		StringBuilder sb = new StringBuilder();

		for (GOTFaction faction : FACTIONS) {
			String pageName = getFactionPagename(faction);
			neededPages.add(pageName);
			if (!existingPages.contains(pageName)) {
				sb.append(TITLE_SINGLE).append(pageName);
				sb.append(PAGE_LEFT).append("{{Статья Фракция}}").append(PAGE_RIGHT);
			}
		}

		return sb;
	}

	private static StringBuilder addPagesMinerals(Collection<String> neededPages, Collection<String> existingPages) {
		StringBuilder sb = new StringBuilder();

		for (String pageName : MINERALS) {
			neededPages.add(pageName);
			if (!existingPages.contains(pageName)) {
				sb.append(TITLE_SINGLE).append(pageName);
				sb.append(PAGE_LEFT).append("{{Статья Ископаемое}}").append(PAGE_RIGHT);
			}
		}

		return sb;
	}

	private static StringBuilder addPagesStructures(Collection<String> neededPages, Collection<String> existingPages) {
		StringBuilder sb = new StringBuilder();

		for (Class<? extends WorldGenerator> strClass : STRUCTURE_CLASSES) {
			String pageName = getStructureName(strClass);
			neededPages.add(pageName);
			if (!existingPages.contains(pageName)) {
				sb.append(TITLE_SINGLE).append(pageName);
				sb.append(PAGE_LEFT).append("{{Статья Структура}}").append(PAGE_RIGHT);
			}
		}

		return sb;
	}

	private static StringBuilder addPagesTrees(Collection<String> neededPages, Collection<String> existingPages) {
		StringBuilder sb = new StringBuilder();

		for (GOTTreeType tree : TREES) {
			String pageName = getTreeName(tree);
			neededPages.add(pageName);
			if (!existingPages.contains(pageName)) {
				sb.append(TITLE_SINGLE).append(pageName);
				sb.append(PAGE_LEFT).append("{{Статья Дерево}}").append(PAGE_RIGHT);
			}
		}

		return sb;
	}

	private static Set<String> getExistingPages() {
		try {
			File file = new File("hummel/sitemap.txt");
			if (!file.exists()) {
				boolean created = file.createNewFile();
				if (!created) {
					GOTLog.getLogger().info("DatabaseGenerator: file wasn't created");
				}
			}
			try (Stream<String> lines = Files.lines(Paths.get("hummel/sitemap.txt"))) {
				return lines.collect(Collectors.toSet());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Collections.emptySet();
	}

	private static void markPagesForRemoval(Collection<String> neededPages, Iterable<String> existingPages) {
		try (PrintWriter printWriter = new PrintWriter("hummel/removal.txt", UTF_8)) {
			StringBuilder sb = new StringBuilder();

			for (String existing : existingPages) {
				if (!neededPages.contains(existing)) {
					sb.append(existing).append('\n');
				}
			}
			printWriter.write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void searchForEntities(World world) {
		for (Class<? extends Entity> entityClass : ENTITY_CLASSES) {
			ENTITY_CLASS_TO_ENTITY.put(entityClass, GOTReflection.newEntity(entityClass, world));
		}
	}

	private static void searchForStructures(World world) {
		for (Class<? extends WorldGenerator> structureClass : STRUCTURE_CLASSES) {
			WorldGenerator generator = null;
			try {
				generator = structureClass.getConstructor(Boolean.TYPE).newInstance(true);
			} catch (Exception ignored) {
			}

			if (generator instanceof GOTStructureBase) {
				GOTStructureBase structure = (GOTStructureBase) generator;
				structure.setRestrictions(false);
				structure.setWikiGen(true);
				structure.generate(world, world.rand, 0, 0, 0, 0);

				STRUCTURE_CLASS_TO_STRUCTURE.put(structureClass, structure);
			}
		}
	}

	private static void searchForMinerals() {
		for (GOTBiome biome : BIOMES) {
			Collection<GOTBiomeDecorator.OreGenerant> oreGenerants = new HashSet<>(biome.getDecorator().getBiomeSoils());
			oreGenerants.addAll(biome.getDecorator().getBiomeOres());
			oreGenerants.addAll(biome.getDecorator().getBiomeGems());
			for (GOTBiomeDecorator.OreGenerant oreGenerant : oreGenerants) {
				WorldGenMinable gen = oreGenerant.getOreGen();
				Block block = GOTReflection.getOreGenBlock(gen);
				int meta = GOTReflection.getOreGenMeta(gen);
				if (block instanceof GOTBlockOreGem || block instanceof BlockDirt || block instanceof GOTBlockRock) {
					MINERALS.add(getBlockMetaName(block, meta));
				} else {
					MINERALS.add(getBlockName(block));
				}
			}
		}
	}

	private static void searchForPagenamesBiome() {
		next:
		for (GOTBiome biome : BIOMES) {
			String preName = getBiomeName(biome);
			for (GOTFaction faction : FACTIONS) {
				if (preName.equals(getFactionName(faction))) {
					BIOME_TO_PAGENAME.put(preName, preName + " (" + Lang.PAGE_BIOME + ')');
					continue next;
				}
			}
			for (Class<? extends Entity> entityClass : ENTITY_CLASSES) {
				if (preName.equals(getEntityName(entityClass))) {
					BIOME_TO_PAGENAME.put(preName, preName + " (" + Lang.PAGE_BIOME + ')');
					continue next;
				}
			}
			BIOME_TO_PAGENAME.put(preName, preName);
		}
	}

	private static void searchForPagenamesEntity() {
		next:
		for (Class<? extends Entity> entityClass : ENTITY_CLASSES) {
			String preName = getEntityName(entityClass);
			for (GOTBiome biome : BIOMES) {
				if (preName.equals(getBiomeName(biome))) {
					ENTITY_TO_PAGENAME.put(preName, preName + " (" + Lang.PAGE_ENTITY + ')');
					continue next;
				}
			}
			for (GOTFaction faction : FACTIONS) {
				if (preName.equals(getFactionName(faction))) {
					ENTITY_TO_PAGENAME.put(preName, preName + " (" + Lang.PAGE_ENTITY + ')');
					continue next;
				}
			}
			ENTITY_TO_PAGENAME.put(preName, preName);
		}
	}

	private static void searchForPagenamesFaction() {
		next:
		for (GOTFaction faction : FACTIONS) {
			String preName = getFactionName(faction);
			for (GOTBiome biome : BIOMES) {
				if (preName.equals(getBiomeName(biome))) {
					FACTION_TO_PAGENAME.put(preName, preName + " (" + Lang.PAGE_FACTION + ')');
					continue next;
				}
			}
			for (Class<? extends Entity> entityClass : ENTITY_CLASSES) {
				if (preName.equals(getEntityName(entityClass))) {
					FACTION_TO_PAGENAME.put(preName, preName + " (" + Lang.PAGE_FACTION + ')');
					continue next;
				}
			}
			FACTION_TO_PAGENAME.put(preName, preName);
		}
	}

	public enum Lang {
		BIOME_HAS_ANIMALS, BIOME_HAS_CONQUEST, BIOME_HAS_INVASION, BIOME_HAS_MINERALS, BIOME_HAS_NPCS, BIOME_HAS_STRUCTURES, BIOME_HAS_TREES, BIOME_HAS_VARIANTS, BIOME_HAS_WAYPOINTS, BIOME_NO_ANIMALS, BIOME_NO_CONQUEST, BIOME_NO_INVASION, BIOME_NO_MINERALS, BIOME_NO_NPCS, BIOME_NO_STRUCTURES, BIOME_NO_TREES, BIOME_NO_VARIANTS, BIOME_NO_WAYPOINTS, CATEGORY, CLIMATE_COLD, CLIMATE_COLD_AZ, CLIMATE_NORMAL, CLIMATE_NORMAL_AZ, CLIMATE_NULL, CLIMATE_SUMMER, CLIMATE_SUMMER_AZ, CLIMATE_WINTER, ENTITY_CONQUEST, ENTITY_CONQUEST_INVASION, ENTITY_HAS_BIOMES, ENTITY_HAS_BUY_POOLS, ENTITY_HAS_LEGENDARY_DROP, ENTITY_HAS_OWNERS, ENTITY_HAS_SELL_UNIT_POOLS, ENTITY_HAS_STRUCTURES, ENTITY_HAS_WAYPOINT, ENTITY_INVASION, ENTITY_NO_BIOMES, ENTITY_NO_BUY_POOLS, ENTITY_NO_LEGENDARY_DROP, ENTITY_NO_OWNERS, ENTITY_NO_SELL_UNIT_POOLS, ENTITY_NO_STRUCTURES, ENTITY_NO_WAYPOINT, FACTION_HAS_ATTRIBUTES, FACTION_HAS_BANNERS, FACTION_HAS_CHARACTERS, FACTION_HAS_CONQUEST_BIOMES, FACTION_HAS_INVASION_BIOMES, FACTION_HAS_NPCS, FACTION_HAS_RANKS, FACTION_HAS_SPAWN_BIOMES, FACTION_HAS_STRUCTURES, FACTION_HAS_WAR_CRIMES, FACTION_HAS_WAYPOINTS, FACTION_NO_ATTRIBUTES, FACTION_NO_BANNERS, FACTION_NO_CHARACTERS, FACTION_NO_CONQUEST_BIOMES, FACTION_NO_INVASION_BIOMES, FACTION_NO_NPCS, FACTION_NO_RANKS, FACTION_NO_SPAWN_BIOMES, FACTION_NO_STRUCTURES, FACTION_NO_WAR_CRIMES, FACTION_NO_WAYPOINTS, MINERAL_HAS_BIOMES, MINERAL_NO_BIOMES, NEED_PLEDGE, NO_PLEDGE, PAGE_BIOME, PAGE_ENTITY, PAGE_FACTION, REPUTATION, RIDER, SEASON_AUTUMN, SEASON_SPRING, SEASON_SUMMER, SEASON_WINTER, STRUCTURE_HAS_BIOMES, STRUCTURE_HAS_ENTITIES, STRUCTURE_NO_BIOMES, STRUCTURE_NO_ENTITIES, TREE_HAS_BIOMES, TREE_NO_BIOMES;

		@Override
		public String toString() {
			return StatCollector.translateToLocal("got.db." + name() + ".name");
		}
	}

	public enum Type {
		XML("xml"), TABLES("tables");

		private final String codeName;

		Type(String name) {
			codeName = name;
		}

		public static Type forName(String name) {
			for (Type db : values()) {
				if (db.codeName.equals(name)) {
					return db;
				}
			}
			return null;
		}

		public static Set<String> getNames() {
			Set<String> names = new HashSet<>();
			for (Type db : values()) {
				names.add(db.codeName);
			}
			return names;
		}
	}

	private static String getBannerName(GOTItemBanner.BannerType banner) {
		return StatCollector.translateToLocal("item.got:banner." + banner.getBannerName() + ".name");
	}

	private static String getBiomeLink(GOTBiome biome) {
		String biomeName = getBiomeName(biome);
		String biomePagename = getBiomePagename(biome);
		if (biomeName.equals(biomePagename)) {
			return "[[" + biomeName + "]]";
		}
		return "[[" + biomePagename + '|' + biomeName + "]]";
	}

	private static String getBiomeName(GOTBiome biome) {
		return StatCollector.translateToLocal("got.biome." + biome.biomeName + ".name");
	}

	private static String getBiomePagename(GOTBiome biome) {
		return BIOME_TO_PAGENAME.get(getBiomeName(biome));
	}

	private static String getBiomeVariantName(GOTBiomeVariant variant) {
		return StatCollector.translateToLocal("got.variant." + variant.getVariantName() + ".name");
	}

	private static String getBlockMetaName(Block block, int meta) {
		return StatCollector.translateToLocal(block.getUnlocalizedName() + '.' + meta + ".name");
	}

	private static String getBlockName(Block block) {
		return StatCollector.translateToLocal(block.getUnlocalizedName() + ".name");
	}

	private static String getBlockLink(String name) {
		return "[[" + name + "]]";
	}

	private static String getCapeFilename(GOTCapes cape) {
		return "[[File:Cape " + cape.name().toLowerCase(Locale.ROOT) + ".png]]";
	}

	private static String getEntityLink(Class<? extends Entity> entityClass) {
		if (!GOTEntityRegistry.CLASS_TO_NAME_MAPPING.containsKey(entityClass)) {
			return getEntityVanillaName(entityClass);
		}
		String entityName = getEntityName(entityClass);
		String entityPagename = getEntityPagename(entityClass);
		if (entityName.equals(entityPagename)) {
			return "[[" + entityPagename + "]]";
		}
		return "[[" + entityPagename + '|' + entityName + "]]";
	}

	private static String getEntityName(Class<? extends Entity> entityClass) {
		return StatCollector.translateToLocal("entity.got." + GOTEntityRegistry.CLASS_TO_NAME_MAPPING.get(entityClass) + ".name");
	}

	private static String getEntityVanillaName(Class<? extends Entity> entityClass) {
		return StatCollector.translateToLocal("entity." + EntityList.classToStringMapping.get(entityClass) + ".name");
	}

	private static String getEntityPagename(Class<? extends Entity> entityClass) {
		return ENTITY_TO_PAGENAME.get(getEntityName(entityClass));
	}

	private static String getFactionLink(GOTFaction fac) {
		String facName = getFactionName(fac);
		String facPagename = getFactionPagename(fac);
		if (facName.equals(facPagename)) {
			return "[[" + facPagename + "]]";
		}
		return "[[" + facPagename + '|' + facName + "]]";
	}

	private static String getFactionName(GOTFaction fac) {
		return StatCollector.translateToLocal("got.faction." + fac.codeName() + ".name");
	}

	private static String getFactionPagename(GOTFaction fac) {
		return FACTION_TO_PAGENAME.get(getFactionName(fac));
	}

	private static String getItemFilename(Item item) {
		return "[[File:" + item.getUnlocalizedName().substring("item.got:".length()) + ".png|32px|link=]]";
	}

	private static String getItemName(Item item) {
		return StatCollector.translateToLocal(item.getUnlocalizedName() + ".name");
	}

	private static String getShieldFilename(GOTShields shield) {
		return "[[File:Shield " + shield.name().toLowerCase(Locale.ROOT) + ".png]]";
	}

	private static String getStructureLink(Class<? extends WorldGenerator> structureClass) {
		return "[[" + StatCollector.translateToLocal("got.structure." + GOTStructureRegistry.CLASS_TO_NAME_MAPPING.get(structureClass) + ".name") + "]]";
	}

	private static String getStructureName(Class<? extends WorldGenerator> structureClass) {
		return StatCollector.translateToLocal("got.structure." + GOTStructureRegistry.CLASS_TO_NAME_MAPPING.get(structureClass) + ".name");
	}

	private static String getSettlementName(String nameAlias) {
		return StatCollector.translateToLocal("got.structure." + nameAlias + ".name");
	}

	private static Set<String> getSettlementNames(Class<? extends GOTStructureBaseSettlement> clazz) {
		Set<String> names = GOTStructureRegistry.S_CLASS_TO_NAME_MAPPING.get(clazz);
		if (names == null) {
			return Collections.emptySet();
		}
		return GOTStructureRegistry.S_CLASS_TO_NAME_MAPPING.get(clazz);
	}

	private static String getTreeName(GOTTreeType tree) {
		return StatCollector.translateToLocal("got.tree." + tree.name().toLowerCase(Locale.ROOT) + ".name");
	}

	private static String getTreeLink(GOTTreeType tree) {
		return "[[" + StatCollector.translateToLocal("got.tree." + tree.name().toLowerCase(Locale.ROOT) + ".name") + "]]";
	}

	private static void appendPreamble(StringBuilder sb, Collection<String> section, Lang full, Lang empty) {
		sb.append(section.isEmpty() ? empty : full);
	}

	private static void appendPreamble(StringBuilder sb, String value, Lang full, Lang empty) {
		sb.append(value.isEmpty() ? empty : full);
	}

	private static void appendSection(StringBuilder sb, Collection<String> section) {
		for (String item : section) {
			sb.append(item);
		}

		section.clear();
	}

	private static void appendSection(StringBuilder sb, String value) {
		sb.append(value);
	}

	private static void appendDefault(StringBuilder sb, String value) {
		sb.append("#default = ").append(value).append(NL);
		sb.append(value);
	}
}