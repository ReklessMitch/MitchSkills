package me.reklessmitch.mitchskills.configs;
import com.massivecraft.massivecore.store.SenderEntity;
import lombok.Getter;
import me.reklessmitch.mitchskills.utils.SkillLevel;
import org.jetbrains.annotations.NotNull;
import java.util.Map;
import java.util.stream.Collectors;

public class SPlayer extends SenderEntity<SPlayer> {

    public static SPlayer get(Object oid) {
        return SPlayerColl.get().get(oid);
    }

    @Getter
    Map<String, SkillLevel> skills = createSkills();

    @Override
    public SPlayer load(@NotNull SPlayer that)
    {
        super.load(that);
        return this;
    }

    public Map<String, SkillLevel> createSkills() {
        return MConf.get().skills.keySet().stream().collect(Collectors.toMap(skill -> skill,
                skill -> new SkillLevel(0, 0.0)));
    }


}
