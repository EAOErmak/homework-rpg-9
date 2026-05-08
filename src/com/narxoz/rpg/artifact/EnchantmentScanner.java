package com.narxoz.rpg.artifact;

/**
 * Reports magical signatures across different artifacts.
 */
public class EnchantmentScanner implements ArtifactVisitor {

    private int resonanceScore;

    public int getResonanceScore() {
        return resonanceScore;
    }

    @Override
    public void visit(Weapon weapon) {
        int resonance = weapon.getAttackBonus() * 2;
        resonanceScore += resonance;
        System.out.println("[Scan] Weapon " + weapon.getName()
                + " hums with battle runes. Resonance +" + resonance + ".");
    }

    @Override
    public void visit(Potion potion) {
        int resonance = Math.max(1, potion.getHealing() / 5);
        resonanceScore += resonance;
        System.out.println("[Scan] Potion " + potion.getName()
                + " bubbles with restorative alchemy. Resonance +" + resonance + ".");
    }

    @Override
    public void visit(Scroll scroll) {
        int resonance = scroll.getSpellName().length();
        resonanceScore += resonance;
        System.out.println("[Scan] Scroll " + scroll.getName()
                + " contains the spell signature '" + scroll.getSpellName()
                + "'. Resonance +" + resonance + ".");
    }

    @Override
    public void visit(Ring ring) {
        int resonance = ring.getMagicBonus() * 3;
        resonanceScore += resonance;
        System.out.println("[Scan] Ring " + ring.getName()
                + " circles with focused mana. Resonance +" + resonance + ".");
    }

    @Override
    public void visit(Armor armor) {
        int resonance = armor.getDefenseBonus() * 2;
        resonanceScore += resonance;
        System.out.println("[Scan] Armor " + armor.getName()
                + " projects layered warding sigils. Resonance +" + resonance + ".");
    }
}
