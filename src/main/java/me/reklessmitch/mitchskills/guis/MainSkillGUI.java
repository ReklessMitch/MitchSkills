package me.reklessmitch.mitchskills.guis;

import com.massivecraft.massivecore.chestgui.ChestGui;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;
import me.reklessmitch.mitchskills.configs.MConf;
import me.reklessmitch.mitchskills.configs.SPlayer;
import me.reklessmitch.mitchskills.skills.SkillStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

import static me.reklessmitch.mitchskills.utils.Util.format;

public class MainSkillGUI extends ChestGui {

    SPlayer sPlayer;
    Player player;

    public MainSkillGUI(Player player) {
        this.player = player;
        sPlayer = SPlayer.get(player.getUniqueId());
        Inventory inventory = Bukkit.createInventory(null, 27, format("&8Skills"));
        sPlayer.getSkills().forEach((s, skill) -> {
            SkillStats skillStats = MConf.get().skills.get(s);
            ItemStack item = createItem(s, skillStats);
            inventory.setItem(skillStats.getGuiSlot(), item);
            setAction(skillStats.getGuiSlot(), s);
        });
        this.setInventory(inventory);
    }

    private ItemStack createItem(String s, SkillStats skillStats) {
        ItemStack item = new ItemStack(skillStats.getGuiItem());
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(format(skillStats.getDisplayName()));
        int level = sPlayer.getSkills().get(s).getLevel();
        meta.setLore(Arrays.asList(
                format( "&7Level: &f" + level),
                format("&7XP: &f" + sPlayer.getSkills().get(s).getXp() + "/" + skillStats.getSkillXpNeeded(level)),
                format("&7Max Level: &f" + skillStats.getMaxLevel())
        ));
        item.setItemMeta(meta);
        return item;
    }

    public void open() {
        player.openInventory(this.getInventory());
    }
}
