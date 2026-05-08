package com.narxoz.rpg.artifact;

/**
 * Estimates resale value for every artifact type.
 */
public class GoldAppraiser implements ArtifactVisitor {

    private int totalEstimatedValue;

    public int getTotalEstimatedValue() {
        return totalEstimatedValue;
    }

    @Override
    public void visit(Weapon weapon) {
        int estimate = weapon.getValue() + weapon.getAttackBonus() * 18;
        totalEstimatedValue += estimate;
        System.out.println("[Gold] Weapon " + weapon.getName()
                + " is worth about " + estimate
                + " gold thanks to its +" + weapon.getAttackBonus() + " edge.");
    }

    @Override
    public void visit(Potion potion) {
        int estimate = potion.getValue() + potion.getHealing() * 7;
        totalEstimatedValue += estimate;
        System.out.println("[Gold] Potion " + potion.getName()
                + " is worth about " + estimate
                + " gold with " + potion.getHealing() + " healing power.");
    }

    @Override
    public void visit(Scroll scroll) {
        int estimate = scroll.getValue() + scroll.getSpellName().length() * 8;
        totalEstimatedValue += estimate;
        System.out.println("[Gold] Scroll " + scroll.getName()
                + " is worth about " + estimate
                + " gold because it carries " + scroll.getSpellName() + ".");
    }

    @Override
    public void visit(Ring ring) {
        int estimate = ring.getValue() + ring.getMagicBonus() * 20;
        totalEstimatedValue += estimate;
        System.out.println("[Gold] Ring " + ring.getName()
                + " is worth about " + estimate
                + " gold with a +" + ring.getMagicBonus() + " arcane focus.");
    }

    @Override
    public void visit(Armor armor) {
        int estimate = armor.getValue() + armor.getDefenseBonus() * 15;
        totalEstimatedValue += estimate;
        System.out.println("[Gold] Armor " + armor.getName()
                + " is worth about " + estimate
                + " gold with +" + armor.getDefenseBonus() + " defense.");
    }
}
