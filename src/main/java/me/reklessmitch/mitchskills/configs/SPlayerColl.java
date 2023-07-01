package me.reklessmitch.mitchskills.configs;

import com.massivecraft.massivecore.MassiveCore;
import com.massivecraft.massivecore.store.Coll;
import com.massivecraft.massivecore.store.SenderColl;

public class SPlayerColl extends SenderColl<SPlayer> {

    private static final SPlayerColl i = new SPlayerColl();

    public static SPlayerColl get() {
        return i;
    }

    @Override
    public void onTick() {
        super.onTick();
    }

    @Override
    public void setActive(boolean active) {
        super.setActive(active);
        // if (!active) return;

    }
}