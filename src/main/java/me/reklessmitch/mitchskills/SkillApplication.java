package me.reklessmitch.mitchskills;

import com.massivecraft.massivecore.MassivePlugin;
import lombok.Getter;
import me.reklessmitch.mitchskills.commands.skillcommand.CmdSkill;
import me.reklessmitch.mitchskills.commands.skillcommand.SkillCommand;
import me.reklessmitch.mitchskills.configs.MConfColl;
import me.reklessmitch.mitchskills.configs.SPlayerColl;
import me.reklessmitch.mitchskills.events.CombatEvent;
import me.reklessmitch.mitchskills.events.FarmingEvent;
import me.reklessmitch.mitchskills.events.FishingEvent;
import me.reklessmitch.mitchskills.events.PlayerJoin;
import me.reklessmitch.mitchskills.placeholders.SkillPlaceholder;

import org.bukkit.Bukkit;

@Getter
public final class SkillApplication extends MassivePlugin {

    private static SkillApplication i;
    public static SkillApplication get() { return i; }

    public SkillApplication() {
        this.setVersionSynchronized(true);
        SkillApplication.i = this;
    }


    @Override
    public void onEnableInner(){
        this.activate(
                // Configs
                CmdSkill.class,
                MConfColl.class,
                SPlayerColl.class,
                //SPlayerConf.class,
                // Events
                PlayerJoin.class,
                FarmingEvent.class,
                CombatEvent.class,
                FishingEvent.class
        );
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) new SkillPlaceholder().register();
    }

    @Override
    public void onDisable(){
        super.onDisable();
        i = null;
    }

}
