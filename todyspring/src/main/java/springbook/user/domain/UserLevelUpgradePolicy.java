package springbook.user.domain;

public interface UserLevelUpgradePolicy {
    boolean canUpgradedLevel(User user);
    void upgradeLevel(User user);

}
