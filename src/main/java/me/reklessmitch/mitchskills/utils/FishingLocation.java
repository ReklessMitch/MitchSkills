package me.reklessmitch.mitchskills.utils;

import lombok.Getter;

import java.util.List;

public class FishingLocation {

    @Getter private final String regionID;
    private final List<Reward> rewardList;

    public FishingLocation(String regionID, List<Reward> rewardList) {
        this.regionID = regionID;
        this.rewardList = rewardList;
    }

    public Reward getReward(){
        double totalChance = 0;
        for(Reward reward : rewardList){
            totalChance += reward.getChance();
        }
        double random = Math.random() * totalChance;
        double current = 0;
        for(Reward reward : rewardList){
            current += reward.getChance();
            if(random <= current){
                return reward;
            }
        }
        return null;
    }
}
