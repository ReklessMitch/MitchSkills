package me.reklessmitch.mitchskills.commands.skillcommand;

import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.util.Txt;
import me.reklessmitch.mitchskills.Perm;

public class CmdSkill extends SkillCommand {
    private static final CmdSkill i = new CmdSkill();
    public static CmdSkill get() { return i; }

    public CmdSkillGUI skillGUI = new CmdSkillGUI();
    public CmdSkillSet skillSet = new CmdSkillSet();
    public CmdSkillAdd skillAdd = new CmdSkillAdd();
    public CmdSkillRemove skillRemove = new CmdSkillRemove();

    public CmdSkill() {
        this.addAliases("skill");
        this.addChild(this.skillAdd);
        this.addChild(this.skillRemove);
        this.addChild(this.skillGUI);
        this.addChild(this.skillSet);
        this.addRequirements(RequirementHasPerm.get(Perm.SKILL));
    }

    @Override
    public void perform() {
        sender.sendMessage(Txt.parse("Skill command\n" +
                "Combat: %mitchskills_combat_level% (%mitchskills_combat_xp%)\n" +
                "Farming: %mitchskills_farming_level% (%mitchskills_farming_xp%)\n" +
                "Fishing: %mitchskills_fishing_level% (%mitchskills_fishing_xp%)\n" +
                "Mining: %mitchskills_mining_level% (%mitchskills_mining_xp%)\n"));
    }

}
