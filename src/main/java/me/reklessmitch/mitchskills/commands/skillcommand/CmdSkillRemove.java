package me.reklessmitch.mitchskills.commands.skillcommand;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.primitive.TypeInteger;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivecore.command.type.sender.TypePlayer;
import me.reklessmitch.mitchskills.Perm;
import me.reklessmitch.mitchskills.configs.SPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CmdSkillRemove extends SkillCommand{

    public CmdSkillRemove() {
        this.addAliases("remove");
        this.addParameter(TypePlayer.get(), "player", "ReklessMitch");
        this.addParameter(TypeString.get(), "skill", "combat");
        this.addParameter(TypeInteger.get(), "level", "1");
        this.addRequirements(RequirementHasPerm.get(Perm.SKILL_REMOVE));
    }


    @Override
    public void perform() throws MassiveException {
        Player targetPlayer = this.readArg();
        String skill = this.readArg();
        int level = this.readArg();

        SPlayer sPlayer = SPlayer.get(targetPlayer.getUniqueId());
        sPlayer.getSkills().get(skill).addLevel(targetPlayer, level);
        sender.sendMessage(ChatColor.GREEN + "Successfully removed" + level + " " + skill + "level(s) to player: " + targetPlayer.getName());
    }
}
