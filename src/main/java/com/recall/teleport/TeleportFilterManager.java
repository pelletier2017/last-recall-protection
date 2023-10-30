package com.recall.teleport;

import com.recall.teleport.filter.TeleportItem;
import com.recall.teleport.filter.TeleportSpell;
import com.recall.teleport.filter.TeleportFilter;
import com.recall.teleport.filter.TeleportObject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ItemID;
import net.runelite.api.MenuEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class TeleportFilterManager {

    private List<TeleportFilter> itemFilters;

    private List<TeleportFilter> spellFilters;

    private List<TeleportFilter> objectFilters;

    public TeleportFilterManager() {
        // TODO add mory legs, kandarin headgear, scepter, karamja gloves, frem boots, wildy sword,
        //  master spellbook, seed pod, burning amulet, chronacle, drakens, ring of shadows
        //  house tabs, master book teleport scrolls
        // todo do with/without easy teleports plugin AND in inventory and equipped

        // house tabs, redirect house tabs
        // skill capes
        // combat bracelet
        // skills necklace
        // ring of wealth
        // ring of returning
        // digsite pendant
        // eternal slayer ring
        // amulet of the eye
        // ring of the elements
        // clue reward teleports
        // go through list of other capes (fishing, max, etc?)

        itemFilters = List.of(
                // capes
                new TeleportItem(
                        "farming cape",
                        List.of(
                                ItemID.FARMING_CAPE,
                                ItemID.FARMING_CAPET
                        ),
                        List.of("Teleport")),
                new TeleportItem(
                        "crafting cape",
                        List.of(
                                ItemID.CRAFTING_CAPE,
                                ItemID.CRAFTING_CAPET
                        ),
                        List.of("Teleport")),
                new TeleportItem(
                        "quest point cape",
                        List.of(
                                ItemID.QUEST_POINT_CAPE,
                                ItemID.QUEST_POINT_CAPE_T
                        ),
                        List.of("Teleport")),
                new TeleportItem(
                        "achievement diary cape",
                        List.of(
                                ItemID.ACHIEVEMENT_DIARY_CAPE,
                                ItemID.ACHIEVEMENT_DIARY_CAPE_T
                        ),
                        List.of("Teleport")),
                new TeleportItem(
                        "music cape",
                        List.of(
                                ItemID.MUSIC_CAPE,
                                ItemID.MUSIC_CAPET
                        ),
                        List.of("Teleport")),
                new TeleportItem(
                        "mythical cape",
                        List.of(
                                ItemID.MYTHICAL_CAPE,
                                ItemID.MYTHICAL_CAPE_22114
                        ),
                        List.of("Teleport")),

                // chargeable items
                new TeleportItem(
                        "ring of the elements",
                        List.of(
                                ItemID.RING_OF_THE_ELEMENTS,
                                ItemID.RING_OF_THE_ELEMENTS_26818
                        ),
                        List.of("Rub")),
                new TeleportItem(
                        "enchanted lyre",
                        List.of(
                                ItemID.LYRE,
                                ItemID.ENCHANTED_LYRE,
                                ItemID.ENCHANTED_LYRE1,
                                ItemID.ENCHANTED_LYRE2,
                                ItemID.ENCHANTED_LYRE3,
                                ItemID.ENCHANTED_LYRE4,
                                ItemID.ENCHANTED_LYRE5,
                                ItemID.ENCHANTED_LYREI
                        ),
                        List.of("Play", "Rellekka", "Waterbirth Island", "Neitiznot", "Jatiszo")),
                new TeleportItem(
                        "skull sceptre",
                        List.of(
                                ItemID.SKULL_SCEPTRE,
                                ItemID.SKULL_SCEPTRE_I
                        ),
                        List.of("Invoke")),
                new TeleportItem(
                        "teleport crystal",
                        List.of(
                                ItemID.TELEPORT_CRYSTAL,
                                ItemID.TELEPORT_CRYSTAL_1,
                                ItemID.TELEPORT_CRYSTAL_2,
                                ItemID.TELEPORT_CRYSTAL_3,
                                ItemID.TELEPORT_CRYSTAL_4,
                                ItemID.TELEPORT_CRYSTAL_5,
                                ItemID.ETERNAL_TELEPORT_CRYSTAL
                        ),
                        List.of("Prifddinas", "Lletya")),
                new TeleportItem(
                        "chronicle",
                        List.of(
                                ItemID.CHRONICLE
                        ),
                        List.of("Teleport")),
                new TeleportItem(
                        "pharaoh's sceptre",
                        List.of(
                                ItemID.PHARAOHS_SCEPTRE_9045,
                                ItemID.PHARAOHS_SCEPTRE_9046,
                                ItemID.PHARAOHS_SCEPTRE_9047,
                                ItemID.PHARAOHS_SCEPTRE_9048,
                                ItemID.PHARAOHS_SCEPTRE_9049,
                                ItemID.PHARAOHS_SCEPTRE_9050,
                                ItemID.PHARAOHS_SCEPTRE_9051,
                                ItemID.PHARAOHS_SCEPTRE_13074,
                                ItemID.PHARAOHS_SCEPTRE_13075,
                                ItemID.PHARAOHS_SCEPTRE_13076,
                                ItemID.PHARAOHS_SCEPTRE_13077,
                                ItemID.PHARAOHS_SCEPTRE_13078,
                                ItemID.PHARAOHS_SCEPTRE_16176,
                                ItemID.PHARAOHS_SCEPTRE_21445,
                                ItemID.PHARAOHS_SCEPTRE_21446,
                                ItemID.PHARAOHS_SCEPTRE_UNCHARGED
                        ),
                        List.of("Teleport", "Last-Teleport", "Pyramid Plunder", "Agility Pyramid", "Ancients Pyramid", "Necropolis", "Jalsavrah", "Jaleustrophos",
                                "Jaldraocht", "Jaltevas")),
                new TeleportItem(
                        "master scroll book",
                        List.of(
                                ItemID.MASTER_SCROLL_BOOK,
                                ItemID.MASTER_SCROLL_BOOK_EMPTY
                        ),
                        List.of("Teleport", "Open")),

                // quest teleport items
                new TeleportItem(
                        "book of the dead",
                        List.of(
                                ItemID.BOOK_OF_THE_DEAD,
                                ItemID.KHAREDSTS_MEMOIRS
                        ),
                        List.of("Reminisce", "Hosidius", "Port Piscarilius", "Shayzien", "Lovakengj", "Arceuus")),
                new TeleportItem(
                        "camulet",
                        List.of(
                                ItemID.CAMULET
                        ),
                        List.of("Rub", "Temple", "Surface")),
                new TeleportItem(
                        "ectophial",
                        List.of(
                                ItemID.ECTOPHIAL,
                                ItemID.ECTOPHIAL_4252
                        ),
                        List.of("Empty")),
                new TeleportItem(
                        "xeric's talisman",
                        List.of(
                                ItemID.XERICS_TALISMAN,
                                ItemID.XERICS_TALISMAN_INERT
                        ),
                        // includes "Easy Teleports" plugin
                        List.of("Rub", "Xeric's Lookout", "Xeric's Glade", "Xeric's Inferno", "Xeric's Heart", "Xeric's Honour", "Shazian", "Hosidius", "Lovakengj",
                                "Kourend Castle", "Chambers of Xeric")),
                new TeleportItem(
                        "drakan's medallion",
                        List.of(
                                ItemID.DRAKANS_MEDALLION
                        ),
                        // includes "Easy Teleports" plugin
                        List.of("Ver Sinhaza", "Darkmeyer", "Slepe", "Theatre of Blood", "Vampyre City", "Nightmare")),
                new TeleportItem(
                        "ring of shadows",
                        List.of(
                                ItemID.RING_OF_SHADOWS,
                                ItemID.RING_OF_SHADOWS_UNCHARGED
                        ),
                        List.of("Teleport", "Ancient Vault", "Ghorrock Dungeon", "The Scar", "Lassar Undercity", "The Stranglewood")),
                new TeleportItem(
                        "royal seed pod",
                        List.of(
                                ItemID.ROYAL_SEED_POD
                        ),
                        List.of("Commune")),

                // diary teleports
                new TeleportItem(
                        "kandarin headgear",
                        List.of(
                                ItemID.KANDARIN_HEADGEAR,
                                ItemID.KANDARIN_HEADGEAR_1,
                                ItemID.KANDARIN_HEADGEAR_2,
                                ItemID.KANDARIN_HEADGEAR_3,
                                ItemID.KANDARIN_HEADGEAR_4
                        ),
                        List.of("Teleport")),
                new TeleportItem(
                        "ardougne cloak",
                        List.of(
                                ItemID.ARDOUGNE_CLOAK,
                                ItemID.ARDOUGNE_CLOAK_1,
                                ItemID.ARDOUGNE_CLOAK_2,
                                ItemID.ARDOUGNE_CLOAK_3,
                                ItemID.ARDOUGNE_CLOAK_4
                        ),
                        List.of("Monastery Teleport", "Farm Teleport", "Kandarin Monastery", "Ardougne Farm")),
                new TeleportItem(
                        "karamja gloves",
                        List.of(
                                ItemID.KARAMJA_GLOVES,
                                ItemID.KARAMJA_GLOVES_1,
                                ItemID.KARAMJA_GLOVES_2,
                                ItemID.KARAMJA_GLOVES_3,
                                ItemID.KARAMJA_GLOVES_4
                        ),
                        List.of("Gem Mine", "Duradel")),
                new TeleportItem(
                        "morytania legs",
                        List.of(
                                ItemID.MORYTANIA_LEGS,
                                ItemID.MORYTANIA_LEGS_1,
                                ItemID.MORYTANIA_LEGS_2,
                                ItemID.MORYTANIA_LEGS_3,
                                ItemID.MORYTANIA_LEGS_4
                        ),
                        List.of("Ecto Teleport", "Burgh Teleport", "Burgh de Rott", "Ectofuntus Pit")),
                new TeleportItem(
                        "fremennik sea boots",
                        List.of(
                                ItemID.FREMENNIK_SEA_BOOTS,
                                ItemID.FREMENNIK_SEA_BOOTS_1,
                                ItemID.FREMENNIK_SEA_BOOTS_2,
                                ItemID.FREMENNIK_SEA_BOOTS_3,
                                ItemID.FREMENNIK_SEA_BOOTS_4
                        ),
                        List.of("Teleport")),
                new TeleportItem(
                        "wilderness sword",
                        List.of(
                                ItemID.WILDERNESS_SWORD,
                                ItemID.WILDERNESS_SWORD_1,
                                ItemID.WILDERNESS_SWORD_2,
                                ItemID.WILDERNESS_SWORD_3,
                                ItemID.WILDERNESS_SWORD_4
                        ),
                        List.of("Teleport")),
                new TeleportItem(
                        "rada's blessing",
                        List.of(
                                ItemID.RADAS_BLESSING,
                                ItemID.RADAS_BLESSING_1,
                                ItemID.RADAS_BLESSING_2,
                                ItemID.RADAS_BLESSING_3,
                                ItemID.RADAS_BLESSING_4
                        ),
                        List.of("Kourend Woodland", "Mount Karuulm")),
                new TeleportItem(
                        "desert amulet",
                        List.of(
                                ItemID.DESERT_AMULET,
                                ItemID.DESERT_AMULET_1,
                                ItemID.DESERT_AMULET_2,
                                ItemID.DESERT_AMULET_3,
                                ItemID.DESERT_AMULET_4
                        ),
                        List.of("Nardah", "Kalphite cave")),
                new TeleportItem(
                        "ghommal's hilt",
                        List.of(
                                ItemID.GHOMMALS_HILT_1,
                                ItemID.GHOMMALS_HILT_2,
                                ItemID.GHOMMALS_HILT_3,
                                ItemID.GHOMMALS_HILT_4,
                                ItemID.GHOMMALS_HILT_5,
                                ItemID.GHOMMALS_HILT_6,
                                ItemID.GHOMMALS_AVERNIC_DEFENDER_5,
                                ItemID.GHOMMALS_AVERNIC_DEFENDER_5_L,
                                ItemID.GHOMMALS_AVERNIC_DEFENDER_6,
                                ItemID.GHOMMALS_AVERNIC_DEFENDER_6_L
                        ),
                        List.of("Trollheim", "Mor Ul Rek")),

                // teleport jewelry
                new TeleportItem(
                        "necklace of passage",
                        List.of(
                                ItemID.NECKLACE_OF_PASSAGE1,
                                ItemID.NECKLACE_OF_PASSAGE2,
                                ItemID.NECKLACE_OF_PASSAGE3,
                                ItemID.NECKLACE_OF_PASSAGE4,
                                ItemID.NECKLACE_OF_PASSAGE5
                        ),
                        List.of("Wizards' Tower", "The Outpost", "Eagles' Eyrie", "Rub")),
                new TeleportItem(
                        "games necklace",
                        List.of(
                                ItemID.GAMES_NECKLACE1,
                                ItemID.GAMES_NECKLACE2,
                                ItemID.GAMES_NECKLACE3,
                                ItemID.GAMES_NECKLACE4,
                                ItemID.GAMES_NECKLACE5,
                                ItemID.GAMES_NECKLACE6,
                                ItemID.GAMES_NECKLACE7,
                                ItemID.GAMES_NECKLACE8
                        ),
                        List.of("Burthorpe", "Barbarian Outpost", "Corporeal Beast", "Tears of Guthix", "Wintertodt Camp", "Rub")),
                new TeleportItem(
                        "ring of dueling",
                        List.of(
                                ItemID.RING_OF_DUELING1,
                                ItemID.RING_OF_DUELING2,
                                ItemID.RING_OF_DUELING3,
                                ItemID.RING_OF_DUELING4,
                                ItemID.RING_OF_DUELING5,
                                ItemID.RING_OF_DUELING6,
                                ItemID.RING_OF_DUELING7,
                                ItemID.RING_OF_DUELING8
                        ),
                        List.of("PvP Arena", "Castle Wars", "Ferox Enclave", "Rub")),
                new TeleportItem(
                        "digsite pendant",
                        List.of(
                                ItemID.DIGSITE_PENDANT_1,
                                ItemID.DIGSITE_PENDANT_2,
                                ItemID.DIGSITE_PENDANT_3,
                                ItemID.DIGSITE_PENDANT_4,
                                ItemID.DIGSITE_PENDANT_5
                        ),
                        List.of("Fossil Island", "Digsite", "Lithkren Dungeon", "Rub")),
                new TeleportItem(
                        "amulet of glory",
                        List.of(
                                ItemID.AMULET_OF_GLORY,
                                ItemID.AMULET_OF_GLORY1,
                                ItemID.AMULET_OF_GLORY2,
                                ItemID.AMULET_OF_GLORY3,
                                ItemID.AMULET_OF_GLORY4,
                                ItemID.AMULET_OF_GLORY5,
                                ItemID.AMULET_OF_GLORY6,
                                ItemID.AMULET_OF_GLORY_T,
                                ItemID.AMULET_OF_GLORY_T1,
                                ItemID.AMULET_OF_GLORY_T2,
                                ItemID.AMULET_OF_GLORY_T3,
                                ItemID.AMULET_OF_GLORY_T4,
                                ItemID.AMULET_OF_GLORY_T5,
                                ItemID.AMULET_OF_GLORY_T6,
                                ItemID.AMULET_OF_ETERNAL_GLORY
                        ),
                        List.of("Edgeville", "Karamja", "Draynor Village", "Al Kharid", "Rub")),
                new TeleportItem(
                        "combat bracelet",
                        List.of(
                                ItemID.COMBAT_BRACELET,
                                ItemID.COMBAT_BRACELET1,
                                ItemID.COMBAT_BRACELET2,
                                ItemID.COMBAT_BRACELET3,
                                ItemID.COMBAT_BRACELET4,
                                ItemID.COMBAT_BRACELET5,
                                ItemID.COMBAT_BRACELET6
                        ),
                        List.of("Warriors' Guild", "Champions' Guild", "Monastery", "Ranging Guild", "Rub")),
                new TeleportItem(
                        "skills necklace",
                        List.of(
                                ItemID.SKILLS_NECKLACE,
                                ItemID.SKILLS_NECKLACE1,
                                ItemID.SKILLS_NECKLACE2,
                                ItemID.SKILLS_NECKLACE3,
                                ItemID.SKILLS_NECKLACE4,
                                ItemID.SKILLS_NECKLACE5,
                                ItemID.SKILLS_NECKLACE6
                        ),
                        List.of("Fishing Guild", "Mining Guild", "Crafting Guild", "Cooking Guild", "Woodcutting Guild", "Farming Guild", "Rub")),
                new TeleportItem(
                        "ring of wealth",
                        List.of(
                                ItemID.RING_OF_WEALTH,
                                ItemID.RING_OF_WEALTH_1,
                                ItemID.RING_OF_WEALTH_2,
                                ItemID.RING_OF_WEALTH_3,
                                ItemID.RING_OF_WEALTH_4,
                                ItemID.RING_OF_WEALTH_5,
                                ItemID.RING_OF_WEALTH_I,
                                ItemID.RING_OF_WEALTH_I1,
                                ItemID.RING_OF_WEALTH_I2,
                                ItemID.RING_OF_WEALTH_I3,
                                ItemID.RING_OF_WEALTH_I4,
                                ItemID.RING_OF_WEALTH_I5
                        ),
                        List.of("Miscellania", "Grand Exchange", "Falador Park", "Dondakan's Rock", "Rub")),
                new TeleportItem(
                        "slayer ring",
                        List.of(
                                ItemID.SLAYER_RING_ETERNAL,
                                ItemID.SLAYER_RING_1,
                                ItemID.SLAYER_RING_2,
                                ItemID.SLAYER_RING_3,
                                ItemID.SLAYER_RING_4,
                                ItemID.SLAYER_RING_5,
                                ItemID.SLAYER_RING_6,
                                ItemID.SLAYER_RING_7,
                                ItemID.SLAYER_RING_8
                        ),
                        List.of("Teleport", "Rub")),
                new TeleportItem(
                        "burning amulet",
                        List.of(
                                ItemID.BURNING_AMULET1,
                                ItemID.BURNING_AMULET2,
                                ItemID.BURNING_AMULET3,
                                ItemID.BURNING_AMULET4,
                                ItemID.BURNING_AMULET5
                        ),
                        List.of("Rub", "Chaos Temple", "Bandit Camp", "Lava Maze"))

        );

        spellFilters = List.of(
                // standard teleports
                new TeleportSpell("lumbridge home teleport",
                        List.of("Cast", "Animation")),

                new TeleportSpell("varrock teleport",
                        List.of("Cast", "Grand Exchange", "Configure")),

                new TeleportSpell("lumbridge teleport",
                        List.of("Cast")),

                new TeleportSpell("falador teleport",
                        List.of("Cast")),

                new TeleportSpell("teleport to house",
                        List.of("Cast", "Outside", "Group: Choose", "Group: Previous")),

                new TeleportSpell("camelot teleport",
                        List.of("Cast", "Seers'", "Configure")),

                new TeleportSpell("ardougne teleport",
                        List.of("Cast")),

                new TeleportSpell("watchtower teleport",
                        List.of("Cast", "Yanille", "Configure")),

                new TeleportSpell("trollheim teleport",
                        List.of("Cast")),

                new TeleportSpell("ape atoll teleport",
                        List.of("Cast")),

                // ancients teleports
                new TeleportSpell("edgeville home teleport",
                        List.of("Cast", "Animation")),

                new TeleportSpell("paddewwa teleport",
                        List.of("Cast")),

                new TeleportSpell("senntisten teleport",
                        List.of("Cast")),

                new TeleportSpell("kharyrll teleport",
                        List.of("Cast")),

                new TeleportSpell("lassar teleport",
                        List.of("Cast")),

                new TeleportSpell("dareeyak teleport",
                        List.of("Cast", "Enable warning", "Disable warning")),

                new TeleportSpell("carrallanger teleport",
                        List.of("Cast", "Enable warning", "Disable warning")),

                new TeleportSpell("teleport to target",
                        List.of("Cast", "Enable warning", "Disable warning")),

                new TeleportSpell("annakarl teleport",
                        List.of("Cast", "Enable warning", "Disable warning")),

                new TeleportSpell("ghorrock teleport",
                        List.of("Cast", "Enable warning", "Disable warning")),

                // lunars teleports
                new TeleportSpell("lunar home teleport",
                        List.of("Cast", "Animation")),

                new TeleportSpell("moonclan teleport",
                        List.of("Cast")),

                new TeleportSpell("ourania teleport",
                        List.of("Cast")),

                new TeleportSpell("waterbirth teleport",
                        List.of("Cast")),

                new TeleportSpell("barbarian teleport",
                        List.of("Cast")),

                new TeleportSpell("khazard teleport",
                        List.of("Cast")),

                new TeleportSpell("fishing guild teleport",
                        List.of("Cast")),

                new TeleportSpell("teleport to target",
                        List.of("Cast", "Enable warning", "Disable warning")),

                new TeleportSpell("catherby teleport",
                        List.of("Cast")),

                new TeleportSpell("ice plateau teleport",
                        List.of("Cast")),

                // arceuus teleports
                new TeleportSpell("arceuus home teleport",
                        List.of("Cast", "Animation")),

                new TeleportSpell("arceuus library teleport",
                        List.of("Cast")),

                new TeleportSpell("draynor manor teleport",
                        List.of("Cast")),

                new TeleportSpell("battlefront teleport",
                        List.of("Cast")),

                new TeleportSpell("respawn teleport",
                        List.of("Cast")),

                new TeleportSpell("salve graveyard teleport",
                        List.of("Cast")),

                new TeleportSpell("fenkenstrain's castle teleport",
                        List.of("Cast")),

                new TeleportSpell("west ardougne teleport",
                        List.of("Cast")),

                new TeleportSpell("harmony island teleport",
                        List.of("Cast")),

                new TeleportSpell("cemetery teleport",
                        List.of("Cast")),

                new TeleportSpell("barrows teleport",
                        List.of("Cast")),

                new TeleportSpell("ape atoll teleport",
                        List.of("Cast"))
        );

        objectFilters = List.of(
                new TeleportObject("portal",
                        List.of("Enter", "Home", "Build mode", "Friend's house")));

    }

    public MenuEntry[] filterAll(MenuEntry[] menuEntries) {
        List<TeleportFilter> allFilters = new ArrayList<>();
        Stream.of(itemFilters, spellFilters, objectFilters).forEach(allFilters::addAll);

        for (TeleportFilter teleportFilter : allFilters) {

            MenuEntry[] newMenuEntries = teleportFilter.filterOutTeleports(menuEntries);

            // if any entries are removed, filtering is done because only one item will ever filter
            if (newMenuEntries.length != menuEntries.length) {
                return newMenuEntries;
            }
        }
        return menuEntries;
    }
}
