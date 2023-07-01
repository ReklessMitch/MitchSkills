package me.reklessmitch.mitchskills.skills;

import lombok.Getter;
import org.bukkit.Material;

@Getter
public class SkillStats {
    String displayName;
    int baseCost;
    int increase;
    double percentIncrease;
    int maxLevel;
    Material guiItem;
    int guiSlot;

    public SkillStats(String displayName, int baseCost, int increase, double percentIncrease, int maxLevel, Material guiItem, int guiSlot) {
        this.displayName = displayName;
        this.baseCost = baseCost;
        this.increase = increase;
        this.percentIncrease = percentIncrease;
        this.maxLevel = maxLevel;
        this.guiItem = guiItem;
        this.guiSlot = guiSlot;
    }

    public float getSkillXpNeeded(int level) {
        return (float) (baseCost + (increase * Math.pow(percentIncrease, level)));
    }
}
