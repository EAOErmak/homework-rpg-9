package com.narxoz.rpg.artifact;

/**
 * Fourth visitor used to prove open/closed extension without changing the
 * artifact hierarchy.
 */
public class WeightLedger implements ArtifactVisitor {

    private int totalWeight;

    public int getTotalWeight() {
        return totalWeight;
    }

    @Override
    public void visit(Weapon weapon) {
        totalWeight += weapon.getWeight();
        System.out.println("[Weight] Weapon " + weapon.getName()
                + " adds " + weapon.getWeight() + " load units to the rack.");
    }

    @Override
    public void visit(Potion potion) {
        totalWeight += potion.getWeight();
        System.out.println("[Weight] Potion " + potion.getName()
                + " adds " + potion.getWeight() + " load unit to the satchel.");
    }

    @Override
    public void visit(Scroll scroll) {
        totalWeight += scroll.getWeight();
        System.out.println("[Weight] Scroll " + scroll.getName()
                + " adds " + scroll.getWeight() + " load unit to the scroll case.");
    }

    @Override
    public void visit(Ring ring) {
        totalWeight += ring.getWeight();
        System.out.println("[Weight] Ring " + ring.getName()
                + " adds " + ring.getWeight() + " load unit to the pouch.");
    }

    @Override
    public void visit(Armor armor) {
        totalWeight += armor.getWeight();
        System.out.println("[Weight] Armor " + armor.getName()
                + " adds " + armor.getWeight() + " load units to the pack frame.");
    }
}
