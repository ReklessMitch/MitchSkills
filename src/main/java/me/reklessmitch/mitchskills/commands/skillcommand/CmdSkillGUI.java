package me.reklessmitch.mitchskills.commands.skillcommand;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import me.reklessmitch.mitchskills.guis.MainSkillGUI;
import me.reklessmitch.mitchskills.Perm;
import org.bukkit.entity.Player;

public class CmdSkillGUI extends SkillCommand {


    public CmdSkillGUI() {
        this.addAliases("gui");
        this.addRequirements(RequirementIsPlayer.get());
        this.addRequirements(RequirementHasPerm.get(Perm.SKILL_GUI));

    }

    @Override
    public void perform() throws MassiveException {
        new MainSkillGUI((Player) sender).open();

    }

}
