package me.reklessmitch.mitchskills.events;

import com.massivecraft.massivecore.Engine;
import me.reklessmitch.mitchskills.configs.SPlayer;
import me.reklessmitch.mitchskills.configs.SPlayerColl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin extends Engine {


    private static final PlayerJoin i = new PlayerJoin();
    public static PlayerJoin get() { return i; }


    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {

    }

}
