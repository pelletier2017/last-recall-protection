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
        itemFilters = List.of(
                new TeleportItem(
                        "necklace of passage",
                        List.of(
                                ItemID.NECKLACE_OF_PASSAGE1,
                                ItemID.NECKLACE_OF_PASSAGE2,
                                ItemID.NECKLACE_OF_PASSAGE3,
                                ItemID.NECKLACE_OF_PASSAGE4,
                                ItemID.NECKLACE_OF_PASSAGE5
                        ),
                        List.of("Wizard's Tower", "The Outpost", "Eagles' Eyrie", "Rub")),
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
                        List.of("Barbarian Outpost", "Corporeal Beast", "Tears of Guthix", "Wintertodt Camp", "Rub")),
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
                        List.of("Teleport", "Rub"))

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
