package me.reklessmitch.mitchskills.commands.skillcommand;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.primitive.TypeInteger;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivecore.command.type.sender.TypePlayer;
import me.reklessmitch.mitchskills.configs.SPlayer;
import me.reklessmitch.mitchskills.Perm;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSkillSet extends SkillCommand{

    public CmdSkillSet() {
        this.addAliases("set");
        this.addParameter(TypePlayer.get(), "player", "ReklessMitch");
        this.addParameter(TypeString.get(), "skill", "combat");
        this.addParameter(TypeInteger.get(), "level", "1");
        this.addRequirements(RequirementHasPerm.get(Perm.SKILL_SET));
    }


    @Override
    public void perform() throws MassiveException {
        Player targetPlayer = this.readArg();
        String skill = this.readArg();
        int level = this.readArg();

        SPlayer sPlayer = SPlayer.get(targetPlayer.getUniqueId());
        sPlayer.getSkills().get(skill).setLevel(level);
        sender.sendMessage(ChatColor.GREEN + "Successfully set skill for player: " + targetPlayer.getName());
    }
}
