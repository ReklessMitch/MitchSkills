package me.reklessmitch.mitchskills.events;

import com.massivecraft.massivecore.Engine;
import me.reklessmitch.mitchpets.entity.PPlayer;
import me.reklessmitch.mitchpets.entity.PetType;
import me.reklessmitch.mitchskills.configs.MConf;
import me.reklessmitch.mitchskills.configs.SPlayer;
import me.reklessmitch.mitchskills.utils.SkillLevel;
import org.bukkit.ChatColor;
import org.bukkit.CropState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.material.Crops;

import java.util.Arrays;


public class FarmingEvent extends Engine {

    private static final FarmingEvent i = new FarmingEvent();
    public static FarmingEvent get() { return i; }

    @EventHandler(ignoreCancelled = true)
    public void farmingEvents(BlockBreakEvent e){
        Material blockType = e.getBlock().getType();
        if(!MConf.get().farmingEco.containsKey(blockType)) return;
        double value = MConf.get().farmingEco.get(blockType);
        value = addPetBonusXP(e.getPlayer(), value);
        SkillLevel stats = SPlayer.get(e.getPlayer().getUniqueId()).getSkills().get("farming");
        if(blockType.equals(Material.SUGAR_CANE)){
            e.setCancelled(true);
            stats.addXP(e.getPlayer(), sugarCaneMath(e.getBlock(), value, e));
        }else {
            final Ageable ageable = (Ageable) e.getBlock().getState().getBlockData();
            if (ageable.getAge() != ageable.getMaximumAge()) return;
            stats.addXP(e.getPlayer(), value);
        }
    }

    private double addPetBonusXP(Player player, double amount){
        PPlayer petPlayer = PPlayer.get(player.getUniqueId());
        double petBooster = petPlayer.getActivePet().equals(PetType.FARMING) ? petPlayer.getPet(PetType.FARMING).getPetBooster() : 1.0;
        player.sendMessage("Pet booster: " + petBooster);
        return amount * petBooster;
    }

    @EventHandler(ignoreCancelled = true)
    public static void canePlaceEvent(BlockPlaceEvent e){
        Material blockType = e.getBlock().getType();
        if(blockType.equals(Material.SUGAR_CANE)){
            Location location = e.getBlock().getLocation();
            location.setY(location.getY() - 1);
            if(location.getBlock().getType().equals(Material.SUGAR_CANE)){
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + "You cannot place sugar cane on-top of another sugarcane!");
            }
        }
    }



    public static double sugarCaneMath(Block block, double xpPerCrop, BlockBreakEvent event){
        Location location = block.getLocation();
        location.setY(location.getY() - 1);
        if(location.getBlock().getType().equals(Material.SUGAR_CANE)){
            location.setY(location.getY() + 2);
            block.breakNaturally();
            if(location.getBlock().getType().equals(Material.SUGAR_CANE)){
                return xpPerCrop * 2.0;
            }else{
                return xpPerCrop;
            }
        }else{
            event.getPlayer().sendMessage(ChatColor.RED + "You cannot break the bottom block of the sugar cane!");
            return 0;
        }

    }



}
