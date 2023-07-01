package me.reklessmitch.mitchskills.events;

import com.massivecraft.massivecore.Engine;
import me.reklessmitch.mitchpets.MitchPets;
import me.reklessmitch.mitchpets.entity.PPlayer;
import me.reklessmitch.mitchpets.entity.PetType;
import me.reklessmitch.mitchskills.configs.MConf;
import me.reklessmitch.mitchskills.configs.SPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;


public class CombatEvent extends Engine {

    private static final CombatEvent i = new CombatEvent();
    public static CombatEvent get() { return i; }

    @EventHandler(ignoreCancelled = true)
    public  void combatOnHitEvent(EntityDamageByEntityEvent e){
        if(!(e.getDamager() instanceof Player)) return;
        Player player = (Player) e.getDamager();
        SPlayer playerConf = SPlayer.get(player.getUniqueId());
        int currentLevel = playerConf.getSkills().get("combat").getLevel();
        int levelIncrease = currentLevel / MConf.get().combatDamageIncrease;
        e.setDamage(e.getDamage() + (e.getDamage() * levelIncrease * MConf.get().combatDamagePercentIncrease));
    }

    @EventHandler(ignoreCancelled = true)
    public void combatKillMobEvent(EntityDeathEvent e) {
        if(e.getEntity().getKiller() == null) return;
        Player player = e.getEntity().getKiller();
        if(player == null) return;
        SPlayer playerConf = SPlayer.get(player.getUniqueId());
        double amount = MConf.get().combatEco.getOrDefault(e.getEntityType(), 0.0).intValue();
        amount = addPetBonusXP(player, amount);
        playerConf.getSkills().get("combat").addXP(player, amount);
    }

    private double addPetBonusXP(Player player, double amount){
        PPlayer petPlayer = PPlayer.get(player.getUniqueId());
        double petBooster = petPlayer.getActivePet().equals(PetType.COMBAT) ? petPlayer.getPet(PetType.COMBAT).getPetBooster() : 1.0;
        player.sendMessage("Pet booster: " + petBooster);
        return amount * petBooster;
    }
}
