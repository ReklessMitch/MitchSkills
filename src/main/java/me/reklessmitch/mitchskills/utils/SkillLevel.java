package me.reklessmitch.mitchskills.utils;

import lombok.Getter;
import me.reklessmitch.mitchpets.entity.PPlayer;
import me.reklessmitch.mitchskills.SkillApplication;
import me.reklessmitch.mitchskills.configs.MConf;
import me.reklessmitch.mitchskills.configs.SPlayer;
import me.reklessmitch.mitchskills.skills.SkillStats;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

@Getter
public class SkillLevel {
    int level;
    double xp;

    public SkillLevel(int level, double xp) {
        this.level = level;
        this.xp = xp;
    }

    public void addXP(Player p, double amount) {
        SPlayer player = SPlayer.get(p.getUniqueId());
        SkillStats stats = player.getSkills().keySet()
                .stream()
                .map(skill -> MConf.get().skills.get(skill))
                .findFirst()
                .orElse(null);

        if(stats == null){return;}
        float costToLevelUp = stats.getSkillXpNeeded(level);
        if(costToLevelUp > xp + amount){
            this.xp += amount;
        }else{
            double remainingXP = xp + amount - costToLevelUp;
            level++;
            xp = 0;
            addXP(p, remainingXP);
        }
        // get the players XP bar and edit it to text showing their level and xp and skill identifier
        player.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                ChatColor.translateAlternateColorCodes('&', " &aLevel: &c" + level
                        + " &aXP: &c" + (int) xp + "/" + (int) costToLevelUp
                )));
        player.changed();

    }

    public void addLevel(Player player, int amount){
        SPlayer splayer = SPlayer.get(player.getUniqueId());
        SkillStats stats = splayer.getSkills().keySet()
                .stream()
                .map(skill -> MConf.get().skills.get(skill))
                .findFirst()
                .orElse(null);
        if(stats == null){return;}
        if(level + amount > stats.getMaxLevel()){
            this.level = stats.getMaxLevel();
            return;
        }
        this.level += amount;
        splayer.changed();
    }

    public void setLevel(int amount){
        this.level = amount;
    }
}
