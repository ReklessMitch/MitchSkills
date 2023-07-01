package me.reklessmitch.mitchskills;

import com.massivecraft.massivecore.Identified;
import com.massivecraft.massivecore.util.PermissionUtil;
import org.bukkit.permissions.Permissible;

public enum Perm implements Identified {

    SKILL_GUI,
    SKILL_SET,
    SKILL,
    SKILL_ADD,
    SKILL_REMOVE,
    ;

    private final String id;

    Perm()
    {
        this.id = PermissionUtil.createPermissionId(SkillApplication.get(), this);
    }

    @Override public String getId() { return this.id; }

    public boolean has(Permissible permissible, boolean verboose)
    {
        return PermissionUtil.hasPermission(permissible, this, verboose);
    }

    public boolean has(Permissible permissible)
    {
        return PermissionUtil.hasPermission(permissible, this);
    }
}