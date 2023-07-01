package me.reklessmitch.mitchskills.configs;

import com.massivecraft.massivecore.MassiveCore;
import com.massivecraft.massivecore.store.Coll;

public class MConfColl extends Coll<MConf>
{
    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static final MConfColl i = new MConfColl();
    public static MConfColl get() { return i; }


    @Override
    public void setActive(boolean active)
    {
        super.setActive(active);
        if (!active) return;
        MConf.i = this.get(MassiveCore.INSTANCE, true);
    }

}