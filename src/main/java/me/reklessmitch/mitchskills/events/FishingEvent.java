package me.reklessmitch.mitchskills.events;

import com.massivecraft.massivecore.Engine;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.reklessmitch.mitchpets.entity.PPlayer;
import me.reklessmitch.mitchpets.entity.PetType;
import me.reklessmitch.mitchskills.SkillApplication;
import me.reklessmitch.mitchskills.configs.MConf;
import me.reklessmitch.mitchskills.utils.FishingLocation;
import me.reklessmitch.mitchskills.utils.Reward;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class FishingEvent extends Engine {

    private static final FishingEvent i = new FishingEvent();
    public static FishingEvent get() { return i; }

    @EventHandler(ignoreCancelled = true)
    public void onFishEvent(PlayerFishEvent e){
        Player player = e.getPlayer();
        Location location = e.getHook().getLocation();
        FishingLocation fishingRegion = getFishingRegion(location);
        if (fishingRegion != null) {
            Reward reward = fishingRegion.getReward();
            reward.getCommands(player).forEach(command -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command));
            player.sendMessage(reward.getMessage(player));
        }
    }

    private FishingLocation getFishingRegion(Location location) {
        ApplicableRegionSet set = WorldGuard.getInstance().getPlatform().getRegionContainer().get(
                new BukkitWorld(location.getWorld())).getApplicableRegions(BlockVector3.at(location.getX(),location.getY(),location.getZ()));
        ProtectedRegion region = set.getRegions().stream().findFirst().orElse(null);
        if(region == null) return null;
        return MConf.get().fishingLocations.stream().filter(l -> l.getRegionID().equals(region.getId())).findAny().orElse(null);
    }

}
