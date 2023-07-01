package me.reklessmitch.mitchskills.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.reklessmitch.mitchskills.configs.SPlayer;
import me.reklessmitch.mitchskills.utils.SkillLevel;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class SkillPlaceholder extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "mitchskills";
    }

    @Override
    public @NotNull String getAuthor() {
        return "ReklessMitch";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        SPlayer playerConf = SPlayer.get(player.getUniqueId());
        String returnValue = "Invalid Placeholder";

        for (Map.Entry<String, SkillLevel> entry : playerConf.getSkills().entrySet()) {
            String skill = entry.getKey();
            SkillLevel skillLevel = entry.getValue();

            if (params.equalsIgnoreCase(skill + "_level")) {
                returnValue = String.valueOf(skillLevel.getLevel());
                break;
            } else if (params.equalsIgnoreCase(skill + "_xp")) {
                returnValue = String.valueOf(skillLevel.getXp());
                break;
            }
        }

        return returnValue;
    }

}
