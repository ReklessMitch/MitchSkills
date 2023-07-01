package me.reklessmitch.mitchskills.configs;

import com.google.common.collect.Lists;
import com.massivecraft.massivecore.command.editor.annotation.EditorName;
import com.massivecraft.massivecore.store.Entity;
import lombok.Getter;
import me.reklessmitch.mitchskills.skills.SkillStats;
import me.reklessmitch.mitchskills.utils.FishingLocation;
import me.reklessmitch.mitchskills.utils.Reward;;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@EditorName("config")
public class MConf extends Entity<MConf> {
    protected static transient MConf i;
    public static MConf get() { return i; }

    @Getter
    boolean petsEnabled = true;

    public Map<String, SkillStats> skills = Map.of(
            "farming", new SkillStats("&3&lFarming", 100, 1, 1.0, 100, Material.WHEAT, 10),
            "combat", new SkillStats("&3&lCombat", 100, 1, 1.0, 100, Material.DIAMOND_SWORD, 12),
            "mining", new SkillStats("&3&lMining", 100, 1, 1.0, 100, Material.DIAMOND_PICKAXE, 14),
            "fishing", new SkillStats("&3&lFishing", 100, 1, 1.0, 100, Material.FISHING_ROD, 16));

    public List<FishingLocation> fishingLocations = Lists.newArrayList(
            new FishingLocation("fishing1",
                    Lists.newArrayList(new Reward(100.0, List.of("give %player% minecraft:gold_ingot 1"),
            "%player% has just won a gold ore!"))));
    public Map<EntityType, Double> combatEco = Map.of(EntityType.ZOMBIE, 10.0);
    public Map<Material, Double> farmingEco = Map.of(Material.WHEAT, 10.0);

    public int combatDamageIncrease = 5;
    public double combatDamagePercentIncrease = 0.15;
}
